package cz.derpest.darkparts.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite implements InputProcessor{
	
	//TODO HitBoxes, stop player from getting stuck agains a tree, make a hitbox draw function

	public static final String TAG = "Player";
	public static final float hitBoxWidth = 48;
	public static final float hitBoxHeight = 64;

	private Vector2 velocity = new Vector2(0, 0);	
	private float speed = 60 * 2; // <- pixels per second	
	private boolean zoomedOut = false;
	private float zoom = 1.5f;
	private TiledMapTileLayer collisionLayer;
	private float increment;
	private String blockedKey = "blocked";
	private Rectangle hitBox;
	
	public Player(Sprite sprite, TiledMapTileLayer collisionLayer) {
		super(sprite);
		this.collisionLayer =  collisionLayer;
		hitBox = new Rectangle(this.getX() + (this.getWidth() - hitBoxWidth)/2,this.getY() + (this.getHeight() - hitBoxHeight)/2, hitBoxWidth, hitBoxHeight);
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}
	
	//Most of the body of this update function belongs to Dermetfan 
	//taken from here: "http://pastebin.com/3Fu03cns"
	public void update(float delta){
		// save old position
        float oldX = getX(), oldY = getY();
        boolean collisionX = false, collisionY = false;
 
        // move on x
        setX(getX() + velocity.x * delta);
 
        // calculate the increment for step in #collidesLeft() and #collidesRight()
        increment = collisionLayer.getTileWidth();
        increment = hitBox.getWidth() < increment ? hitBox.getWidth() / 2 : increment / 2;
        
 
        if(velocity.x < 0) // going left
                collisionX = collidesLeft();
        else if(velocity.x > 0) // going right
                collisionX = collidesRight();
 
        // react to x collision
        if(collisionX) {
                setX(oldX);
                velocity.x = 0;
                Gdx.app.log(TAG, "Collides on X");
        }
 
        // move on y
        setY(getY() + velocity.y * delta);
 
        // calculate the increment for step in #collidesBottom() and #collidesTop()
        increment = collisionLayer.getTileHeight();
        increment = hitBox.getHeight() < increment ? hitBox.getHeight() / 2 : increment / 2;
 
        
        if(velocity.y < 0) // going left
            collisionY = collidesBottom();
        else if(velocity.y > 0) // going right
            collisionY = collidesTop();
        // react to y collision
        if(collisionY) {
                setY(oldY);
                velocity.y = 0;
                Gdx.app.log(TAG, "Collides on Y");
        }
        //update the hitBox location
        hitBox.setX(this.getX() + (this.getWidth() - hitBoxWidth)/2);
        hitBox.setY(this.getY() + (this.getHeight() - hitBoxHeight)/2);
	}
	//TODO Here might be the error 
	private boolean isCellBlocked(float x, float y) {
		Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
		return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(blockedKey);
	}
	 
	public boolean collidesRight() {
	        for(float step = 0; step <= hitBox.getHeight(); step += increment)
	                if(isCellBlocked(hitBox.getX() + hitBox.getWidth(), hitBox.getY() + step))
	                        return true;
	        return false;
	}
	 
	public boolean collidesLeft() {
	        for(float step = 0; step <= hitBox.getHeight(); step += increment)
	                if(isCellBlocked(hitBox.getX(), hitBox.getY() + step))
	                        return true;
	        return false;
	}
	 
	public boolean collidesTop() {
	        for(float step = 0; step <= hitBox.getWidth(); step += increment)
	                if(isCellBlocked(hitBox.getX() + step, hitBox.getY() + hitBox.getHeight()))
	                        return true;
	        return false;
	}
	 
	public boolean collidesBottom() {
	        for(float step = 0; step <= hitBox.getWidth(); step += increment)
	                if(isCellBlocked(hitBox.getX() + step, hitBox.getY()))
	                        return true;
	        return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		//For logging key input
		//Gdx.app.log(TAG, "The keycode is:" + keycode);
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
		case Keys.SHIFT_LEFT:
			zoomedOut = !zoomedOut;
			break;
		case Keys.I:
			Gdx.app.log(TAG, "Sprite x:" + this.getX()+"\n Sprite y: " + this.getY());
			Gdx.app.log(TAG, "Hitbox x:" + hitBox.getX()+"\n Hitbox y: " + hitBox.getY());
			Gdx.app.log(TAG, "Hitbox width:"+ hitBox.getWidth() + "Height"+ hitBox.getHeight());
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

	public boolean isZoomedOut() {
		return zoomedOut;
	}

	public void setZoomedOut(boolean zoomedOut) {
		this.zoomedOut = zoomedOut;
	}

	public float getZoom() {
		return zoom;
	}

	public void setZoom(float zoom) {
		this.zoom = zoom;
	}

}
