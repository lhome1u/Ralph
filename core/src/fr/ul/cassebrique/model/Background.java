package fr.ul.cassebrique.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.cassebrique.dataFactories.TextureFactory;


public class Background {

    public static final int BORDER = TextureFactory.getTexBorder().getWidth();
    public static final int WORLD_WIDTH_BORDER = TextureFactory.getTexBack().getWidth()- BORDER;
    public static final int WORLD_HEIGHT = TextureFactory.getTexBack().getHeight() - BORDER;
    protected Body backBody;
    protected GameWorld gw;


    public Background(GameWorld g){
        gw = g;
        creationBody();
    }

    private void creationBody() {

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.bullet = false;
        bd.fixedRotation = false;

        backBody = gw.getWorld().createBody(bd);

        Vector2[] vects = {
                // bas gauche
                new Vector2((BORDER )* GameWorld.PIXELS_TO_METERS,0),

                // haut gauche
                new Vector2((BORDER )* GameWorld.PIXELS_TO_METERS, (WORLD_HEIGHT)* GameWorld.PIXELS_TO_METERS ),

                //haut droite
                new Vector2((WORLD_WIDTH_BORDER -BORDER )* GameWorld.PIXELS_TO_METERS  , (WORLD_HEIGHT)* GameWorld.PIXELS_TO_METERS) ,

                //bas droite
                new Vector2((WORLD_WIDTH_BORDER -BORDER )* GameWorld.PIXELS_TO_METERS , 0)
        };


        ChainShape shape = new ChainShape();
        shape.createChain(vects);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;

        backBody.createFixture(fd);
        shape.dispose();

    }

    public void draw(SpriteBatch sb){
        sb.draw(TextureFactory.getTexBack(),0,0);
    }
}
