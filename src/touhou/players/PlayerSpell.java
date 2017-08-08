package touhou.players;

import tklibs.SpriteUtils;
import touhou.bases.Vector2D;
import touhou.bases.renderers.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;


public class PlayerSpell {
    public Vector2D position1;
    public Vector2D position2;
    public ImageRenderer renderer;

    public PlayerSpell() {
        BufferedImage image = SpriteUtils.loadImage("assets/images/player-spells/a/1.png");
        position1 = new Vector2D();
        position2 = new Vector2D();
        renderer = new ImageRenderer(image);
    }

    public void render(Graphics2D g2d) {
        renderer.render(g2d, position1);
        renderer.render(g2d, position2);
    }

    public void run() {
        position1.addUp(0, -12);
        position2.addUp(0, -12);
    }
}
