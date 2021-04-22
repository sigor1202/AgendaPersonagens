package com.igor.listapersonagens.ui.dao;

import com.igor.listapersonagens.ui.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {
    //cria a lista de personagens
    private final static List<Personagem> personagens = new ArrayList<>();
    //variavel do id
    private static int contadorDeId = 1;

    //função para salvar
    public void salva(Personagem personagemSalvo) {
        personagemSalvo.setId(contadorDeId);
        //adiciona as informaçoes recebidas a lista
        personagens.add(personagemSalvo);
        atualizaId();


    }
    //adiciona um numero ao id
    private void atualizaId() {
        contadorDeId++;
    }

    //verifica se a id é diferente de nulo e salva
    public void editar(Personagem personagem) {
        Personagem personagemEscolhido = BuscaPersonagemID(personagem);
        if (personagemEscolhido != null) {
            int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoDoPersonagem, personagem);
        }

    }

    //verifica qual o numero do id
    private Personagem BuscaPersonagemID(Personagem personagem) {
        for (Personagem p :
                personagens) {
            if (p.getId() == personagem.getId())
                return p;
        }
        return null;
    }


    public List<Personagem> todos() {
        return new ArrayList<>(personagens);

    }

    public void Remove(Personagem personagem) {
        Personagem personagemDevolvido = BuscaPersonagemID(personagem);
        if (personagemDevolvido != null) {
            //remove o item selecionado
            personagens.remove(personagemDevolvido);
        }

    }
}
