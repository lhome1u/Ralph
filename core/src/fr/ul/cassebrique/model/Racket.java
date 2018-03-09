package fr.ul.cassebrique.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.cassebrique.dataFactories.TextureFactory;

import java.util.ArrayList;

public class Racket {

    public static final int RACKET_WIDTH  = TextureFactory.getTexRacket().getWidth();
    public static final int RACKET_HEIGHT = TextureFactory.getTexRacket().getHeight();
    private static final int RAYON = RACKET_HEIGHT/2 ;

    private final int PAS = 10 ;

    protected GameWorld gw;
    protected Vector2 pos;
    protected ArrayList<Body> bodys;
    protected Body bgBody;
    protected Body bdBody;
    protected Body milieuBody;



    public Racket(GameWorld gameWorld){
        gw = gameWorld;
        pos = new Vector2( (TextureFactory.getTexBack().getWidth() /2) - (RACKET_WIDTH /2) -20, 50);
        bodys = new ArrayList<Body>();
        creationBody(pos.x, pos.y);
    }

    public void draw(SpriteBatch sb){
        sb.draw(TextureFactory.getTexRacket(), pos.x , pos.y );
        //sb.draw(TextureFactory.getTexRacket(), racketBody.getPosition().x, racketBody.getPosition().y);
    }

    public static int getRacketWidth() {
        return RACKET_WIDTH;
    }

    public static int getRacketHeight() {
        return RACKET_HEIGHT;
    }

