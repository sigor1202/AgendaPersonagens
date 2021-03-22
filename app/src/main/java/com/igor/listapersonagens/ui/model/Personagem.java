package com.igor.listapersonagens.ui.model;

public class Personagem {

    private final String nome;
    private final String altura;
    private final String nascimento;
    //construtor
    public Personagem(String nome, String altura, String nascimento) {
        //set
        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
    }

    //getters
    public String getNome() {
        return nome;
    }

    public String getAltura() {
        return altura;
    }

    public String getNascimento() {
        return nascimento;
    }
}
