package touhou.players;

import tklibs.SpriteUtils;
import touhou.bases.GameObject;
import touhou.bases.Vector2D;
import touhou.bases.physics.BoxCollider;
import touhou.bases.physics.Physics;
import touhou.bases.physics.PhysicsBody;
import touhou.bases.renderers.ImageRenderer;
import touhou.enemies.Enemy;

import java.awt.image.BufferedImage;


public class PlayerSpell extends GameObject implements PhysicsBody {


    private BoxCollider boxCollider;

    public PlayerSpell() {
        super();
        BufferedImage image = SpriteUtils.loadImage("assets/images/player-spells/a/1.png");
        renderer = new ImageRenderer(image);
        boxCollider = new BoxCollider(20,20);
        this.children.add(boxCollider);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        position.addUp(0, -12);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
