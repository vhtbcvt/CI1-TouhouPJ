package touhou.enemys;

import tklibs.SpriteUtils;
import touhou.bases.Constraints;
import touhou.bases.FrameCounter;
import touhou.bases.Vector2D;
import touhou.bases.renderers.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy {

    public Vector2D position;
    public ImageRenderer renderer;
    private Constraints constraints;
    private FrameCounter delay = new FrameCounter(8);
    private final int SPEED = 4;

    ArrayList<Enemy> enemies = new ArrayList<>();

    public Enemy(int x, int y){
        position = new Vector2D( x, y);
        BufferedImage image = SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png");
        renderer = new ImageRenderer(image);

    }


    public void run(){
        if (constraints != null) {
            constraints.make(position);
        }
        position.addUp(0,2);
    }



    public void render(Graphics2D g2d){
        renderer.render(g2d, position);
    }

    public void setConstraints(Constraints constraints){

        this.constraints = constraints;
    }


}



