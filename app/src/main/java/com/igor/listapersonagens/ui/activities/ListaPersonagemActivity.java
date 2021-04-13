package com.igor.listapersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.igor.listapersonagens.R;
import com.igor.listapersonagens.ui.dao.PersonagemDAO;
import com.igor.listapersonagens.ui.model.Personagem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity {

    private final PersonagemDAO dao = new PersonagemDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        //set o titulo
        setTitle("Lista de personagens");
        //seta dois personagens para testes
        dao.salva(new Personagem("ken","1,80","02041979"));
        dao.salva(new Personagem("ryu","1,80","02041979"));


        //List<String> personagens = new ArrayList<>(Arrays.asList("Alex", "Ken", "Ryu"));

         FloatingActionButton  botaoNovoPersonagem = findViewById(R.id.fab_add);
         botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
             }
         });


    }

    @Override
    protected void onResume() {
        super.onResume();


        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);
        List<Personagem> personagens = dao.todos();
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));

        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Personagem personagemEscolhido = personagens.get(position);
                Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
                vaiParaFormulario.putExtra("personagem", personagemEscolhido);
                startActivity(vaiParaFormulario);
            }
        });

    }

}
