/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spotify.DAO.Interfaces;

import com.spotify.model.Usuario;
import java.util.List;

/**
 *
 * @author Voronhuk
 */
public interface IUsuarioDAO {

    public boolean criarUsuario(Usuario usuario);

    public List<Usuario> buscarUsuarios();

    public boolean alterarSenha(String login, String senhaNova);

    public boolean fazerLogin(String login, String senha);

}
