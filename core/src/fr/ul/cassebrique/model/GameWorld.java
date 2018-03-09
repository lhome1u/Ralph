package fr.ul.cassebrique.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.cassebrique.views.GameScreen;

import java.util.ArrayList;

public class GameWorld {

    public static final float METERS_TO_PIXELS = 250f;
    public static final float PIXELS_TO_METERS = 1/250f;

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
                if (wm.getNormal().x != 0) {
                    ball.ballBody.setLinearVelocity (
                            - ball.ballBody.getLinearVelocity().x,
                            ball.ballBody.getLinearVelocity().y
                    ) ;
                }else {

                    //if (wm.getNormal().y != 0) {
                        ball.ballBody.setLinearVelocity(
                                ball.ballBody.getLinearVelocity().x,
                                -ball.ballBody.getLinearVelocity().y
                        );
                   // }
                }
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
        balls.add(new Ball(this,racket.pos.x + (Racket.getRacketWidth()/2) - Ball.RAYON, racket.pos.y + Racket.getRacketHeight() +8));
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

    void setSpeed() {
        float rdm = (float) (Math.random() * (200 - (-200))) + (-200);
        balls.get(0).ballBody.setLinearVelocity(rdm *0.01f * PIXELS_TO_METERS, 200 *0.01f * PIXELS_TO_METERS);
    }

    public boolean ballLost(){
        if(balls.get(0).estSortie()){
            balls.remove(0);
            return true;
        }
        return false;
    }

    public void replace(){
        //racket.replace();
        System.out.println(balls.get(0).ballBody.getPosition());
        balls.get(0).setPosition(racket.pos.x + (Racket.getRacketWidth()/2) - Ball.RAYON, racket.pos.y + Racket.getRacketHeight() +8);
        System.out.println(balls.get(0).ballBody.getPosition());
        if(balls.get(1) != null){
            balls.get(0).setPosition(Background.WORLD_WIDTH_BORDER + (Background.BORDER/4), (Background.BORDER/4));
        }
        //setSpeed();
    }

    public boolean looseGame(){return balls.isEmpty();}

    public void destroy(Body brickBody) {
        world.destroyBody(brickBody);
    }
}
