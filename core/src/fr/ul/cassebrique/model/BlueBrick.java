package fr.ul.cassebrique.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BlueBrick extends Brick{


    public BlueBrick(){
        super(1);

    }


    public void draw(SpriteBatch sb) {
        if (getVie() > 0) {
            sb.draw(BLUE_BRICK_TEX, posX, posY);
        }
    }
}
