/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spotify.exceptions;

import javax.swing.JOptionPane;

/**
 *
 * @author Voronhuk
 */
public class PersistenceException extends Exception {
    
    public PersistenceException(String mensagem) {
        super(mensagem);
        JOptionPane.showMessageDialog(null, mensagem);
    }
    
}
