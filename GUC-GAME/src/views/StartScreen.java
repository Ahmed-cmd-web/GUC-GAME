package views;

import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JLayeredPane;
import javax.imageio.ImageIO;
import javax.swing.JButton;




public class StartScreen extends JLayeredPane {

    public StartScreen(MotherFrame frame) {
        JButton startButton = new JButton("Start Game");
        this.setSize(960, 540);
        this.setLayout(new GridBagLayout());
        startButton.addActionListener(e -> frame.layout.next(frame.getContentPane()));
        startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        startButton.setForeground(Color.white);
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setSize(50, 100);
        startButton.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 40));

        startButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                startButton.setForeground(Color.red);
            };

            public void mouseExited(MouseEvent e) {
                startButton.setForeground(Color.white);
            };
        });
        var gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        this.add(startButton, gbc);
    }



    @Override
    public void paint(Graphics g) {
        try {
            var image = ImageIO.read(new File("GUC-GAME/src/assets/lastofus.jpg"))
                    .getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
            
            g.drawImage(image, 0, 0, this);

            super.paint(g);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
