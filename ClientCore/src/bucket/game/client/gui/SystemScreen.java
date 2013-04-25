package bucket.game.client.gui;

import bucket.game.client.core.ScreenHandler;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;

/**
 * This class will be called when the player clicked on a tile on the @HexMap. In the constructor the relevant data to
 * identify a solar system will be passed on as well as creating a layout around a solar system view.
 * 
 * @author Katharina
 * 
 */
public class SystemScreen implements Screen {

	private ScreenHandler handler;

	public SystemScreen(ScreenHandler handler, Vector2 TileID) {
		this.handler = handler;
	}

	@Override
	public void render(float delta) {

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

}
