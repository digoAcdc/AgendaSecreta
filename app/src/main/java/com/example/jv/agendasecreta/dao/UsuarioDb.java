package com.example.jv.agendasecreta.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jv.agendasecreta.model.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jv on 16/01/16.
 */
public class UsuarioDb {

    public static final String ID = "_id";
    public static final String SENHA = "_senha";
    public static final String EMAIL = "_email";

    private Db meuDbUser;
    private SQLiteDatabase db;

    public UsuarioDb(Context context) {

        meuDbUser = new Db(context);
    }

    public String inserirUsuario(Usuario usuario){

        ContentValues valores = new ContentValues();
        valores.put(SENHA, usuario.getSenha());
        valores.put(EMAIL, usuario.getEmail());

        db = meuDbUser.getWritableDatabase();

            long resultado = db.insert(meuDbUser.TABELA_USUARIOS, null, valores);

            db.close();

            if(resultado == -1){
                return "Erro ao cadastrar usuário. ";
            }else{
                return "Usuário cadastrado com sucesso!";
            }

    }


    //Listar usuario por email e senha

    public Usuario UserListado(String email, String senha){

        db = meuDbUser.getWritableDatabase();
        try{
            String myQuery =  EMAIL+" like '%"+email+"%'" + " AND " + SENHA + " like '%"+senha+"%'";
            Cursor c = db.query(meuDbUser.TABELA_USUARIOS, null, myQuery, null,null, null, null );
            List<Usuario> usuarios = new ArrayList<>();
            usuarios = toList(c);
            Usuario usuario = new Usuario();
            if(usuarios.size() != 0){
               return usuario = usuarios.get(0);
            }else{
               return usuario = null;
            }

        }finally {
            db.close();
        }
    }

    public int UserListadoPorEmail(String email){

        db = meuDbUser.getWritableDatabase();
        try{
            String[] whereArgs = new String[]{email};
            Cursor c = db.query(meuDbUser.TABELA_USUARIOS, null, EMAIL+"=?", whereArgs,null, null, null );
            if(c.getCount() == 0){
                Log.d("a","Teste");
                return 0;
            }else{
                return 1;
            }
        }finally {
            db.close();
        }
    }


    private List<Usuario> toList(Cursor c){
        List<Usuario> usuarios = new ArrayList<>();

         if(c.moveToFirst()){
            do{
                Usuario usuario = new Usuario();
                usuarios.add(usuario);
                usuario.setId(c.getInt(c.getColumnIndex(UsuarioDb.ID)));
                usuario.setEmail(c.getString(c.getColumnIndex(UsuarioDb.EMAIL)));
                usuario.setSenha(c.getString(c.getColumnIndex(UsuarioDb.SENHA)));

            }while (c.moveToNext());
        }

        return usuarios;
    }
}
