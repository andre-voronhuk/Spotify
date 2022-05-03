/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package others;

import com.spotify.DAO.AlbunDAO;
import com.spotify.DAO.UsuarioDAO;
import com.spotify.model.Albun;
import com.spotify.model.Usuario;
import java.util.List;

/**
 *
 * @author Voronhuk
 */
public class testeMain {

    public static void main(String[] args) {

//        AlbunDAO dadosAlbun = new AlbunDAO();
//        Albun albun = new Albun(0, "The Girl", "Flux Zone");
//        Albun albun2 = new Albun(0, "Black Sabbath 2009 Remaster", "Black Sabbath");
//        dadosAlbun.criarAlbun(albun);
//        dadosAlbun.criarAlbun(albun2);
//
//        usuarioDao.criarUsuario(user);
//        usuarioDao.excluirUsuario(2);
//        List<Usuario> userBuscar = usuarioDao.buscarUsuarios();
        //  Usuario user = new Usuario(0, "Conta Admin", true, "root", "root");
        //   usuarioDao.criarUsuario(user);
        UsuarioDAO usuarioDao = new UsuarioDAO();

        boolean resultado = usuarioDao.fazerLogin("root", "root");
        
        if (!resultado) {
            System.out.println("errou");
        } else {

            System.out.println(resultado);
        }

    }
}
