/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spotify.model;

import com.spotify.DAO.*;

/**
 *
 * @author Voronhuk
 */
public class Usuario {

    private int id;
    private String nome;
    private boolean funcao;
    private String login;
    private String senha;

    public Usuario() {
    }

    public Usuario(int id, String nome, boolean funcao, String login, String senha) {

        this.id = id;
        this.nome = nome;
        this.funcao = funcao;
        this.login = login;
        this.senha = senha;

    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getFuncao() {
        return funcao;
    }

    public void setFuncao(boolean funcao) {
        this.funcao = funcao;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Musica buscarMusica(int id) {

        return new MusicaDAO().buscarMusica(id);

    }

}
