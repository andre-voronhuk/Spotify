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

        try {

            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, usuario.getNome());
            ps.setBoolean(2, usuario.getFuncao());
            ps.setString(3, usuario.getLogin());
            ps.setString(4, senha);

            boolean result = ps.execute();

            ps.close();

            return result;

        } catch (Exception e) {
            System.out.println("ERRO AlbunDAO: " + e);
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

        String query = "SELECT  nome,funcao,login, senha FROM usuario WHERE id = ? ";

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
                return u;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("ERRO AO RECUPERAR:" + e);
        }
        System.out.println("VAZIO");
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
    public Usuario fazerLogin(String login, String senha) {
        return null;
    }

    @Override
    public boolean alterarSenha(String senhaAtual, String senhaNova) {

        return false;
    }
}
