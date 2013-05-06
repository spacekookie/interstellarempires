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

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.r2soft.space.client.actors.GenericMapObject;
import de.r2soft.space.client.core.ScreenHandler;
import de.r2soft.space.client.groups.SolarMap;
import de.r2soft.space.client.settings.Resources;
import de.r2soft.space.client.util.ResPack;
import de.r2soft.space.framework.map.SolarSystem;
import de.r2soft.space.framework.objects.GameObject.TYPE;
import de.r2soft.space.framework.objects.Unit;

/**
 * This singleton class will be called when the player clicked on a tile on the @HexMap. In the
 * constructor
 * the relevant data to
 * identify a solar system will be passed on as well as creating a layout around a solar system
 * view.
 * 
 * @author ***REMOVED***
 * 
 */
public class SystemScreen implements Screen {

	/** private singleton instance */
	private static SystemScreen instance = null;

	/** Scene2D stuff */
	private Stage stage;
	private ScreenHandler handler;
	private Table back;
	private TextButton backToMap;
	private SolarSystem system;
	private SolarMap map;

	/** Actual game content */
	private Table contentRight;
	private Label sidebarTitle;
	private Group objectInfoHead, objectInfoTail;
	private Set<Label> contentRightLabelsHead, contentRightLabelsTail;

	/** unit logic */
	private Set<Unit> units;
	private Set<GenericMapObject> childobjects;
	private GenericMapObject selectionfocus = null;

	/** To know what object to get */
	private TYPE childType;

	@Deprecated
	/** Move to string resource */
	private final String wallofText = "Currently selected object";
	@Deprecated
	/** Move to string resource */
	private final String wallofText2 = "None";
	@Deprecated
	/** Move to string resource */
	private final String nothingselected = "Nothing selected";

	/** DO NOT USE! */
	@Deprecated
	public SystemScreen() {
	}

	/**
	 * Do not use!
	 * 
	 * @return may return null
	 */
	@Deprecated
	public static SystemScreen getInstance() {
		return instance;

	}

	public SystemScreen(ScreenHandler handler, SolarSystem childsystem) {
		initializeLabels();
		this.handler = handler;
		SystemScreen.instance = this;
		System.out.println(this);
		Gdx.app.log(Resources.LOG_SOLAR_MAP, "From screen: " + this.toString());
		this.system = childsystem;
		Gdx.graphics.setTitle(Resources.SUPERTITLE + " - " + Resources.VERSION_NUMBER + " - "
				+ Resources.SCREENTITLE_SOLAR);
		childobjects = new HashSet<GenericMapObject>();

		if (childsystem.getUnits() != null) {
			units = childsystem.getUnits();

			for (Unit unit : units) {
				childobjects.add(new GenericMapObject(unit));
			}
		}
	}

	private void initializeLabels() {
		contentRightLabelsHead = new HashSet<Label>();
		contentRightLabelsHead.add(new Label("Type: ", ResPack.UI_SKIN));
		contentRightLabelsHead.add(new Label("Flag: ", ResPack.UI_SKIN));
		contentRightLabelsHead.add(new Label("Owner: ", ResPack.UI_SKIN));
		contentRightLabelsHead.add(new Label("Count: ", ResPack.UI_SKIN));
		contentRightLabelsHead.add(new Label("Pos: ", ResPack.UI_SKIN));

		contentRightLabelsTail = new HashSet<Label>();
		contentRightLabelsTail.add(new Label(nothingselected, ResPack.UI_SKIN));
		contentRightLabelsTail.add(new Label(nothingselected, ResPack.UI_SKIN));
		contentRightLabelsTail.add(new Label(nothingselected, ResPack.UI_SKIN));
		contentRightLabelsTail.add(new Label(nothingselected, ResPack.UI_SKIN));
		contentRightLabelsTail.add(new Label(nothingselected, ResPack.UI_SKIN));
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

		if (selectionfocus != null) {

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

		/** Back button navigation */
		back = new Table();
		back.setFillParent(true);
		stage.addActor(back);
		backToMap = new TextButton("Leave system", ResPack.UI_SKIN);
		back.add(backToMap).width(ResPack.SIZE_UI_BUTTON_NAVIGON);
		back.row();
		back.top().left();

		/** ACTUAL SCREEN CONTENT **/
		contentRight = new Table();
		contentRight.setFillParent(true);
		stage.addActor(contentRight);

		/** CONTENT GROUP RIGHT */
		objectInfoHead = new Group();
		int headCounter = 1;
		for (Label label : contentRightLabelsHead) {
			label.setY((headCounter * ResPack.SIZE_UI_GROUP_OFFSET)
					+ ResPack.SIZE_SOLAR_GROUP_OFFSET_INITIAL);
			objectInfoHead.addActor(label);
			headCounter++;
		}

		objectInfoTail = new Group();
		int tailCounter = 1;
		for (Label label : contentRightLabelsTail) {
			label.setY((tailCounter * ResPack.SIZE_UI_GROUP_OFFSET)
					+ ResPack.SIZE_SOLAR_GROUP_OFFSET_INITIAL);
			label.setX(4 * ResPack.SIZE_SOLAR_GROUP_OFFSET_INITIAL);
			objectInfoTail.addActor(label);
			tailCounter++;
		}

		TextButton kill = new TextButton("Destroy everything", ResPack.UI_SKIN);
		TextButton refresh = new TextButton("Refresh", ResPack.UI_SKIN);
		sidebarTitle = new Label(wallofText, ResPack.UI_SKIN);
		sidebarTitle.setWrap(true);

		contentRight.add(kill).width(ResPack.SIZE_UI_FIELD_CONTENT);
		contentRight.row();
		contentRight.add(refresh).width(ResPack.SIZE_UI_FIELD_CONTENT);
		contentRight.row();
		contentRight.add(objectInfoHead).left();
		contentRight.add(objectInfoTail).left();
		contentRight.row();
		contentRight.add(sidebarTitle).width(ResPack.SIZE_UI_FIELD_CONTENT);
		contentRight.row();
		contentRight.top().right();
		contentRight.setX(ResPack.SIZE_UI_GLOBAL_FRAME_OFFSET);
		contentRight.setY(ResPack.SIZE_UI_GLOBAL_FRAME_OFFSET);

		/** ### ALL LISTENERS AND STUFF NOBODY WANTS TO SEE ### **/

		refresh.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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

	public GenericMapObject getSelectionfocus() {
		return selectionfocus;
	}

	public void setSelectionfocus(GenericMapObject selectionfocus, TYPE type) {
		this.selectionfocus = selectionfocus;
		this.childType = type;
		System.out.println(this.selectionfocus);
	}
}