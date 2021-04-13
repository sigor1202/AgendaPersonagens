package com.igor.listapersonagens.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.igor.listapersonagens.R;
import com.igor.listapersonagens.ui.dao.PersonagemDAO;
import com.igor.listapersonagens.ui.model.Personagem;

import java.io.Serializable;

public class FormularioPersonagemActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    private final PersonagemDAO dao = new PersonagemDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        setTitle("Lista de personagens");



        //cria as variaveis para receber os respectivos itens dos editText
         campoNome = findViewById(R.id.editTextText_nome);
         campoAltura = findViewById(R.id.editTextText_altura);
         campoNascimento = findViewById(R.id.editTextText_nascimento);

        // cria uma variavel do tipo button e atribui o botão button_salvar a ela por meio do findViewById
        Button botaoSalvar = findViewById(R.id.button_salvar);
        //seta o onClick ao botão e instancia uma nova view
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //atribui o conteudo dos editText as variaveis
                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();
                String nascimento = campoNascimento.getText().toString();

                Personagem personagemSalvo = new Personagem(nome, altura, nascimento);

                //chama o metodo salvar da activity PersonagemDao
                dao.salva(personagemSalvo);
                finish();

                //carrega a activity ListaPersonagemActivity
                //startActivity(new Intent(FormularioPersonagemActivity.this, ListaPersonagemActivity.class));

                /*Toast.makeText(FormularioPersonagemActivity.this,
                        personagemSalvo.getNome() + " - " + personagemSalvo.getAltura() + " - " + personagemSalvo.getNascimento(),Toast.LENGTH_SHORT ).show();*/

                        //new Personagem(nome,altura,nascimento);

                personagemSalvo.setNome(nome);
                personagemSalvo.setAltura(altura);
                personagemSalvo.setNascimento(nascimento);
                dao.editar(personagemSalvo);

                //faz aparecer uma mensagem que desaparece logo em seguida
                //Consecutivamente: tela em que aparecera, mensagem, tempo e comando de aparecer
                //Toast.makeText(FormularioPersonagemActivity.this,"Estou Funcionando!",Toast.LENGTH_SHORT).show();

                Intent dados = getIntent();
                Personagem personagem = (Personagem) dados.getSerializableExtra("personagem");
                campoNome.setText(personagem.getNome());
                campoAltura.setText(personagem.getAltura());
                campoNascimento.setText(personagem.getNascimento());

            }
        });
    }


}