package touhou;

import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import static java.awt.event.KeyEvent.*;

/**
 * Created by huynq on 7/29/17.
 */
public class GameWindow extends Frame {

    private long lastTimeUpdate;
    private long currentTime;
    private Graphics2D windowGraphics;

    private BufferedImage backbufferImage;
    private Graphics2D backbufferGraphics;

    private BufferedImage background;
    private BufferedImage player;

    private int playerX = 384 / 2;
    private int playerY = 600;

    private boolean rightPressed;
    private boolean leftPressed;
    private boolean upPressed;
    private boolean downPressed;

    final int PLAYER_SPEED = 5;

    public GameWindow() {
        background = SpriteUtils.loadImage("assets/images/background/0.png");
        player = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        setupGameLoop();
        setupWindow();
    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;
    }

    private void setupWindow() {
        this.setSize(1024, 768);

        this.setTitle("Touhou - Remade by QHuyDTVT");
        this.setVisible(true);

        this.backbufferImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.backbufferGraphics = (Graphics2D) this.backbufferImage.getGraphics();

        this.windowGraphics = (Graphics2D) this.getGraphics();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case VK_RIGHT:
                        rightPressed = true;
                        break;
                    case VK_LEFT:
                        leftPressed = true;
                        break;
                    case VK_UP:
                        upPressed = true;
                        break;
                    case VK_DOWN:
                        downPressed = true;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case VK_RIGHT:
                        rightPressed = false;
                        break;
                    case VK_LEFT:
                        leftPressed = false;
                        break;
                    case VK_UP:
                        upPressed = false;
                        break;
                    case VK_DOWN:
                        downPressed = false;
                        break;
                }
            }
        });
    }

    public void loop() {
        while(true) {
            if (lastTimeUpdate == -1) {
                lastTimeUpdate = System.currentTimeMillis();
            }
            currentTime = System.currentTimeMillis();
            if (currentTime - lastTimeUpdate > 17) {
                run();
                render();
                lastTimeUpdate = currentTime;
            }
        }
    }

    private void run() {
        if (rightPressed) {
            playerX += PLAYER_SPEED;
        }

        if (leftPressed) {
            playerX -= PLAYER_SPEED;
        }

        if (downPressed) {
            playerY += PLAYER_SPEED;
        }

        if(upPressed) {
            playerY -= PLAYER_SPEED;
        }

    }

    private void render() {
        backbufferGraphics.setColor(Color.black);
        backbufferGraphics.fillRect(0, 0, 1024, 768);
        backbufferGraphics.drawImage(background, 0, 0, null);
        backbufferGraphics.drawImage(player, playerX, playerY, null);

        windowGraphics.drawImage(backbufferImage, 0, 0, null);
    }
}
