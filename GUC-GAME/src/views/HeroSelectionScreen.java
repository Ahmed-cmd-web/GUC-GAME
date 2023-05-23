package views;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class HeroSelectionScreen extends JFrame implements ActionListener {
    private JLabel middleColumn;

    @Override
    public void actionPerformed(ActionEvent e) {
        var source = ((JButton) e.getSource()).getName().replace("png", "jpeg");
        middleColumn.setIcon(new ImageIcon(new ImageIcon("GUC-Game/src/assets/heros/" + source)
                .getImage().getScaledInstance(300, 500, Image.SCALE_SMOOTH)));
    }

    public HeroSelectionScreen() {
        this.setTitle("Select a Hero");
        this.setSize(1000, 540);
        this.setResizable(false);
        this.getContentPane().setSize(1000, 540);
        this.getContentPane().setLayout(new GridLayout(1, 3, 0, 0));
        this.getContentPane().setBackground(Color.BLACK);


        middleColumn = new JLabel();
        middleColumn.setIcon(new ImageIcon(new ImageIcon("GUC-Game/src/assets/heros/Bill.jpeg")
        .getImage().getScaledInstance(300, 500, Image.SCALE_SMOOTH)));
        middleColumn.setSize(300, 540);
        middleColumn.setBackground(Color.WHITE);

        var leftColumn = new JPanel(new GridLayout(3, 1, 0, 0));
        leftColumn.setBorder(new EmptyBorder(10, 10, 10, 10));
        leftColumn.setSize(400, 200);
        leftColumn.setBackground(Color.black);

        var heroRow = new Row("Fighters", new String[] { "Joel.png", "David.png" },this);
        var heroRow2 = new Row("Explorers", new String[] {"Tess.png", "Riley.png", "Tommy.png"},this);
        var heroRow3 = new Row("Medics", new String[] { "Ellie.png", "Bill.png","Henrry.png"},this);


        leftColumn.add(heroRow);
        leftColumn.add(heroRow2);
        leftColumn.add(heroRow3);


        this.getContentPane().add(leftColumn);
        this.getContentPane().add(middleColumn);
        this.getContentPane().add(new DescriptionPanel("Joel david elsayed", "Fighter"));

    }

    class Row extends JPanel {
        public Row(String title, String[] heros, ActionListener action) {
            super();
            setLayout(new GridLayout(2, 1));
            this.setSize(300, 250);
            var label = new JLabel(title);
            label.setLayout(new FlowLayout(FlowLayout.LEFT));
            label.setSize(300, 10);
            label.setForeground(Color.white);
            label.setFont(new Font("Monospaced", Font.ITALIC, 18));
            this.add(label);
            var imagesRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
            imagesRow.setSize(this.getWidth(), this.getHeight());
            imagesRow.setBackground(Color.BLACK);
            for (var hero : heros) {
                var button = HeroButton(hero, 0, 0, 80, 80, action);
                button.setLayout(null);
                imagesRow.add(button);
            }
            this.add(imagesRow);
            this.setBackground(Color.BLACK);
        }

        private JButton HeroButton(String img, int x, int y, int width, int height, ActionListener action) {
            JButton button = null;
            var scaledImage = new ImageIcon("GUC-Game/src/assets/heroFaces/" + img).getImage().getScaledInstance(width,
                    height, Image.SCALE_FAST);
            button = new JButton();
            button.setName(img);
            button.setIcon(new ImageIcon(scaledImage));
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setSize(width, height);
            button.addActionListener(action);
            return button;
        }
    }

    class DescriptionPanel extends JPanel {
        public DescriptionPanel(String name, String type) {
            super();
            this.setSize(300, 540);
            this.setLayout(new GridLayout(2, 1, 20, 20));
            this.setBackground(Color.black);
            var title = new JLabel("Hero Name: " + name);
            var heroType = new JLabel("Hero type:  " + type);
            title.setForeground(Color.white);
            title.setFont(new Font("Monospaced", Font.ITALIC, 18));
            heroType.setForeground(Color.white);
            heroType.setFont(new Font("Monospaced", Font.ITALIC, 18));
            this.add(title);
            this.add(heroType);
        }
    }




}
