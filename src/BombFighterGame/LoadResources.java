package BombFighterGame;

import javax.swing.*;
import java.awt.*;

public class LoadResources implements Runnable{

    private static JButton startButton;

    public static void init() {

        startButton = new JButton("");
//        startButton.setBackground(new Color(160, 159, 218));
        startButton.setPreferredSize(new Dimension(130, 60));

        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
    }


    public static JButton getStartButton() {
        return startButton;
    }

    @Override
    public void run() {
        init();
        SoundCache.initStartSound();
        SoundCache.loadSounds();
//        ImageCache.getImageCache();
    }
}
