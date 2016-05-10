package com.mygdx.gagarin;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Flyer extends Rectangle {
	private Texture textura1;
	private float acceleration;
	private boolean live;
	public Flyer(Texture textura1, float acceleration, Rocket rakieta){
		this.textura1 = textura1;
		this.acceleration = acceleration;
		this.height = this.textura1.getHeight()-10;
		this.width = this.textura1.getWidth()-200;
		this.x = MathUtils.random(-1000f+rakieta.x, +1000f+rakieta.x);
		this.y = rakieta.y +2000;
		this.live = true;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public boolean isLive() {
		return live;
	}
	public Texture getTextura1() {
		return textura1;
	}
	public float getAcceleration() {
		return acceleration;
	}
	public void drawFlyer(SpriteBatch batch) {
		batch.draw(this.textura1, this.x, this.y, 200, 150);
	}
}
