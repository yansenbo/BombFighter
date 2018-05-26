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
    private BufferedImage image;
    private BufferedImage image1;

    public GameMenu() throws IOException {
        image = ImageIO.read(new File(Globals.IMAGEPATH + "panda2_1.png"));
        image1 = ImageIO.read(new File(Globals.IMAGEPATH + "tiger2.png"));
    }

    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
        draw(g, 120, 150, 300,300, image1);
        draw(g, 450, 150, 200,300, image);
    }

    private void draw(Graphics g, int x, int y, int width, int height, BufferedImage pic) {
        g.drawImage(pic, x, y, width, height, this);
    }

}
