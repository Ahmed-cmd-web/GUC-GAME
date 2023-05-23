package views;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class HeroSelectionScreen extends JFrame {

    public HeroSelectionScreen() {
        this.setTitle("Welcome");
        this.setSize(960, 540);
        this.setResizable(false);
        this.getContentPane().setSize(960, 540);
        var panel = new JPanel(new GridLayout(3, 1,0,20));
        panel.setSize(500, 400);
        panel.setBackground(Color.BLACK);
        var heroRow = new Row("Fighters", new String[] { "joel.png", "David.png" });
        var heroRow2 = new Row("Explorers", new String[] {"Tess.png", "Riley.png", "Tommy.png"});
        var heroRow3 = new Row("Medics", new String[] { "Ellie.png", "Bill.png",
                "Henrry.png"});

        this.setLayout(null);
        panel.add(heroRow);
        panel.add(heroRow2);
        panel.add(heroRow3);
        this.getContentPane().add(panel);
        this.getContentPane().setBackground(Color.black);

    }



    private JButton HeroButton(String img, int x, int y, int width, int height) {
        JButton button = null;
        var scaledImage = new ImageIcon(img).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button = new JButton();
        button.setIcon(new ImageIcon(scaledImage));
        button.setBorder(BorderFactory.createEmptyBorder());
        return button;
    }


    class Row extends JPanel {
        public Row(String title, String[] heros) {
            super();
            setLayout(new GridLayout(2, 1));
            this.setSize(300, 250);
            var label = new JLabel(title);
            label.setLayout(new FlowLayout(FlowLayout.LEFT));
            label.setSize(300, 300);
            label.setForeground(Color.white);
            this.add(label);
            var imagesRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
            imagesRow.setSize(this.getWidth(), this.getHeight());
            imagesRow.setBackground(Color.BLACK);
            for (var hero : heros) {
                var button = HeroButton("GUC-Game/src/assets/heroFaces/" + hero, 0, 0, 50, 50);
                button.setLayout(null);
                imagesRow.add(button);
            }
            this.add(imagesRow);
            this.setBackground(Color.BLACK);
        }
    }
}
