package touhou.enemies;

import tklibs.SpriteUtils;
import touhou.bases.FrameCounter;
import touhou.bases.GameObject;
import touhou.bases.Vector2D;
import touhou.bases.physics.BoxCollider;
import touhou.bases.physics.Physics;
import touhou.bases.physics.PhysicsBody;
import touhou.bases.renderers.ImageRenderer;
import touhou.gameplay.Hp;
import touhou.players.Player;
import touhou.players.PlayerSpell;

import java.awt.image.BufferedImage;
import java.util.Random;


public class Enemy extends GameObject implements PhysicsBody {

    private static final int SPEED = 3;
    private BoxCollider boxCollider;
    private boolean bulletLock;
    private FrameCounter coolDownCounter;
    private Random random;
    private int x;
    private Hp hp;

    public Enemy(){
        super();
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png"));
        coolDownCounter = new FrameCounter(20);
        boxCollider = new BoxCollider(20, 20);
        this.children.add(boxCollider);
        random = new Random();
        x=  random.nextInt(3);
        hp = new Hp(3);
    }

    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        fly();
        shoot();
        hitSpells();
    }
    private void hitSpells() {
        PlayerSpell spellCollider = Physics.collideWithSpell(this.boxCollider);
        if (spellCollider != null){
            spellCollider.setActive(false);
            hp.hpSub(1);
        }
        if (hp.die()) this.isActive = false;
    }

    private void fly(){
        if (x==0) position.addUp(3, SPEED);
        if (x==1) position.addUp(-3, SPEED);
        if ((x==2)) position.addUp(0, SPEED);
    }

    private void shoot(){
        if(!bulletLock){
            EnemyBullet newBullet = new EnemyBullet();
            newBullet.getPosition().set(this.position.add(0, 30));
            GameObject.add(newBullet);
            bulletLock = true;
            coolDownCounter.reset();
        }
        unLockBullet();
    }

    private void unLockBullet() {
        if (bulletLock) {
            if (coolDownCounter.run()) {
                bulletLock = false;
            }
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}



