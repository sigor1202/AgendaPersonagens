package com.igor.listapersonagens.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.igor.listapersonagens.R;
import com.igor.listapersonagens.ui.model.Personagem;

public class FormularioPersonagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);

        //cria as variaveis para receber os respectivos itens dos editText
        EditText campoNome = findViewById(R.id.editTextText_nome);
        EditText campoAltura = findViewById(R.id.editTextText_altura);
        EditText campoNascimento = findViewById(R.id.editTextText_nascimento);

        // cria uma variavel do tipo button e atribui o botão button_salvar a ela por meio do findViewById
        Button botaoSalvar = findViewById(R.id.button_salvar);
        //seta o onClick ao botão e instancia uma nova view
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();
                String nascimento = campoNascimento.getText().toString();

                Personagem personagemSalvo = new Personagem(nome, altura, nascimento);



                Toast.makeText(FormularioPersonagemActivity.this,
                        personagemSalvo.getNome() + " - " + personagemSalvo.getAltura() + " - " + personagemSalvo.getNascimento(),Toast.LENGTH_SHORT ).show();

                        new Personagem(nome,altura,nascimento);

                //faz aparecer uma mensagem que desaparece logo em seguida
                //Consecutivamente: tela em que aparecera, mensagem, tempo e comando de aparecer
                //Toast.makeText(FormularioPersonagemActivity.this,"Estou Funcionando!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}