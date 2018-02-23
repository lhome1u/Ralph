package fr.ul.cassebrique.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GreenBrick extends Brick{

    public GreenBrick(int vie){
        super(vie);

    }


    public void draw(SpriteBatch sb) {
        if(getVie() == 2) {
            sb.draw(GREEN_BRICK_TEX, posX , posY);
        }else{
            sb.draw(BROKEN_GREEN_BRICK_TEX, posX, posY);
        }
    }

}
