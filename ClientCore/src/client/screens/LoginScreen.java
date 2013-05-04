/* 
 * Copyright (c) 2013 Katharina Fey
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

package client.screens;

import java.awt.Label;

import client.core.ScreenHandler;
import client.util.ResourcePacker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LoginScreen implements Screen {

	private ScreenHandler handler;

	private Skin skin;
	private Stage stage;

	public LoginScreen(ScreenHandler handler) {
		this.handler = handler;
	}

	@Override
	public void resize(int w, int h) {
		if (stage == null)
			stage = new Stage(w, h, true);
		stage.clear();

		Gdx.input.setInputProcessor(stage);

		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);

		TextButton login = new TextButton("LOGIN", ResourcePacker.UI_SKIN);
		TextField user = new TextField("User", ResourcePacker.UI_SKIN);
		TextField pw = new TextField("PW", ResourcePacker.UI_SKIN);
		pw.setPasswordMode(false);

		table.add(user).width(200f);
		table.row();
		table.add(pw).width(200f);
		table.row();
		table.add(login).width(200f);
		table.row();

		login.addListener(new ClickListener() {

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				handler.setScreen(new MenuScreen(handler));
			}
		});

	}

	@Override
	public void show() {

	}

	private void loginCompleted() {
		handler.setScreen(new MenuScreen(handler));
	}

	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1); // Paint it black
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

}
