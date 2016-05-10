package com.mygdx.gagarin;

import java.util.LinkedList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainClass extends Game {
	private OrthographicCamera cam;
	private SpriteBatch batch;
	// private SpriteBatch backgroundBatch;
	private Texture img;
	private Texture rocketVostok;
	private Texture plomienGraphic;
	private BitmapFont font;
	private Rocket wostok, soyuz;
	private float timeHelper;

	private Music music;

	private Texture background;

	private float gravity;

	private Texture ufok;
	private LinkedList<Flyer> szkodniki;
	private int collets;
	private int counter;
	private double startTime;
	private double currentTime;
	private double score;
	private boolean gameOver;

	@Override
	public void create() {

		cam = new OrthographicCamera(1400, 800);
		batch = new SpriteBatch();

		background = new Texture(Gdx.files.internal("niebo.png"));
		background.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		img = new Texture("gagarin.jpg");
		ufok = new Texture("ufok.png");
		rocketVostok = new Texture("rokieta_vostok.png");
		plomienGraphic = new Texture("plomien.png");

		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(2);
		gravity = 3.3f;

		wostok = new Rocket(rocketVostok, 2.7f);
		wostok.x = 100;
		wostok.y = 10;

		wostok.height = 200;
		wostok.width = 80;

		counter = 0;
		collets = 0;

		soyuz = new Rocket(rocketVostok, 10f);
		soyuz.x = 300;
		soyuz.y = 10;
		soyuz.height = soyuz.getTextura1().getHeight();
		soyuz.width = soyuz.getTextura1().getWidth();

		music = Gdx.audio.newMusic(Gdx.files.internal("trayofgift-learning-to-be-alone.mp3"));
		music.play();
		music.setVolume(0.08f);

		szkodniki = new LinkedList<Flyer>();
		startTime = System.currentTimeMillis();
		currentTime =0;
		gameOver=false;
	}

	@Override
	public void render() {

		batch.begin();

		update();

		Gdx.gl.glClearColor(10, 10, 10, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.draw(background, wostok.x - 1500, -50, 3000, 300000);

		wostok.drawRocket(batch);

		batch.draw(soyuz.getTextura1(), soyuz.x, soyuz.y);
		font.draw(batch, "Poyehali na: " + wostok.y, cam.position.x - 650, cam.position.y - 300);
		font.draw(batch, "Damage: " + collets, cam.position.x + 520, cam.position.y - 300);
		font.draw(batch, "Current X: " + wostok.x, cam.position.x + 520, cam.position.y - 200);
		font.draw(batch, "Time: " + currentTime, cam.position.x + 520, cam.position.y - 100);
		if(wostok.y >=300000 || gameOver ==true){
			font.draw(batch, "Score: " + score , cam.position.x + 520, cam.position.y);
		
		}
		
		for (int i = 0; i < szkodniki.size(); i++) {

			if (szkodniki.get(i).isLive() == true) {
				szkodniki.get(i).drawFlyer(batch);
			}
		}
		batch.end();
	}

	private void update() {
		cam.update();

		batch.setProjectionMatrix(cam.combined);
		cam.position.set(wostok.x + wostok.width / 2, wostok.y + wostok.height + 300, 0);

		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			cam.zoom = 10.02f;
		} else {
			cam.zoom = 2f;
		}

		wostok.move();

		if (wostok.overlaps(soyuz)) {
			wostok.x -= 250 * Gdx.graphics.getDeltaTime();
		}
		timeHelper += Gdx.graphics.getDeltaTime();
		if (timeHelper > 1) {
			soyuz.y += 50 * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyJustPressed(Keys.M)) {
			if (music.isPlaying() == true) {
				music.stop();
			} else {
				music.play();
			}
		}
		if ((wostok.y) / (400) > counter) {

			szkodniki.add(counter, new Flyer(ufok, gravity, wostok));
			counter++;

		}
		for (int i = 0; i < szkodniki.size(); i++) {

			if (wostok.overlaps(szkodniki.get(i))&&szkodniki.get(i).isLive()==true) {
				szkodniki.get(i).setLive(false);
				collets++;
			}
		}
		currentTime =(System.currentTimeMillis() - startTime)/1000;
		if(wostok.y >=300000 && gameOver==false){
			score= 300000/(currentTime + collets);
			gameOver = true;
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
		super.dispose();
	}
}
