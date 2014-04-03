package de.r2soft.empires.client.screens.overlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.r2soft.empires.client.core.GameCore;
import de.r2soft.empires.client.graphics.R2Overlay;
import de.r2soft.empires.client.resources.Assets;

public class PreferencesOverlay extends R2Overlay {

  /** Layouts */
  private Table primary;
  private TextButton back, apply;
  private Preferences prefs;

  /** Preferences */
  private CheckBox intro, music, fullscreen;

  public PreferencesOverlay() {
	super(new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
  }

  @Override
  public void build() {

	primary = new Table(Assets.UI_SKIN);
	primary.setFillParent(true);
	primary.center();

	/* Buttons */
	back = new TextButton("Back", Assets.UI_SKIN);
	apply = new TextButton("Apply Settings", Assets.UI_SKIN);

	/* Add actors to table */
	primary.add(back);
	primary.add(apply);

	stage.addActor(primary);

	this.makeListeners();
  }

  private void makeListeners() {
	back.addListener(new ClickListener() {
	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		GameCore.getInstance().removeOverlay();
	  }
	});
  }

  @Override
  public void setInputFocus() {
	Gdx.input.setInputProcessor(stage);
  }

}
