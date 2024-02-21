package com.example.p3_daniellcabrera_2096072;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.p3_daniellcabrera_2096072.bd.Banco;
import com.example.p3_daniellcabrera_2096072.bd.ListComprasDAO;
import com.example.p3_daniellcabrera_2096072.modelo.ListaCompras;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Banco bd;
    ListComprasDAO lcDAO;
    ArrayAdapter<ListaCompras> lcAdapter;
    List<ListaCompras> listaCompras;
    ListView listasDeCompras;
    ListaCompras lcEdit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bd = Room.databaseBuilder(getApplicationContext(), Banco.class, "BancoSetorProdutos").
                fallbackToDestructiveMigration().
                build();
        lcDAO = bd.getListaCompraDAO();
        lcDAO.listar().observe(this, new Observer<List<ListaCompras>>() {
            @Override
            public void onChanged(List<ListaCompras> lc) {
                listaCompras.clear();
                if (lc != null) {
                    listaCompras.addAll(lc);
                }
                lcAdapter.notifyDataSetChanged();
            }
        });
        listasDeCompras = (ListView) findViewById(R.id.listas);
        listaCompras = new LinkedList<>();
        lcAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, listaCompras);
        listasDeCompras.setAdapter(lcAdapter);
        listasDeCompras.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listasDeCompras.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            //O Clique longo em uma lista, deveria levar o usuário para a activity de compras...
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(MainActivity.this, comprasActivity.class);
                startActivity(it);
                return true;
            }
        });
        listasDeCompras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //O clique normal, seleciona uma lista para editar/cancelar
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                lcEdit = listaCompras.get(i);
            }
        });
    }

    public void cadastrarSetor(View v) {
        Intent it = new Intent(this, setoresActivity.class);
        startActivity(it);
    }

    public void cadastrarProduto(View v) {
        Intent it = new Intent(this, produtoActivity.class);
        startActivity(it);
    }

    public void listaCompra(View v) {
        Intent it = new Intent(this, listaCompra.class);
        startActivity(it);
    }

    public void excluirLista(View v) { //método para excluir uma lista (cancelar)
        int pos = listasDeCompras.getCheckedItemPosition();
        if (pos < 0) {
            Toast.makeText(this, "Selecione a lista que cancelará!", Toast.LENGTH_SHORT).show();
            return;
        }
        ListaCompras lc = listaCompras.get(pos);
        new Thread() {
            public void run(){
                Looper.prepare();
                lcDAO.apagar(lc);
                listaCompras.remove(lc);
                listasDeCompras.clearChoices();
                runOnUiThread(new Thread(){
                    public void run() {
                        lcAdapter.notifyDataSetChanged();
                    }
                });
                Looper.loop();
            }
        }.start();
    }

    public void editarLista(View v) {//Método da tentativa de editar o nome/prioridade da lista, conforme solicitado
        int pos = listasDeCompras.getCheckedItemPosition();
        if (pos < 0) {
            Toast.makeText(this, "Selecione a lista que deseja editar!", Toast.LENGTH_SHORT).show();
            return;
        }
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        bld.setTitle("Editar Lista");
        View tela = getLayoutInflater().inflate(R.layout.dialogo_edicao_lista, null);
        bld.setView(tela);
        bld.setPositiveButton("✓", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                EditText edNome = tela.findViewById(R.id.ed_nomeLista);
                EditText edPrior = tela.findViewById(R.id.ed_prioridade);
                edNome.setText(lcEdit.getNome());
                edPrior.setText(String.valueOf(lcEdit.getPrioridade()));
                String nome = edNome.getText().toString();
                int prio = 0;
                try {
                    prio = Integer.parseInt(edPrior.getText().toString().trim());
                } catch(Exception e) {}

                if (nome != null && !nome.isEmpty() && prio != 0) {
                    int finalPrio = prio;
                    new Thread() {
                        public void run() {
                            Looper.prepare();
                            if(lcEdit != null) {
                                lcEdit.setNome(nome);
                                lcEdit.setPrioridade(finalPrio);
                                lcDAO.alterar(lcEdit);
                                lcEdit = null;
                                runOnUiThread(new Thread() {
                                    public void run() {
                                        lcAdapter.notifyDataSetChanged();
                                    }
                                });
                            }
                            edNome.setText("");
                            edPrior.setText("");
                            Looper.loop();
                        }
                    }.start();
                } else {
                    Toast.makeText(MainActivity.this, "Escolha um nome e o nível de prioridade da lista", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bld.setNegativeButton("X", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                      return;
            }
        });
        bld.show();
        //Inflate diagolo_edicao_lista.xml
    }
}