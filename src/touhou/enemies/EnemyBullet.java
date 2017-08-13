package touhou.enemies;

import tklibs.SpriteUtils;
import touhou.bases.GameObject;
import touhou.bases.Vector2D;
import touhou.bases.physics.BoxCollider;
import touhou.bases.physics.Physics;
import touhou.bases.physics.PhysicsBody;
import touhou.bases.renderers.ImageRenderer;
import touhou.players.Player;

public class EnemyBullet extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;
    private Player player;

    public EnemyBullet() {
            super();
            this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/enemies/bullets/green.png"));
            boxCollider = new BoxCollider(20,20);
            this.children.add(boxCollider);
        }
        public void run(Vector2D parentPosition){
            super.run(parentPosition);
            position.addUp(0, 8);
        }


    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

}

