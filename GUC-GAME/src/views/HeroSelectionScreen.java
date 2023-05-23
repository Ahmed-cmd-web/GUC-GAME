package views;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class HeroSelectionScreen extends JFrame {

    public HeroSelectionScreen() {
        this.setTitle("Welcome");
        this.setSize(960, 540);
        this.setResizable(false);
        this.getContentPane().setSize(960, 540);
        var panel = new JPanel(new GridLayout(1, 8,20,0));
        panel.setSize(960, 50);


        String heros[] = {"joel.png","David.png","Tess.png","Riley.png","Tommy.png","Ellie.png","Bill.png","Henrry.png"};
        int i=0;
        for (var hero : heros) {
            var button = HeroButton("GUC-Game/src/assets/heroFaces/" + hero, i, 0, 50, 50);
            panel.add(button);
            i += 150;
        }
        this.setLayout(null);
        this.getContentPane().add(panel);

    }



    private JButton HeroButton(String img, int x, int y, int width, int height) {
        JButton button = null;
        var scaledImage = new ImageIcon(img).getImage().getScaledInstance(width, height, Image.SCALE_FAST);
        button = new JButton(new ImageIcon(scaledImage));
        button.setBounds(x, y, width, height);
        // button.setBorder(new LineBorder(Color.BLUE, 1, true));
        return button;
    }


    // static class Row {
    //     public Row(String hero) {

    //     }
    // }

}
