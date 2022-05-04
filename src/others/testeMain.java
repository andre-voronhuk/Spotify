/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package others;

import com.spotify.DAO.AlbunDAO;
import com.spotify.DAO.UsuarioDAO;
import com.spotify.model.Administrador;
import com.spotify.model.Albun;
import com.spotify.model.Musica;
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
        UsuarioDAO usuarioDao = new UsuarioDAO();

//        usuarioDao.excluirUsuario(2);
//        List<Usuario> userBuscar = usuarioDao.buscarUsuarios();
        Administrador admin = new Administrador();
        Usuario user = new Usuario(0, "Joao", true, "teste@gmail.com", "1111");
//        Musica musica = new Musica(0,
//                "N.I.B",
//                "Black Sabbath",
//                "/music/8.mp3",
//                0, "Rock"
//        );
//        admin.criarMusica(musica);

         List<Usuario> usuarios =  usuarioDao.buscarUsuarios();
            for (Usuario usuario : usuarios) {
                System.out.println(usuario.getNome());
            
        }
//        boolean resultado = usuarioDao.alterarSenha("root", "root", "root");
//        System.out.println(resultado);
    }
}
