package com.example.p1_daniellcabrera_2096072;

import java.io.Serializable;

public class ContatoVeiculo implements Serializable {
    private String nome, email, telefone, marca, modelo, cor; //Adicionado cor (não obrigatório para quem deseja comprar - O resto foi solicitado
    private double valor; //Solicitado
    private int ano, interesse, tipoVeiculo; //variável para controlar se deseja comprar x vender e para definir a imagem do ListView + ano

    public ContatoVeiculo() { //Construtor
    }

    //getters e setters
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }

    public int getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(int tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getInteresse() {
        return interesse;
    }

    public void setInteresse(int interesse) {
        this.interesse = interesse;
    }
}

