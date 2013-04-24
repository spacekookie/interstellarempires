package bucket.game.client.gui;

import bucket.game.client.core.ScreenHandler;
import bucket.game.client.util.Settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * This screen will enable the user to change stuff about their game client
 * 
 * @author ***REMOVED***
 * 
 */
public class SettingsScreen implements Screen {

	/** Container and Backends */
	private ScreenHandler handler;
	private Stage stage;
	private Skin skin;
	private TextButton button;
	private Table table;
	private Table navigation;

	private CheckBox skipIntro;
	private Label introLabel;

	public SettingsScreen(ScreenHandler handler) {
		this.handler = handler;
		Gdx.graphics
				.setTitle(Settings.SUPERTITLE + " - " + Settings.VERSION_NUMBER + " - " + Settings.SCREENTITLE_SETTINGS);
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
		stage.setViewport(width, height, true);

		navigation = new Table();
		navigation.setFillParent(true);
		stage.addActor(navigation);
		button = new TextButton("Back to main screen", skin);

		navigation.add(button).width(200);
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

		skipIntro = new CheckBox("", skin);
		introLabel = new Label("Skip the intro in the beginning", skin);

		CheckBox cakeBox = new CheckBox("", skin);
		Label cakeLabel = new Label("   Receive a free cupcake on game launch", skin);

		table.add(skipIntro);
		table.add(introLabel);
		table.row();
		table.add(cakeBox);
		table.add(cakeLabel);
		table.row();
		table.center();
		stage.addActor(table);

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
		skin.dispose();
		stage.dispose();
	}

}
