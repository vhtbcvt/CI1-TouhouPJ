package touhou.players;

import tklibs.SpriteUtils;
import touhou.bases.Constraints;
import touhou.bases.FrameCounter;
import touhou.bases.GameObject;
import touhou.bases.Vector2D;
import touhou.bases.physics.BoxCollider;
import touhou.bases.physics.Physics;
import touhou.bases.physics.PhysicsBody;
import touhou.bases.renderers.ImageRenderer;
import touhou.enemies.Enemy;
import touhou.enemies.EnemyBullet;
import touhou.gameplay.Hp;
import touhou.inputs.InputManager;

import java.awt.*;



public class Player extends GameObject implements PhysicsBody {
    private static final int SPEED = 5;
    private InputManager inputManager;
    private Constraints constraints;
    private BoxCollider boxCollider;

    private FrameCounter coolDownCounter;
    private boolean spellLock;
    private Hp hp;

    public Player() {
        super();
        this.spellLock = false;
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/players/straight/0.png"));
        coolDownCounter = new FrameCounter(6);
        boxCollider = new BoxCollider(20, 20);
        this.children.add(boxCollider);
        hp = new Hp(10);
    }

    public void setConstraints(Constraints constraints){
        this.constraints = constraints;
    }

    public  void setInputManager(InputManager inputManager){
        if (inputManager != null)
        this.inputManager = inputManager;
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
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
        hit();
    }

    private void hit() {
        EnemyBullet bulletCollider = Physics.collideWithEnemyBullet(this.boxCollider);
        Enemy enemyCollider = Physics.collideWithEnemy(this.boxCollider);
        if (bulletCollider != null){
            hp.hpSub(1);
            bulletCollider.setActive(false);
        }
        if (enemyCollider != null){
            hp.hpSub(1);
            enemyCollider.setActive(false);
        }
        if (hp.die()) this.isActive = false;
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

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

}
