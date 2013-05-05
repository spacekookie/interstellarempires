package de.r2soft.space.client.screens;

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

import java.util.HashSet;
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
import de.r2soft.space.client.core.MainClientLauncher;
import de.r2soft.space.client.core.ScreenHandler;
import de.r2soft.space.client.groups.SolarMap;
import de.r2soft.space.client.settings.Settings;
import de.r2soft.space.client.types.IntVec2;
import de.r2soft.space.client.util.ResPack;

import framework.objects.Unit.TYPE;

/**
 * This class will be called when the player clicked on a tile on the @HexMap.
 * In the constructor the relevant data to identify a solar system will be
 * passed on as well as creating a layout around a solar system view.
 * 
 * @author Katharina
 * 
 */
public class SystemScreen implements Screen {

	private Stage stage;
	private ScreenHandler handler;
	private Table back;
	private TextButton backToMap;
	private IntVec2 tileID;
	private SolarMap map;
	private Set<GenericMapObject> localGameObjects;

	private int radius;

	/** IMPORTANT **/
	private ResPack res;
	private final String wallofText = "This is an example of how Textwrapping works!";
	private final String wallofText2 = "To the left you see the solar map. It will display all actions in the selected solar system. On the right you will have several buttons that might come in handy when working with your units.";

	public SystemScreen(ScreenHandler handler, IntVec2 tileID) {
		this.handler = handler;
		this.tileID = tileID;
		res = new ResPack();
		Gdx.graphics.setTitle(Settings.SUPERTITLE + " - " + Settings.VERSION_NUMBER + " - "
				+ Settings.SCREENTITLE_SOLAR + ": " + tileID);
		// TODO: get System with ID
		radius = MainClientLauncher.getSystemWithID(tileID).getRadius();

		// TODO: Set<Unit> = solar.getUnits();

		localGameObjects = new HashSet<GenericMapObject>();
		localGameObjects.add(new GenericMapObject(200f, 200f, TYPE.FLEET, "SampleFleet", null, null));

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

		// if (Gdx.input.isButtonPressed(0))
		// {
		// System.out.println(Gdx.input.getX() + " " + Gdx.input.getY());
		// }

		if (Gdx.input.getX() > 150 && Gdx.input.getX() < 720 && Gdx.input.getY() > 20
				&& Gdx.input.getY() < 580)
			{
				map.setInputToChild();
			} else
			{
				Gdx.input.setInputProcessor(stage);
			}

	}

	@Override
	public void resize(int width, int height) {
		if (stage == null)
			stage = new Stage(width, height, true);
		stage.clear();
		Gdx.input.setInputProcessor(stage);

		map = new SolarMap(tileID, MainClientLauncher.getSystemWithID(tileID), localGameObjects); // TODO:
																																																																																												// Replace
																																																																																												// with
																																																																																												// server
																																																																																												// request!
		stage.addActor(map);

		back = new Table();
		back.setFillParent(true);
		stage.addActor(back);
		backToMap = new TextButton("BACK", ResPack.UI_SKIN);
		back.add(backToMap).width(150);
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

		elements.add(kill).width(250);
		elements.row();
		elements.add(refresh).width(250);
		elements.row();
		elements.add(wall).width(250);
		elements.row();
		elements.add(wall2).width(250);
		elements.row();
		elements.top().right();
		elements.setX(-50);
		elements.setY(-50);

		kill.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				localGameObjects.clear();
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