package BombFighterGame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageCache {
    private static ImageCache imageCache;

    static {
        try {
            imageCache = new ImageCache();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage panda;
    private BufferedImage tiger;
    private BufferedImage wall;
    private BufferedImage river;
    private BufferedImage castle;
    private BufferedImage coin;
    private BufferedImage mine;
    private BufferedImage lollipop;
    private BufferedImage outerWall;
    private BufferedImage explosionImg;


    private ImageCache() throws IOException {
        panda = loadImage("panda");
        tiger = loadImage("tiger");
        wall = loadImage("wall");
        river = loadImage("river");
        castle = loadImage("castle");
        coin = loadImage("coin");
        mine = loadImage("mine");
        lollipop = loadImage("lollipop");
        outerWall = loadImage("outerWall");
        explosionImg = loadImage("explosionEffect");
    }

    public BufferedImage loadImage(String path) throws IOException {
        return ImageIO.read(new File(Globals.IMAGEPATH + path + ".png"));
    }


    public BufferedImage getPanda() {
        return panda;
    }

    public BufferedImage getTiger() {
        return tiger;
    }

    public BufferedImage getWall() {
        return wall;
    }

    public BufferedImage getRiver() {
        return river;
    }

    public BufferedImage getCastle() {
        return castle;
    }

    public BufferedImage getCoin() {
        return coin;
    }

    public BufferedImage getMine() {
        return mine;
    }

    public BufferedImage getLollipop() {
        return lollipop;
    }

    public BufferedImage getOuterWall() {
        return outerWall;
    }


    public BufferedImage getExplosionImg() {
        return explosionImg;
    }

    public static ImageCache getImageCache() {
        return imageCache;
    }
}
