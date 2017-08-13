package touhou.bases.physics;

import touhou.GameWindow;
import touhou.bases.GameObject;
import touhou.bases.Vector2D;

public class BoxCollider extends GameObject{
    private float width;
    private float height;

    public BoxCollider(float x, float y, float width, float height){
        super();
        this.position.set(x, y);
        this.width = width;
        this.height = height;
    }

    public BoxCollider(float width, float height){
        this(0, 0, width, height);
    }

    public float left(){
        return this.screenPosition.x - this.width / 2;
    }

    public float right(){
        return this.screenPosition.x + this.width / 2;
    }

    public float top(){
        return this.screenPosition.y - this.height / 2;
    }

    public float bottom(){
        return this.screenPosition.y + this.height / 2;
    }

    public boolean intersects(BoxCollider other){
        return ((this.bottom() >= other.top())&&
                (this.top() <= other.bottom())&&
                (this.right() >= other.left())&&
                (this.left() <= other.right()));
    }

    @Override
    public String toString() {
        return "BoxCollider{" +
                "width=" + width +
                ", height=" + height +
                ", screenPosition=" + screenPosition +
                '}';
    }
}
