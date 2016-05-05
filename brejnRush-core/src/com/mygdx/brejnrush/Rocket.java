package com.mygdx.gagarin;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Rocket extends Rectangle {
private Texture textura1;
private float acceleration;
public Rocket(Texture textura1, float acceleration){
	this.textura1 = textura1;
	this.acceleration = acceleration;
}
public Texture getTextura1() {
	return textura1;
}
public float getAcceleration() {
	return acceleration;
}

	
}
