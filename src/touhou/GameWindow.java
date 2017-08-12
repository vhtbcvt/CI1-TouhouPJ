package touhou;

import touhou.bases.Constraints;
import touhou.bases.GameObject;
import touhou.background.Background;
import touhou.enemies.EnemySpawner;
import touhou.inputs.InputManager;
import touhou.players.Player;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;


public class GameWindow extends Frame {

    private long lastTimeUpdate;
    private long currentTime;
    private Graphics2D windowGraphics;

    private BufferedImage backbufferImage;
    private Graphics2D backbufferGraphics;

    private int bgy = 768 - 3109;

    Background backGround = new Background();
    Player player = new Player();
    EnemySpawner enemySpawner = new EnemySpawner(); //TODO: sua thanh game object

    InputManager inputManager = new InputManager();

    public GameWindow() {
        pack();

        addBackground();
        addPlayer();
        GameObject.add(enemySpawner);

        setupGameLoop();
        setupWindow();
    }

    private void addBackground(){
        backGround.getPosition().set(384/2, 768 - 3109/2);
        GameObject.add(backGround);
    }
    
    private void addPlayer() {
        player.setInputManager(this.inputManager) ;
        player.setConstraints(new Constraints( getInsets().top, 740, getInsets().left, 384));
        player.getPosition().set(192, 650);

        GameObject.add(player);
    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;
    }

    private void setupWindow() {
        this.setSize(384, 740);

        this.setTitle("Touhou - Remade");
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
                inputManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.keyReleased(e);
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
        GameObject.runAll();
    }


    private void render() {
        backbufferGraphics.setColor(Color.black);
        backbufferGraphics.fillRect(0, 0, 384, 768);

        GameObject.renderAll(backbufferGraphics);

        windowGraphics.drawImage(backbufferImage, 0, 0, null);
    }
}