package com.example.p2_daniellcabrera_2096072;

import java.io.Serializable;
import java.util.Objects;

public class Veiculo implements Serializable {
    //variaveis para gestão de veiculos
    int ano;
    String cor, placa;
    Modelo modelo;

    @Override
    public String toString() {
        return ano + " | " + cor + " | " + placa + " | " + modelo.getDescricao();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return placa.equals(veiculo.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa);
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
