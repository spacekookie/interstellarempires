package bucket.game.client.screens;

/* 
 * Copyright (c) 2012 ***REMOVED***
 * 
 package bucket.game.client.gui;
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import bucket.game.client.core.ScreenHandler;
import bucket.game.client.objects.groups.SolarMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * This class will be called when the player clicked on a tile on the @HexMap. In the
 * constructor the relevant data to identify a solar system will be passed on as well as
 * creating a layout around a solar system view.
 * 
 * @author ***REMOVED***
 * 
 */
public class SystemScreen implements Screen {

	private Skin skin;
	private SpriteBatch batch;
	private TextureAtlas atlas;
	private TextureRegion star, fleet;
	private Stage stage;
	private ScreenHandler handler;
	private Vector2 fleetPosition;
	private Table back;
	private TextButton backToMap;
	private Vector2 tileID;
	private SolarMap map;

	public SystemScreen(ScreenHandler handler, Vector2 tileID) {
		this.handler = handler;
		this.tileID = tileID;
		fleetPosition = new Vector2();
		fleetPosition.x = (Gdx.graphics.getWidth() / 2) - 300;
		fleetPosition.y = (Gdx.graphics.getHeight() / 2) - 150;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.

		batch.begin();
		stage.draw();

		// batch.draw(star, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0, 0,
		// 128, 128, 1, 1, 0);

		// batch.draw(fleet, fleetPosition.x, fleetPosition.y, 0, 0, 128, 128, 1, 1, 0);
		batch.end();

		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			fleetPosition.x += 2;
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			fleetPosition.x -= 2;
		if (Gdx.input.isKeyPressed(Keys.UP))
			fleetPosition.y += 2;
		if (Gdx.input.isKeyPressed(Keys.DOWN))
			fleetPosition.y -= 2;

		// if (Gdx.input.isButtonPressed(0)) {
		// System.out.println(Gdx.input.getX(0) + "," + Gdx.input.getY(0));
		// }
	}

	@Override
	public void resize(int width, int height) {
		if (stage == null)
			stage = new Stage(width, height, true);
		stage.clear();
		Gdx.input.setInputProcessor(stage);

		map = new SolarMap(tileID);
		stage.addActor(map);

		back = new Table();
		back.setFillParent(true);
		stage.addActor(back);
		backToMap = new TextButton("BACK", skin);

		back.add(backToMap).width(150);
		back.row();

		back.top().left();

		back.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				handler.setScreen(new MenuScreen(handler));
			}
		});

	}

	@Override
	public void show() {
		skin = new Skin(Gdx.files.internal("assets/gui/skins/defaults/uiskin.json"));
		atlas = new TextureAtlas(Gdx.files.internal("assets/solar/prot-solarsystem-icons.pack"));
		star = atlas.findRegion("prot-star-browndwarf");
		fleet = atlas.findRegion("prot-fleet-fighter-player");
		batch = new SpriteBatch();
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