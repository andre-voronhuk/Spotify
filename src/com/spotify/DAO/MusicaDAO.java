/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spotify.DAO;

import com.spotify.model.Musica;
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
public class MusicaDAO {
    
    private Conexao con = new Conexao();
    private Connection conexao = con.conectar();
    
    public boolean criarMusica(Musica musica) {
        String query = "INSERT INTO musica (nome,artista,caminho, estilo, albun_id) VALUES (?,?,?,?,?)";
        
        try {
            
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, musica.getNome());
            ps.setString(2, musica.getArtista());
            ps.setString(3, musica.getCaminho());
            ps.setString(4, musica.getEstilo());
            ps.setInt(5, musica.getAlbunId());
            
            boolean result = ps.execute();
            
            ps.close();
            
            return true;
            
        } catch (Exception e) {
            System.out.println("ERRO MusicaDao: " + e);
            return false;
        }
        
    }
    
    public boolean excluirMusica(int id) {
        String query = "DELETE FROM musica WHERE id = ?";
        try {
            boolean result;
            try ( PreparedStatement ps = conexao.prepareStatement(query)) {
                ps.setInt(1, id);
                result = ps.execute();
                ps.close();
            }
            return true;
            
        } catch (SQLException e) {
            
            System.out.println("Erro musica" + e);
            
        }
        return false;
    }
    
    public Musica buscarMusica(String nome) {
        String query = "SELECT * FROM musica WHERE nome = ?";
        Musica musica = new Musica();
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, nome);
            
            ResultSet result = ps.executeQuery();
            
            result.next();
            
            musica.setId(result.getInt("id"));
            musica.setNome(result.getString("nome"));
            musica.setArtista(result.getString("artista"));
            musica.setCaminho(result.getString("caminho"));
            musica.setEstilo(result.getString("estilo"));
            musica.setAlbunId(result.getInt("albun_id"));
            ps.close();
            return musica;
        } catch (SQLException e) {
            return null;
        }
        
    }
    
    public List<Musica> buscarMusicas() {
        String query = "SELECT id,nome,artista,caminho,estilo,albun_id FROM musica";
        List<Musica> musicas = new ArrayList<>();
        
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            
            ResultSet result = ps.executeQuery();
            
            while (result.next()) {
                Musica musica = new Musica();
                
                musica.setId(result.getInt("id"));
                musica.setNome(result.getString("nome"));
                musica.setArtista(result.getString("artista"));
                musica.setCaminho(result.getString("caminho"));
                musica.setEstilo(result.getString("estilo"));
                musica.setAlbunId(result.getInt("albun_id"));
                
                musicas.add(musica);
            }
            return musicas;
        } catch (SQLException e) {
            return musicas;
        }
        
    }
    
    public boolean alterarMusica(Musica musica) {
        
        String query = "UPDATE musica SET nome=?, artista=?, caminho=?,estilo=?,albun_id=? WHERE id=?";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, musica.getNome());
            ps.setString(2, musica.getArtista());
            ps.setString(3, musica.getCaminho());
            ps.setString(4, musica.getEstilo());
            ps.setInt(5, musica.getAlbunId());
            ps.setInt(6, musica.getId());
            ps.execute();
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println("SQL erro ao alterar musica");
            return false;
        }
    }
    
}
