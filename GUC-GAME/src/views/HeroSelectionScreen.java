package views;

import java.awt.Color;
import java.awt.Image;
import java.io.File;

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
        // JButton button = new JButton("Start Game");
        this.getContentPane().setSize(960, 540);
        // button.setBackground(Color.YELLOW);
        var button = HeroButton("GUC-Game/src/assets/heroFaces/David.jpg");
        //  var icon = new ImageIcon(img);
        
        button.setBounds(this.getContentPane().getWidth()/2-50, this.getHeight()*2/3, 100, 100);
        // button.setSize(40, 40);
        // button.setOpaque(false);
        // button.setBorder(new LineBorder(Color.BLUE, 1, true));
        this.setLayout(null);
        this.getContentPane().add(button);

    }



    private JButton HeroButton(String img) {
        JButton button=null;
        try {
            Image image = ImageIO.read(new File(img));
            button= new JButton(new ImageIcon(image));
           button.setSize(40, 40);
           // button.setOpaque(false);
           button.setBorder(new LineBorder(Color.BLUE, 1, true));
       } catch (Exception e) {
           // TODO: handle exception
           System.out.println(e.getMessage());
       }
        return button;
    }

}
