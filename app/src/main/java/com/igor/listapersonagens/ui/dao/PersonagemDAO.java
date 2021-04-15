package com.igor.listapersonagens.ui.dao;

import com.igor.listapersonagens.ui.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    private final static List<Personagem> personagens = new ArrayList<>();
    private static int contadorDeId = 1;

    public void salva(Personagem personagemSalvo) {
        personagemSalvo.setId(contadorDeId);
        //adiciona as informaçoes recebidas a lista
        personagens.add(personagemSalvo);
        atualizaId();


    }

    private void atualizaId() {
        contadorDeId++;
    }

    public void editar(Personagem personagem) {
        Personagem personagemEscolhido = BuscaPersonagemID(personagem);
        if (personagemEscolhido != null) {
            int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoDoPersonagem, personagem);
        }


    }

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

}
