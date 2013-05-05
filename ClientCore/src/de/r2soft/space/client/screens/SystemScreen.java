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

import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.r2soft.space.client.actors.GenericMapObject;
import de.r2soft.space.client.core.ScreenHandler;
import de.r2soft.space.client.groups.SolarMap;
import de.r2soft.space.client.settings.Settings;
import de.r2soft.space.client.util.ResPack;
import de.r2soft.space.framework.map.SolarSystem;
import de.r2soft.space.framework.objects.Unit;

/**
 * This class will be called when the player clicked on a tile on the @HexMap. In the constructor
 * the relevant data to
 * identify a solar system will be passed on as well as creating a layout around a solar system
 * view.
 * 
 * @author Katharina
 * 
 */
public class SystemScreen implements Screen {

	private Stage stage;
	private ScreenHandler handler;
	private Table back;
	private TextButton backToMap;
	private SolarSystem system;
	private SolarMap map;
	private Set<Unit> units;
	private Set<GenericMapObject> childobjects;

	/** IMPORTANT **/
	private final String wallofText = "This is an example of how Textwrapping works!";
	private final String wallofText2 = "To the left you see the solar map. It will display all actions "
			+ "in the selected solar system. On the right you will have several buttons that might come in handy"
			+ " when working with your units.";

	public SystemScreen(ScreenHandler handler, SolarSystem childsystem) {
		this.handler = handler;
		this.system = childsystem;
		Gdx.graphics.setTitle(Settings.SUPERTITLE + " - " + Settings.VERSION_NUMBER + " - "
				+ Settings.SCREENTITLE_SOLAR + ": " + childsystem.getId());

		units = childsystem.getUnits();

		for (Unit u : units) {
			childobjects.add(new GenericMapObject(u.getPosition().x, u.getPosition().y, u.getType(), u
					.getFlag(), u.getClaim()));
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

		// Dynamically switching input listener
		if (Gdx.input.getX() > 150 && Gdx.input.getX() < 720 && Gdx.input.getY() > 20
				&& Gdx.input.getY() < 580) {
			map.setInputToChild();
		}
		else {
			Gdx.input.setInputProcessor(stage);
		}
	}

	@Override
	public void resize(int width, int height) {
		if (stage == null)
			stage = new Stage(width, height, true);
		stage.clear();
		Gdx.input.setInputProcessor(stage);

		// TODO:Replace with server request
		map = new SolarMap(system, childobjects);
		stage.addActor(map);

		back = new Table();
		back.setFillParent(true);
		stage.addActor(back);
		backToMap = new TextButton("Leave system", ResPack.UI_SKIN);
		back.add(backToMap).width(ResPack.SIZE_UI_BUTTON_NAVIGON);
		back.row();
		back.top().left();

		/** Example for system gui */
		Table elements = new Table();
		elements.setFillParent(true);
		stage.addActor(elements);

		TextButton kill = new TextButton("Destroy everything", ResPack.UI_SKIN);
		TextButton refresh = new TextButton("Refresh", ResPack.UI_SKIN);
		Label wall = new Label(wallofText, ResPack.UI_SKIN);
		Label wall2 = new Label(wallofText2, ResPack.UI_SKIN);
		wall.setWrap(true);
		wall2.setWrap(true);

		elements.add(kill).width(ResPack.SIZE_UI_FIELD_CONTENT);
		elements.row();
		elements.add(refresh).width(ResPack.SIZE_UI_FIELD_CONTENT);
		elements.row();
		elements.add(wall).width(ResPack.SIZE_UI_FIELD_CONTENT);
		elements.row();
		elements.add(wall2).width(ResPack.SIZE_UI_FIELD_CONTENT);
		elements.row();
		elements.top().right();
		elements.setX(ResPack.SIZE_UI_GLOBAL_FRAME_OFFSET);
		elements.setY(ResPack.SIZE_UI_GLOBAL_FRAME_OFFSET);

		kill.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

			}
		});

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
		map.dispose();
		stage.dispose();
	}
}