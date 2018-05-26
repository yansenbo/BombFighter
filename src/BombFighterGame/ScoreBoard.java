package BombFighterGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScoreBoard extends JPanel{
    private Bombfighter player1;
    private Bombfighter player2;

    private BufferedImage coinImg;
    private JLabel score1;
    private JLabel score2;
    private JButton timeButton;
    private long startTime;


    ScoreBoard(Bombfighter player1, Bombfighter player2, long startTime) throws IOException {
        this.startTime = startTime;
        score1 = new JLabel();
        score2 = new JLabel();
        timeButton =  new JButton();
        this.player1 = player1;
        this.player2 = player2;
        score1.setText(player1.get_numberOfCoin() + "");
        score2.setText(player2.get_numberOfCoin() + "");
//        timeButton.setText(105 - ((int)(System.currentTimeMillis() - startTime)/1000)+ " ");
        timeButton.setBackground(new Color(171, 215, 218));
        timeButton.setEnabled(false);
        coinImg = ImageIO.read(new File(Globals.IMAGEPATH + "coin.png"));
        this.add(score1);
        this.add(timeButton);
        this.add(score2);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        draw(g, 0, 0, 40, 40, player1.getImg());
        draw(g, 30, 0, 40, 40 , coinImg);
        score1.setText(player1.get_numberOfCoin() + "");
        draw(g, 450, 0, 40, 40, coinImg);
        draw(g, 480, 0, 40, 40 , player2.getImg());
        score2.setText(player2.get_numberOfCoin() + "");
//        timeButton.setText(Math.max(0, 10 - ((int)(System.currentTimeMillis() - startTime)/1000))+ " ");
        timeButton.setText(Math.max(0, 90 - ((int)(System.currentTimeMillis() - startTime)/1000))+ " ");

    }

    private void draw(Graphics g, int x, int y, int width, int height, BufferedImage pic) {
        g.drawImage(pic, x, y, width, height, this);
    }
}
