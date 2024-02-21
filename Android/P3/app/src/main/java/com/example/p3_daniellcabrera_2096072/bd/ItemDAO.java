package com.example.p3_daniellcabrera_2096072.bd;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.p3_daniellcabrera_2096072.modelo.Item;

import java.util.List;

@Dao
public interface ItemDAO {

    @Insert
    public void gravar(Item i);

    @Update
    public void alterar(Item i);

    @Delete
    public void apagar(Item i);

    @Query("select * from item order by id")
    public LiveData<List<Item>> listar();

}
