package com.example.p2_daniellcabrera_2096072;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.LinkedList;

import javax.net.ssl.HttpsURLConnection;

public class cadastro_veiculo extends AppCompatActivity {
    Gson gson;
    ArrayAdapter<Veiculo> adapter;
    LinkedList<Veiculo> veiculos;
    ListView lista;
    final static int TELA_MODELO = 120;
    int posicaoEdicao = -1;
    EditText edPlaca, edCor, edAno;
    TextView edModelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Método semelhante o passado em aula
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_veiculo);
        edPlaca = (EditText) findViewById(R.id.ed_placa);
        edCor = (EditText) findViewById(R.id.ed_cor);
        edAno = (EditText) findViewById(R.id.ed_ano);
        edModelo = (TextView) findViewById(R.id.ed_modelo);
        gson = new Gson();
        veiculos = new LinkedList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, veiculos);
        lista = (ListView) findViewById(R.id.lista_veiculos);
        lista.setAdapter(adapter);
        new Thread(){
            public void run() {
                buscarVeiculos();
            }
        }.start();
        GsonBuilder bld = new GsonBuilder();
        gson = bld.create();
        lista.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) { //preenche os dados do objeto da lista
                posicaoEdicao = position;
                Veiculo v = veiculos.get(position);
                edPlaca.setText(v.getPlaca());
                edAno.setText(v.getAno());
                edCor.setText(v.getCor());
                return true;
            }
        });
    }

    public void buscarVeiculos(){ //Metodo semelhante o passado em aula
        try {
            URL url = new URL("http://argo.td.utfpr.edu.br/locadora-war/ws/veiculo");
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
                Veiculo[]  veic = gson.fromJson(resp, Veiculo[].class);
                if (veic != null) {
                    veiculos.clear();
                    for (Veiculo v : veic)
                        veiculos.add(v);
                }
                lista.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            } else if (con.getResponseCode() == 304) {

            }
        } catch (Throwable t) {t.printStackTrace();}
    }

    public void selecionaModelo(View v){ //Vai para a Activity para seleção do modelo com base na marca, mas não consegui fazer o retorno do modelo para o TextView
        Intent it = new Intent(this, activity_modelo.class);
        startActivityForResult(it, TELA_MODELO);
    }

    public void confirmar(View v) { //Semelhante o passado em aula
        String placa = ((EditText) findViewById(R.id.ed_placa)).getText().toString();
        String cor = ((EditText) findViewById(R.id.ed_cor)).getText().toString();
        String ano = ((EditText) findViewById(R.id.ed_ano)).getText().toString();
        //String idModelo = ((TextView) findViewById(R.id.ed_modelo)).getText().toString();
        Veiculo veic = new Veiculo();
        veic.setCor(cor);
        veic.setPlaca(placa);
        veic.setAno(Integer.parseInt(ano));
        //veic.setIdModelo(0);
        new Thread() {
           public void run() {
               cadastrarVeiculo(veic);
           }
        }.start();
        ((TextView) findViewById(R.id.ed_modelo)).setText("");
        ((EditText) findViewById(R.id.ed_ano)).setText("");
        ((EditText) findViewById(R.id.ed_cor)).setText("");
        ((EditText) findViewById(R.id.ed_placa)).setText("");
    }

    public void cadastrarVeiculo(Veiculo veic){ ;//semelhante o passado em aula
        String json = gson.toJson(veic);
        try {
            URL url = new URL("http://argo.td.utfpr.edu.br/locadora-war/ws/veiculo/");
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("content-type","application/json");
            PrintWriter pw = new PrintWriter( con.getOutputStream() );
            pw.println(json);
            pw.flush();
            con.connect();
            if (con.getResponseCode() == 201) {
                buscarVeiculos();
            }
        } catch (Throwable t){t.printStackTrace();}
    }

    public void atualizarVeiculo(Veiculo veiculo) { //Tentativa de criar o método PUT
        String json = gson.toJson(veiculo);
        try {
            URL url = new URL("http://argo.td.utfpr.edu.br/locadora-war/ws/veiculo/" + veiculo.getPlaca());
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("content-type", "application/json");
            PrintWriter pw = new PrintWriter(con.getOutputStream());
            pw.println(json);
            pw.flush();
            con.connect();
            if (con.getResponseCode() == 200) {
                buscarVeiculos();
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void excluirVeiculo(String placa) { //Deve excluir o veiculo com base na placa do veiculo
        try {
            URL url = new URL("http://argo.td.utfpr.edu.br/locadora-war/ws/veiculo/" + placa);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            con.connect();
            if (con.getResponseCode() == 200) {
                buscarVeiculos();
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int codigo, int resultado, Intent dados) { //Deveria ser o retorno da activity de seleção do modelo, para preencher o TextView
        super.onActivityResult(codigo, resultado, dados);
        if(codigo == TELA_MODELO && resultado == RESULT_OK) {
            //Veiculo v = new Veiculo;
            //v.setId(resultado);
            //edModelo.setText(v.getModelo().getDescricao());
        }
    }
}