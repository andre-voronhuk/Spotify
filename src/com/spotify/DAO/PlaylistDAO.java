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


/**
 *
 * @author Voronhuk
 */
public class PlaylistDAO {

    private final Conexao con = new Conexao();
    private final Connection conexao = con.conectar();

    public boolean criarPlaylist(String nome, int dono_id) {
        String query = "INSERT INTO playlist (nome,dono_id) VALUES (?,?)";

        if (nome.equals("") || nome.equals(" ")) {
            return false;
        }

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
        String query = "SELECT id,nome,dono_id from playlist where dono_id = ? ";

        List<Playlist> playlists = new ArrayList<>();
        PreparedStatement ps;
        try {
            ps = conexao.prepareStatement(query);
            ps.setInt(1, dono_id);
            ResultSet dados = ps.executeQuery();

            while (dados.next()) {

                Playlist p = new Playlist();
                p.setId(dados.getInt(1));
                p.setNome(dados.getString(2));
                p.setDono(dados.getInt(3));

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

             ps.execute();

            ps.close();

            return true;
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

    public List<Musica> getMusicas(String nomePlaylist, int dono_id) {
        boolean todas = false;
        String query = "SELECT m.id,m.nome, m.artista, m.caminho, m.estilo,m.albun_id\n"
                + "FROM playlist as p \n"
                + "INNER JOIN playlist_musica as pm\n"
                + "ON pm.playlist_id = p.id\n"
                + "INNER JOIN musica as m \n"
                + "ON m.id = pm.musica_id\n"
                + "where p.nome=? and p.dono_id=?;";

        if (nomePlaylist == "Todas as Musicas") {
            todas = true;
            query = "SELECT m.id,m.nome, m.artista, m.caminho, m.estilo,m.albun_id\n"
                    + "FROM musica m;";

        }
        List<Musica> musicas = new ArrayList<>();
        PreparedStatement ps;
        try {
            ps = conexao.prepareStatement(query);
            if (!todas) {
                ps.setString(1, nomePlaylist);
                ps.setInt(2, dono_id);

            }

            ResultSet dados = ps.executeQuery();

            while (dados.next()) {

                Musica m = new Musica();
                m.setId(dados.getInt(1));
                m.setNome(dados.getString(2));
                m.setArtista(dados.getString(3));
                m.setCaminho(dados.getString(4));
                m.setEstilo(dados.getString(5));
                m.setAlbunId(dados.getInt(6));

                musicas.add(m);
            }
            return musicas;
        } catch (SQLException ex) {
            return null;
        }

    }

    public boolean excluirPlaylist(String nome, int dono_id) {
        String query = "DELETE FROM playlist WHERE nome = ? and dono_id =?";
        PreparedStatement ps;
        try {
            ps = conexao.prepareStatement(query);
            ps.setString(1, nome);
            ps.setInt(2, dono_id);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }

    }

    public Playlist buscarPlaylist(String nomePlaylist, int dono_id) {
        List<Playlist> playlists = getPlaylists(dono_id);
        Playlist playlistSelecionada = null;

        for (Playlist playlist : playlists) {
            if (playlist.getNome().equals(nomePlaylist)) {
                playlistSelecionada = playlist;
                return playlistSelecionada;
            }

        }
        if (playlistSelecionada == null) {
            System.out.println("Playlist nao encontrada");
            return null;
        }
        return null;

    }

}
