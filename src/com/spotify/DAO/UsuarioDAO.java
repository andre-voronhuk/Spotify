/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spotify.DAO;

import com.spotify.DAO.Interfaces.IUsuarioDAO;
import com.spotify.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import others.FuncaoHash;

/**
 *
 * @author Voronhuk
 */
public class UsuarioDAO implements IUsuarioDAO {

    private final Conexao con = new Conexao();
    private final Connection conexao = con.conectar();

    @Override
    public boolean criarUsuario(Usuario usuario) {
        String query = "INSERT INTO usuario (nome,funcao,login, senha) VALUES (?,?,?,?)";

        String senha = FuncaoHash.gerarHash(usuario.getSenha());// cria hash com salt

        if (buscarUsuario(usuario.getLogin()) == null) {
            //a partir daqui so entra se o usuario nao existir
            try {

                PreparedStatement ps = conexao.prepareStatement(query);
                ps.setString(1, usuario.getNome());
                ps.setBoolean(2, usuario.getFuncao());
                ps.setString(3, usuario.getLogin());
                ps.setString(4, senha);

                boolean result = ps.execute();
                ps.close();
                return true;

            } catch (Exception e) {
                System.out.println("ERRO AlbunDAO: " + e);
                return false;
            }
        } else {
            System.out.println(usuario.getLogin() + " Ja existe");
            return false;
        }

    }

    @Override
    public List<Usuario> buscarUsuarios() {

        String query = "SELECT id, nome,funcao,login, senha FROM usuario";

        try {
            List<Usuario> usuarios = new ArrayList<>();
            PreparedStatement ps = conexao.prepareStatement(query);
            ResultSet dados = ps.executeQuery();

            while (dados.next()) {

                Usuario u = new Usuario();
                u.setId(dados.getInt(1));
                u.setNome(dados.getString(2));
                u.setFuncao(dados.getBoolean(3));
                u.setLogin(dados.getString(4));
                u.setSenha(dados.getString(5));
                usuarios.add(u);
            }
            return usuarios;
        } catch (SQLException e) {
            System.out.println("ERRO AO RECUPERAR:" + e);
        }

        return null;
    }

    public Usuario buscarUsuarios(int id) {

        String query = "SELECT  nome,funcao,login, senha,id FROM usuario WHERE id = ? ";

        try {

            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet dados = ps.executeQuery();

            if (dados.next()) {

                Usuario u = new Usuario();

                u.setNome(dados.getString(1));
                u.setFuncao(dados.getBoolean(2));
                u.setLogin(dados.getString(3));
                u.setSenha(dados.getString(4));
                u.setId(dados.getInt(5));
                return u;

            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("ERRO AO RECUPERAR:" + e);
        }

        return null;
    }

    public Usuario buscarUsuario(String login) {

        String query = "SELECT  nome,funcao,login, senha,id FROM usuario WHERE login = ? ";

        try {

            PreparedStatement ps = conexao.prepareStatement(query);

            ps.setString(1, login);
            ResultSet dados = ps.executeQuery();

            if (dados.next()) {

                Usuario u = new Usuario();
                u.setNome(dados.getString(1));
                u.setFuncao(dados.getBoolean(2));
                u.setLogin(dados.getString(3));
                u.setSenha(dados.getString(4));
                u.setId(dados.getInt(5));
                return u;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("ERRO AO RECUPERAR:" + e);
        }

        return null;
    }

    public boolean excluirUsuario(int id) {
        String query = "DELETE  FROM usuario WHERE id = ? ";

        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id);
            return ps.execute();
        } catch (SQLException ex) {
            System.out.println("Registro nao encontrado");
            return false;
        }

    }

    @Override
    public boolean fazerLogin(String login, String senha) {
        String query = "SELECT id FROM usuario WHERE login = ? AND senha = ?";

        senha = FuncaoHash.gerarHash(senha);

        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, login);
            ps.setString(2, senha);
            ResultSet result = ps.executeQuery();
            return result.next();

        } catch (SQLException ex) {
            System.out.println("Exception Login:" + ex);
            return false;
        }

    }

    @Override
    public boolean alterarSenha(String login, String senhaNova) {
        String query = "SELECT id FROM usuario WHERE login = ?";
        boolean logado = false;
        ResultSet result = null;
        int id = 0;

        senhaNova = FuncaoHash.gerarHash(senhaNova);
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, login);
            result = ps.executeQuery();

            if (result.next()) {
                logado = true;
                id = result.getInt(1);

            } else {
                return false;
            }

        } catch (SQLException ex) {
            System.out.println("Exception Alterar senha LOGIN:" + ex);

        }
        if (logado) {
            query = "UPDATE usuario SET senha=? WHERE id =?";
            try {
                PreparedStatement ps = conexao.prepareStatement(query);
                ps.setString(1, senhaNova);
                ps.setInt(2, id);
                boolean resultado = ps.execute();

                return true;

            } catch (SQLException ex) {
                System.out.println("SQL erro ao alterar senha");
                return false;
            }

        }
        return false;

    }

}
