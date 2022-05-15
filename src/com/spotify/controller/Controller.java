/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spotify.controller;

import com.spotify.DAO.MusicaDAO;
import com.spotify.DAO.PlaylistDAO;
import com.spotify.DAO.UsuarioDAO;
import com.spotify.model.Musica;
import com.spotify.model.Usuario;
import com.spotify.view.TelaLogin;
import com.spotify.view.ViewFactory;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import spotify_teste.TesteConexao;

/**
 *
 * @author Voronhuk
 */
public class Controller {
    
    Controller controller = this;
    ViewFactory factory = new ViewFactory();
    JFrame telaLogin;
    JFrame telaCadastro;
    JFrame telaAlterarSenha;
    JFrame telaAnterior;
    JFrame nova;
    JFrame telaAtual;
    Usuario user = new Usuario();
    
    public void main() {
        boolean con = new TesteConexao().testar(null);
        if (!con) {
            JFrame a;
            a = new ViewFactory().createView("nada", null);
            
            JOptionPane.showMessageDialog(
                    a, "Erro de conexão com banco de dados", "Conexao", 2);
            a.dispose();
            
        } else {
            
            iniciarApp();
        }
        
    }
    
    public void iniciarApp() {
        
        this.abrirTelaLogin();
    }
    
    public void abrirTelaLogin() {
        telaLogin = new TelaLogin(controller);
        this.telaAtual = telaLogin;
        telaLogin = factory.createView("login", this);
        
    }
    
    public void abrirTela(JFrame telaAtual, String nomeTela) {
        this.telaAtual = telaAtual;
        nova = factory.createView(nomeTela, this);
        
        if (this.telaAtual != null) {
            this.telaAtual.dispose();
        }
    }
    
    public boolean fazerLogin(String login, String senha) {
        boolean result = new UsuarioDAO().fazerLogin(login, senha);
        
        if (result) {
            
            this.user = new UsuarioDAO().buscarUsuario(login);
            
            this.abrirTela(telaAtual, "home");
            //salva os dados do usuario logado
            return true;
        } else {
            return false;
        }
    }
    
    public boolean cadastrarUsuario(Usuario usuario) {
        boolean result = new UsuarioDAO().criarUsuario(usuario);
        
        return result;
    }
    
    public Usuario getUser() {
        return this.user;
    }
    
    public boolean criarPlaylist(String nome) {
        return new PlaylistDAO().criarPlaylist(nome, this.user.getId());
        
    }
    
    public List<Musica> buscarMusicasPlaylist(String nomePlaylist) {
        return new PlaylistDAO().getMusicas(nomePlaylist, this.user.getId());
    }

    public Musica buscarMusica(String nome) {
        return new MusicaDAO().buscarMusica(nome);
    }
    
}
