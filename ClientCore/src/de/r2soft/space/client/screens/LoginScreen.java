/* 
 * Copyright (c) 2013 ***REMOVED***
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
 */

package de.r2soft.space.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.r2soft.space.client.core.ScreenHandler;
import de.r2soft.space.client.util.ResPack;

public class LoginScreen implements Screen {

	private ScreenHandler handler;

	/** UI elements */
	private Skin skin;
	private Stage stage;
	private Table intro, outro;
	private TextField userField, passField;
	private TextButton login, exit;

	/** Background stuff */
	private String name_clear, password_clear;

	public LoginScreen(ScreenHandler handler) {
		this.handler = handler;
		login = new TextButton("LOGIN", ResPack.UI_SKIN);
		passField = new TextField("", ResPack.UI_SKIN);
		userField = new TextField("", ResPack.UI_SKIN);
	}

	@Override
	public void resize(int w, int h) {
		if (stage == null)
			stage = new Stage(w, h, true);
		stage.clear();

		Gdx.input.setInputProcessor(stage);

		intro = new Table();
		intro.setFillParent(true);
		outro = new Table();
		outro.setFillParent(true);

		// Exiting the game
		exit = new TextButton("Exit Game", ResPack.UI_SKIN);
		outro.add(exit).width(ResPack.SIZE_UI_BUTTON_NAVIGON);
		outro.row();
		outro.top().left();

		userField.setMessageText("Your username");
		passField.setMessageText("Your password");
		passField.setPasswordCharacter('*');
		passField.setPasswordMode(true);

		intro.add(userField).width(ResPack.SIZE_UI_FIELD_CONTENT);
		intro.row();
		intro.add(passField).width(ResPack.SIZE_UI_FIELD_CONTENT);
		intro.row();
		intro.add(login).width(ResPack.SIZE_UI_FIELD_CONTENT);
		intro.row();

		login.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				scheduleLogin();
			}
		});

		exit.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.exit();
			}
		});

		stage.addActor(outro);
		stage.addActor(intro);
	}

	@Override
	public void show() {

	}

	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1); // Paint it black
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();

	}

	private void scheduleLogin() {
		name_clear = userField.getText().toString();
		password_clear = passField.getText().toString();

		// TODO: encrypt password and request Login from server.
		handler.setScreen(new MenuScreen(handler));

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

}
