package BombFighterGame;

import javax.swing.*;
import java.io.IOException;


public class GameMain {
    public static void main(String[] args) throws IOException {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread thread = new Thread(new LoadResources());
                    thread.start();
                    BoardManager boardManager = new BoardManager();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

//        BoardManager boardManager = new BoardManager();
//        Thread thread = new Thread(new RefreshThread(boardManager));
//        thread.start();
    }
}
