package BombFighterGame;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Castle extends Piece {
    Castle(int rowPos, int colPos, boolean visable, boolean destroyable, boolean passable, boolean collectable, String name, BufferedImage image) throws IOException {
        super(rowPos, colPos, visable, destroyable, passable, collectable, name, image);
    }
}
