package com.example.p3_daniellcabrera_2096072.modelo;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.List;

public class ProdutoComItem implements Serializable {

    @Embedded
    private Produto produto;

    @Relation(parentColumn = "id", entityColumn = "id_produto")
    private List<Item> itens;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return produto.getDescricao();
    }
}
