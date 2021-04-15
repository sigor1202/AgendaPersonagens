package com.igor.listapersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.igor.listapersonagens.R;
import com.igor.listapersonagens.ui.dao.PersonagemDAO;
import com.igor.listapersonagens.ui.model.Personagem;

import java.util.List;

import static com.igor.listapersonagens.ui.activities.ConstanteActivitis.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {
    //variavel utilizada para o st title
    public static final String TITULO_APPBAR = "Lista de personagens";
    private final PersonagemDAO dao = new PersonagemDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        //seta o titulo
        setTitle(TITULO_APPBAR);


        configuraFabNovoPersonagem();


    }

    private void configuraFabNovoPersonagem() {
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_add);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbreFormulario();
            }
        });
    }

    private void AbreFormulario() {
        startActivity(new Intent(this, FormularioPersonagemActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);
        final List<Personagem> personagens = dao.todos();
        ListaDePersonagens(listaDePersonagens, personagens);

        ConfiguraItemPorClick(listaDePersonagens);

    }

    private void ConfiguraItemPorClick(ListView listaDePersonagens) {
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(position);
                abreFormularioEditar(personagemEscolhido);
            }
        });
    }

    private void abreFormularioEditar(Personagem personagemEscolhido) {
        Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
        vaiParaFormulario.putExtra(CHAVE_PERSONAGEM, personagemEscolhido);
        startActivity(vaiParaFormulario);
    }

    private void ListaDePersonagens(ListView listaDePersonagens, List<Personagem> personagens) {
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));
    }

}
