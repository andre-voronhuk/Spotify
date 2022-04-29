/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spotify.DAO;

import com.spotify.model.Musica;
import java.sql.Connection;

/**
 *
 * @author Voronhuk
 */
public class MusicaDAO {

    private Conexao con = new Conexao();
    private Connection conexao = con.conectar();

    public boolean criarMusica(Musica musica) {

        return false;
    }

    public boolean excluirMusica(int id) {

        return false;
    }

}
