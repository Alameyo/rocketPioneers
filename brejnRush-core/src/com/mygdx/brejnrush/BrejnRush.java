package com.mygdx.brejnrush;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainClass extends ApplicationAdapter {
	private OrthographicCamera cam;
	private SpriteBatch batch;
	//private SpriteBatch backgroundBatch;
	private Texture img;
	private Texture rocketVostok;
	private Texture plomienGraphic;
	private BitmapFont font;
	private Rocket wostok, soyuz;
	private float timeHelper;
	private Music music;
	private Sound engine;
	private float engineLate;
	private Texture background;
	private boolean ogien;

	@Override
	public void create() {
		cam = new OrthographicCamera(1400, 800);
		batch = new SpriteBatch();
		//backgroundBatch = new SpriteBatch();
		background = new Texture(Gdx.files.internal("niebo.png"));
		img = new Texture("gagarin.jpg");
		rocketVostok = new Texture("rokieta_vostok.png");
		plomienGraphic = new Texture("plomien.png");
		
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(2);
		

		wostok = new Rocket(rocketVostok);
		wostok.x = 100;
		wostok.y = 10;
		wostok.height = wostok.getTextura1().getHeight();
		wostok.width = wostok.getTextura1().getWidth();

		soyuz = new Rocket(rocketVostok);
		soyuz.x = 300;
		soyuz.y = 10;
		soyuz.height = soyuz.getTextura1().getHeight();
		soyuz.width = soyuz.getTextura1().getWidth();

		music = Gdx.audio.newMusic(Gdx.files.internal("trayofgift-learning-to-be-alone.mp3"));
		music.play();
		music.setVolume(0.08f);
		engine = Gdx.audio.newSound(Gdx.files.internal("space_shuttle_sound.mp3"));
		engine.play();
		engine.loop();
		engine.pause();
		
		ogien =false;
	}

	@Override
	public void render() {
		

		
		batch.begin();
		
		update();
		
		Gdx.gl.glClearColor(10, 10, 10, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
		batch.draw(background, -100, -50, 1200, 300000);
		batch.draw(wostok.getTextura1(), wostok.x, wostok.y);
		if(ogien ==true){
		batch.draw(plomienGraphic, wostok.x, wostok.y-110);
		}
		batch.draw(soyuz.getTextura1(), soyuz.x, soyuz.y);
		font.draw(batch, "Poyehali na: "+ wostok.y, 50, wostok.y);

		batch.end();
	}

	private void update() {
		cam.update();
		// cam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.setProjectionMatrix(cam.combined);
		cam.position.set(wostok.x + wostok.width / 2, wostok.y + wostok.height / 2, 0);
		if(wostok.y >0){
			wostok.y -=500*Gdx.graphics.getDeltaTime();
		}
		
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			cam.zoom -= 0.02f;
		} else {
			cam.zoom = 1f;
		}

		if (Gdx.input.isKeyPressed(Keys.W)) {
			wostok.y += 2000 * Gdx.graphics.getDeltaTime();
			engine.resume();
			ogien = true;
			
		} else {
			engineLate += Gdx.graphics.getDeltaTime();
			if (engineLate > 0.75f) {
				ogien = false;
				engine.pause();
				engineLate=0f;
			
			}
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			wostok.x -= 500 * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			wostok.y -= 500 * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			wostok.x += 500 * Gdx.graphics.getDeltaTime();
		}
		if (wostok.overlaps(soyuz)) {
			wostok.x -= 250 * Gdx.graphics.getDeltaTime();
		}
		timeHelper += Gdx.graphics.getDeltaTime();
		if (timeHelper > 1) {
			soyuz.y += 50 * Gdx.graphics.getDeltaTime();
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
		font.dispose();
		music.dispose();
		background.dispose();
		engine.dispose();
		super.dispose();
	}
}
