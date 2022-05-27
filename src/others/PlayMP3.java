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

public class PlayMP3 extends Thread {

    public static boolean tocando = false;
    public static MP3Player mp3Player = new MP3Player();

    public PlayMP3() {
        super();
    }

    @Override

    public void run() {
    }

    public void play(String caminho) {

        try {

            File f = new File(caminho);

            if (tocando == false) {
                mp3Player.addToPlayList(f);
                tocando = true;

                mp3Player.play();

            } else {
                tocando = false;
                mp3Player.pause();

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public void next() {

        mp3Player.skipForward();

    }

    public void back() {
        mp3Player.skipBackward();
    }

}
