package views;

import java.awt.CardLayout;
import java.awt.Component;
import java.io.File;

import javax.swing.JFrame;

import jaco.mp3.player.MP3Player;

public class MotherFrame extends JFrame {


    CardLayout layout;

    public MotherFrame() {
        setResizable(false);
        setTitle("Last of Us");
        setSize(1280, 720);
        layout = new CardLayout();
        getContentPane().setLayout(layout);
        playMusic();
    }


    @Override
    public Component add(Component comp) {
        return super.getContentPane().add(comp);
    }

    private void playMusic() {
        var player = new MP3Player(new File("GUC-GAME/src/assets/gameMusic.mp3"));
        player.play();
        player.setRepeat(true);
    }


}
