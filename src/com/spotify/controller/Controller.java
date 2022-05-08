/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spotify.controller;

import com.spotify.DAO.UsuarioDAO;
import com.spotify.model.Usuario;
import com.spotify.view.TelaLogin;
import com.spotify.view.ViewFactory;
import javax.swing.JFrame;

/**
 *
 * @author Voronhuk
 */
public class Controller {

    Controller controller;
    ViewFactory factory = new ViewFactory();
    JFrame telaLogin;
    JFrame telaCadastro;
    JFrame telaAlterarSenha;
    JFrame telaAnterior;
    JFrame nova;
    JFrame telaAtual;

    public static void main(String[] args) {

        Controller controller = new Controller();
        controller.iniciarApp(controller);

    }

    public void iniciarApp(Controller controller) {
        this.controller = controller;
        controller.abrirTelaLogin();
    }

    public void abrirTelaLogin() {
        telaLogin = new TelaLogin(controller);
        this.telaAtual = telaLogin;
        telaLogin = factory.createView("login", controller);

    }

    public void abrirTela(JFrame telaAtual, String nomeTela) {
        this.telaAtual = telaAtual;
        nova = factory.createView(nomeTela, controller);
        if (this.telaAtual != null) {

            this.telaAtual.dispose();
        }
    }

    public boolean fazerLogin(String login, String senha) {
        boolean result = new UsuarioDAO().fazerLogin(login, senha);

        if (result) {
            this.abrirTela(telaAtual, "home");
            return true;
        } else {
            return false;

        }

    }

    public boolean cadastrarUsuario(Usuario usuario) {
        
        
        return controller.cadastrarUsuario(usuario);
    }

}
