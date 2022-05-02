/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spotify.DAO;

import com.spotify.model.Musica;
import com.spotify.model.Playlist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Voronhuk
 */
public class PlaylistDAO {

    private final Conexao con = new Conexao();
    private final Connection conexao = con.conectar();

    public boolean adicionarMusica(Playlist playlist, Musica musica) {

        //adicionar id da playlist e id da musica na tabela playlist_musica
        String query = "INSERT INTO playlist_musica (musica_id,playlist_id) VALUES (?,?)";

        try {

            PreparedStatement ps;
            ps = conexao.prepareStatement(query);
            ps.setInt(1, musica.getId());
            ps.setInt(2, playlist.getId());

            boolean result = ps.execute();

            ps.close();

            return result;
        } catch (SQLException e) {
            System.out.println("ERRO playlistDAO: " + e);
            return false;
        }

    }

    public void removerMusica(Playlist playlist, Musica musica) {
        //remover a linha no banco que associa a musica á playlist

        String query = "SELECT id from playlist_musica WHERE musica_id = ? AND playlist_id = ? LIMIT 1";

        try {
            // ATENCAO! codigo potencialmente bugado, mas nao tenho como testar ele agora.

            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, musica.getId());
            ps.setInt(2, playlist.getId());

            ResultSet dados = ps.executeQuery();
            while (dados.next()) {

                query = "DELETE FROM playlist_musica WHERE id = ?";
                ps = conexao.prepareStatement(query);
                ps.setInt(1, dados.getInt("id"));
                ps.execute();
                ps.close();
            }

        } catch (Exception e) {
            System.out.println("ERRO Delete de musica em playlist: " + e);

        }

    }

}
