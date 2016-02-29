package com.example.jv.agendasecreta.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jv on 01/01/16.
 */
public class Contatos {

    private int Id;
    private String Nome;
    private String Contato;
    private String Email;

    //public Contatos(int id, String nome, String contato) {
    //    Id = id;
    //    Nome = nome;
    //    Contato = contato;
   // }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getContato() {
        return Contato;
    }

    public void setContato(String contato) {
        Contato = contato;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    //public static List<Contatos> getContatos(){
    //
    //    List<Contatos> contatos = new ArrayList<>();
    //    contatos.add(new Contatos(1, "João Vitor", "4455-3344"));
    //    contatos.add(new Contatos(2, "Marcela", "4455-3333"));
   //     contatos.add(new Contatos(3, "Alice", "4422-3344"));
    //    contatos.add(new Contatos(4, "Pedro", "4422-2244"));

     //   return contatos;
  //  }

}

