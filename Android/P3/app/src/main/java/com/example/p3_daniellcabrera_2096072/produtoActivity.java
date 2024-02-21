package com.example.p3_daniellcabrera_2096072;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.p3_daniellcabrera_2096072.bd.Banco;
import com.example.p3_daniellcabrera_2096072.bd.ProdutoDAO;
import com.example.p3_daniellcabrera_2096072.bd.SetorDAO;
import com.example.p3_daniellcabrera_2096072.modelo.Produto;
import com.example.p3_daniellcabrera_2096072.modelo.Setor;
import com.example.p3_daniellcabrera_2096072.modelo.SetorComProdutos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class produtoActivity extends AppCompatActivity {

    class ObservadorSetor implements Observer<List<SetorComProdutos>> {
        @Override
        public void onChanged(List<SetorComProdutos> sts) {
            listaSetor.clear();
            if (sts != null) {
                listaSetor.addAll(sts);

                listaTodosProdutos.clear();
                for (SetorComProdutos setor : sts) {
                    listaTodosProdutos.addAll(setor.getProduto());
                }
                setorAdapter.notifyDataSetChanged();
            }
        }
    }

    Banco bd;
    SetorDAO setorDAO;
    ProdutoDAO produtoDAO;

    LinkedList<SetorComProdutos> listaSetor;
    List<Produto> listaProdutos;
    ArrayAdapter<Setor> setorAdapter;
    ArrayAdapter<Produto> produtoAdapter;
    Spinner spinnerSetor;
    ListView listViewProdutos;
    ObservadorSetor setorObs;
    List<Produto> listaTodosProdutos;
    Produto produtoEditando = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        bd = Room.databaseBuilder(getApplicationContext(), Banco.class, "BancoSetorProdutos").
                fallbackToDestructiveMigration().build();
        setorDAO = bd.getSetorDAO();
        produtoDAO = bd.getProdutoDAO();
        listViewProdutos = (ListView) findViewById(R.id.lista_produtos);
        spinnerSetor = (Spinner) findViewById(R.id.spSetores);
        spinnerSetor.setSelection(0, false);
        listaSetor = new LinkedList<>();
        listaProdutos = new LinkedList<>();
        setorAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaSetor);
        produtoAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, listaProdutos);
        spinnerSetor.setAdapter(setorAdapter);
        listViewProdutos.setAdapter(produtoAdapter);
        listViewProdutos.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        setorObs = new ObservadorSetor();

        listaTodosProdutos = new LinkedList<>();

        setorDAO.listarSetorComProdutos().observe(this, setorObs);

        List<SetorComProdutos> setores = setorDAO.listarSetorComProdutos().getValue();
        setorObs.onChanged(setores);

        if (setores != null) {
            for (SetorComProdutos setor : setores) {
                listaTodosProdutos.addAll(setor.getProduto());
            }
        }
        atualizaLista();
        spinnerSetor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                atualizaLista();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                listaProdutos.clear();
                listaProdutos.addAll(listaTodosProdutos);
                produtoAdapter.notifyDataSetChanged();
            }
        });
        listViewProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                produtoEditando = listaProdutos.get(i);
                ((EditText) findViewById(R.id.ed_descr)).setText(produtoEditando.getDescricao());
                ((EditText) findViewById(R.id.ed_valor)).setText(String.valueOf(produtoEditando.getPreco()));
                return true;
            }
        });
    }

    public void inserirProduto(View v) {
        EditText edDesc = (EditText) findViewById(R.id.ed_descr);
        EditText edValor = (EditText) findViewById(R.id.ed_valor);
        String desc = edDesc.getText().toString();
        double vlr = 0;
        try {
            vlr = Double.parseDouble(edValor.getText().toString());
        } catch (Exception e) {}
        final Setor setor = ((SetorComProdutos) spinnerSetor.getSelectedItem()).getSetores();
        if (setor == null) {
            Toast.makeText(this, "Selecione o setor!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (desc != null && !desc.isEmpty()) {
            double finalVlr = vlr;
            new Thread() {
                public void run() {
                    Looper.prepare();
                    if (produtoEditando != null) {
                        produtoEditando.setDescricao(desc);
                        produtoEditando.setPreco(finalVlr);
                        produtoDAO.alterar(produtoEditando);
                        produtoEditando = null;
                    } else {
                        Produto prod = new Produto();
                        prod.setDescricao(desc);
                        prod.setPreco(finalVlr);
                        prod.setIdSetor(setor.getId());
                        produtoDAO.gravar(prod);
                        listaProdutos.add(prod);
                        runOnUiThread(new Thread() {
                            public void run() {
                                produtoAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                    edDesc.setText("");
                    edValor.setText("");
                    Looper.loop();
                }
            }.start();
        }
    }

    public void excluirProduto(View v) {
        int pos = listViewProdutos.getCheckedItemPosition();
        if (pos < 0) {
            Toast.makeText(this, "Selecione o produto a excluir!", Toast.LENGTH_SHORT).show();
            return;
        }
        Produto p = listaProdutos.get(pos);
        new Thread() {
            public void run() {
                Looper.prepare();
                produtoDAO.apagar(p);
                listaProdutos.remove(p);
                listViewProdutos.clearChoices();
                runOnUiThread(new Thread() {
                    public void run() {
                        produtoAdapter.notifyDataSetChanged();
                    }
                });
                Looper.loop();
            }
        }.start();
    }

    public void atualizaLista() {
        SetorComProdutos setorSelecionado = (SetorComProdutos) spinnerSetor.getSelectedItem();
        listaProdutos.clear();
        if (setorSelecionado != null) {
            listaProdutos.addAll(setorSelecionado.getProduto());
        } else {
            listaProdutos.addAll(listaTodosProdutos);
        }
        produtoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        bd.close();
        super.onStop();
    }
}