package BombFighterGame;

import java.awt.image.BufferedImage;
import java.io.IOException;


public class Mine extends Piece{

    private int power = 2;
    private long createTime;
    private long triggeredTime = -1;

    Mine(int rowPos, int colPos, boolean visable, boolean destroyable, boolean passable, boolean collectable, String name, BufferedImage image) throws IOException {
        super(rowPos, colPos, visable, destroyable, passable, collectable, name, image);
    }


    public int getPower() {
        return power;
    }

    public long getTriggeredTime() {
        return triggeredTime;
    }

    public Mine setCreateTime(long createTime) {
        this.createTime = createTime;
        return this;
    }

    public Mine setTriggeredTime(long triggeredTime) {
        this.triggeredTime = triggeredTime;
        return this;
    }
}
