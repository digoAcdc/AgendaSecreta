package com.example.jv.agendasecreta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jv.agendasecreta.dao.ContatoDb;
import com.example.jv.agendasecreta.model.Contatos;

public class EditarContato extends AppCompatActivity {

    private EditText etEditarNome;
    private EditText etEditarContato;
    private Button btnEditar;
    private ContatoDb banco = new ContatoDb(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contato);

        etEditarNome = (EditText) findViewById(R.id.etEditarNome);
        etEditarContato = (EditText) findViewById(R.id.etEditarContato);
        btnEditar = (Button) findViewById(R.id.btnEdtarContato);

        String nome = getIntent().getStringExtra("nome");
        String contato = getIntent().getStringExtra("contato");
        String email = getIntent().getStringExtra("Email");
        final int id = getIntent().getIntExtra("Id", 0);

        etEditarNome.setText(nome);
        etEditarContato.setText(contato);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Contatos contato = preencherContato(id);
                InserirContato(contato);
                

            }


        });

    }

    public Contatos preencherContato(int id){
        Contatos contato = new Contatos();
        contato.setId(id);
        contato.setNome(etEditarNome.getText().toString());
        contato.setContato(etEditarContato.getText().toString());

        return contato;
    }

    public void InserirContato(Contatos contato){

        Bundle bundle = getIntent().getExtras();
        String email = getIntent().getStringExtra("Email");
        contato.setEmail(email);
        String salvar = banco.InserirContato(contato);
        Toast.makeText(this, salvar.toString(), Toast.LENGTH_SHORT).show();
        Intent toLista = new Intent(this, MainActivity.class).putExtras(bundle);
        startActivity(toLista);
        finish();
    }
}

