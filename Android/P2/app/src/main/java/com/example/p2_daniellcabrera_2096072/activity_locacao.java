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
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Date;
import java.util.LinkedList;

import javax.net.ssl.HttpsURLConnection;

public class activity_locacao extends AppCompatActivity {

    Gson gson;
    ArrayAdapter<Locacao> adapter;
    LinkedList<Locacao> locacoes;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Semelhante o código utilizado em aula, para que inicie a activity e solicite o preenchimento da lista
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locacao);
        gson = new Gson();
        locacoes = new LinkedList<>();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_single_choice,
                locacoes);
        lista = (ListView) findViewById(R.id.lista_locacao);
        lista.setAdapter( adapter );
        new Thread() {
            public void run() {
                buscarLocacoes();
            }
        }.start();
        GsonBuilder bld = new GsonBuilder();
        gson = bld.create();
        lista.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) { //Deve preencher os campos com as informações do item pressionado
                EditText edPlaca, edCPF, edDataInicio, edDataFim;
                edPlaca = (EditText) findViewById(R.id.ed_placa);
                edCPF = (EditText) findViewById(R.id.ed_cpf);
                edDataInicio = (EditText) findViewById(R.id.data_inicio);
                edDataFim = (EditText) findViewById(R.id.data_fim);
                Locacao loc = locacoes.get(position);
                edPlaca.setText(loc.getPlaca());
                edCPF.setText(loc.getCpf());
                edDataInicio.setText(loc.getDt_inicio().toString());
                edDataFim.setText(loc.getDt_fim().toString());
                return true;
            }
        });
    }

    public void buscarLocacoes() { //Semelhante o utilizado em aula
        try {
            URL url = new URL("http://argo.td.utfpr.edu.br/locadora-war/ws/locacao");
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
                Locacao[] locac = gson.fromJson(resp, Locacao[].class);
                if (locac != null) {
                    locacoes.clear();
                    for (Locacao l : locac)
                        locacoes.add(l);
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

    public void confirmar(View v) { //Semelhante o utilizado em aula
        String placa = ((EditText) findViewById(R.id.ed_placa)).getText().toString();
        String cpf = ((EditText) findViewById(R.id.ed_cpf)).getText().toString();
        String dataInicio = ((EditText) findViewById(R.id.data_inicio)).getText().toString();
        String dataFim = ((EditText) findViewById(R.id.data_fim)).getText().toString();
        Locacao lo = new Locacao();
        try {
            lo.setPlaca(placa);
            lo.setCpf(Integer.parseInt(cpf));
            lo.setDt_inicio(Date.valueOf(dataInicio));
            lo.setDt_fim(Date.valueOf(dataFim));
        } catch (Exception e) {
        }
        new Thread() {
            public void run() {
                cadastrarLocacao(lo);
            }
        }.start();
        ((EditText) findViewById(R.id.ed_placa)).setText("");
        ((EditText) findViewById(R.id.ed_cpf)).setText("");
        ((EditText) findViewById(R.id.data_inicio)).setText("");
        ((EditText) findViewById(R.id.data_fim)).setText("");
    }

    public void cadastrarLocacao(Locacao loc) { //Semelhante o utilizado em aula
        String json = gson.toJson(loc);
        try {
            URL url = new URL("http://argo.td.utfpr.edu.br/locadora-war/ws/locacao/");
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("content-type", "application/json");
            PrintWriter pw = new PrintWriter(con.getOutputStream());
            pw.println(json);
            pw.flush();
            con.connect();
            if (con.getResponseCode() == 201) {
                buscarLocacoes();
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void atualizarLocacao(Locacao locacao) { //Tentativa de criação pro metodo PUT
        String json = gson.toJson(locacao);
        try {
            URL url = new URL("http://argo.td.utfpr.edu.br/locadora-war/ws/locador/" + locacao.getId());
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("content-type", "application/json");
            PrintWriter pw = new PrintWriter(con.getOutputStream());
            pw.println(json);
            pw.flush();
            con.connect();
            if (con.getResponseCode() == 200) {
                buscarLocacoes();
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void excluirLocacao(View v) { //Tentativa de deletar o objeto com base no id
        try {
            URL url = new URL("http://argo.td.utfpr.edu.br/locadora-war/ws/locador/+" /*id*/);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            con.connect();
            if (con.getResponseCode() == 200) {
                buscarLocacoes();
            }
            ((EditText) findViewById(R.id.ed_nome)).setText("");
            ((EditText) findViewById(R.id.ed_cpf)).setText("");
            ((EditText) findViewById(R.id.ed_cep)).setText("");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}