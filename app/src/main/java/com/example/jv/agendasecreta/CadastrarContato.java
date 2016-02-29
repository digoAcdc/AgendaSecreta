package com.example.jv.agendasecreta;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jv.agendasecreta.dao.ContatoDb;
import com.example.jv.agendasecreta.model.Contatos;

public class CadastrarContato extends AppCompatActivity {

    private Button btnCadastrar;
    private EditText etNome;
    private EditText etContato;

    private ContatoDb banco = new ContatoDb(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_contato);

        btnCadastrar = (Button)findViewById(R.id.btnCadastrar);
        etNome = (EditText)findViewById(R.id.etNome);
        etContato = (EditText) findViewById(R.id.etTelefone);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Contatos contato = preencherContato();
                inserirContato(contato);
                String email = contato.getEmail();
                finish();


            }
        });

    }


    public Contatos preencherContato(){

        Bundle bundle = getIntent().getExtras();
        String email = bundle.getString("Email");
        Contatos contato = new Contatos();
        contato.setNome(etNome.getText().toString());
        contato.setContato(etContato.getText().toString());
        contato.setEmail(email);

        return contato;
    }

    public void inserirContato(Contatos contato){

        Bundle bundle = getIntent().getExtras();
        String salvar = banco.InserirContato(contato);
        Toast.makeText(this, salvar.toString(), Toast.LENGTH_SHORT).show();
        Intent toLista = new Intent(this, MainActivity.class).putExtras(bundle);
        startActivity(toLista);
        finish();


    }
}
