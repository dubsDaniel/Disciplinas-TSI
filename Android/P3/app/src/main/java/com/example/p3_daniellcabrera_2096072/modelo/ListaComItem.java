package com.example.p3_daniellcabrera_2096072.modelo;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.List;

public class ListaComItem implements Serializable {

    @Embedded
    private ListaCompras lista;

    @Relation(parentColumn = "id", entityColumn = "id_lista")
    private List<Item> itens;

    public ListaCompras getLista() {
        return lista;
    }

    public void setLista(ListaCompras lista) {
        this.lista = lista;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return lista.getNome();
    }
}
