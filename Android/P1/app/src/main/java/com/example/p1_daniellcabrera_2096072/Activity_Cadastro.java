package com.example.p1_daniellcabrera_2096072;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Activity_Cadastro extends AppCompatActivity {

    EditText edNome, edTelefone, edEmail, edMarca, edModelo, edAno, edCor, edValor; //Váriaveis para coletar as infos
    RadioButton rbVendedores, rbCompradores, rbMoto, rbCarro, rbCaminhao; //Váriaveis para coletar as infos x2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        edNome = (EditText) findViewById(R.id.ed_nome);
        edTelefone = (EditText) findViewById(R.id.ed_telefone);
        edEmail = (EditText) findViewById(R.id.ed_email);
        edValor = (EditText) findViewById(R.id.ed_valor);
        edMarca = (EditText) findViewById(R.id.ed_marca);
        edModelo = (EditText) findViewById(R.id.ed_modelo);
        edAno = (EditText) findViewById(R.id.ed_ano);
        edCor = (EditText) findViewById(R.id.ed_cor);
        rbVendedores = (RadioButton) findViewById(R.id.rb_queroVender);
        rbCompradores = (RadioButton) findViewById(R.id.rb_queroComprar);
        rbMoto = (RadioButton) findViewById(R.id.rb_moto);
        rbCarro = (RadioButton) findViewById(R.id.rb_carro);
        rbCaminhao = (RadioButton) findViewById(R.id.rb_caminhao);

        ContatoVeiculo cv = (ContatoVeiculo) getIntent().getSerializableExtra("veiculoEdicao"); //Caso seja uma ação de edição, configurado para preencher as infos do
        if (cv != null) {
            if (cv.getInteresse() == 1) {
                rbVendedores.setChecked(true);
            } else {
                rbCompradores.setChecked(true);
            }
            if (cv.getTipoVeiculo() == 1){
                rbMoto.setChecked(true);
            } else if (cv.getTipoVeiculo() == 2) {
                rbCarro.setChecked(true);
            } else {
                rbCaminhao.setChecked(true);
            }
            if (cv.getCor() != null) {
                edCor.setText(String.valueOf(cv.getCor()));
            }
            edNome.setText(cv.getNome());
            edTelefone.setText(cv.getTelefone());
            edEmail.setText(String.valueOf(cv.getEmail()));
            edValor.setText(String.valueOf(cv.getValor()));
            edMarca.setText(String.valueOf(cv.getMarca()));
            edModelo.setText(String.valueOf(cv.getModelo()));
            edAno.setText(String.valueOf(cv.getAno()));
        }
    }

    public void salvar(View v) {
        //Ao salvar, é necessário verificar se os valores informados são realmente números
        String anoStr = edAno.getText().toString();
        String valorStr = edValor.getText().toString();
        if (!anoStr.isEmpty() && !valorStr.isEmpty()) {
            try {
                int ano = Integer.parseInt(anoStr);
                Double valor = Double.parseDouble(valorStr);
            } catch (NumberFormatException e) {
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Valor inválido");
                alert.setMessage("Insira um ano ou um valor válido.");
                alert.setPositiveButton("OK", null);
                alert.show();
                return;
            }
        }
        //Verificações para salvar
        if ((rbVendedores.isChecked()) && (rbMoto.isChecked() || rbCarro.isChecked() || rbCaminhao.isChecked())) { //Todos os RádioGroups devem ter sido preenchidos
            if (edNome.getText().toString().trim().length() > 0 && //Valida se as variáveis não estão vazios ou se é apenas "espaço"
                    edTelefone.getText().toString().trim().length() > 0 &&
                    edEmail.getText().toString().trim().length() > 0 &&
                    edMarca.getText().toString().trim().length() > 0 &&
                    edModelo.getText().toString().trim().length() > 0 &&
                    edAno.getText().toString().trim().length() > 0 &&
                    edCor.getText().toString().trim().length() > 0 &&
                    edValor.getText().toString().trim().length() > 0) { //No caso de venda, TODOS OS CAMPOS devem ser preenchidos
                AlertDialog.Builder bld = new AlertDialog.Builder(this); //Dialogo para confirmar ação
                bld.setTitle("Confirmação");
                bld.setMessage("Deseja realmente salvar/editar este veículo à lista?");
                bld.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Coleta as informações descritas e preenche o objeto ContatoVeiculo
                        String nome = edNome.getText().toString();
                        String telefone = edTelefone.getText().toString();
                        String email = edEmail.getText().toString();
                        String marca = edMarca.getText().toString();
                        String modelo = edModelo.getText().toString();
                        String cor = edCor.getText().toString();
                        int ano = Integer.parseInt(edAno.getText().toString());
                        Double vlr = Double.parseDouble(edValor.getText().toString());
                        ContatoVeiculo cv = new ContatoVeiculo();
                        cv.setAno(ano);
                        cv.setEmail(email);
                        cv.setCor(cor);
                        cv.setMarca(marca);
                        cv.setModelo(modelo);
                        cv.setNome(nome);
                        cv.setTelefone(telefone);
                        cv.setValor(vlr);
                        cv.setInteresse(1);
                        if (rbMoto.isChecked()){
                            cv.setTipoVeiculo(1);
                        } else if (rbCarro.isChecked()) {
                            cv.setTipoVeiculo(2);
                        } else {
                            cv.setTipoVeiculo(3);
                        }
                        Intent dados = new Intent();
                        dados.putExtra("veiculo", cv);
                        setResult(RESULT_OK, dados); //adiciona e volta pra Main Activity
                        finish();
                    }
                });
                bld.setNegativeButton("Não", null); //não faz nada
                bld.show();
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
        } else if ((rbCompradores.isChecked()) && (rbMoto.isChecked() || rbCarro.isChecked() || rbCaminhao.isChecked())) { //Todos os RádioGroups devem ter sido preenchidos
            if (edNome.getText().toString().trim().length() > 0 && //Somente o campo de cor não é necessário, pois, ao comprar as vezes a cor não é levada em consideração, mas pode ser preenchida mesmo assim
                    edTelefone.getText().toString().trim().length() > 0 &&
                    edEmail.getText().toString().trim().length() > 0 &&
                    edMarca.getText().toString().trim().length() > 0 &&
                    edModelo.getText().toString().trim().length() > 0 &&
                    edValor.getText().toString().trim().length() > 0 &&
                    edAno.getText().toString().trim().length() > 0) {
                AlertDialog.Builder bld = new AlertDialog.Builder(this); //Dialogo de confirmação
                bld.setTitle("Confirmação");
                bld.setMessage("Deseja realmente salvar/editar este veículo à lista?");
                bld.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Coleta as informações descritas e preenche o objeto ContatoVeiculo
                        String nome = edNome.getText().toString();
                        String telefone = edTelefone.getText().toString();
                        String email = edEmail.getText().toString();
                        String marca = edMarca.getText().toString();
                        String modelo = edModelo.getText().toString();
                        int ano = Integer.parseInt(edAno.getText().toString());
                        Double vlr = Double.parseDouble(edValor.getText().toString());
                        ContatoVeiculo cv = new ContatoVeiculo();
                        cv.setEmail(email);
                        cv.setMarca(marca);
                        cv.setModelo(modelo);
                        cv.setNome(nome);
                        cv.setTelefone(telefone);
                        cv.setValor(vlr);
                        cv.setInteresse(2);
                        cv.setAno(ano);
                        if (rbMoto.isChecked()){
                            cv.setTipoVeiculo(1);
                        } else if (rbCarro.isChecked()) {
                            cv.setTipoVeiculo(2);
                        } else {
                            cv.setTipoVeiculo(3);
                        }
                        Intent dados = new Intent();
                        dados.putExtra("veiculo", cv);
                        setResult(RESULT_OK, dados); //Salva na lista e volta pro Main
                        finish();
                    }
                });
                bld.setNegativeButton("Não", null); //Não faz nada
                bld.show();
            } else {
                Toast.makeText(this, "Preencha todos os campos obrigatórios", Toast.LENGTH_SHORT).show(); //Mensagem solicita que o usuário preencha os campos
            }
        } else {
            Toast.makeText(this, "Selecione um tipo de interesse e o tipo de veiculo", Toast.LENGTH_SHORT).show(); //Mensagem para que selecione o interesse e o tipo
        }
    }
}