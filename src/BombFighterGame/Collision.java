package BombFighterGame;

import java.util.List;

public class Collision {

    private static List<Coin> coinLists;
    private static List<Bombfighter> bombfighterLists;
    private static List<Piece> pieceLists;
    private static List<Bomb> bombLists;
    private static List<Mine> mineLists;
    private SoundCache sounds;

    public  static  void  load(List<Coin> _coinLists, List<Bombfighter> _bombfighterLists, List<Piece> _pieceLists, List<Bomb> _bombLists, List<Mine> _mineLists) {
        coinLists = _coinLists;
        bombfighterLists = _bombfighterLists;
        pieceLists = _pieceLists;
        bombLists = _bombLists;
        mineLists = _mineLists;
    }

    public static void checkBombExplosion(List<Bomb> bombLists) {
        for (Bomb b: bombLists) {
            if (System.currentTimeMillis() > b.getTriggeredTime()) {
                //TODO: loadExplodeSound()
                SoundCache.loadExplosionSound();
                b.setVisiable(false);
                explode(b.getRowPos(), b.getColPos(), b.getBombPower());
            }
        }
    }

    public static void checkMineExplosion(List<Mine> mineLists) {
        for (Mine m: mineLists) {
            if (m.getTriggeredTime() != -1 && System.currentTimeMillis() > m.getTriggeredTime()) {
                //TODO: loadExplodeSound()


                m.setVisiable(true);
                explode(m.getRowPos(), m.getColPos(), m.getPower());
                SoundCache.loadExplosionSound();
            }
        }
    }

    public static String checkCoin(int RowPos, int ColPos) {
        for (Coin c: coinLists) {
            if (c.getRowPos() == RowPos && c.getColPos() == ColPos) {
                c.setVisiable(false);
                return c.getName();
            }
        }
        return null;
    }

    public static boolean checkFighterCollision(int nexRowPos, int nexColPos) {
        for (Bombfighter t: bombfighterLists) {
            if (t.getRowPos() == nexRowPos && t.getColPos() == nexColPos)
                return true;
        }
        for (Piece p: pieceLists) {
            if (p.getRowPos() == nexRowPos && p.getColPos() == nexColPos)
                return true;
        }
        for (Bomb b: bombLists) {
            if (b.getRowPos() == nexRowPos && b.getColPos() == nexColPos)
                return true;
        }
        for (Mine m: mineLists) {
            if (m.getRowPos() == nexRowPos && m.getColPos() == nexColPos)
                return true;
        }
        return false;
    }

    private static void explode(int rowPos, int colPos, int bombPower) {
        for (int i = rowPos; i <= 11 && i <= rowPos + bombPower; ++i) {
            if (bombPieceCollision(i, colPos)) {
                //TODO: addAnimination()
                break;
            }
        }

        // bomb up side
        for (int i = rowPos; i >= 0 && i >= rowPos - bombPower; --i) {
            if (bombPieceCollision(i, colPos)) {
                //TODO: addAnimination()
                break;
            }
        }


        // bomb left side (j ~ row)
        for (int j = colPos; j >= 0 && j >= colPos - bombPower; --j) {
            if (bombPieceCollision(rowPos, j)) {
                //TODO: addAnimination()
                break;
            }
        }

        // bomb right side
        for (int j = colPos; j <= 11 && j <= colPos + bombPower; ++j) {
            if (bombPieceCollision(rowPos, j)) {
                //TODO: addAnimination()
                break;
            }
        }
    }

    private static boolean bombPieceCollision(int rowPos, int colPos) {
        for (Piece p: pieceLists) {
            if (p.getRowPos() == rowPos && p.getColPos() == colPos && !p.isPassable()) {
                if (p.getDestroyable()) {
                    p.setVisiable(false);
                    detectMine(rowPos, colPos);
                }
                if (p.getName() == "river") continue;
                return true;
            }
        }
        for (Bombfighter t: bombfighterLists) {
            if (t.getRowPos() == rowPos && t.getColPos() == colPos){
                t.setVisiable(false);
                return true;
            }
        }
        for (Coin c: coinLists) {
            if (c.getRowPos() == rowPos && c.getColPos() == colPos){
                c.setVisiable(false);
                return true;
            }
        }
        return false;
    }

    private static void detectMine(int rowPos, int colPos) {
        for (Mine m: mineLists) {
            if (m.getRowPos() == rowPos && m.getColPos() == colPos) {
                m.setCreateTime(System.currentTimeMillis()).
                        setTriggeredTime(System.currentTimeMillis() + 1400); // explode after 1.4s
            }
        }
    }
}
