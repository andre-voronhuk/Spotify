/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spotify.model;

/**
 *
 * @author Voronhuk
 */
public class Playlist {

    private final int id;
    private final String nome;
    private final Usuario dono;

    public Playlist(int id, String nome, Usuario dono) {
        this.id = id;
        this.nome = nome;

        this.dono = dono;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Usuario getDono() {
        return dono;
    }

}
