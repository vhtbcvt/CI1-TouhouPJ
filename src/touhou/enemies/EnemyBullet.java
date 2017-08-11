package touhou.enemies;

import tklibs.SpriteUtils;
import touhou.bases.GameObject;
import touhou.bases.renderers.ImageRenderer;

public class EnemyBullet extends GameObject {
        public EnemyBullet() {
            super();
            this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/enemies/bullets/green.png"));
        }
        public void run(){
            position.addUp(0, 8);
        }
}

