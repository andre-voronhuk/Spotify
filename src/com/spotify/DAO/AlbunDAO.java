package com.spotify.DAO;

import com.spotify.model.Albun;
import com.spotify.model.Usuario;
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
public class AlbunDAO {

    private Conexao con = new Conexao();
    private Connection conexao = con.conectar();

    public boolean criarAlbun(Albun albun) {

        String query = "INSERT INTO albun (nome, artista) VALUES (?,?)";

        try {

            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, albun.getNome());
            ps.setString(2, albun.getArtista());
            ps.execute();
            ps.close();

            return true;

        } catch (Exception e) {
            System.out.println("ERRO AlbunDAO: " + e);
            return false;
        }

    }

    public boolean excluirAlbun(int id) {
        String query = "DELETE FROM albun WHERE id = ?;";

        try {

            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id);

            boolean result = ps.execute();

            ps.close();

            return result;

        } catch (Exception e) {
            System.out.println("ERRO ao excluir albun: " + e);
            return false;
        }

    }

    public List<Albun> buscarAlbuns() {

        String query = "SELECT id, nome,artista FROM albun";

        try {
            List<Albun> albuns = new ArrayList<>();
            PreparedStatement ps = conexao.prepareStatement(query);
            ResultSet dados = ps.executeQuery();

            while (dados.next()) {

                Albun u = new Albun();
                u.setId(dados.getInt(1));
                u.setNome(dados.getString(2));
                u.setArtista(dados.getString(3));

                albuns.add(u);
            }
            return albuns;
        } catch (SQLException e) {
            System.out.println("ERRO AO RECUPERAR:" + e);
        }

        return null;

    }
}
