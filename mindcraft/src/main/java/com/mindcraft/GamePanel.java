package com.mindcraft;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.mindcraft.entities.Player;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    final int maxScreenCols = 18;
    final int maxScreenRows = 14;
    final int screenWidth = tileSize * maxScreenCols;
    final int screenHeight = tileSize * maxScreenRows;

    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyHandler);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; // 0.0166666666666667 seconds
        double nextDrawInterval = System.nanoTime() + drawInterval;

        while(gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawInterval - System.nanoTime();
                remainingTime /= 1000000;

                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawInterval += drawInterval;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);

        g2.dispose();
    }
}
