package com.example.p3_daniellcabrera_2096072.bd;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.p3_daniellcabrera_2096072.modelo.ListaComItem;
import com.example.p3_daniellcabrera_2096072.modelo.ListaCompras;

import java.util.List;

@Dao
public interface ListComprasDAO {

    @Insert
    public void gravar(ListaCompras lc);

    @Update
    public void alterar(ListaCompras lc);

    @Delete
    public void apagar(ListaCompras lc);

    @Query("select * from listacompras order by prioridade")
    public LiveData<List<ListaCompras>> listar();

    @Transaction
    @Query("select * from listacompras order by prioridade")
    LiveData<List<ListaComItem>> listarListaCompraComItem();
}
