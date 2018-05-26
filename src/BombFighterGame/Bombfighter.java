package BombFighterGame;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Bombfighter extends Piece {

    //private int numberOfLife;

    //    private int speed;
    private String name;
    private String _direction;
    private int _numberOfCoin;
    private int bombPower = 2;
    //Key map to keyvalue
    private HashMap<Integer, int[]> keyToMoveValue;
    //key map to direction
    private HashMap<Integer, String> keyToDirection;
    private List<Bomb> listBombs = new ArrayList<Bomb>();
    int _placeBombKey;
    private int keyCode = -1;
    private BufferedImage bombImg;

    Bombfighter(String direction, int up, int down, int left, int right, int placeBombKey, int colPos, int rowPos, String name, BufferedImage image) throws IOException {
        super(rowPos, colPos, true, true, false, false, name, image);
        this.name = name;

        bombImg = ImageIO.read(new File(Globals.IMAGEPATH + "bomb.png"));
        _numberOfCoin = 0;
        _direction = direction;
        _placeBombKey = placeBombKey;

        keyToDirection = new HashMap<Integer, String>();
        keyToDirection.put(up, "up");
        keyToDirection.put(down, "down");
        keyToDirection.put(left, "left");
        keyToDirection.put(right, "right");

        keyToMoveValue = new HashMap<Integer, int[]>();
        keyToMoveValue.put(up, new int[]{-1, 0});
        keyToMoveValue.put(down, new int[]{1, 0});
        keyToMoveValue.put(left, new int[]{0, -1});
        keyToMoveValue.put(right, new int[]{0, 1});

    }

    public void move() {     // {row_step, col_step}
        if (!keyToMoveValue.containsKey(keyCode))
            return;
        int[] steps = keyToMoveValue.get(keyCode);
        int row_step = steps[0];
        int col_step = steps[1];
        int nextRowPos = getRowPos() + row_step;
        int nextColPos = getColPos() + col_step;
        if (0 <= nextRowPos && nextRowPos < 12
                && 0 <= nextColPos && nextColPos < 12
                && !Collision.checkFighterCollision(nextRowPos, nextColPos)) {
                    if (Collision.checkCoin(nextRowPos, nextColPos)  == "coin") {
                        collectCoin();
                    } else if (Collision.checkCoin(nextRowPos, nextColPos)  == "lollipop") {
                        collectLollipop();
                    }
                    setRowPos(nextRowPos);
                    setColPos(nextColPos);
        }
        keyCode = -1;
        //TODO: load_move_sounds()
    }

    public Bomb placeBomb() throws IOException {
        Bomb bomb = new Bomb(this.getRowPos(),
                this.getColPos(),
                "bomb",
                System.currentTimeMillis(),
                this.bombPower,
                bombImg);
        //TODO: load_place_Bomb_sounds()
        return bomb;
    }

    public void collectCoin() {
        _numberOfCoin++;
    }

    public void collectLollipop() {
        this.bombPower++;
    }

    public void keyPressed(KeyEvent e) throws IOException {
        keyCode = e.getKeyCode();
        //check if the key press is for shoot
        if (keyCode == _placeBombKey) {
            listBombs.add(placeBomb());
            //check if the key press is in for this player
        } else if (keyToMoveValue.containsKey(keyCode)) {
            setDirection(keyToDirection.get(keyCode));
            move();
        }
    }

    public void clearBombList() {
        listBombs.clear();
    }

    public void keyRelease(KeyEvent e) {
        keyCode = -1;
    }


    private void setDirection(String direction) throws IOException {
        _direction = direction;
    }


    public List<Bomb> getListBombs() {
        return listBombs;
    }

    public int get_numberOfCoin() {
        return _numberOfCoin;
    }

    public String getName() {
        return name;
    }
}
