package com.spotify.view;

import com.spotify.controller.Controller;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;

/**
 *
 * @author Voronhuk
 */
public class ViewFactory {

    Controller controller;

    public ViewFactory() {

    }

    public JFrame createView(String name, Controller controller) {
        this.controller = controller;
        JFrame frame;
     

        switch (name) {
            case "login":
                frame = new TelaLogin(controller);
                break;

            case "home":
                frame = new TelaInicial(controller);
                break;

            case "cadastro":
                frame = new TelaCadastro(controller);
                break;
            
            case "senha":
                frame = new TelaAlterarSenha(controller);
                break;

            default:
                frame = new JFrame();
                break;

        }

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
      

        return frame;
    }

   

}
