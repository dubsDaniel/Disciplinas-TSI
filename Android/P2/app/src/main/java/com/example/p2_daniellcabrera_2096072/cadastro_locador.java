package com.example.p2_daniellcabrera_2096072;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.LinkedList;
import javax.net.ssl.HttpsURLConnection;

public class cadastro_locador extends AppCompatActivity {
    Gson gson;
    ArrayAdapter<Locador> adapter;
    LinkedList<Locador> locadores;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_locador);
        gson = new Gson();
        locadores = new LinkedList<>();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_single_choice,
                locadores);
        lista = (ListView) findViewById(R.id.lista_locadores);
        lista.setAdapter(adapter);
        new Thread() {
            public void run() {
                buscarLocadores();
            }
        }.start(); //Método utilizado em aula para preencher a lista
        lista.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { //deve preencher os campos ao pressionar e segurar um dos itens da lista
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                EditText edNome, edCPF, edCEP;
                edNome = (EditText) findViewById(R.id.ed_nome);
                edCPF = (EditText) findViewById(R.id.ed_cpf);
                edCEP = (EditText) findViewById(R.id.ed_cep);
                Locador loc = locadores.get(position);
                edNome.setText(loc.getNome());
                edCPF.setText(loc.getCpf());
                edCEP.setText(loc.getCep());
                return true;
            }
        });
    }

    public void buscarLocadores() { //Semelhante ao utilizado em aula para coleta dos itens da lista
        try {
            URL url = new URL("http://argo.td.utfpr.edu.br/locadora-war/ws/locador");
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            if (con.getResponseCode() == 200) {
                String resp = "";
                String linha;
                BufferedReader buf = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                do {
                    linha = buf.readLine();
                    if (linha != null) {
                        resp += linha;
                    }
                } while (linha != null);
                Locador[] locad = gson.fromJson(resp, Locador[].class);
                if (locad != null) {
                    locadores.clear();
                    for (Locador l : locad)
                        locadores.add(l);
                }
                lista.post(new Runnable() {
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void confirmar(View v) { //Semelhante o que foi utilizado em aula para começar o processo de salvar o objeto
        String nome = ((EditText) findViewById(R.id.ed_nome)).getText().toString();
        String cpf = ((EditText) findViewById(R.id.ed_cpf)).getText().toString();
        String cep = ((EditText) findViewById(R.id.ed_cep)).getText().toString();
        Locador lo = new Locador();
        try {
            lo.setNome(nome);
            lo.setCep(Integer.parseInt(cpf));
            lo.setCep(Integer.parseInt(cep));
        } catch (Exception e) {
        }
        boolean cpfExiste = false;
        for (Locador l : locadores) { //checa se já existe algum CPF igual cadastrado
            if (l.getCpf() == Integer.parseInt(cpf)) {
                cpfExiste = true;
                break;
            }
        }
        if (!cpfExiste) {
            new Thread() {
                public void run() {
                    cadastrarLocador(lo);
                }
            }.start();
        } else {
            Toast.makeText(this, "Por favor, insira um CPF", Toast.LENGTH_SHORT).show();
        }
        ((EditText) findViewById(R.id.ed_nome)).setText("");
        ((EditText) findViewById(R.id.ed_cpf)).setText("");
        ((EditText) findViewById(R.id.ed_cep)).setText("");
    }

    public void cadastrarLocador(Locador loc) { //Semelhante o que foi mostrado em aula
        String json = gson.toJson(loc);
        try {
            URL url = new URL("http://argo.td.utfpr.edu.br/locadora-war/ws/locador/");
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("content-type", "application/json");
            PrintWriter pw = new PrintWriter(con.getOutputStream());
            pw.println(json);
            pw.flush();
            con.connect();
            if (con.getResponseCode() == 201) {
                buscarLocadores();
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void atualizarLocador(Locador locador) { //Tentativa para atualizar o objeto no banco
        String json = gson.toJson(locador);
        try {
            URL url = new URL("http://argo.td.utfpr.edu.br/locadora-war/ws/locador/" + locador.getCpf());
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("content-type", "application/json");
            PrintWriter pw = new PrintWriter(con.getOutputStream());
            pw.println(json);
            pw.flush();
            con.connect();
            if (con.getResponseCode() == 200) {
                buscarLocadores();
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void excluirLocador(View v) { //Método delete utilizando o cpf como identificador para ser deletado
        String cpf = ((EditText) findViewById(R.id.ed_cpf)).getText().toString();
        if (!cpf.isEmpty()) {
            try {
                URL url = new URL("http://argo.td.utfpr.edu.br/locadora-war/ws/locador/" + cpf);
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                con.setRequestMethod("DELETE");
                con.connect();
                if (con.getResponseCode() == 200) {
                    buscarLocadores();
                }
                ((EditText) findViewById(R.id.ed_nome)).setText("");
                ((EditText) findViewById(R.id.ed_cpf)).setText("");
                ((EditText) findViewById(R.id.ed_cep)).setText("");
            } catch (Throwable t) {
                t.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Por favor, insira um CPF", Toast.LENGTH_SHORT).show();
        }
    }
}