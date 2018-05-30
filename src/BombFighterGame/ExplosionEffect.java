package BombFighterGame;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ExplosionEffect extends Square{
    private long ExplosionTime;

    public long getDisappearTime() {
        return DisappearTime;
    }

    private long DisappearTime;

    ExplosionEffect(int rowPos, int colPos, boolean isVisiable, String name, BufferedImage image, long ExplosionTime) throws IOException {
        super(rowPos, colPos, isVisiable, name, image);
        DisappearTime = ExplosionTime + 500;
    }
}
