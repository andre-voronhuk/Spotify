/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spotify.model;

import com.spotify.DAO.AlbunDAO;

/**
 *
 * @author Voronhuk
 */
public class Albun {

    private int id;
    private String nome;
    private String artista;

    public Albun(int id, String nome, String artista) {
        this.id = id;
        this.nome = nome;
        this.artista = artista;

    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getArtista() {
        return artista;
    }

    public boolean criarAlbun() {
        return new AlbunDAO().criarAlbun(this);
    }

}
