/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spotify.model;

/**
 *
 * @author Voronhuk
 */
public class Musica {
    private int id;
    private String nome;
    private String artista;
    private String caminho;
    private String estilo;
    private int albunId;

    public Musica(int id, String nome, String artista, String caminho,int albunId, String estilo) {
        this.id = id;
        this.nome = nome;
        this.artista = artista;
        this.caminho = caminho;
        this.estilo = estilo;
        this.albunId = albunId;
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

    public String getCaminho() {
        return caminho;
    }

    public String getEstilo() {
        return estilo;
    }

    public int getAlbunId() {
        return albunId;
    }
    
    

}
