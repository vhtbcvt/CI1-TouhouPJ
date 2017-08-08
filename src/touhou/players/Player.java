package touhou.players;

import tklibs.SpriteUtils;
import touhou.bases.Constraints;
import touhou.bases.FrameCounter;
import touhou.bases.Vector2D;
import touhou.bases.renderers.ImageRenderer;
import touhou.inputs.InputManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Player {
    private static final int SPEED = 5;
    private Vector2D position;
    private InputManager inputManager;
    private Constraints constraints;
    public ArrayList<PlayerSpell> playerSpells;
    private ImageRenderer renderer;

    private FrameCounter coolDownCounter;
    private boolean spellLock;

    public Player() {
        position = new Vector2D(384/2, 600);
        this.spellLock = false;
        BufferedImage image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        renderer = new ImageRenderer(image);
        coolDownCounter = new FrameCounter(4);
    }

    public void setConstraints(Constraints constraints){
        this.constraints = constraints;
    }

    public  void setInputManager(InputManager inputManager){
        if (inputManager != null)
        this.inputManager = inputManager;
    }

    public void run() {
        if (inputManager.upPressed)
            position.addUp(0, -SPEED);
        if (inputManager.downPressed)
            position.addUp(0, SPEED);
        if (inputManager.leftPressed)
            position.addUp(-SPEED, 0);
        if (inputManager.rightPressed)
            position.addUp(SPEED, 0);

        if (constraints != null) {
            constraints.make(position);
        }

        if (coolDownCounter.run()){
            coolDownCounter.reset();

        }
        castSpell();

    }

    private void castSpell() {
        if ((inputManager.cPressed)&&(!spellLock)) {
            PlayerSpell newSpell = new PlayerSpell();
            newSpell.position1.set(this.position.x - 9, this.position.y - 14);
            newSpell.position2.set(this.position.x + 9, this.position.y - 14);
            playerSpells.add(newSpell);
            spellLock = true;
            coolDownCounter.reset();
        }

        unlockSpell();
    }

    private void unlockSpell() {
        if (spellLock){
            if (coolDownCounter.run()){
                spellLock = false;

            }
        }
    }

    public void render(Graphics2D g2d) {
        renderer.render(g2d, position);
    }
}
