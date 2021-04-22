package com.igor.listapersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.igor.listapersonagens.R;
import com.igor.listapersonagens.ui.dao.PersonagemDAO;
import com.igor.listapersonagens.ui.model.Personagem;

import static com.igor.listapersonagens.ui.activities.ConstanteActivitis.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {

    //constantes utilizadas no appbar
    public static final String TITULO_APPBAR_EDITA_PERSONAGEM = "editar personagens";
    public static final String TITULO_APPBAR_NOVO_PERSONAGEM = "novo personagens";

     //variaveis de editText
    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    //instancia a classe dao
    private final PersonagemDAO dao = new PersonagemDAO();
    //instancia a classe personagem
    private Personagem personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //pega o layout que esta sendo usado
        setContentView(R.layout.activity_formulario_personagem);
        //chama o metodo para configurar o botão
        inicializacaoDeCampos();
        //chama o metodo para inicializar os campos
        configuraBotaoSalvar();
        //chama o metododo CarregaPersonagem
        CarregaPersonagem();
    }

    private void CarregaPersonagem() {

        Intent dados = getIntent();
        //verifica se esta adicionando um personagem ou editando um existente
        if (dados.hasExtra(CHAVE_PERSONAGEM)) {
            setTitle(TITULO_APPBAR_EDITA_PERSONAGEM);
             personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            preencherCampos();
        }
        else{
            setTitle(TITULO_APPBAR_NOVO_PERSONAGEM);
            personagem = new Personagem();
        }
    }

    private void preencherCampos() {
        //prenche os campos
        campoNome.setText(personagem.getNome());
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
    }

    private void configuraBotaoSalvar() {
        // cria uma variavel do tipo button e atribui o botão button_salvar a ela por meio do findViewById
        Button botaoSalvar = findViewById(R.id.button_salvar);
        //seta o onClick ao botão e instancia uma nova view
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                finalizarPersonagem();

            }
        }
        );
    }
    //salvar os dados
    private void finalizarPersonagem() {
        preencherPersonagem();

        if(personagem.IdValido()){
            dao.editar(personagem);
            finish();
        }
        else {
            dao.salva(personagem);
        }
        finish();
    }

    //metodo para inicializar os campos
    private void inicializacaoDeCampos() {
        //cria as variaveis para receber os respectivos itens dos editText
        campoNome = findViewById(R.id.editTextText_nome);
        campoAltura = findViewById(R.id.editTextText_altura);
        campoNascimento = findViewById(R.id.editTextText_nascimento);
    }

    //preenche o construtor
    private void preencherPersonagem(){

        //atribui o conteudo dos editText as variaveis
        String nome = campoNome.getText().toString();
        String altura = campoAltura.getText().toString();
        String nascimento = campoNascimento.getText().toString();

        //seta o conteudo no construtor
        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);
    }

}