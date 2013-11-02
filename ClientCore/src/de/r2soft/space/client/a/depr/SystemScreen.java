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
package de.r2soft.space.client.a.depr;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.r2soft.space.client.actors.GenericMapObject;
import de.r2soft.space.client.core.ScreenHandler;
import de.r2soft.space.client.settings.Resources;
import de.r2soft.space.client.util.ResPack;
import de.r2soft.space.client.util.Sizes;
import de.r2soft.space.framework.map.SolarSystem;
import de.r2soft.space.framework.objects.GameObject.SuperClass;
import de.r2soft.space.framework.objects.Ship;

/**
 * This singleton class will be called when the player clicked on a tile on the @HexMap. In the
 * constructor the relevant
 * data to identify a solar system will be passed on as well as creating a layout around a solar
 * system view.
 * 
 * Use @StarsystemScreen for now on.
 * 
 * @author Katharina
 * 
 */
@Deprecated
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
	private TextButton refresh;

	private Label typeTail, flatTail, ownerTail, countTail, posTail;

	/** unit logic */
	private Set<Ship> units;
	private Set<GenericMapObject> childobjects;
	private GenericMapObject selectionfocus = null;

	/** To know what object to get */
	private SuperClass childSuper;

	@Deprecated
	/** Move to string resource */
	private final String wallofText = "Object Information";
	@Deprecated
	/** Move to string resource */
	private final String nothingselected = "Nothing selected";

	/** DO NOT USE! */
	@Deprecated
	public SystemScreen() {
	}

	/**
	 * Do not normally use!
	 * 
	 * @return may return null
	 */
	@Deprecated
	public static SystemScreen getInstance() {
		return instance;

	}

	public SystemScreen(ScreenHandler handler, SolarSystem childsystem) {
		this.handler = handler;
		SystemScreen.instance = this;
		Gdx.app.log(Resources.LOG_SOLAR_MAP, "From screen: " + this.toString());
		this.system = childsystem;
		Gdx.graphics.setTitle(Resources.SUPERTITLE + " - " + Resources.VERSION_NUMBER + " - "
				+ Resources.SCREENTITLE_SOLAR);
		childobjects = new HashSet<GenericMapObject>();

		if (childsystem.getUnits() != null) {
			units = childsystem.getUnits();

			for (Ship unit : units) {
				childobjects.add(new GenericMapObject(unit));
			}
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

		/** Dynamically switching input listener */
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

		map = new SolarMap(system, childobjects);
		stage.addActor(map);

		/** Back button navigation */
		back = new Table();
		back.setFillParent(true);
		stage.addActor(back);
		backToMap = new TextButton("Back to map", ResPack.UI_SKIN);
		back.add(backToMap).width(Sizes.SIZE_UI_BUTTON_NAVIGON);
		back.row();
		back.top().left();

		/** Right side table to display selected unit and offer basic orders */
		this.setupRightBar();

		/** ### ALL LISTENERS AND STUFF NOBODY WANTS TO SEE ### */

		refresh.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				SystemScreen.getInstance().resize(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
				// TODO: Fetch new data from server and display
			}
		});

		back.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Preferences prefs = Gdx.app.getPreferences(Resources.PREFERENCE_FILE_NAME);
				handler.setScreen(new HexagonScreen(handler, prefs
						.getString(Resources.PREFERENCE_SAVED_USER_NAME)));
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

	private void setupRightBar() {
		contentRight = new Table();
		contentRight.setFillParent(true);
		contentRight.align(Align.left);
		contentRight.top().right();
		contentRight.setX(Sizes.SIZE_UI_GLOBAL_FRAME_OFFSET);
		contentRight.setY(Sizes.SIZE_UI_GLOBAL_FRAME_OFFSET);
		stage.addActor(contentRight);

		refresh = new TextButton("Refresh", ResPack.UI_SKIN);
		sidebarTitle = new Label(wallofText, ResPack.UI_SKIN);

		typeTail = new Label(nothingselected, ResPack.UI_SKIN);
		typeTail.setWrap(true);

		flatTail = new Label(nothingselected, ResPack.UI_SKIN);
		flatTail.setWrap(true);

		countTail = new Label(nothingselected, ResPack.UI_SKIN);
		countTail.setWrap(true);

		ownerTail = new Label(nothingselected, ResPack.UI_SKIN);
		ownerTail.setWrap(true);

		posTail = new Label(nothingselected, ResPack.UI_SKIN);
		posTail.setWrap(true);

		/** CONTENT FIELDS **/

		contentRight.add(refresh).width(
				(Sizes.SIZE_UI_FIELD_CONTENT / 2) + (Sizes.SIZE_UI_FIELD_CONTENT / 4));
		contentRight.row();

		contentRight.add(sidebarTitle).colspan(2).left();
		contentRight.row();

		contentRight.add(new Label("Type: ", ResPack.UI_SKIN)).width(Sizes.SIZE_UI_FIELD_CONTENT / 2)
				.left();
		contentRight.add(typeTail).width(Sizes.SIZE_UI_FIELD_CONTENT);
		contentRight.row();

		contentRight.add(new Label("Flag: ", ResPack.UI_SKIN)).width(Sizes.SIZE_UI_FIELD_CONTENT / 2)
				.left();
		contentRight.add(flatTail).width(Sizes.SIZE_UI_FIELD_CONTENT);
		contentRight.row();

		contentRight.add(new Label("Count: ", ResPack.UI_SKIN)).width(Sizes.SIZE_UI_FIELD_CONTENT / 2)
				.left();
		contentRight.add(countTail).width(Sizes.SIZE_UI_FIELD_CONTENT);
		contentRight.row();

		contentRight.add(new Label("Owner: ", ResPack.UI_SKIN)).width(Sizes.SIZE_UI_FIELD_CONTENT / 2)
				.left();
		contentRight.add(ownerTail).width(Sizes.SIZE_UI_FIELD_CONTENT);
		contentRight.row();

		contentRight.add(new Label("Pos: ", ResPack.UI_SKIN)).width(Sizes.SIZE_UI_FIELD_CONTENT / 2)
				.left();
		contentRight.add(posTail).width(Sizes.SIZE_UI_FIELD_CONTENT);
		contentRight.row();

	}

	public GenericMapObject getSelectionfocus() {
		return selectionfocus;
	}

	/** This method will be moved to @SystemScreen */
	public void setSelectionfocus(GenericMapObject selectionfocus, SuperClass superclass) {
		this.selectionfocus = selectionfocus;
		this.childSuper = superclass;

		if (selectionfocus == null) {
			typeTail.setText(nothingselected);
			flatTail.setText(nothingselected);
			ownerTail.setText(nothingselected);
			countTail.setText(nothingselected);
			posTail.setText(nothingselected);
		}
		else {
			switch (childSuper) {
			case SHIP:
				Ship temp = selectionfocus.getUnitIfExists();
				if (temp != null) {
					typeTail.setText(temp.getType().toString());
					flatTail.setText(temp.getName().toString());
					ownerTail.setText(temp.getClaim().getName());
					posTail.setText(temp.getPosition().toString());
				}
				break;

			default:
				Gdx.app.log("UI_THREAD", "ERROR RETRIEVING SELECTION INFO. DID YOU ATTACH A SUPERCLASS?");
				break;
			}
		}
	}
}