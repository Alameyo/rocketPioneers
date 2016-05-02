package com.mygdx.brejnrush;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BrejnRush extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("gagarin.jpg");
		font = new BitmapFont();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 100, 100);
		font.setColor(Color.WHITE);
		font.draw(batch, "Poyechali", 50, 50);
		batch.end();
	}
	@Override
	public void dispose(){
		batch.dispose();
		font.dispose();
		img.dispose();
		
	}
}
