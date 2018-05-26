package BombFighterGame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;


public class BoardUI extends JPanel{

    private List<Bomb> bombLists;
    private List<Mine> mineLists;
    private List<Coin> coinLists;
    private List<Bombfighter> bombfighterLists;
    private List<Piece> pieceLists;

    BoardUI(List<Bomb> bombLists, List<Mine> mineLists, List<Coin> coinLists, List<Bombfighter> bombfighterLists, List<Piece> pieceLists) {
        this.mineLists = mineLists;
        this.bombLists = bombLists;
        this.coinLists = coinLists;
        this.bombfighterLists = bombfighterLists;
        this.pieceLists = pieceLists;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        setBackground(new Color(169, 181, 218));
        int x;
        int y;

        for (Mine b: mineLists) {
            int i = b.getRowPos();
            int j = b.getColPos();
            x = i * 40;
            y = j * 40;
            draw(g, y, x, 40, 40, b.getImg());
        }
        for (Coin c: coinLists) {
            int i = c.getRowPos();
            int j = c.getColPos();
            x = i * 40;
            y = j * 40;
            draw(g, y, x, 40, 40, c.getImg());
        }
        for (Piece p: pieceLists) {
            int i = p.getRowPos();
            int j = p.getColPos();
            x = i * 40;
            y = j * 40;
            draw(g, y, x, 40, 40, p.getImg());

        }

        for (Bomb b: bombLists) {
            int i = b.getRowPos();
            int j = b.getColPos();
            x = i * 40;
            y = j * 40;
            draw(g, y, x, 40, 40, b.getImg());
        }

        for (Bombfighter t: bombfighterLists) {
            int i = t.getRowPos();
            int j = t.getColPos();
            x = i * 40; // i
            y = j * 40; // j
            draw(g, y, x, 40, 40, t.getImg());
        }

    }
    private void draw(Graphics g, int x, int y, int width, int height, BufferedImage pic) {
        g.drawImage(pic, x, y, width, height, this);
    }


}


