/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package spotify_teste;

import com.spotify.DAO.Conexao;
import java.sql.Connection;

/**
 *
 * @author Voronhuk
 */
public class TesteConexao {

    /**
     * @param args the command line arguments
     */
    public boolean testar(String[] args) {
        // TODO code application logic here

        Conexao con = new Conexao();

        Connection conexao = con.conectar();

        if (conexao == null) {
            return false;
        } else {
            return true;

        }

    }

}
