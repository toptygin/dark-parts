package cz.derpest.darkparts.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import cz.derpest.darkparts.entities.Player;

/**
 * 
 * @author vbh
 * The main game screen
 */

public class Play implements Screen {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Player player;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.setView(camera);
		renderer.render();
		
		renderer.getSpriteBatch().begin();
		player.draw(renderer.getSpriteBatch());
		renderer.getSpriteBatch().end();
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportHeight = height;
		camera.viewportWidth = width;
		camera.update();
	}

	@Override
	public void show() {
		TmxMapLoader loader = new TmxMapLoader();
		map = loader.load("maps/mapa.tmx");
		
		renderer = new OrthogonalTiledMapRenderer(map);
		
		camera = new OrthographicCamera();
		camera.translate(1700, 2350);
		
		player = new Player(new Sprite(new Texture("character_sprites/witch.png")));
		Gdx.input.setInputProcessor(player);
		player.setX(1700);
		player.setY(2350);
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
		player.getTexture().dispose();
	}

}
