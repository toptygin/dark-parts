package cz.derpest.darkparts;

import com.badlogic.gdx.Game;

import cz.derpest.darkparts.screens.Play;

public class DarkParts extends Game {
	
	@Override
	public void create() {
		setScreen(new Play());
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
