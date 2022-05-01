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

    public static void main(String[] args) {

        Controller controller = new Controller();
        controller.iniciarApp();

    }

    public void iniciarApp() {
        this.controller = new Controller();
        controller.abrirTelaLogin();
    }

    public void abrirTelaLogin() {
        JFrame login = factory.createView("login", controller);
    }

    public void abrirTelaCadastro() {
        JFrame cadastro = factory.createView("cadastro", controller);

    }

}
