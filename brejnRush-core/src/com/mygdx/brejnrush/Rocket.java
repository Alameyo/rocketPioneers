
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Rocket extends Rectangle {
	private Texture textura1;
	private float acceleration;
	private float accTimer;
	private float gravity = 3.3f;
	private Sound engine;
	private Texture plomienGraphic;
	private float engineLate;
	private boolean ogien;
	public Rocket(Texture textura1, float acceleration) {
		this.plomienGraphic = new Texture(Gdx.files.internal("plomien.png"));
		this.textura1 = textura1;
		this.acceleration = acceleration;
		this.accTimer = 0;
		this.engine = Gdx.audio.newSound(Gdx.files.internal("space_shuttle_sound.mp3"));
		engine.play();
		engine.loop();
		engine.pause();
		this.ogien = false;
	}

	public void move() {
		if (Gdx.input.isKeyPressed(Keys.W)) {
			if (this.y <= 0 && accTimer < 0) {
				accTimer = 0;
			}
			if (accTimer < 2000) {
				accTimer += this.acceleration * this.acceleration;
			}
			this.y += accTimer * Gdx.graphics.getDeltaTime();
			engine.resume();
			ogien = true;

		} else {
			if (accTimer > -4000) {
				accTimer -= gravity * gravity;
			}
			engineLate += Gdx.graphics.getDeltaTime();
			if (engineLate > 0.75f) {
				ogien = false;
				engine.pause();
				engineLate = 0f;
			}
			if (this.y >= 0) {
				this.y += accTimer * Gdx.graphics.getDeltaTime();
			}
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			this.x -= 800 * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			this.y -= 500 * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			this.x += 800 * Gdx.graphics.getDeltaTime();
		}
	}

	public void drawRocket(SpriteBatch batch) {
		batch.draw(this.getTextura1(), this.x, this.y, 80, 200);
		if (ogien == true) {
			batch.draw(plomienGraphic, this.x + 5, this.y - 70, 70, 80);
		}
	}
	public float getRocketX(){
		return this.x;
	}
	public float getRocketY(){
		return this.y;
	}
	
	public Texture getTextura1() {
		return textura1;
	}

	public float getAcceleration() {
		return acceleration;
	}
	public float getAccTimer(){
		return accTimer;
	}
}
