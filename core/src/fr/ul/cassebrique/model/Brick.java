package fr.ul.cassebrique.model;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.cassebrique.dataFactories.TextureFactory;

public abstract class Brick {


    static final Texture GREEN_BRICK_TEX = TextureFactory.getTexGreenBrickA();
    static final Texture BROKEN_GREEN_BRICK_TEX = TextureFactory.getTexGreenBrickB();
    static final Texture BLUE_BRICK_TEX = TextureFactory.getTexBlueBrick();

    static final int BRICK_WIDTH  = GREEN_BRICK_TEX.getWidth();
    static final int BRICK_HEIGHT = GREEN_BRICK_TEX.getHeight();

    protected int vie;
    protected int posX;
    protected int posY;

    protected Body brickBody;

    public Brick(int coup){
        vie = coup;
        posX = 0;
        posY = 0;

    }

    public abstract void draw(SpriteBatch sb);
    public Body getBrickBody() { return brickBody; }

    public int getVie() {
        return vie;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int x) {
        this.posX = x;
    }

    public void setPosY(int y) {
        this.posY = y;
    }

    public void creationBody() {

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.bullet = false;
        bd.fixedRotation = false;

        brickBody = GameWorld.getWorld().createBody(bd);

        Vector2[] vects = {
                // bas gauche
                new Vector2((posX)* GameWorld.PIXELS_TO_METERS ,(posY)* GameWorld.PIXELS_TO_METERS),

                // haut gauche
                new Vector2((posX)* GameWorld.PIXELS_TO_METERS , (posY + BRICK_HEIGHT )* GameWorld.PIXELS_TO_METERS ),

                //haut droite
                new Vector2((posX + BRICK_WIDTH )* GameWorld.PIXELS_TO_METERS , (posY + BRICK_HEIGHT )* GameWorld.PIXELS_TO_METERS),

                //bas droite
                new Vector2( (posX + BRICK_WIDTH )* GameWorld.PIXELS_TO_METERS , (posY )* GameWorld.PIXELS_TO_METERS )
        };


        PolygonShape shape = new PolygonShape();
        shape.set(vects);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1;
        fd.friction = 0;
        fd.restitution = 1;

        brickBody.createFixture(fd);
        shape.dispose();

    }

    public void collision() {
        --vie;

    }
}
