package touhou.enemies;

import touhou.bases.FrameCounter;
import touhou.bases.GameObject;

import java.util.Random;

public class EnemySpawner extends GameObject {
    private FrameCounter frameCounter;
    private Random random;

    public EnemySpawner(){
        super();
        frameCounter = new FrameCounter(30);
        random = new Random();
    }

    public void spawn(){
        if (frameCounter.run()) {
            frameCounter.reset();
            Enemy enemy = new Enemy();
            enemy.getPosition().set(random.nextInt(384), 40);
            GameObject.add(enemy);
        }
    }

    public void run(){
        spawn();
    }
}
