package com.example.p3_daniellcabrera_2096072.modelo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Setor implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String nome;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Setor setor = (Setor) o;
        return id == setor.id && Objects.equals(nome, setor.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    public String toString() {
        return nome;
    }
}