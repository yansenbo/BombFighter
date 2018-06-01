package BombFighterGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GameMenu extends JPanel {
//    private BufferedImage tiger1;
//    private BufferedImage panda1;
    private BufferedImage background;

    GameMenu() throws IOException {
//        tiger1 = ImageIO.read(new File(Globals.IMAGEPATH + "panda2_1.png"));
//        panda1 = ImageIO.read(new File(Globals.IMAGEPATH + "tiger2.png"));
//        background = ImageIO.read(new File(Globals.IMAGEPATH + "background.jpg"));
        background = ImageIO.read(new File(Globals.IMAGEPATH + "menu.png"));
    }

    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
        draw(g, 0, 0, 700,700, background);
//        draw(g, 120, 150, 300,300, panda1);
//        draw(g, 450, 150, 200,300, tiger1);
    }

    private void draw(Graphics g, int x, int y, int width, int height, BufferedImage pic) {
        g.drawImage(pic, x, y, width, height, this);
    }
}
