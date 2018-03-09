package fr.ul.cassebrique.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.cassebrique.dataFactories.TextureFactory;

public class Ball {

    public static int RAYON = 12 ;
    protected GameWorld gw;
    protected Body ballBody;

    public Ball(GameWorld g, float x, float y){
        gw = g;
        creationBody(x,y);
    }

    private void creationBody(float x, float y) {
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.bullet = true;
        bd.fixedRotation = true;

        ballBody =gw.getWorld().createBody(bd);
        ballBody.setTransform(new Vector2(x* GameWorld.PIXELS_TO_METERS,y* GameWorld.PIXELS_TO_METERS), 0);

        CircleShape shape = new CircleShape();
        shape.setPosition(new Vector2(RAYON* GameWorld.PIXELS_TO_METERS,RAYON* GameWorld.PIXELS_TO_METERS));
        shape.setRadius(RAYON* GameWorld.PIXELS_TO_METERS);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1;
        fd.friction = 0;
        fd.restitution = 1;

        ballBody.createFixture(fd);
        shape.dispose();

    }

    public boolean estSortie(){
        return ballBody.getPosition().y < 0 - ( RAYON * GameWorld.PIXELS_TO_METERS) ;
    }

    public void draw(SpriteBatch sb){
        sb.draw(TextureFactory.getTexBall(), ballBody.getPosition().x* GameWorld.METERS_TO_PIXELS, ballBody.getPosition().y* GameWorld.METERS_TO_PIXELS);
    }


    public void setPosition(float v, float v1) {
        ballBody.setTransform(v * GameWorld.PIXELS_TO_METERS, v1 * GameWorld.PIXELS_TO_METERS, 0);
    }
}
