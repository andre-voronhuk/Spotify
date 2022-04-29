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
    public static void main(String[] args) {
        ViewFactory factory = new ViewFactory();
        Controller controller = new Controller();
        
       JFrame login = factory.createView("login", controller);
       
    }
    
}
