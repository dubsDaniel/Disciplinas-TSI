package com.example.p1_daniellcabrera_2096072;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    final static int TELA_CADASTRO = 10; //exemplo usado em aula
    private LinkedList<ContatoVeiculo> veiculos; //Lista de veiculos
    private ContatoVeiculoAdapter adapter; //Para o listview personalizado
    ListView lista; //Lista do xml
    int posicaoEdicao = -1; //sempre iniciar com a posição no -1 (exemplo em aula)

    RadioButton rbTodos, rbCarrosVenda, rbCarrosCompra; //RadioGroup para filtragem rápida da lista

    @Override
    protected void onSaveInstanceState(Bundle outState) { //salvar os dados da lista
        super.onSaveInstanceState(outState);
        outState.putSerializable("veiculos", veiculos);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rbTodos = (RadioButton) findViewById(R.id.rb_todos);
        rbCarrosVenda = (RadioButton) findViewById(R.id.rb_compradores);
        rbCarrosCompra = (RadioButton) findViewById(R.id.rb_vendedores);
        lista = (ListView) findViewById(R.id.lista_carros);
        if (savedInstanceState != null) {
            veiculos = (LinkedList<ContatoVeiculo>) savedInstanceState.getSerializable("veiculos");
            adapter = new ContatoVeiculoAdapter(this, veiculos); // Recria o adapter com a lista restaurada
            lista.setAdapter(adapter);
        } else {
            veiculos = new LinkedList<>(); //nova lista ao iniciar o programa
            adapter = new ContatoVeiculoAdapter(this, veiculos); //novo adapter para receber a listview personalizada
            lista.setAdapter(adapter);
        }
        lista.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { //clique longo para "editar" mas também para visualizar as informações do objeto
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,
                                           int pos, long id) {
                posicaoEdicao = pos; //vai para a tela de "cadastro" com as informações do item selecionado
                Intent it = new Intent(MainActivity.this, Activity_Cadastro.class);
                it.putExtra("veiculoEdicao", veiculos.get(pos));
                startActivityForResult(it, TELA_CADASTRO);
                return true;
            }
        });
    }

    public void adicionar(View v) { //Vai para tela de cadastro
        Intent it = new Intent(this, Activity_Cadastro.class);
        startActivityForResult(it, TELA_CADASTRO);
    }

    public void remover(View v) {
        final int pos = lista.getCheckedItemPosition();
        if (pos == -1) { //checa se algum item foi selecionado
            Toast.makeText(this, "Selecione o produto a remover",
                    Toast.LENGTH_SHORT).show();
        } else {
            AlertDialog.Builder bld = new AlertDialog.Builder(this); //Dialogo para confirmar exclusão do item
            bld.setTitle("Confirmação");
            bld.setMessage("Deseja realmente remover este veículo da lista??");
            bld.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) { //remove e volta para a posição -1 (nada selecionado)
                    veiculos.remove(pos);
                    lista.setItemChecked(-1,true);
                    adapter.notifyDataSetChanged();
                }
            });
            bld.setNegativeButton("Não", null); //não faz nada
            bld.show();
        }
    }

    public void filtrar(View v) {//Metodo do filto
        AlertDialog.Builder bld = new AlertDialog.Builder(this);//Abre um popup para aplicação dos filtros
        bld.setTitle("Aplicar filtro");
        View tela = getLayoutInflater().inflate(
                R.layout.dialogo_filtro, null);
        bld.setView(tela);
        bld.setPositiveButton("Filtrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Coleta as informações colocadas pelo usuário
                EditText edMarca = tela.findViewById(R.id.ed_marca);
                EditText edModelo = tela.findViewById(R.id.ed_modelo);
                EditText edAno = tela.findViewById(R.id.ed_ano);
                EditText edCor = tela.findViewById(R.id.ed_cor);
                EditText edValor = tela.findViewById(R.id.ed_valor);
                //Radiogroup para saber o limite mínimo ou o limite máximo definido pelo usuário
                RadioGroup rgAjustaValor = tela.findViewById(R.id.rb_ajustaValor);
                int selectedAjustaValorId = rgAjustaValor.getCheckedRadioButtonId();
                //Radiogroup para saber se o usuário está buscando um veiculo à venda ou alguem para comprar algum veiculo especifico
                RadioGroup rgInteresse = tela.findViewById(R.id.rb_interesse);
                int rbInteresse = rgInteresse.getCheckedRadioButtonId();
                RadioButton rbTipoInteresse = tela.findViewById(rbInteresse);
                //Recebe as informações
                String marca = edMarca.getText().toString();
                String modelo = edModelo.getText().toString();
                String ano = edAno.getText().toString();
                String cor = edCor.getText().toString();
                String valor = edValor.getText().toString();
                if (!ano.isEmpty() && !valor.isEmpty()) { //checa se os valores em números são realmente números
                    try {
                        int anoi = Integer.parseInt(ano);
                        Double valord = Double.parseDouble(valor);
                    } catch (NumberFormatException e) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                        alert.setTitle("Valor inválido");
                        alert.setMessage("Insira um ano ou um valor válido.");
                        alert.setPositiveButton("OK", null);
                        alert.show();
                        return;
                    }
                }

                boolean atendeFiltro; //Váriavel para ser utilizada como

                int tipoInteresse = 0; //Variavel de integração com o objeto ContatoVeiculo (tipo interesse é int 1 ou 2)

                if (rbTipoInteresse != null) {
                    if (rbTipoInteresse.getId() == R.id.rb_vendedores) {
                        tipoInteresse = 1;
                    } else if (rbTipoInteresse.getId() == R.id.rb_compradores) {
                        tipoInteresse = 2;
                    } else if (tipoInteresse == 0) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this); //é necessário informar o interesse para realizar uma pesquisa
                        alert.setTitle("Selecione um interesse");
                        alert.setMessage("Selecione se deseja comprar ou vender este veiculo");
                        alert.setPositiveButton("OK", null);
                        alert.show();
                        return;
                    }
                }

                LinkedList<ContatoVeiculo> veiculosFiltrados = new LinkedList<>(); //nova lista de veiculos filtrados
                for (ContatoVeiculo veiculo : veiculos) { //para cada item da lista
                    atendeFiltro = true;

                    if (!marca.isEmpty() && !veiculo.getMarca().equals(marca)) { //verifica se a marca está ou não vazia e se existe algum objeto com essa marca
                        atendeFiltro = false;
                    }

                    if (!modelo.isEmpty() && !veiculo.getModelo().equals(modelo)) { //mesma coisa para modelo
                        atendeFiltro = false;
                    }

                    if (!ano.isEmpty() && !(veiculo.getAno() == Integer.parseInt(ano))) { //mesma coisa para ano
                        atendeFiltro = false;
                    }

                    if (!cor.isEmpty() && !veiculo.getCor().equals(cor)) { //mesma coisa para cor
                        atendeFiltro = false;
                    }

                    double valorVeiculo = veiculo.getValor();
                    if (selectedAjustaValorId != -1) { //verifica se o usuário definiu o valor como limite máximo ou mínimo
                        RadioButton rbAjustaValor = tela.findViewById(selectedAjustaValorId);

                        if ((selectedAjustaValorId == R.id.rb_apartir) && valorVeiculo < Double.parseDouble(valor)) { //verifica se há valores acima do valor informado
                            atendeFiltro = false;
                        } else if ((selectedAjustaValorId == R.id.rb_deate) && valorVeiculo > Double.parseDouble(valor)) { //verifica se há valores abaixo do valor informado
                            atendeFiltro = false;
                        }
                    }
                    //Verifica o interesse que o usuário quer filtrar
                    if (tipoInteresse == 1 && veiculo.getInteresse() == 1) {
                        if (!atendeFiltro) {
                            continue;
                        }
                        veiculosFiltrados.add(veiculo);
                    } else if (tipoInteresse == 2 && veiculo.getInteresse() == 2) {
                        if (!atendeFiltro) {
                            continue;
                        }
                        veiculosFiltrados.add(veiculo); //adiciona na lista filtrada se passar em todos as solicitações
                    }
                }
                ContatoVeiculoAdapter adapterFiltrado = new ContatoVeiculoAdapter(MainActivity.this, veiculosFiltrados);
                lista.setAdapter(adapterFiltrado); //traz a lista filtrada
            }
        });
        bld.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() { // cancela o filtro e volta a lista padrão
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                lista.setAdapter(adapter);
            }
        });
        bld.show();
    }

    @Override
    public void onActivityResult(int codigo,
                                 int resultado,
                                 Intent dados) { //recebe o item cadastrado ou editado e atualiza a lista
        super.onActivityResult(codigo, resultado, dados);
        if(codigo == TELA_CADASTRO && resultado == RESULT_OK) {
            ContatoVeiculo veic = (ContatoVeiculo) dados.getSerializableExtra("veiculo");
            if (posicaoEdicao>=0) {
                veiculos.set(posicaoEdicao, veic);
                posicaoEdicao = -1;
            } else {
                veiculos.add(veic);
            }
            adapter.notifyDataSetChanged();
        }
    }
    public void filtroMain(View v) { //filtro rápido no main pelos RadioButtons
        RadioGroup radioGroup = findViewById(R.id.rg_filtrarMain);
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

        if (selectedRadioButtonId == R.id.rb_todos) { //se foi selecionado todos, deve trazer todos os veiculos
            ContatoVeiculoAdapter adapter = new ContatoVeiculoAdapter(this, veiculos);
            lista.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else { //se foi selecionado algum, traga somente os veiculos do interesse informado
            LinkedList<ContatoVeiculo> veiculosFiltrados = new LinkedList<>();

            for (ContatoVeiculo veiculo : veiculos) {
                int interesse = veiculo.getInteresse();
                if ((selectedRadioButtonId == R.id.rb_vendedores && interesse == 1) ||
                        (selectedRadioButtonId == R.id.rb_compradores && interesse == 2)) {
                    veiculosFiltrados.add(veiculo);
                }
            }
            ContatoVeiculoAdapter adapter = new ContatoVeiculoAdapter(this, veiculosFiltrados);
            lista.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}