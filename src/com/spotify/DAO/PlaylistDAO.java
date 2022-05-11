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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Voronhuk
 */
public class PlaylistDAO {

    private final Conexao con = new Conexao();
    private final Connection conexao = con.conectar();

    public boolean criarPlaylist(String nome, int dono_id) {

        String query = "INSERT INTO playlist (nome,dono_id) VALUES (?,?)";

        try {

            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, nome);
            ps.setInt(2, dono_id);
            boolean result = ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println("ERRO playlist: " + e);
            return false;
        }
    }

    public List<Playlist> getPlaylists(int dono_id) {
        String query = "SELECT nome,dono_id from playlist where dono_id = ? ";

        List<Playlist> playlists = new ArrayList<>();
        PreparedStatement ps;
        try {
            ps = conexao.prepareStatement(query);
           ps.setInt(1, dono_id);
            ResultSet dados = ps.executeQuery();

            while (dados.next()) {

                Playlist p = new Playlist();
                p.setNome(dados.getString(1));
                p.setDono(dados.getInt(2));

                playlists.add(p);
            }
            return playlists;
        } catch (SQLException ex) {
            return null;
        }

    }

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
