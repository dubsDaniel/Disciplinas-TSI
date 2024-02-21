package com.example.p3_daniellcabrera_2096072;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.p3_daniellcabrera_2096072.bd.Banco;
import com.example.p3_daniellcabrera_2096072.bd.ListComprasDAO;
import com.example.p3_daniellcabrera_2096072.bd.SetorDAO;
import com.example.p3_daniellcabrera_2096072.modelo.ListaCompras;
import com.example.p3_daniellcabrera_2096072.modelo.Setor;
import com.example.p3_daniellcabrera_2096072.modelo.SetorComProdutos;

import java.util.LinkedList;
import java.util.List;

public class listaCompra extends AppCompatActivity {
    Banco bd;
    ListComprasDAO lcDAO;
    SetorDAO setorDAO;
    List<ListaCompras> listaCompras;
    ArrayAdapter<ListaCompras> lcAdapter;
    ListView listViewListaCompras;
    ListaCompras lcEdit = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compra);

        bd = Room.databaseBuilder(getApplicationContext(), Banco.class, "BancoSetorProdutos").
                fallbackToDestructiveMigration().build();
        lcDAO = bd.getListaCompraDAO();
        setorDAO = bd.getSetorDAO();
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
        listViewListaCompras= findViewById(R.id.listas);
        listaCompras = new LinkedList<>();
        lcAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, listaCompras);
        listViewListaCompras.setAdapter(lcAdapter);
        listViewListaCompras.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listViewListaCompras.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                lcEdit = listaCompras.get(i);
                AlertDialog.Builder bld = new AlertDialog.Builder(listaCompra.this);
                bld.setTitle("Adicionar produtos à lista");
                View tela = getLayoutInflater().inflate(R.layout.dialogo_adicionar_itens, null);
                bld.setView(tela);
                bld.setPositiveButton("◀ | ✓", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Deveria receber a tela de dialogo_adicionar_itens
                        //mostrar todos os produtos, para que o usuário clique em um, informe a qt a comprar
                        //ao clicar para adicionar, deveria incluir o item e sua quantidade na lista de compras
                    }
                });
                bld.show();
                return true;
            }
        });
        listViewListaCompras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                lcEdit = listaCompras.get(i);
            }
        });
    }

    public void confirmar(View v) {
        EditText ed = (EditText) findViewById(R.id.ed_nomeLista);
        EditText ed2 = (EditText) findViewById(R.id.ed_prioridade);
        String nome = ed.getText().toString();
        int prio = 0;
        try {
            prio = Integer.parseInt(ed2.getText().toString().trim());
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
                    } else {
                        ListaCompras lc = new ListaCompras();
                        lc.setNome(nome);
                        lc.setPrioridade(finalPrio);
                        lcDAO.gravar(lc);
                        listaCompras.add(lc);
                        runOnUiThread(new Thread() {
                            public void run() {
                                lcAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                    ed.setText("");
                    ed2.setText("");
                    Looper.loop();
                }
            }.start();
        } else {
            Toast.makeText(this, "Escolha um nome e o nível de prioridade da lista", Toast.LENGTH_SHORT).show();
        }
    }

    public void excluir(View v) {
        int pos = listViewListaCompras.getCheckedItemPosition();
        EditText ed = (EditText) findViewById(R.id.ed_nomeLista);
        ed.setText("");
        if (pos < 0) {

        }
        ListaCompras lc = listaCompras.get(pos);
        new Thread() {
            public void run() {
                Looper.prepare();
                lcDAO.apagar(lc);
                listaCompras.remove(lc);
                listViewListaCompras.clearChoices();
                runOnUiThread(new Thread() {
                    public void run() {
                        lcAdapter.notifyDataSetChanged();
                    }
                });
            }
        }.start();
    }

    public void editar(View v) {
        ((EditText) findViewById(R.id.ed_nomeLista)).setText(lcEdit.getNome());
        ((EditText) findViewById(R.id.ed_prioridade)).setText(String.valueOf(lcEdit.getPrioridade()));
    }


    @Override
    public void onStop() {
        bd.close();
        super.onStop();
    }
}