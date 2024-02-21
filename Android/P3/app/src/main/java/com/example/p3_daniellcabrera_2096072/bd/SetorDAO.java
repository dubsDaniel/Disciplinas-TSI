package com.example.p3_daniellcabrera_2096072.bd;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.p3_daniellcabrera_2096072.modelo.Setor;
import com.example.p3_daniellcabrera_2096072.modelo.SetorComProdutos;

import java.util.List;

@Dao
public interface SetorDAO {

    @Insert
    public void gravar(Setor s);

    @Update
    public void alterar(Setor s);

    @Delete
    public void apagar(Setor s);

    @Query("select * from setor order by nome")
    public LiveData<List<Setor>> listar();

    @Transaction
    @Query("select * from setor order by nome")
    public LiveData<List<SetorComProdutos>> listarSetorComProdutos();

}
