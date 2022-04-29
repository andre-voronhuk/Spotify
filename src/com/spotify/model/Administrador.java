/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spotify.model;

import com.spotify.DAO.AlbunDAO;
import com.spotify.DAO.MusicaDAO;
import com.spotify.DAO.UsuarioDAO;

/**
 *
 * @author Voronhuk
 */
public class Administrador extends Usuario {

    public Administrador() {
        super();
    }

    public boolean criarMusica(Musica musica) {

        return new MusicaDAO().criarMusica(musica);
    }

    public boolean excluirMusica(int id) {

        return new MusicaDAO().excluirMusica(id);
    }

    public boolean criarAlbun(Albun albun) {
        return new AlbunDAO().criarAlbun(albun);
    }

    public boolean excluirAlbun(int id) {
        return new AlbunDAO().excluirAlbun(id);
    }

    public boolean excluirUsuario(int id) {
        return new UsuarioDAO().excluirUsuario(id);
    }

}
