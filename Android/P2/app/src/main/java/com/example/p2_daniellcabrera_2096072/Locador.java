package com.example.p2_daniellcabrera_2096072;

import java.io.Serializable;
import java.util.Objects;

public class Locador implements Serializable {
    //variaveis para gestão de locadores
    int cpf, cep;
    String nome, email, telefone;

    @Override
    public String toString() {
        return cpf + " | " + cep + " | " + nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Locador locador = (Locador) o;
        return cpf == locador.cpf;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
