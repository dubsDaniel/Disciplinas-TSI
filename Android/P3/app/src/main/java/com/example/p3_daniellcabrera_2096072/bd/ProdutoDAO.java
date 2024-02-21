package com.example.p3_daniellcabrera_2096072.bd;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.p3_daniellcabrera_2096072.modelo.ProdutoComItem;
import com.example.p3_daniellcabrera_2096072.modelo.Produto;

import java.util.List;

@Dao
public interface ProdutoDAO {

    @Insert
    public void gravar(Produto p);

    @Update
    public void alterar(Produto p);

    @Delete
    public void apagar(Produto p);

    @Query("select * from produto where id_setor = :id order by descricao")
    public LiveData<List<Produto>> buscarPorMarca(long id);

    @Transaction
    @Query("select * from produto where id_setor = :id order by descricao")
    LiveData<List<ProdutoComItem>> buscarProdutosComItens(long id);
}
