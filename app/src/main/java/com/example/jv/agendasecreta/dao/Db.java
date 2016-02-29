package com.example.jv.agendasecreta.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jv on 01/01/16.
 */
public class Db extends SQLiteOpenHelper {

    public static final String TABELA_CONTATOS = "tbContato";
    public static final String TABELA_USUARIOS = "tbUsuario";
    public static final String NOME_BANCO = "contato_Db";
    public static final int VERSAO = 1;



    public Db(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

       //Cria a Tabela de Usuarios

        StringBuilder sql = new StringBuilder();
        sql.append(" Create Table ");
        sql.append(TABELA_USUARIOS);
        sql.append("(");
        sql.append(UsuarioDb.ID);
        sql.append(" integer primary key autoincrement, ");
        sql.append(UsuarioDb.SENHA);
        sql.append(" text, ");
        sql.append(UsuarioDb.EMAIL);
        sql.append(" text ) ");
        db.execSQL(sql.toString());

        //cria tabela de Contatos

        StringBuilder sql2 = new StringBuilder();
        sql2.append(" Create Table ");
        sql2.append(TABELA_CONTATOS);
        sql2.append("(");
        sql2.append(ContatoDb.ID);
        sql2.append(" integer primary key autoincrement, ");
        sql2.append(ContatoDb.NOME);
        sql2.append(" text, ");
        sql2.append(ContatoDb.EMAIL);
        sql2.append(" text, ");
        sql2.append(ContatoDb.CONTATO);
        sql2.append(" text ) ");
        db.execSQL(sql2.toString());


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + TABELA_USUARIOS);
        onCreate(db);

    }
}
