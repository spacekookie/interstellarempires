/* #########################################################################
 * Copyright (c) 2013 Random Robot Softworks
 * 
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
 * 
 ######################################################################### */
package de.r2soft.space.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import de.r2soft.space.client.core.ScreenHandler;
import de.r2soft.space.client.settings.Resources;
import de.r2soft.space.client.util.ResPack;

/**
 * This screen will enable the user to change stuff about their game client
 * 
 * @author Katharina
 * 
 */
public class SettingsScreen implements Screen {

	/** Container and Backends */
	private ScreenHandler handler;
	private Stage stage;
	private TextButton button;
	private Table table;
	private Table navigation;
	private Preferences prefs;

	private CheckBox skipIntro;
	private Label introLabel;

	public SettingsScreen(ScreenHandler handler) {
		this.handler = handler;
		Gdx.graphics.setTitle(Resources.SUPERTITLE + " - " + Resources.VERSION_NUMBER + " - "
				+ Resources.SCREENTITLE_SETTINGS);
		prefs = Gdx.app.getPreferences(Resources.PREFERENCE_FILE_NAME);

		skipIntro = new CheckBox("", ResPack.UI_SKIN);
		if (prefs.contains(Resources.PREFERENCE_SKIP_INTRO))
			skipIntro.setChecked(prefs.getBoolean(Resources.PREFERENCE_SKIP_INTRO));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();

		if (skipIntro.isChecked()) {
			prefs.putBoolean(Resources.PREFERENCE_SKIP_INTRO, true);
		}
		else {
			prefs.putBoolean(Resources.PREFERENCE_SKIP_INTRO, false);
		}

		skipIntro.setChecked(prefs.getBoolean(Resources.PREFERENCE_SKIP_INTRO));
		prefs.flush();

	}

	@Override
	public void resize(int width, int height) {
		if (stage == null)
			stage = new Stage(width, height, true);
		stage.clear();

		// Collect touchdown events
		Gdx.input.setInputProcessor(stage);
		stage.setViewport(width, height, true);

		navigation = new Table();
		navigation.setFillParent(true);
		stage.addActor(navigation);
		button = new TextButton("Back to main screen", ResPack.UI_SKIN);

		navigation.add(button).width(ResPack.SIZE_UI_BUTTON_NAVIGON);
		navigation.row();

		navigation.top().right();

		stage.addActor(button);

		button.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				handler.setScreen(new MenuScreen(handler));
			}
		});

		/** Settings Table */

		table = new Table();
		table.setFillParent(true);

		introLabel = new Label("Skip the intro", ResPack.UI_SKIN);

		table.add(skipIntro);
		table.add(introLabel).width(ResPack.SIZE_UI_FIELD_CONTENT);
		table.row();
		table.center();
		stage.addActor(table);

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

}
