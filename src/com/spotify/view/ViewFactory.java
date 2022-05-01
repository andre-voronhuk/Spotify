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
        System.out.println(name);
        switch (name) {
            case "login":
                frame = new TelaLogin(controller);
                break;

            case "home":
                frame = new TelaLogin(controller);
                break;
            
            case "cadastro":
                
                frame = new TelaCadastro(controller);
               
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
