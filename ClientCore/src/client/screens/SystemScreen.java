package client.screens;

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

import client.core.MainClientLauncher;
import client.core.ScreenHandler;
import client.objects.groups.SolarMap;
import client.settings.Settings;
import client.types.IntVec2;
import client.util.Find;
import client.util.ResourcePacker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import framework.map.SolarSystem;
import framework.objects.Star;
import framework.objects.Star.StarType;

/**
 * This class will be called when the player clicked on a tile on the @HexMap. In the constructor the relevant data to identify a
 * solar system will be passed on as well as creating a layout around a solar system view.
 * 
 * @author ***REMOVED***
 * 
 */
public class SystemScreen implements Screen {

	private SpriteBatch batch;
	private Stage stage;
	private ScreenHandler handler;
	private Table back;
	private TextButton backToMap;
	private IntVec2 tileID;
	private SolarMap map;

	/** IMPORTANT **/
	private ResourcePacker res;

	public SystemScreen(ScreenHandler handler, IntVec2 tileID) {
		this.handler = handler;
		this.tileID = tileID;
		res = new ResourcePacker();
		Gdx.graphics.setTitle(Settings.SUPERTITLE + " - " + Settings.VERSION_NUMBER + " - " + Settings.SCREENTITLE_SOLAR);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		if (stage == null)
			stage = new Stage(width, height, true);
		stage.clear();
		Gdx.input.setInputProcessor(stage);

		map = new SolarMap(tileID, MainClientLauncher.getSystemWithID(tileID));
		stage.addActor(map);

		back = new Table();
		back.setFillParent(true);
		stage.addActor(back);
		backToMap = new TextButton("BACK", res.getUiSkin());
		back.add(backToMap).width(150);
		back.row();
		back.top().left();

		/** Example for system gui */
		Table elements = new Table();
		elements.setFillParent(true);
		stage.addActor(elements);

		TextButton kill = new TextButton("Destroy everything!", res.getUiSkin());
		TextButton pointless = new TextButton("Pointless Button", res.getUiSkin());
		elements.add(kill).width(250);
		elements.row();
		elements.add(pointless).width(250);
		elements.row();
		elements.top().right();
		elements.setX(-50);
		elements.setY(-50);

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

	}

}