package com.example.jv.agendasecreta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jv.agendasecreta.dao.UsuarioDb;
import com.example.jv.agendasecreta.model.Usuario;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;
import java.util.List;

public class login_face extends AppCompatActivity {

    private CallbackManager callbackManager;
    private Button btnCadastrarUser;
    private Button btnEntrarLogin;
    private EditText etLogin;
    private EditText etSenha;
    private UsuarioDb banco;

    private static final String TAG = login_face.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_face);
        etLogin = (EditText)findViewById(R.id.etLogin);
        etSenha = (EditText)findViewById(R.id.etSenha);
        banco = new UsuarioDb(this);



        btnCadastrarUser = (Button)findViewById(R.id.btnCadastrarUser);
        btnCadastrarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login_face.this, CadatsrarUsuario.class));
            }
        });


        btnEntrarLogin = (Button)findViewById(R.id.entrarLogin);
        btnEntrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usurario = etLogin.getText().toString().trim();
                String senha = etSenha.getText().toString().trim();
                if(!usurario.isEmpty() && !senha.isEmpty()){

                    Usuario temUseR = banco.UserListado(usurario, senha);
                    if (temUseR  != null){
                        Bundle bundle = new Bundle();
                        String email = temUseR.getEmail().toString();
                        bundle.putString("Email",email);
                        startActivity(new Intent(login_face.this, MainActivity.class).putExtras(bundle));
                        finish();
                    }else{
                        Toast.makeText(login_face.this, "Usuario nao cadastrado", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    if(usurario.isEmpty()){
                        etLogin.setError("Login obrigatório");
                    }
                    if(senha.isEmpty()){
                        etSenha.setError("Senha obrigatório");
                    }
                }
            }
        });

         //   callbackManager = CallbackManager.Factory.create();
        //final LoginButton loginButton = (LoginButton) findViewById(R.id.usersettings_login_button);
        //loginButton.setReadPermissions(Arrays.asList("public_profile"));


//        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
   //         @Override
  //          public void onSuccess(LoginResult loginResult) {

    //                startActivity(new Intent(login_face.this, MainActivity.class));
    //            finish();
    //        }

    //        @Override
    //        public void onCancel() {

    //            Log.d(TAG, "cancel");
    //        }

    //        @Override
    //        public void onError(FacebookException error) {

    //            Log.d(TAG, error.getMessage());
    //        }
    //    });


    }
}
