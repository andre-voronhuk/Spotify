/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spotify.DAO;

import com.spotify.model.Musica;
import com.spotify.model.Playlist;
import java.sql.Connection;

/**
 *
 * @author Voronhuk
 */
public class PlaylistDAO {

    private Conexao con = new Conexao();
    private Connection conexao = con.conectar();

    public boolean adicionarMusica(Playlist playlist, Musica musica) {

        //adicionar id da playlist e id da musica na tabela playlist_musica
        return false;
    }

    public boolean removerMusica(Playlist playlist, Musica musica) {
        //remover a linha no banco que associa a musica á playlist
        return false;
    }

}
