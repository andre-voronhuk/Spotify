/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spotify.controller;

import com.spotify.view.TelaCadastro;
import com.spotify.view.TelaLogin;
import com.spotify.view.ViewFactory;
import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author Voronhuk
 */
public class Controller {

    Controller controller;
    ViewFactory factory = new ViewFactory();
    static JFrame telaLogin;
    JFrame telaCadastro;
    JFrame telaAnterior;

    public static void main(String[] args) {

        Controller controller = new Controller();
        controller.iniciarApp();

    }

    public void iniciarApp() {
        this.controller = new Controller();
        controller.abrirTelaLogin();
    }

    public void abrirTelaLogin() {
        telaLogin = new TelaLogin(controller);
        telaLogin = factory.createView("login", controller);
        telaLogin.setVisible(true);
    }

    public void abrirTelaCadastro() {
        telaCadastro = new TelaCadastro(controller);
        telaCadastro = factory.createView("cadastro", controller);
        telaCadastro.setVisible(true);
        telaLogin.setVisible(false);
        System.out.println("Abriu");

    }

}
