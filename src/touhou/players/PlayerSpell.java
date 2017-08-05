package touhou.players;

import tklibs.SpriteUtils;
import touhou.bases.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by huynq on 8/2/17.
 */
public class PlayerSpell {
    public Vector2D position;
    public BufferedImage image;

    public PlayerSpell() {
        image = SpriteUtils.loadImage("assets/images/player-spells/a/0.png");
        position = new Vector2D();
    }

    public void render(Graphics2D g2d) {
        g2d.drawImage(image, (int)position.x, (int)position.y, null);
    }

    public void run() {
        position.addUp(0, -10);
    }
}
