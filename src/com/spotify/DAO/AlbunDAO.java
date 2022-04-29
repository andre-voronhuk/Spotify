package com.spotify.DAO;

import com.spotify.model.Albun;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Voronhuk
 */
public class AlbunDAO {

    private Conexao con = new Conexao();
    private Connection conexao = con.conectar();

    public boolean criarAlbun(Albun albun) {

        String query = "INSERT INTO albun (nome, artista) VALUES (?,?)";

        try {
            
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, albun.getNome());
            ps.setString(2, albun.getArtista());
            boolean result = ps.execute();
            
            ps.close();
            
            return result;
            
        } catch (Exception e) {
            System.out.println("ERRO AlbunDAO: " + e);
            return false;
        }

    }

    public boolean excluirAlbun(int id) {
        return false;
    }

}