    public GameWorld getGw() {
        return gw;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public void replace() {
        int widthRack  = TextureFactory.getTexRacket().getWidth() ;

        pos = new Vector2( (TextureFactory.getTexBack().getWidth() /2) - (RACKET_WIDTH /2)-20, 50);
        bdBody.setTransform((pos.x + RAYON)* GameWorld.PIXELS_TO_METERS, (pos.y + RAYON)* GameWorld.PIXELS_TO_METERS , 0); ;
        milieuBody.setTransform((pos.x + RAYON*2) * GameWorld.PIXELS_TO_METERS, (pos.y + RAYON)* GameWorld.PIXELS_TO_METERS, 0); ;
        bgBody.setTransform((pos.x + widthRack - RAYON ) * GameWorld.PIXELS_TO_METERS, (pos.y + RAYON) * GameWorld.PIXELS_TO_METERS, 0); ;
    }

    public void droite() {

        int wBordure = Background.WORLD_WIDTH_BORDER - RACKET_WIDTH ;
        if (pos.x <=  wBordure - Background.BORDER - PAS) {
            pos.x += PAS ;

            bdBody.setTransform (
                    bdBody.getPosition().x + (PAS * GameWorld.PIXELS_TO_METERS),
                    bdBody.getPosition().y,
                    0
            ) ;
            milieuBody.setTransform (
                    milieuBody.getPosition().x + (PAS * GameWorld.PIXELS_TO_METERS),
                    milieuBody.getPosition().y,
                    0
            ) ;
            bgBody.setTransform (
                    bgBody.getPosition().x + (PAS * GameWorld.PIXELS_TO_METERS),
                    bgBody.getPosition().y,
                    0
            ) ;
        }
        else {
            bdBody.setTransform (
                    bdBody.getPosition().x,
                    bdBody.getPosition().y,
                    0
            ) ;
            milieuBody.setTransform (
                    milieuBody.getPosition().x,
                    milieuBody.getPosition().y,
                    0
            ) ;
            bgBody.setTransform (
                    bgBody.getPosition().x,
                    bgBody.getPosition().y,
                    0
            ) ;
        }
    }

    public void gauche() {
        int wBordure = Background.BORDER ;
        if (pos.x >= wBordure + PAS) {
            pos.x -= PAS ;

            bdBody.setTransform (
                    bdBody.getPosition().x - (PAS * GameWorld.PIXELS_TO_METERS),
                    bdBody.getPosition().y,
                    0
            ) ;
            milieuBody.setTransform (
                    milieuBody.getPosition().x - (PAS * GameWorld.PIXELS_TO_METERS),
                    milieuBody.getPosition().y,
                    0
            ) ;
            bgBody.setTransform (
                    bgBody.getPosition().x - (PAS * GameWorld.PIXELS_TO_METERS),
                    bgBody.getPosition().y,
                    0
            ) ;
        }
        else {
            bdBody.setTransform (
                    bdBody.getPosition().x,
                    bdBody.getPosition().y,
                    0
            ) ;
            milieuBody.setTransform (
                    milieuBody.getPosition().x,
                    milieuBody.getPosition().y,
                    0
            ) ;
            bgBody.setTransform (
                    bgBody.getPosition().x,
                    bgBody.getPosition().y,
                    0
            ) ;
        }
    }


    private void creationBody(float x, float y) {

        // bgauche
        BodyDef bodyDef1 = new BodyDef() ;
        bodyDef1.type    = BodyDef.BodyType.StaticBody ;
        bodyDef1.fixedRotation = false ;
        bodyDef1.position.set((x + RAYON)* GameWorld.PIXELS_TO_METERS, (y + RAYON)* GameWorld.PIXELS_TO_METERS) ;
        bgBody = gw.getWorld().createBody(bodyDef1) ;

        CircleShape shapeBGauche = new CircleShape() ;
        shapeBGauche.setRadius(RAYON * GameWorld.PIXELS_TO_METERS) ;

        FixtureDef fixtureDef1  = new FixtureDef() ;
        fixtureDef1.shape       = shapeBGauche ;
        fixtureDef1.density     = 1 ;
        fixtureDef1.restitution = 1 ;
        fixtureDef1.friction    = 0 ;
        bgBody.createFixture(fixtureDef1) ;
        bodys.add(bgBody);

        // bmilieu
        int heightRack = TextureFactory.getTexRacket().getHeight();
        int widthRack  = TextureFactory.getTexRacket().getWidth() ;

        Vector2[] tab = new Vector2[4];
        //bas à gauche
        tab[0] = new Vector2((x + RAYON * 2) * GameWorld.PIXELS_TO_METERS, y * GameWorld.PIXELS_TO_METERS);
        //haut à gauche
        tab[1] = new Vector2((x + RAYON * 2) * GameWorld.PIXELS_TO_METERS, (y + heightRack) * GameWorld.PIXELS_TO_METERS);
        //haut à droite
        tab[2] = new Vector2((x + widthRack - RAYON * 2) * GameWorld.PIXELS_TO_METERS, (y + heightRack) * GameWorld.PIXELS_TO_METERS);
        //bas à droite
        tab[3] = new Vector2((x + widthRack - RAYON * 2 ) * GameWorld.PIXELS_TO_METERS, y * GameWorld.PIXELS_TO_METERS);

        PolygonShape shapeBMilieu = new PolygonShape();
        shapeBMilieu.set(tab);

        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.type = BodyDef.BodyType.StaticBody;
        milieuBody = gw.getWorld().createBody(bodyDef2);

        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.shape = shapeBMilieu;
        fixtureDef2.density = 1;
        fixtureDef2.restitution = 1;
        fixtureDef2.friction = 0;
        milieuBody.createFixture(fixtureDef2);
        bodys.add(milieuBody);

        // bdroite
        BodyDef bodyDef3 = new BodyDef() ;
        bodyDef3.type    = BodyDef.BodyType.StaticBody ;
        bodyDef3.fixedRotation = false ;
        bodyDef3.position.set((x + widthRack - RAYON ) * GameWorld.PIXELS_TO_METERS, (y + RAYON) * GameWorld.PIXELS_TO_METERS) ;
        bdBody = gw.getWorld().createBody(bodyDef3) ;

        CircleShape shapeBDroite =  new CircleShape() ;
        shapeBDroite.setRadius(RAYON * GameWorld.PIXELS_TO_METERS) ;

        FixtureDef fixtureDef3  = new FixtureDef() ;
        fixtureDef3.shape       = shapeBDroite ;
        fixtureDef3.density     = 1 ;
        fixtureDef3.restitution = 1 ;
        fixtureDef3.friction    = 0 ;
        bdBody.createFixture(fixtureDef3) ;
        bodys.add(bdBody);
    }

    public ArrayList<Body> getBodies() {
        return bodys;
    }


}
