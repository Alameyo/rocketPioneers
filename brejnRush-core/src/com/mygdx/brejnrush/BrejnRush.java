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
import com.badlogic.gdx.graphics.Texture.TextureWrap;
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
	private float accTimer;
	private Music music;
	private Sound engine;
	private float engineLate;
	private Texture background;
	private boolean ogien;

	@Override
	public void create() {
		cam = new OrthographicCamera(1400, 800);
		batch = new SpriteBatch();
		
		background = new Texture(Gdx.files.internal("niebo.png"));
		background.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		img = new Texture("gagarin.jpg");
		rocketVostok = new Texture("rokieta_vostok.png");
		plomienGraphic = new Texture("plomien.png");
		
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(2);
		

		wostok = new Rocket(rocketVostok, 50f);
		wostok.x = 100;
		wostok.y = 10;
		//wostok.height = wostok.getTextura1().getHeight();
		//wostok.width = wostok.getTextura1().getWidth();
		wostok.height = 200;
		wostok.width = 80;

		soyuz = new Rocket(rocketVostok, 20f);
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
	
		batch.draw(background, wostok.x-1000, -50, 2600, 300000);
		batch.draw(wostok.getTextura1(), wostok.x, wostok.y, 80, 200);
		if(ogien ==true){
		batch.draw(plomienGraphic, wostok.x+5, wostok.y-70, 70, 80);
		}
		batch.draw(soyuz.getTextura1(), soyuz.x, soyuz.y);
		font.draw(batch, "Poyehali na: "+ wostok.y, cam.position.x-650, cam.position.y-300);

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
			if(accTimer<20){
				accTimer+=3*Gdx.graphics.getDeltaTime();
			}
			wostok.y += (200+wostok.getAcceleration()*accTimer) * Gdx.graphics.getDeltaTime();
			engine.resume();
			ogien = true;
			
		} else {
			
				accTimer=0;
			
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
		if (Gdx.input.isKeyJustPressed(Keys.M)) {
			if(music.isPlaying() == true){
				music.stop();
			}else{
				music.play();
			}
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
		font.dispose();
		music.dispose();
		plomienGraphic.dispose();
		background.dispose();
		engine.dispose();
		
		super.dispose();
	}
}
