package BombFighterGame;

import java.awt.image.BufferedImage;
import java.io.IOException;



public class Piece extends Square {
    private boolean destroyable;
    private boolean passable;
    private boolean collectable;

    Piece(int rowPos, int colPos, boolean visable, boolean destroyable, boolean passable, boolean collectable, String name, BufferedImage image) throws IOException {
        super(rowPos, colPos, visable, name, image);
        this.collectable = collectable;
        this.passable = passable;
        this.destroyable = destroyable;
    }




    public boolean isPassable() {
        return passable;
    }

    public boolean getDestroyable() {
        return destroyable;
    }
}
