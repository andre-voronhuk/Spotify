/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spotify.DAO;

import com.spotify.model.Musica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Voronhuk
 */
public class MusicaDAO {

    private Conexao con = new Conexao();
    private Connection conexao = con.conectar();

    public boolean criarMusica(Musica musica) {
        String query = "INSERT INTO musica (nome,artista,caminho, estilo) VALUES (?,?,?,?)";

        try {

            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, musica.getNome());
            ps.setString(2, musica.getArtista());
            ps.setString(3, musica.getCaminho());
            ps.setString(4, musica.getEstilo());

            boolean result = ps.execute();

            ps.close();

            return result;

        } catch (Exception e) {
            System.out.println("ERRO MusicaDao: " + e);
            return false;
        }

    }

    public boolean excluirMusica(int id) {
        String query = "DELETE FROM musica WHERE id = ?;";
        try {
            boolean result;
            try ( PreparedStatement ps = conexao.prepareStatement(query)) {
                ps.setInt(1, id);
                result = ps.execute();
            }
            return result;

        } catch (SQLException e) {

            System.out.println("Erro musica" + e);

        }
        return false;
    }
}
