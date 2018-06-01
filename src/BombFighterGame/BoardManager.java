package BombFighterGame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BoardManager extends JFrame implements ActionListener{
    private JButton startButton;
    private GameMenu gameMenu;


    private JFrame jf;
    private ScoreBoard scoreBoard;
    private BoardUI boardUI;
    private GameMap gameMap;
    private Bombfighter player1;
    private Bombfighter player2;
    private List<Bomb> bombLists;
    private List<Mine> mineLists;
    private List<Coin> coinLists;
    private List<Bombfighter> bombfighterLists;
    private List<Piece> pieceLists;
    private List<ExplosionEffect> explosionEffectsLists;
    private Timer timer;
    private long startTime;
    public boolean gameOver ;

    BoardManager() throws IOException {
        System.out.println("~~~~~~ " + System.currentTimeMillis());
        intiJframe();
        loadMenu();
//        initList();
//        initPlayer();
//        initPiece(); // List of all the pieces
    }

    private void intiJframe() {
        timer = new Timer(20, this);        // listen to action, trigger action perform
        jf = new JFrame();
        jf.getContentPane().setBackground(new Color(169, 181, 218));
        jf.setTitle("Bomb Fighter");
        jf.setSize(700, 700);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    player1.keyPressed(e);
                    player2.keyPressed(e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void loadMenu() throws IOException {
        gameMenu = new GameMenu();
//        startButton = LoadResources.getStartButton();
//        startButton.addActionListener(this::actionPerformed);
        startButton = new JButton("");
        startButton.setPreferredSize(new Dimension(130, 60));
        startButton.addActionListener(this);

        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);

        jf.add(gameMenu);
        gameMenu.add(startButton);
        jf.setVisible(true);
        SoundCache.loadStartSound();

        System.out.println("loadMe " + System.currentTimeMillis());
    }

    private void initPlayer() throws IOException {
        player1 = new Bombfighter("up", 87, 83, 65, 68, 70,3, 11, "panda", ImageCache.getImageCache().getPanda());
        player2 = new Bombfighter("up", 38, 40, 37, 39, 76,10, 0, "tiger", ImageCache.getImageCache().getTiger());

        System.out.println("initPr " + System.currentTimeMillis());
    }

    private void initList() {
        bombLists = new ArrayList<Bomb>();
        mineLists = new ArrayList<Mine>();
        coinLists = new ArrayList<Coin>();
        bombfighterLists = new ArrayList<Bombfighter>();
        pieceLists = new ArrayList<Piece>();
        explosionEffectsLists = new ArrayList<>();

        System.out.println("initLt " + System.currentTimeMillis());
    }

    private void loadGame() throws IOException {
        initList();
        initPlayer();
        initPiece(); // List of all the pieces

        jf.remove(startButton);
        jf.remove(gameMenu);
        boardUI = new BoardUI(bombLists, mineLists, coinLists, bombfighterLists, pieceLists, explosionEffectsLists);
        boardUI.setBounds(100,100,480, 480);
        startTime = System.currentTimeMillis();
        scoreBoard = new ScoreBoard(player1, player2, startTime);
        scoreBoard.setBounds(60, 0, 560, 40);
        OuterWall outer = new OuterWall();
        outer.setBounds(0, 160, 620, 620);
        jf.add(boardUI);
        jf.add(scoreBoard);
        jf.add(outer);
        jf.requestFocus();
    }

    private class OuterWall extends JPanel {
        @Override
        public void paint(Graphics g) {
            BufferedImage outerWall = null;
            outerWall = ImageCache.getImageCache().getOuterWall();
            for (int row = 0; row < 14; ++row) {
                int x = row * 40 + 60;
                int y = 60;
                draw(g, y, x, 40, 40, outerWall);
                draw(g, y+520, x, 40, 40 , outerWall);
            }

            for (int col = 0; col < 14; ++col) {
                int x = 60;
                int y = col * 40 + 60;
                draw(g, y, x, 40, 40, outerWall);
                draw(g, y, x+520, 40, 40 , outerWall);
            }

        }
        private void draw(Graphics g, int x, int y, int width, int height, BufferedImage pic) {
            g.drawImage(pic, x, y, width, height, this);
        }
    }


    private void initPiece() throws IOException {
        gameMap = new GameMap();
        int[][] upLayerMap = gameMap.getupLayerMap();
        int[][] innerLayerMap = gameMap.getinnerLayerMap();
        int m = 12;
        int n = 12;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (upLayerMap[i][j] == 0) {
                    pieceLists.add(new Wall(i, j, true, true, false, false, "wall", ImageCache.getImageCache().getWall()));
                } else if (upLayerMap[i][j] == 3) {
                    pieceLists.add(new River(i, j, true, false, false, false, "river", ImageCache.getImageCache().getRiver()));
                } else if (upLayerMap[i][j] == 4) {
                    bombfighterLists.add(player1);
                } else if (upLayerMap[i][j] == 5) {
                    pieceLists.add(new Castle(i, j, true, false, false, false, "castle", ImageCache.getImageCache().getCastle()));
                } else if (upLayerMap[i][j] == 7) {
                    bombfighterLists.add(player2);
                }

                if (innerLayerMap[i][j] == 1) {
                    coinLists.add(new Coin(i, j, true, true, true, true, "coin", ImageCache.getImageCache().getCoin()));
                } else if (innerLayerMap[i][j] == 2) {
                    mineLists.add(new Mine(i, j, false, true, false, false, "mine", ImageCache.getImageCache().getMine()));
                } else if (innerLayerMap[i][j] == 8) {
                    coinLists.add(new Lollipop(i, j, true, true, false, false, "lollipop", ImageCache.getImageCache().getLollipop()));
                }
            }
        }

        System.out.println("initPc " + System.currentTimeMillis());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == startButton) {
            jf.remove(gameMenu);
            SoundCache.startSoundStop();
            gameOver = false;
            // listen to action, trigger action perform

            try {
                loadGame();
                timer.start();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            if (gameOver) {
                timer.stop();
                try {
                    showDialog();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                return;
            }
            updatePices();
            Collision.load(coinLists, bombfighterLists, pieceLists, bombLists, mineLists, explosionEffectsLists);
            checkExplosion();
            checkGameOver();
            boardUI.repaint();
        }
    }

    private void showDialog() throws IOException {
        String message = "";
        ImageIcon icon;
        Bombfighter winner = checkWinner();
        SoundCache.loadWinnerSound();
        if (winner == null) {
            message = "No game winner, it is tie." ;
            icon = new ImageIcon(Globals.IMAGEPATH + "tie.png");
        } else {
            message =  winner.getName().toUpperCase() + " Wins! Coins:" + winner.get_numberOfCoin();
            icon = new ImageIcon(Globals.IMAGEPATH + winner.getName() +"win.png");
        }

        String[] choices = {"End Game", "Restart Game"};
            int response = JOptionPane.showOptionDialog(
                null
                , message
                , "Game Result"
                , JOptionPane.YES_NO_OPTION
                , JOptionPane.PLAIN_MESSAGE
                , icon
                , choices
                , "Game Result"
        );
        if (response == 0) {
            System.exit(0);
        } else {
            restartGame();
        }
    }

    private Bombfighter checkWinner() {
        if (bombfighterLists.size() == 2 && bombfighterLists.get(0).getVisiable() == true
                && bombfighterLists.get(1).getVisiable() == true) {
            return bombfighterLists.get(0).get_numberOfCoin() == bombfighterLists.get(1).get_numberOfCoin() ? null :
                    bombfighterLists.get(0).get_numberOfCoin() > bombfighterLists.get(1).get_numberOfCoin() ?
                            bombfighterLists.get(0) : bombfighterLists.get(1);

        }
        return bombfighterLists.get(0).getVisiable()? bombfighterLists.get(0) : bombfighterLists.get(1);
    }


    private void checkGameOver() {
        // 1 tank dead
        if (bombfighterLists.size() < 2) {
            gameOver = true;
        }
        for (Bombfighter t: bombfighterLists) {
            if (!t.getVisiable()) {
                gameOver = true;
            }
        }
        if (System.currentTimeMillis() - startTime >= 90000) {
//        if (System.currentTimeMillis() - startTime >= 10000) {
            gameOver = true;
        }

    }

    private void checkExplosion() {
        Collision.checkBombExplosion(bombLists);
        Collision.checkMineExplosion(mineLists);
    }

    private void updatePices() {
        if (!gameOver) {
            updateBomb();
            updatePice();
            updateMine();
            updateCoin();
            updateScoreBoard();
            updateExplosionEffect();
        }
    }

    private void updateExplosionEffect() {
        int n = explosionEffectsLists.size();
        for (int i = n-1; i >= 0; --i) {
            if (System.currentTimeMillis() >= explosionEffectsLists.get(i).getDisappearTime())
                explosionEffectsLists.remove(i);
        }
    }

    private void updateScoreBoard() {
        scoreBoard.repaint();
    }

    private void updateCoin() {
        int n = coinLists.size();
        for (int i = n-1; i >= 0; --i) {
            if (!coinLists.get(i).getVisiable())
                coinLists.remove(i);
        }
    }

    private void updateMine() {
        int n = mineLists.size();
        for (int i = n-1; i >= 0; --i) {
            if (mineLists.get(i).getVisiable())
                mineLists.remove(i);
        }
    }

    private void updatePice() {
        int n = pieceLists.size();
        for (int i = n-1; i >= 0; --i) {
            if (!pieceLists.get(i).getVisiable())
                pieceLists.remove(i);
        }
    }

    private void updateBomb() {
        int n = bombLists.size();
        for (int i = n-1; i >= 0; --i) {
            if (!bombLists.get(i).getVisiable())
                bombLists.remove(i);
        }

        for (Bombfighter t: bombfighterLists) {
            bombLists.addAll(t.getListBombs());
            t.clearBombList();
        }
    }

    private void restartGame() throws IOException {
//        initList();
//        initPlayer();
//        initPiece();
        loadGame();
        timer.start();
        gameOver = false;
    }
}

