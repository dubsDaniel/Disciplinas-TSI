package com.example.p3_daniellcabrera_2096072;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.room.Room;

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
import com.example.p3_daniellcabrera_2096072.bd.ProdutoDAO;
import com.example.p3_daniellcabrera_2096072.bd.SetorDAO;
import com.example.p3_daniellcabrera_2096072.modelo.Setor;

import java.util.LinkedList;
import java.util.List;

public class setoresActivity extends AppCompatActivity {

    Banco bd;
    SetorDAO setorDAO;
    List<Setor> listaSetores;
    ArrayAdapter<Setor> setorAdapter;
    ListView listViewSetores;

    Setor setorEdit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setores);

        bd = Room.databaseBuilder(getApplicationContext(),Banco.class, "BancoSetorProdutos").
                fallbackToDestructiveMigration().build();
        setorDAO = bd.getSetorDAO();

        setorDAO.listar().observe(this, new Observer<List<Setor>>() {
            @Override
            public void onChanged(List<Setor> setores) {
                // Atualize a lista e notifique o adapter
                listaSetores.clear();
                listaSetores.addAll(setores);
                setorAdapter.notifyDataSetChanged();
            }
        });

        listViewSetores = findViewById(R.id.lista_setores);
        listaSetores = new LinkedList<>();
        setorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, listaSetores);
        listViewSetores.setAdapter(setorAdapter);
        listViewSetores.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        listViewSetores.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                setorEdit = listaSetores.get(i);
                ((EditText) findViewById(R.id.ed_nome)).setText(setorEdit.getNome());
                return true;
            }
        });
    }

    public void confirmar(View v) {
        EditText ed = (EditText) findViewById(R.id.ed_nome);
        String nome = ed.getText().toString();

        if (nome != null && !nome.isEmpty()) {
            new Thread() {
                @Override
                public void run() {
                    Looper.prepare();
                    if (setorEdit != null) {
                        setorEdit.setNome(nome);
                        setorDAO.alterar(setorEdit);
                        setorEdit = null;
                        runOnUiThread(new Thread() {
                            public void run() {
                            setorAdapter.notifyDataSetChanged();
                            }
                        });
                    } else {
                        Setor set = new Setor();
                        set.setNome(nome);
                        setorDAO.gravar(set);
                        listaSetores.add(set);
                        runOnUiThread(new Thread() {
                            public void run() {
                                setorAdapter.clear();
                                setorAdapter.addAll(listaSetores);
                                setorAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                    ed.setText("");
                    Looper.loop();
                }
            }.start();
        }
    }

    public void excluir(View v) {
        int pos = listViewSetores.getCheckedItemPosition();
        if (pos < 0) {
            Toast.makeText(this, "Selecione o setor!", Toast.LENGTH_SHORT).show();
        }
        Setor s = listaSetores.get(pos);
        new Thread() {
            public void run() {
                Looper.prepare();
                setorDAO.apagar(s);
                listaSetores.remove(s);
                listViewSetores.clearChoices();
                runOnUiThread(new Thread() {
                    public void run() {
                        setorAdapter.notifyDataSetChanged();
                    }
                });
            }
        }.start();
    }

    @Override
    public void onStop() {
        bd.close();
        super.onStop();
    }


}