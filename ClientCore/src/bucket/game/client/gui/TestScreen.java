package bucket.game.client.gui;

import bucket.game.client.core.ScreenHandler;
import bucket.game.client.util.Settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class TestScreen implements Screen {

	private ScreenHandler handler;
	private Stage stage;
	private Skin skin;
	private Skin cSkin;
	private Table table;
	private Table backbutton;
	private CheckBox box;
	private Label boxLable;
	private TextButton back;

	public TestScreen(ScreenHandler handler) {
		this.handler = handler;
		Gdx.graphics.setTitle(Settings.SUPERTITLE + " - " + Settings.VERSION_NUMBER + " - " + Settings.SCREENTITLE_TEST);
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

		/** Setting up the Stage */
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		stage.setViewport(width, height, true);

		Table navigation = new Table();
		navigation.setFillParent(true);
		stage.addActor(navigation);
		back = new TextButton("Back to main screen", skin);

		navigation.add(back).width(200);
		navigation.row();

		navigation.top().right();

		stage.addActor(back);

		back.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				handler.setScreen(new MenuScreen(handler));
			}
		});

	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void show() {
		skin = new Skin(Gdx.files.internal("assets/gui/skins/defaults/uiskin.json"));

		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("assets/gui/buttons/alpha-generic-checkbox.pack"));

		cSkin = new Skin();
		cSkin.addRegions(atlas);
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

}
