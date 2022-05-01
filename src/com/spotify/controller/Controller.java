/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spotify.controller;

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
    static JFrame login;
    static JFrame cadastro;

    /**
     * as variaveis estao como static para que exista somente uma instancia
     * rodando dentro do controller
     */
    public static void main(String[] args) {

        Controller controller = new Controller();
        controller.iniciarApp();

    }

    public void iniciarApp() {
        this.controller = new Controller();
        abrirTelaLogin();
    }

    public void abrirTelaLogin() {
        this.login = factory.createView("login", this);

    }

    public void abrirTelaCadastro() {
        cadastro = factory.createView("cadastro", this);
        login.dispose();
    }

}
