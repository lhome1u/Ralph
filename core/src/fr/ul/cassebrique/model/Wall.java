package fr.ul.cassebrique.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import static fr.ul.cassebrique.model.Brick.*;

public class Wall {

    protected int nbL;
    protected int nbC;
    protected Brick[][] wall;
    protected static final Brick[][] wallInit = {
            {new BlueBrick(), new GreenBrick(2), new BlueBrick(), new GreenBrick(1), new BlueBrick(), new BlueBrick(), new GreenBrick(1), new BlueBrick(),  new GreenBrick(1), new BlueBrick()},
            {new BlueBrick(), new BlueBrick(), new GreenBrick(2), new BlueBrick(), new GreenBrick(1), new GreenBrick(1), new BlueBrick(),  new GreenBrick(2), new BlueBrick(), new BlueBrick()},
            {new BlueBrick(), new BlueBrick(), new BlueBrick(),  new GreenBrick(2), new BlueBrick(), new BlueBrick(),  new GreenBrick(2), new BlueBrick(), new BlueBrick(), new BlueBrick()},
            {new BlueBrick(), new BlueBrick(), new BlueBrick(), new BlueBrick(),  new GreenBrick(2),  new GreenBrick(2), new BlueBrick(), new BlueBrick(), new BlueBrick(), new BlueBrick()},
            {null, new BlueBrick(), null, null, new BlueBrick(), new BlueBrick(), null, null, new BlueBrick(), null},
     } ;
    protected GameWorld gw;

    public Wall(GameWorld w){
        nbL = wallInit.length;
        nbC = wallInit[0].length;
        wall = new Brick[nbL][nbC];
        this.setBricks(false);
        gw = w;
    }

    public void setBricks(boolean alea){
        if(alea){

        }else{
            wall = wallInit;
            for (int i = 0; i < nbL; i++) {
                for(int j = 0; j <nbC ; j++) {
                    Brick currBrick = wall[i][j];
                    if (currBrick == null) {
                        continue;
                    }
                    currBrick.setPosX(50 + BRICK_WIDTH*j);
                    currBrick.setPosY(550 - BRICK_HEIGHT*i);
                    currBrick.creationBody();

                }
            }
        }
    }

    public void draw(SpriteBatch sb){
        for (int i = 0; i < nbL; i++) {
            for(int j = 0; j < nbC ; j++) {
                Brick currBrick = wall[i][j] ;
                if (currBrick == null) { continue ; }
                currBrick.draw(sb) ;
            }
        }

    }

    public void destroyBrick(Body body) {
        for (int i = 0; i < nbL; i++) {
            for (int j = 0; j < nbC; j++) {
                if (wall[i][j] == null) {
                    continue;
                }
                if (wall[i][j].getBrickBody() == body) {
                    wall[i][j].collision();
                    if (wall[i][j].getVie() == 0) {
                        gw.getWorld().destroyBody(wall[i][j].getBrickBody());
                        wall[i][j] = null;
                    }
                    break;
                }
            }
        }
    }
}
