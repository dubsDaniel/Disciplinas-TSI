package com.example.p3_daniellcabrera_2096072;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.p3_daniellcabrera_2096072.bd.Banco;
import com.example.p3_daniellcabrera_2096072.bd.ProdutoDAO;
import com.example.p3_daniellcabrera_2096072.bd.SetorDAO;
import com.example.p3_daniellcabrera_2096072.modelo.Produto;
import com.example.p3_daniellcabrera_2096072.modelo.Setor;
import com.example.p3_daniellcabrera_2096072.modelo.SetorComProdutos;

import java.util.LinkedList;
import java.util.List;

public class comprasActivity extends AppCompatActivity {
    //Deveria ser parecido com a produtoActivity juntando com a relação do Item...
    //Estive focado em na correção de outros erros, não consegui dedicar tempo suficiente para ela

    Banco bd;
    SetorDAO setorDAO;
    ProdutoDAO produtoDAO;
    LinkedList<Setor> listaSetor;
    List<Produto> listaProdutos;
    ArrayAdapter<SetorComProdutos> setorAdapter;
    ArrayAdapter<Produto> produtoAdapter;
    ListView listViewProdutos;
    Spinner spinnerSetor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);
        spinnerSetor = (Spinner) findViewById(R.id.spSetores);
        spinnerSetor.setSelection(0, false);

        listaSetor = new LinkedList<Setor>();
        setorAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaSetor);
        spinnerSetor.setAdapter(setorAdapter);
        spinnerSetor.setSelection(0, false);

        setorDAO.listar().observe(this, new Observer<List<Setor>>() {
            @Override
            public void onChanged(List<Setor> setores) {
                listaSetor.clear();
                if(setores != null) {
                    listaSetor.addAll(setores);
                }
                setorAdapter.notifyDataSetChanged();
            }
        });

        spinnerSetor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
               Setor s = listaSetor.get(i);
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    public void comprarItem(View v) {
        //pegar a posiçao do produto selecionado e adicionar um item, para adicionar que foi comprado
    }

    public void cancelarItem(View v) {
        //pegar a posiçao do produto selecionado e deletar da lista de compra
    }

}