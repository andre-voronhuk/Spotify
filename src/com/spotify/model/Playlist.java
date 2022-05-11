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

    private int id;
    private String nome;
    private int dono_id;

    public Playlist() {
    }

    public Playlist(String nome, int dono_id) {
        this.id = 0;
        this.nome = nome;
        this.dono_id = dono_id;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getDono() {
        return dono_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDono(int dono_id) {
        this.dono_id = dono_id;
    }

}
