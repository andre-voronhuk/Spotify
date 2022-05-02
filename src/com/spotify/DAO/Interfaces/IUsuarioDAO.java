/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spotify.DAO.Interfaces;

import com.spotify.exceptions.PersistenceException;
import com.spotify.model.Usuario;
import java.util.List;

/**
 *
 * @author Voronhuk
 */
public interface IUsuarioDAO {

    public boolean criarUsuario(Usuario usuario) throws PersistenceException;

    public List<Usuario> buscarUsuarios() throws PersistenceException;

    public boolean alterarSenha(String senhaAtual, String senhaNova) throws PersistenceException;

    public Usuario fazerLogin(String login, String senha) throws PersistenceException;

}
