package fr.ul.cassebrique;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.cassebrique.views.GameScreen;

public class CasseBrique extends Game {
	protected GameScreen gs;
	
	@Override
	public void create () {
		gs = new GameScreen();
		setScreen(gs);
	}
	
	@Override
	public void dispose () {
		gs.dispose();
	}
}
