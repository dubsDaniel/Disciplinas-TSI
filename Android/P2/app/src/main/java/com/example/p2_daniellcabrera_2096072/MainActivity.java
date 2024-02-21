package com.example.p2_daniellcabrera_2096072;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity { //Tela apenas para escolher qual serviço realizar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void veiculos(View v) { //Cadastro de veiculo
        Intent it = new Intent(this, cadastro_veiculo.class);
        startActivity(it);
    }

    public void locadores(View v) { //Cadastro de locadores
        Intent it = new Intent(this, cadastro_locador.class);
        startActivity(it);
    }

    public void locacao(View v) { //Cadastro de locacoes
        Intent it = new Intent(this, activity_locacao.class);
        startActivity(it);
    }

}