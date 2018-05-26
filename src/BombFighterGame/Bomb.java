package BombFighterGame;

import java.awt.image.BufferedImage;
import java.io.IOException;


public class Bomb extends Square {
    private long createTime;
    private long triggeredTime;
    private int bombPower;
//    private boolean passable;

    Bomb(int rowPos, int colPos, String name, long createTime, int bombPower, BufferedImage image) throws IOException {
        super(rowPos, colPos, true, name, image);
        this.createTime = createTime;
        this.triggeredTime = createTime + 2000;
        this.bombPower = bombPower;
    }



    public long getTriggeredTime() {
        return triggeredTime;
    }

    public int getBombPower() {
        return bombPower;
    }
}
