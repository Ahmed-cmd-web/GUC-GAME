package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;


public class HeroSelectionScreen extends JLayeredPane implements ActionListener{
    private JLabel middleColumn;
    private JLabel rightColumn;
    private Row heroRow = new Row("Fighters", new String[] { "Joel.png", "David.png" },this);
    private Row  heroRow2 = new Row("Explorers", new String[] {"Tess.png", "Riley.png", "Tommy.png"},this);
    private Row  heroRow3 = new Row("Medics", new String[] { "Ellie.png", "Bill.png","Henrry.png"},this);



    private ImageIcon returnScaledIcon(String path,int width,int height) {
        return new ImageIcon(new ImageIcon(path)
                .getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var source = ((JButton) e.getSource()).getName();
        middleColumn.setIcon(returnScaledIcon("GUC-Game/src/assets/heros/" + source.replace("png", "jpeg"), 300, 540));
        rightColumn.setIcon(returnScaledIcon("GUC-Game/src/assets/heroDescriptions/" + source, 300, 540));
    }


    public HeroSelectionScreen(MotherFrame frame) {
        this.setLayout(new GridLayout(1, 3, 0, 0));


        var leftColumn = new JLayeredPane();
        var gbc = new GridBagConstraints();
        leftColumn.setLayout(new GridBagLayout());
        leftColumn.setBorder(new EmptyBorder(10, 10, 10, 10));
        leftColumn.setSize(400, 200);
        var backButton = new JButton("<Back");
        backButton.addActionListener(e->frame.layout.previous(frame.getContentPane()));
        backButton.setForeground(Color.white);
        backButton.setSize(50, 100);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setFont(new Font(Font.DIALOG, Font.ITALIC, 40));
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                backButton.setForeground(Color.red);
            };

            public void mouseExited(MouseEvent e) {
                backButton.setForeground(Color.white);
            };
        });


        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weighty = 0.5;
        leftColumn.add(backButton, gbc);
        gbc.gridy = 1;
        leftColumn.add(heroRow,gbc);
        gbc.gridy = 2;
        leftColumn.add(heroRow2,gbc);
        gbc.gridy = 3;
        leftColumn.add(heroRow3,gbc);


        middleColumn = new JLabel();
        middleColumn.setIcon(returnScaledIcon("GUC-Game/src/assets/heros/Bill.jpeg", 300, 540));
        middleColumn.setSize(300, 540);


        rightColumn = new JLabel();
        rightColumn.setIcon(returnScaledIcon("GUC-Game/src/assets/heroDescriptions/Bill.png", 300, 540));
        rightColumn.setSize(300, 540);


        this.add(leftColumn);
        this.add(middleColumn);
        this.add(rightColumn);

    }

    class Row extends JLayeredPane {
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
            var imagesRow = new JLayeredPane();
            imagesRow.setLayout(new FlowLayout(FlowLayout.LEFT));

            imagesRow.setSize(this.getWidth(), this.getHeight());
            for (var hero : heros) {
                var button = HeroButton(hero, 0, 0, 80, 80, action);
                button.setLayout(null);
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                imagesRow.add(button);
            }
            this.add(imagesRow);
        }

        private JButton HeroButton(String img, int x, int y, int width, int height, ActionListener action) {
            var button = new JButton();
            var scaledImage = new ImageIcon("GUC-Game/src/assets/heroFaces/" + img).getImage().getScaledInstance(width,
                    height, Image.SCALE_FAST);
            button.setName(img);
            button.setIcon(new ImageIcon(scaledImage));
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setSize(width, height);
            button.addActionListener(action);
            return button;
        }
    }
    @Override
    public void paint(Graphics g) {
        try {
            var image = ImageIO.read(new File("GUC-GAME/src/assets/SelectScreen.png"))
            .getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
            g.drawImage(image, 0, 0, this);
            super.paintComponents(g);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
