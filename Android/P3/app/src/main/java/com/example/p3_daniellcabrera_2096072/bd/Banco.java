package com.example.p3_daniellcabrera_2096072.bd;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.p3_daniellcabrera_2096072.modelo.Item;
import com.example.p3_daniellcabrera_2096072.modelo.ListaCompras;
import com.example.p3_daniellcabrera_2096072.modelo.Produto;
import com.example.p3_daniellcabrera_2096072.modelo.Setor;

@Database(entities = {Setor.class, Produto.class, Item.class, ListaCompras.class}, version = 5)
public abstract class Banco extends RoomDatabase {

    public abstract SetorDAO getSetorDAO();

    public abstract ProdutoDAO getProdutoDAO();

    public abstract ItemDAO getItemDAO();

    public abstract ListComprasDAO getListaCompraDAO();
}
