package com.example.jv.agendasecreta.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jv.agendasecreta.model.Contatos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jv on 01/01/16.
 */
public class ContatoDb {

    public static final String ID = "_id";
    public static final String NOME = "_nome";
    public static final String CONTATO = "_contato";
    public static final String EMAIL = "_email";

    private Db meuDb;
    private SQLiteDatabase db;

    public ContatoDb(Context context){

        meuDb = new Db(context);
    }

    public String InserirContato(Contatos contato){

        ContentValues valores = new ContentValues();
        valores.put(NOME, contato.getNome().toString());
        valores.put(CONTATO, contato.getContato().toString());
        valores.put(EMAIL, contato.getEmail().toString());

        db = meuDb.getWritableDatabase();

        if(contato.getId() > 0){

            String[] whereArgs = new String[]{String.valueOf(contato.getId())};
            long resuldado = db.update(meuDb.TABELA_CONTATOS, valores, ID+"=?", whereArgs);

            if(resuldado == -1){
                return "Erro ao editar cadastro!";
            }else {
                return "Alteração reaizada com sucesso!";
            }

        }else{

            long resultado = db.insert(meuDb.TABELA_CONTATOS, null, valores);

            db.close();

            if(resultado == -1){
                return "Erro ao cadastrar contato. ";
            }else{
                return "Contato cadastrado com sucesso!";
            }

        }
    }

    // Excluir contatos
    public int excluir(Contatos contato){
        db = meuDb.getWritableDatabase();
        try {
            int count = db.delete(meuDb.TABELA_CONTATOS, ContatoDb.ID+"=?", new String[]{String.valueOf(contato.getId())} );
            return count;
        }finally {
            db.close();
        }
    }

    // Listar Contatos
    public List<Contatos> listarContatos(String email){
        db = meuDb.getWritableDatabase();

        try {
            String[] whereArgs = new String[]{email};
            Cursor c = db.query(meuDb.TABELA_CONTATOS, null, EMAIL+"=?", whereArgs,null, null, null);
            return toList(c);
        }finally {
            db.close();
        }

    }

    //Listar contato por Nome ou Numero

    public List<Contatos> contatosListado(String texto, String email){

        String parametro = null;
        if (!texto.isEmpty()){
            parametro = texto;
        }

        db = meuDb.getWritableDatabase();
        try{
            String myQuery = EMAIL+" like '%"+email+"%'"+ " AND " + NOME+" like '%"+parametro+"%'" + " OR " + EMAIL+" like '%"+email+"%'"+ " AND " + CONTATO + " like '%"+parametro+"%'";
            Cursor c = db.query(meuDb.TABELA_CONTATOS, null, myQuery, null,null, null, null );
            return toList(c);
        }finally {
            db.close();
        }
    }


    private List<Contatos> toList(Cursor c){
        List<Contatos> contatos = new ArrayList<>();
        if(c.moveToFirst()){
            do{
                Contatos contato = new Contatos();
                contatos.add(contato);
                contato.setId(c.getInt(c.getColumnIndex(ContatoDb.ID)));
                contato.setNome(c.getString(c.getColumnIndex(ContatoDb.NOME)));
                contato.setContato(c.getString(c.getColumnIndex(ContatoDb.CONTATO)));

            }while (c.moveToNext());
        }

        return contatos;
    }


}
