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
        bd.fixedRotation = false;

        ballBody =gw.getWorld().createBody(bd);
        ballBody.setTransform(new Vector2(x,y), 0);

        CircleShape shape = new CircleShape();
        shape.setPosition(new Vector2(RAYON,RAYON));
        shape.setRadius(RAYON);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1;
        fd.friction = 0;
        fd.restitution = 1;

        ballBody.createFixture(fd);
        shape.dispose();

    }


    public void draw(SpriteBatch sb){
        sb.draw(TextureFactory.getTexBall(), ballBody.getPosition().x, ballBody.getPosition().y);
    }


}