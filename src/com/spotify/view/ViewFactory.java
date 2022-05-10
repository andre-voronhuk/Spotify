package com.spotify.view;

import com.spotify.controller.Controller;
import java.awt.Color;
import others.ComponentFontFormatter;
import javax.swing.JFrame;
import others.ComponentesSwing;

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
        Color minhaCor = new Color(60, 63, 65);
        Color colorLabel = new Color(187, 187, 187);

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

        frame.setVisible(true);
        frame.getContentPane().setBackground(minhaCor);
        ComponentFontFormatter cff = new ComponentFontFormatter(frame);
        cff.format(colorLabel, ComponentesSwing.JLABEL);
        cff.format(colorLabel, ComponentesSwing.JBUTTON);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return frame;
    }

}
