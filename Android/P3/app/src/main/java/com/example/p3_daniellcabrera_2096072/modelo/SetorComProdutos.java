package com.example.p3_daniellcabrera_2096072.modelo;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.List;

public class SetorComProdutos implements Serializable {

    @Embedded
    private Setor setor;

    @Relation(parentColumn = "id", entityColumn = "id_setor")
    private List<Produto> produto;

    public Setor getSetores() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produtos) {
        this.produto = produtos;
    }

    @Override
    public String toString() {
        return setor.getNome();
    }

}
