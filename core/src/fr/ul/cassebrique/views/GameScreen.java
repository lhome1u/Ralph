package fr.ul.cassebrique.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import fr.ul.cassebrique.dataFactories.TextureFactory;
import fr.ul.cassebrique.model.GameWorld;
import fr.ul.cassebrique.model.Racket;

public class GameScreen extends ScreenAdapter {

    protected SpriteBatch sb;
    protected GameWorld gw = new GameWorld(this);
    protected int timeIter;
    private OrthographicCamera cam;
    private Box2DDebugRenderer debugRenderer;

    public GameScreen(){
        sb = new SpriteBatch();
        cam= new OrthographicCamera(1150, 700);
        //cam= new OrthographicCamera(7, 3);

        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
        debugRenderer = new Box2DDebugRenderer();
    }

    public void render(float delta){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        gw.draw(sb);
        sb.end();
        debugRenderer.render(gw.getWorld(), cam.combined);
        update();
    }

    public void dispose(){
        sb.dispose();
    }

    public void update(){
        Racket r = gw.getRacket();
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            if(Gdx.input.getX() < r.getPos().x){
                r.gauche();
            }else{
                r.droite();
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            r.gauche();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            r.droite();
        }
    }
}


/*
* Class gamescreen
attribut :
private OrthographicCamera cam;
private Box2DDebugRenderer debugRenderer;

constructeur :
cam= new OrthographicCamera(1150, 700);

cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
cam.update();
debugRenderer = new Box2DDebugRenderer();


render :
avant le begin
cam.update();
sb.setProjectionMatrix(cam.combined);

apres le end
debugRenderer.render(gw.getWorld(), cam.combined);
* */