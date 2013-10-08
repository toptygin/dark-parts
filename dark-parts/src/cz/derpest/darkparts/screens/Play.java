package cz.derpest.darkparts.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * 
 * @author vbh
 * The main game screen
 */

public class Play implements Screen {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private int cameraMovePx = 30;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.setView(camera);
		
		// Here is the code for moving the camera as of now, later it will should
		// be enhanced to make possible moving the main character
		if(Gdx.input.isKeyPressed(Keys.W)) camera.translate(0, cameraMovePx * Gdx.graphics.getDeltaTime()); 
		if(Gdx.input.isKeyPressed(Keys.S)) camera.translate(0, -cameraMovePx * Gdx.graphics.getDeltaTime());
		if(Gdx.input.isKeyPressed(Keys.D)) camera.translate(cameraMovePx * Gdx.graphics.getDeltaTime(), 0);
		if(Gdx.input.isKeyPressed(Keys.A)) camera.translate(-cameraMovePx * Gdx.graphics.getDeltaTime(), 0);
		camera.update();
		
		renderer.render();
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
		map = loader.load("data/mapa.tmx");
		
		renderer = new OrthogonalTiledMapRenderer(map);
		
		camera = new OrthographicCamera();
		camera.translate(1700, 2350);
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
	}

}
