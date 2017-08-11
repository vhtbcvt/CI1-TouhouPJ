package touhou.players;

import tklibs.SpriteUtils;
import touhou.bases.GameObject;
import touhou.bases.renderers.ImageRenderer;
import java.awt.image.BufferedImage;


public class PlayerSpell extends GameObject {


    public PlayerSpell() {
        super();
        BufferedImage image = SpriteUtils.loadImage("assets/images/player-spells/a/1.png");
        renderer = new ImageRenderer(image);
    }

    public void run() {
        position.addUp(0, -12);
    }
}
