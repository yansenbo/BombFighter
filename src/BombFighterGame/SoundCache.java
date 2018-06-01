package BombFighterGame;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundCache {
    private static Clip bombSound;           // explosion Sound
    private static Clip placeBombSound;
    private static Clip collectSound;
    private static Clip winnerSound;
    private static Clip startSound;
    private static boolean initialized = false;
    private static boolean initStartSound = false;


    public static void loadSounds(){
        // load timeBomb sound
        try {
//            File explosionFile = new File(Globals.SOUNDPATH + "timeBomb.wav");
            File explosionFile = new File(Globals.SOUNDPATH + "bombExplosion.wav");
            bombSound = AudioSystem.getClip();
            AudioInputStream explosionStream = AudioSystem.getAudioInputStream(explosionFile);
            bombSound.open(explosionStream);
            bombSound.setFramePosition(bombSound.getFrameLength());

            File placeBombFile = new File(Globals.SOUNDPATH + "placeBomb.wav");
            placeBombSound = AudioSystem.getClip();
            AudioInputStream placeBombStream = AudioSystem.getAudioInputStream(placeBombFile);
            placeBombSound.open(placeBombStream);
            placeBombSound.setFramePosition(bombSound.getFrameLength());

            File collectSoundFile = new File(Globals.SOUNDPATH + "collectSound.wav");
            collectSound = AudioSystem.getClip();
            AudioInputStream collectSoundStream = AudioSystem.getAudioInputStream(collectSoundFile);
            collectSound.open(collectSoundStream);
            collectSound.setFramePosition(collectSound.getFrameLength());

            File winnerSoundFile = new File(Globals.SOUNDPATH + "winner.wav");
            winnerSound = AudioSystem.getClip();
            AudioInputStream winnerSoundStream = AudioSystem.getAudioInputStream(winnerSoundFile);
            winnerSound.open(winnerSoundStream);
            winnerSound.setFramePosition(winnerSound.getFrameLength());


        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }

        initialized = true;
    }

    public static void initStartSound() {
        try {
            File startSoundFile = new File(Globals.SOUNDPATH + "startSound.wav");
            startSound = AudioSystem.getClip();
            AudioInputStream startSoundStream = AudioSystem.getAudioInputStream(startSoundFile);
            startSound.open(startSoundStream);
            startSound.setFramePosition(startSound.getFrameLength());
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        initStartSound = true;
    }

    public static void loadExplosionSound() {
        if (!initialized) loadSounds();
        bombSound.loop(1);
    }

    public static void loadplaceBombSound() {
        if (!initialized) loadSounds();
        placeBombSound.loop(1);
    }

    public static void loadCollectSound() {
        if (!initialized) loadSounds();
        collectSound.loop(1);
    }

    public static void loadWinnerSound() {
        if (!initialized) loadSounds();
        winnerSound.loop(1);
    }

    public static void loadStartSound() {
        if (!initStartSound) initStartSound();
        startSound.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void startSoundStop() {
        startSound.stop();
    }
}
