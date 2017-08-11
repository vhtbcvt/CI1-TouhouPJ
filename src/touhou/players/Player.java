package touhou.players;

import tklibs.SpriteUtils;
import touhou.bases.Constraints;
import touhou.bases.FrameCounter;
import touhou.bases.GameObject;
import touhou.bases.renderers.ImageRenderer;
import touhou.inputs.InputManager;

import java.awt.*;



public class Player extends GameObject {
    private static final int SPEED = 5;
    private InputManager inputManager;
    private Constraints constraints;

    private FrameCounter coolDownCounter;
    private boolean spellLock;

    public Player() {
        super();
        this.spellLock = false;
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/players/straight/0.png"));
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
            newSpell.getPosition().set(this.position.x, this.position.y - 14);
            GameObject.add(newSpell);
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
