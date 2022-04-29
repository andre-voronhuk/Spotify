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
//        Usuario user = new Usuario(0, "Joao Teste", false, "jteste", "2220");
//        usuarioDao.criarUsuario(user);

        UsuarioDAO usuarioDao = new UsuarioDAO();

        usuarioDao.excluirUsuario(2);
        List<Usuario> userBuscar = usuarioDao.buscarUsuarios();

        if (userBuscar == null) {
            System.out.println("Sem registros");

        } else {
            for (Usuario usuario : userBuscar) {
                System.out.println(usuario.getNome());
            }

        }

    }

}
