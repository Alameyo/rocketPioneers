package com.mygdx.gagarin;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gagarin.screens.GameScreen;

public class MainClass extends Game {

	private SpriteBatch batch;

	@Override
	public void create() {

		batch = new SpriteBatch();
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render() {
		super.render();

	}

	@Override
	public void dispose() {
		batch.dispose();
		super.dispose();
	}

	public SpriteBatch getBatch() {
		return this.batch;
	}
}
