/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package spotify_poo;

import com.spotify.DAO.Conexao;
import java.sql.Connection;

/**
 *
 * @author Voronhuk
 */
public class Spotify_POO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Conexao con = new Conexao();
      
            Connection conexao = con.conectar();
          

            if (conexao == null) {
                System.out.println("Nao conectou");
            } else {
                System.out.println("Conectou");

            }

        
    }

}
