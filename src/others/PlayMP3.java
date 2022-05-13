/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package others;

/**
 *
 * @author Voronhuk
 */
import jaco.mp3.player.MP3Player;

import java.io.File;

public class PlayMP3 extends Thread{

    public PlayMP3() {
        super();
    }

    @Override
    
    public void run() {
        try {

            File f = new File("musicas/8.mp3");

            MP3Player mp3Player = new MP3Player(f);
            mp3Player.play();

            //while (!mp3Player.isStopped()) {
            //    //Thread.sleep(5000);
            //}
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        
    }
}