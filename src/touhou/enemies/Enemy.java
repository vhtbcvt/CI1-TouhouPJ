package touhou.enemies;

import tklibs.SpriteUtils;
import touhou.bases.FrameCounter;
import touhou.bases.GameObject;
import touhou.bases.renderers.ImageRenderer;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Enemy extends GameObject {

    private static final int SPEED = 3;
    private boolean bulletLock;
    private FrameCounter coolDownCounter;
    private Random random;
    private int x;

    public Enemy(){
        super();
        BufferedImage image = SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png");
        renderer = new ImageRenderer(image);
        coolDownCounter = new FrameCounter(20);
        random = new Random();
        x=  random.nextInt(2);
    }

    public void run(){
        super.run();
        fly();
        shoot();
    }

    private void fly(){
        if (x==0) position.addUp(3, SPEED);
        else position.addUp(-3, SPEED);
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
}



