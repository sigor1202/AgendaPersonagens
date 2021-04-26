package com.igor.listapersonagens.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.igor.listapersonagens.R;
import com.igor.listapersonagens.ui.dao.PersonagemDAO;
import com.igor.listapersonagens.ui.model.Personagem;

import java.util.List;

import static com.igor.listapersonagens.ui.activities.ConstanteActivitis.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {
    //variavel utilizada para o set title
    public static final String TITULO_APPBAR = "Lista de personagens";
    //instancia a classe personagemDAO
    private final PersonagemDAO dao = new PersonagemDAO();
    private ArrayAdapter<Personagem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //pega o layout que esta sendo usado
        setContentView(R.layout.activity_lista_personagem);
        //seta o titulo
        setTitle(TITULO_APPBAR);

        //chama a classe  configuraFabNovoPersonagem
        configuraFabNovoPersonagem();
        configuraLista();

    }

    //ao clicar no botão chama a função abre formulario
    private void configuraFabNovoPersonagem() {
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_add);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbreFormulario();
            }
        });
    }

    //função para abrir o formulario
    private void AbreFormulario() {
        startActivity(new Intent(this, FormularioPersonagemActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaLista();

    }

    //-
    private void atualizaLista() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    //função para deletar
    private void remove(Personagem personagem){
        dao.Remove(personagem);
        adapter.remove(personagem);
    }

        //faz aparecer um popUP
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        //menu.add("Remover");
        getMenuInflater().inflate(R.menu.acrivity_lista_personagem_menu, menu);
    }

    //seleciona uym item da lista e o deleta
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        configuraMenu(item);
        return super.onContextItemSelected(item);
    }

    //essa função verifica se o usuario realmente quer deletar e se sim deleta
    private void configuraMenu(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_lista_personagem_menu_remover){

            new AlertDialog.Builder(this)
                    .setTitle("removendo personagem")
                    .setMessage("tem certeza que deseja remover?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                            Personagem personagemEscolhido = adapter.getItem(menuInfo.position);
                            remove(personagemEscolhido);
                        }
                    })
                    .setNegativeButton("Não",null)
                    .show();
        }
    }

    private void configuraLista() {
        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);
        //final List<Personagem> personagens = dao.todos();
        //ListaDePersonagens(listaDePersonagens, personagens);
        ListaDePersonagens(listaDePersonagens);
        ConfiguraItemPorClick(listaDePersonagens);
        registerForContextMenu(listaDePersonagens);
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

    private void ListaDePersonagens(ListView listaDePersonagens) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaDePersonagens.setAdapter(adapter);
    }

}
