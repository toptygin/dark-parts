package cz.derpest.darkparts.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite implements InputProcessor{

	public static final String TAG = "Player";

	private Vector2 velocity = new Vector2(0, 0);
	private float speed = 60 * 2; // <- pixels per second
	
	@Override
	public void draw(SpriteBatch spriteBatch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}
	
	public void update(float delta){
		setX(getX() + velocity.x * delta);
		setY(getY() + velocity.y * delta);
	}

	public Player(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean keyDown(int keycode) {
		Gdx.app.log(TAG, "The keycode is:" + keycode);
		switch (keycode) {
		case Keys.W:
			velocity.y = speed;
			break;
		case Keys.S:
			velocity.y = -speed;
			break;
		case Keys.A:
			velocity.x = -speed;
			break;
		case Keys.D:
			velocity.x = speed;
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.W:
			velocity.y = 0;
			break;
		case Keys.S:
			velocity.y = 0;
			break;
		case Keys.A:
			velocity.x = 0;
			break;
		case Keys.D:
			velocity.x = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
