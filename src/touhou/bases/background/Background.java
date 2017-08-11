package touhou.bases.background;

import tklibs.SpriteUtils;
import touhou.bases.GameObject;
import touhou.bases.Vector2D;
import touhou.bases.renderers.ImageRenderer;

import java.awt.image.BufferedImage;

public class Background extends GameObject {

    public Background() {
        super();
        BufferedImage image = SpriteUtils.loadImage("assets/images/background/0.png");
        this.renderer = new ImageRenderer(image);
    }

    public void run() {
        if (position.y < 3109/2)
        position.addUp(0, 2);
    }
}
