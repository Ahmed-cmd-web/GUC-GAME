package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JFrame;

import jaco.mp3.player.MP3Player;



public class StartScreen extends JFrame {

    public StartScreen() {
        this.setTitle("Welcome");
        this.setSize(960, 540);
        this.setResizable(false);
        JButton button = new JButton("Start Game");
        this.getContentPane().setSize(960, 540);
        button.setBounds(this.getContentPane().getWidth()/2-50, this.getHeight()*2/3, 100, 100);
        button.setBackground(Color.YELLOW);
        this.setLayout(null);
        this.getContentPane().add(button);
        // this.playMusic();
    }


    private void playMusic() {
        var player = new MP3Player(new File("GUC-GAME/src/assets/gameMusic.mp3"));
        player.play();
        player.setRepeat(true);
    }

    @Override
    public void paint(Graphics g) {
        try {
            super.paintComponents(g);
            Image image = ImageIO.read(new File("GUC-GAME/src/assets/startScreen.jpg"))
            .getScaledInstance(this.getContentPane().getWidth(), this.getHeight(), Image.SCALE_FAST);
            g.drawImage(image, 0, 0, this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
