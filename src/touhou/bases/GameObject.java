package touhou.bases;

import touhou.bases.physics.Physics;
import touhou.bases.physics.PhysicsBody;
import touhou.bases.renderers.ImageRenderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class GameObject {
    protected Vector2D position;
    protected Vector2D screenPosition;
    protected ImageRenderer renderer;
    protected ArrayList<GameObject> children;
    protected boolean isActive;

    private static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newGameObjects = new Vector<>();

    public static void runAll(){
        // instanceof
        for (GameObject gameObject: gameObjects){
            if (gameObject.isActive)
                gameObject.run(new Vector2D(0,0));  // TODO: Optimize
        }

        for (GameObject newGameObject : newGameObjects){
            if (newGameObject instanceof PhysicsBody)Physics.add((PhysicsBody) newGameObject);
        }

        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
    }

    public static void renderAll(Graphics2D g2d){
        for (GameObject gameObject: gameObjects){
            if (gameObject.isActive) gameObject.render(g2d);
        }
    }

    public static void add(GameObject gameObject){
        newGameObjects.add(gameObject);
    }

    public GameObject() {
        position = new Vector2D();
        children = new ArrayList<>();
        screenPosition = new Vector2D();
        isActive = true;
    }

    public void run(Vector2D parentPosition){
        screenPosition = parentPosition.add(position);
        for (GameObject child: children){
            if (child.isActive) child.run(screenPosition);
        }
    }

    public void render(Graphics2D g2d){
        if (renderer != null){
            renderer.render(g2d, screenPosition); // null.render() => NullPointerException
        }
        for (GameObject child : children){
            if (child.isActive) child.render(g2d);
        }
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        if (position != null)
        this.position = position;
    }

    public void setRenderer(ImageRenderer renderer) {
        if (renderer != null)
        this.renderer = renderer;
    }

    public ImageRenderer getRenderer() {
        return renderer;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
