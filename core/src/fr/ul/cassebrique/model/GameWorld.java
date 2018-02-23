package fr.ul.cassebrique.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.cassebrique.views.GameScreen;

import java.util.ArrayList;

public class GameWorld {

    public static final float METERS_TO_PIXELS = 250F;
    public static final float PIXELS_TO_METERS = 1/250F;

    protected GameScreen gs;
    protected Wall wall;
    protected Background background;
    protected Racket racket;
    protected ArrayList<Ball> balls;
    protected static World world;
    protected Body bodyBrickColl;

    public GameWorld(GameScreen gs){
        this.gs = gs;
        world = new World(new Vector2(0,0), true);
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                WorldManifold wm = contact.getWorldManifold();

                Ball ball = balls.get(0);
                Body other;

                if(contact.getFixtureA().getBody() == ball.ballBody){
                    other = contact.getFixtureB().getBody();
                }else{
                    other = contact.getFixtureA().getBody();
                }


                if(racket.getBodies().contains(other)){
                    // collision avec la racket

                }else if (background.backBody == other){
                    // collision avec le background

                }else{
                    // collision avec une brique
                    bodyBrickColl = other;
                }



                ball.ballBody.setLinearVelocity (
                        (ball.ballBody.getLinearVelocity().x * 2) * METERS_TO_PIXELS ,
                        (- ball.ballBody.getLinearVelocity().y * 2) * METERS_TO_PIXELS
                ) ;
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
        wall = new Wall(this);
        background = new Background(this);
        racket = new Racket(this);
        balls = new ArrayList<Ball>();
        placementBalls();
        setSpeed();
    }

    private void placementBalls() {
        balls.add(new Ball(this,racket.pos.x + (Racket.getRacketWidth()/2) - Ball.RAYON, racket.pos.y + Racket.getRacketHeight()));
        balls.add(new Ball(this, Background.WORLD_WIDTH_BORDER + (Background.BORDER/4), (Background.BORDER/4)));
        balls.add(new Ball(this, Background.WORLD_WIDTH_BORDER + (Background.BORDER/4), Background.BORDER+ (25/2)));
    }

    public void draw(SpriteBatch sb){
        background.draw(sb);
        wall.draw(sb);
        racket.draw(sb);
        for (Ball b: balls) {
            b.draw(sb);
        }
        world.step(6,2,0);
        if (bodyBrickColl != null) {
            wall.destroyBrick(bodyBrickColl);
            bodyBrickColl = null;
        }
    }

    public Racket getRacket() {
        return racket;
    }

    public static World getWorld() {
        return world;
    }

    public void setSpeed(){
        int rand = (int)(Math.random()*400)-200;
        balls.get(0).ballBody.setLinearVelocity(rand * METERS_TO_PIXELS,200 * METERS_TO_PIXELS);
    }

    public Body getBodyBrickColl() {
        return bodyBrickColl;
    }

    public void emptyBodyBrickColl() {
        bodyBrickColl = null ;
    }

    public void destroy(Body brickBody) {
        world.destroyBody(brickBody);
    }
}
