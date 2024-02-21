package com.example.p2_daniellcabrera_2096072;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;

import javax.net.ssl.HttpsURLConnection;

public class activity_modelo extends AppCompatActivity {

    Gson gsonMarca;
    Gson gsonModelo;
    ArrayAdapter<Marca> marcaAdapter;
    ArrayAdapter<Modelo> modeloAdapter;
    LinkedList<Marca> marcas;
    LinkedList<Modelo> modelos;
    ListView listaMarcas;
    ListView listaModelos;

    Modelo modelo;

    int posicaoSelecionada = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Semelhante o metodo utilizado em aula, solicitando o preenchimento da lista com as informações do objeto
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modelo);
        gsonMarca = new Gson();
        marcas = new LinkedList<>();
        marcaAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_single_choice,
                marcas);
        modelos = new LinkedList<>();
        modeloAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_single_choice,
                modelos);
        listaMarcas = (ListView) findViewById(R.id.lista_marcas);
        listaMarcas.setAdapter(marcaAdapter);
        new Thread() {
            public void run() {
                buscarMarcas();
            }
        }.start();
        GsonBuilder bld = new GsonBuilder();
        gsonMarca = bld.create();
        listaMarcas.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listaMarcas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) { //salvar a posição para utilizar depois
                posicaoSelecionada = position;
            }
        });
    }

    public void buscarMarcas() { //Busca os itens, semelhante ao da aula
        try {
            URL url = new URL("https://argo.td.utfpr.edu.br/clients/ws/marca");
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            if (con.getResponseCode() == 200) {
                String resp = "";
                String linha = "";
                BufferedReader buf = new BufferedReader(new InputStreamReader(con.getInputStream()));
                do {
                    linha = buf.readLine();
                    if (linha != null) {
                        resp += linha;
                    }
                } while (linha != null);
                Marca[] marc = gsonMarca.fromJson(resp, Marca[].class);
                if (marc != null) {
                    marcas.clear();
                    for (Marca m : marc)
                        marcas.add(m);
                }
                listaMarcas.post(new Runnable() {
                    @Override
                    public void run() {
                        marcaAdapter.notifyDataSetChanged();
                    }
                });
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void selecionaMarca(View v) { //Ao selecionar uma marca, um AlertDialog com uma lista dos modelos para essa marca deveria aparecer para seleção do modelo
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        bld.setTitle("Escolha um modelo");
        View tela = getLayoutInflater().inflate(R.layout.dialogo_modelo, null);
        bld.setView(tela);
        gsonModelo = new Gson();
        int idMarcaSelecionada = obterIdPosicaoSelecionada(posicaoSelecionada);
        listaModelos = (ListView) findViewById(R.id.lista_modelo);
        listaModelos.setAdapter(modeloAdapter);
        new Thread() {
            public void run() {
                buscarModelos(idMarcaSelecionada);
            }
        }.start();
        GsonBuilder bldm = new GsonBuilder();
        gsonModelo = bldm.create();
        listaModelos.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        bld.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Não consegui encontrar uma maneira de preencher o TextView com o nome do modelo
            }
        });
        bld.setNegativeButton("X", null);
        bld.show();
    }


    public void buscarModelos(int id_marca) { //Semelhante o utilizado em aula, para preencher a lista do AlertDialog com base no id da marca selecionada
        try {
            URL url = new URL("https://argo.td.utfpr.edu.br/clients/ws/modelos/");
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            if (con.getResponseCode() == 200) {
                String resp = "";
                String linha = "";
                BufferedReader buf = new BufferedReader(new InputStreamReader(con.getInputStream()));
                do {
                    linha = buf.readLine();
                    if (linha != null) {
                        resp += linha;
                    }
                } while (linha != null);
                Modelo[] model = gsonModelo.fromJson(resp, Modelo[].class);
                if (model != null) {
                    modelos.clear();
                    for (Modelo m : model) {
                        if (m.getMarca().getId() == id_marca) {
                            modelos.add(m);
                        }
                    }
                }
                listaMarcas.post(new Runnable() {
                    @Override
                    public void run() {
                        modeloAdapter.notifyDataSetChanged();
                    }
                });
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public int obterIdPosicaoSelecionada(int posicaoSelecionada) { //método para coletar o id da marca selecionada na lista
        if (posicaoSelecionada >= 0 && posicaoSelecionada < marcas.size()) {
            return marcas.get(posicaoSelecionada).getId();
        } else return -1;
    }
}