package com.igor.listapersonagens.ui.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagem implements Serializable {
    //variaveis usadas no construtor
    private String nome;
    private String altura;
    private String nascimento;
    private int id = 0;

    //construtor
    public Personagem(String nome, String altura, String nascimento) {
        //set
        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
    }
    //construtor vazio
    public Personagem() {
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public  boolean IdValido(){
        return id>0;
    }

}
