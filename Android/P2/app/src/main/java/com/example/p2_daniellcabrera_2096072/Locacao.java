package com.example.p2_daniellcabrera_2096072;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Locacao implements Serializable {
    //variaveis para gestão de locacoes
    int cpf, id;
    String placa;
    Date dt_inicio, dt_fim;
    Double valor;

    @Override
    public String toString() {
        return cpf + " | " + placa + " | " + dt_inicio + " | " + dt_fim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Locacao locacao = (Locacao) o;
        return cpf == locacao.cpf && placa.equals(locacao.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, placa);
    }

    public int getId() {
        return id;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getDt_inicio() {
        return dt_inicio;
    }

    public void setDt_inicio(Date dt_inicio) {
        this.dt_inicio = dt_inicio;
    }

    public Date getDt_fim() {
        return dt_fim;
    }

    public void setDt_fim(Date dt_fim) {
        this.dt_fim = dt_fim;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
