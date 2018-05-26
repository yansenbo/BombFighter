package BombFighterGame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class Square {
    private int rowPos;
    private int colPos;
    private boolean isVisiable;
    private String name;
    private BufferedImage image;

    Square(int rowPos, int colPos, boolean isVisiable, String name, BufferedImage image) throws IOException {
        super();
        this.rowPos = rowPos;
        this.colPos = colPos;
        this.isVisiable = isVisiable;
        this.name = name;
        this.image = image;
    }

    public  boolean getVisiable() {
        return isVisiable;
    }

    public int getRowPos() {
        return rowPos;
    }

    public void setRowPos(int rowPos) {
        this.rowPos = rowPos;
    }

    public int getColPos() {
        return colPos;
    }

    public void setColPos(int colPos) {
        this.colPos = colPos;
    }

    public BufferedImage getImg() {
        return image;
    }

    public boolean isVisiable() {
        return isVisiable;
    }

    public void setVisiable(boolean visiable) {
        isVisiable = visiable;
    }

    public String getName() {
        return name;
    }
}
