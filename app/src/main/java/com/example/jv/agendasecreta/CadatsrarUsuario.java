package com.example.jv.agendasecreta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jv.agendasecreta.dao.UsuarioDb;
import com.example.jv.agendasecreta.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class CadatsrarUsuario extends AppCompatActivity {

    private EditText etEmailCad;
    private EditText etSenhaCad;
    private Button btnCadUser;

    private UsuarioDb banco = new UsuarioDb(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadatsrar_usuario);

        etEmailCad = (EditText)findViewById(R.id.etEmailCad);
        etSenhaCad = (EditText)findViewById(R.id.etSenhaCad);
        btnCadUser = (Button)findViewById(R.id.btnCadUser);

        btnCadUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = preencherUsuario();
                inserirUsuario(usuario);

            }
        });
    }

    public Usuario preencherUsuario(){

        Usuario usuario = new Usuario();
        usuario.setEmail(etEmailCad.getText().toString());
        usuario.setSenha(etSenhaCad.getText().toString());

        return usuario;
    }

    public void inserirUsuario(Usuario usuario){

      int temUsuario = banco.UserListadoPorEmail(etEmailCad.getText().toString());
        if(temUsuario == 0){
            String salvar = banco.inserirUsuario(usuario);
            Toast.makeText(this, salvar.toString(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(CadatsrarUsuario.this, login_face.class));
            finish();
        }else{
            Toast.makeText(this, "Este e-mail pertence a um usuário já cadastrado na base de dados ", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
