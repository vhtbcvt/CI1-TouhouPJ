package touhou.bases.physics;

import touhou.bases.Vector2D;
import touhou.enemies.Enemy;
import touhou.enemies.EnemyBullet;
import touhou.players.Player;
import touhou.players.PlayerSpell;

import javax.swing.*;
import java.util.Vector;

public class Physics {
    private static Vector<PhysicsBody> bodies = new Vector<>();

    public static PlayerSpell collideWithSpell(BoxCollider boxCollider){
        for (PhysicsBody body : bodies){
            if (body.isActive())
                if (body instanceof PlayerSpell && body.getBoxCollider().intersects(boxCollider)){
                    return (PlayerSpell) body;
            }
        }

        return null;
    }

    public static EnemyBullet collideWithEnemyBullet(BoxCollider boxCollider){
        for (PhysicsBody body : bodies){
            if (body.isActive())
                if (body instanceof EnemyBullet && body.getBoxCollider().intersects(boxCollider)){
                    return (EnemyBullet) body;
                }
        }

        return null;
    }

    public static Enemy collideWithEnemy(BoxCollider boxCollider){
        for (PhysicsBody body : bodies){
            if (body.isActive())
                if (body instanceof Enemy && body.getBoxCollider().intersects(boxCollider)){
                    return (Enemy) body;
                }
        }

        return null;
    }

    public static Player collideWithPlayer(BoxCollider boxCollider){
        for (PhysicsBody body : bodies){
            if (body.isActive())
                if (body instanceof Player && body.getBoxCollider().intersects(boxCollider)){
                    return (Player) body;
                }
        }

        return null;
    }

    public static void add(PhysicsBody body) {
        bodies.add(body);
    }


}
