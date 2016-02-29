package com.example.jv.agendasecreta.model;

/**
 * Created by jv on 16/01/16.
 */
public class Usuario {

    private int Id;
    private String Senha;
    private String Email;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
