package com.example.p1_daniellcabrera_2096072;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.List;

public class ContatoVeiculoAdapter extends ArrayAdapter<ContatoVeiculo> { //Extends do ArrayAdapter para a lógica de personalização do item_list_veiculo
    public ContatoVeiculoAdapter(Context context, List<ContatoVeiculo> contatosVeiculo) { //Método padrão
        super(context, 0, contatosVeiculo);
    }

    public View getView(int position, View convertView, ViewGroup parent) { //Método para buscar o ListView personalizado para cada item da lista
        ContatoVeiculo contatoVeiculo = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_veiculo, parent, false);
        }

        //busca os campos do xml
        ImageView imagemTipoVeiculo = convertView.findViewById(R.id.imagemTipoVeiculo);
        TextView textMarcaModelo = convertView.findViewById(R.id.textMarcaModelo);
        TextView textAno = convertView.findViewById(R.id.textAno);
        TextView textValor = convertView.findViewById(R.id.textValor);

        //configura as informações que aparecerão
        imagemTipoVeiculo.setImageResource(getImagemTipoVeiculo(contatoVeiculo.getTipoVeiculo()));
        textMarcaModelo.setText(contatoVeiculo.getMarca() + " / " + contatoVeiculo.getModelo());
        textAno.setText(String.valueOf(contatoVeiculo.getAno()));
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String valorFormatado = "R$: " + df.format(contatoVeiculo.getValor());
        textValor.setText(valorFormatado);

        return convertView; //retorna o item da lista com view "convertida"=personalizada
    }

    private int getImagemTipoVeiculo(int tipoVeiculo) { //Método para buscar o ícone do veículo
        switch (tipoVeiculo) {
            case 1:
                return R.drawable.moto;
            case 2:
                return R.drawable.carro;
            case 3:
                return R.drawable.caminhao;
            default:
                return R.drawable.ic_launcher_foreground;
        }
    }
}
