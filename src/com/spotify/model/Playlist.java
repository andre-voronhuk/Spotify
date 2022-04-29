/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spotify.model;

import com.spotify.DAO.PlaylistDAO;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Voronhuk
 */
public class Playlist {

    private int id;
    private String nome;
    private List musicas;
    private Usuario dono;

    public Playlist(int id, String nome, Usuario dono) {
        this.id = id;
        this.nome = nome;
        this.musicas = new ArrayList<Musica>();
        this.dono = dono;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List getMusicas() {
        return musicas;
    }

    public Usuario getDono() {
        return dono;
    }

    public boolean adicionarMusica(Musica musica) {
        return new PlaylistDAO().adicionarMusica(this, musica);

    }

    public boolean removerMusica(Musica musica) {
        return new PlaylistDAO().removerMusica(this, musica);

    }

}
