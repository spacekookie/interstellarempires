package bucket.game.client.gui;

/* 
 * Copyright (c) 2012 Katharina Fey
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

import bucket.game.client.actors.HexMap;
import bucket.game.client.core.ScreenHandler;
import bucket.game.client.util.Coordinator;
import bucket.game.client.util.Settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

/**
 * For now the only menu screen with buttons and a scrollable map view. Hopefully :)
 * 
 * @author: Katharina
 */
public class MenuScreen implements Screen {

	/** Container and Backends */
	private ScreenHandler handler;
	private Stage stage;
	private Skin skin;
	private TextButton settings;
	private TextButton exitGame;

	private HexMap map;

	public MenuScreen(ScreenHandler handler) {
		this.handler = handler;
		Gdx.graphics.setTitle(Settings.SUPERTITLE + " - " + Settings.VERSION_NUMBER + " - " + Settings.SCREENTITLE_HOME);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		if (stage == null)
			stage = new Stage(width, height, true);
		stage.clear();

		// Collect touchdown events
		Gdx.input.setInputProcessor(stage);

		Table table = new Table();
		Table mapTable = new Table();

		Table backToIntro = new Table();
		backToIntro.setFillParent(true);
		stage.addActor(backToIntro);
		TextButton backham = new TextButton("Back to Intro", skin);
		backham.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				handler.setScreen(new TweenScreen(handler));
			}
		});

		backToIntro.add(backham);
		backToIntro.row();
		backToIntro.top().left();

		table.setFillParent(true);
		mapTable.setFillParent(true);
		stage.addActor(table);
		stage.addActor(mapTable);

		map = new HexMap(600f, 350f, handler);

		mapTable.add(map);
		mapTable.center(); // First center it
		mapTable.setX(-300); // Then reduce the X value // Gdx.graphics.getWidth() / 3
		map.setScreenPosition(mapTable.getX(), mapTable.getY());

		settings = new TextButton("Settings", skin);
		exitGame = new TextButton("Exit Game", skin);

		table.add(settings).width(200);
		table.row();
		table.add(exitGame).width(200);
		table.top().right();

		/** OnClickListener */

		settings.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				handler.setScreen(new SettingsScreen(handler));
			}
		});

		exitGame.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.exit();
			}
		});

	}

	@Override
	public void show() {
		skin = new Skin(Gdx.files.internal("assets/gui/skins/defaults/uiskin.json"));
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
		stage.dispose();
		map.dispose();

	}

}
