package com.example.finanaspessoais;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "transacoes")
public class Transacao {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "descricao")
    private String descricao;

    @ColumnInfo(name = "valor")
    private double valor;

    @ColumnInfo(name = "categoria")
    private String categoria;

    @ColumnInfo(name = "data")
    private String data;

    @ColumnInfo(name = "is_receita")
    private boolean isReceita;

    @ColumnInfo(name = "titulo")
    private String titulo;

    // Construtor
    public Transacao(String descricao, double valor, String categoria, String data, boolean isReceita) {
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.data = data;
        this.isReceita = isReceita;
        this.titulo = titulo;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isReceita() {
        return isReceita;
    }

    public void setReceita(boolean receita) {
        isReceita = receita;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}