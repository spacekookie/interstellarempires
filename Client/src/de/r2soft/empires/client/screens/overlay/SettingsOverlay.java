package de.r2soft.empires.client.screens.overlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import de.r2soft.empires.client.core.GameCore;
import de.r2soft.empires.client.graphics.R2Overlay;
import de.r2soft.empires.client.resources.Assets;
import de.r2soft.empires.client.resources.Values;

/**
 * Replaces the Settings Screen displaying all of the games settings, preferences and also credits.
 * 
 * @author ***REMOVED*** <***REMOVED***>
 * 
 */
public class SettingsOverlay extends R2Overlay {

  /** Layouts */
  private Table primary;
  private TextButton back, apply;
  private Label title;
  private Preferences prefs;

  /** Preferences */
  private CheckBox intro, music, fullscreen, hexagonDebug;

  /** Credits */
  private Label katharinaHead, leanderHead, steveHead, julieHead;
  private Label katharina, leander, steve, julie;

  public SettingsOverlay() {
	super(new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));
	prefs = Gdx.app.getPreferences(Values.PREFERENCE_FILE_NAME);

	reinforce();
  }

  private void reinforce() {
	float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	Vector2 start = new Vector2((width / 2) - 350, 0);
	Vector2 size = new Vector2(700, height);
	float alpha = 0.9f;
	Color color = new Color(Color.DARK_GRAY);
	super.addAdditionalAlpha(start, size, alpha, color);
  }

  @Override
  public void build() {

	primary = new Table(Assets.R2_UI_SKIN);
	primary.setFillParent(true);
	primary.center();
	primary.defaults().height(30f);

	/* Buttons */
	back = new TextButton("Cancel", Assets.R2_UI_SKIN);
	apply = new TextButton("Apply Settings", Assets.R2_UI_SKIN);

	/* Checkboxes */
	intro = new CheckBox("Skip the Game Intro", Assets.R2_UI_SKIN);
	music = new CheckBox("Play Background Music", Assets.R2_UI_SKIN);
	fullscreen = new CheckBox("Use Fullscreen (Experimental)", Assets.R2_UI_SKIN);
	hexagonDebug = new CheckBox("Render debug frames around hexagon-tiles", Assets.R2_UI_SKIN);

	this.checkCurrentSettings();

	/* Header */
	title = new Label("Game Settings", Assets.UI_SKIN);
	title.setFontScale(2.5f);

	/* Credits */
	katharinaHead = new Label("Lead Developer", Assets.UI_SKIN);
	leanderHead = new Label("Programming", Assets.UI_SKIN);
	steveHead = new Label("Music & Sounds", Assets.UI_SKIN);
	julieHead = new Label("Graphics & Art", Assets.UI_SKIN);

	katharinaHead.setFontScale(1.1f);
	katharinaHead.setColor(Color.PINK);

	leanderHead.setFontScale(1.1f);
	leanderHead.setColor(Color.CYAN);

	steveHead.setFontScale(1.1f);
	steveHead.setColor(Color.PINK);

	julieHead.setFontScale(1.1f);
	julieHead.setColor(Color.CYAN);

	/* Credit Names */
	katharina = new Label("***REMOVED***", Assets.R2_UI_SKIN);
	leander = new Label("Leander Sabel", Assets.R2_UI_SKIN);
	steve = new Label("Steve Sancteria", Assets.R2_UI_SKIN);
	julie = new Label("Julie Rae-Clarke", Assets.R2_UI_SKIN);

	/* Add actors to table */
	primary.add(title).colspan(2).padBottom(45f).align(Align.center);

	primary.row();
	primary.add(intro).left();
	primary.add(katharinaHead);
	primary.row();

	primary.add(music).left();
	primary.add(katharina);
	primary.row();

	primary.add(fullscreen).left();
	primary.add(leanderHead);
	primary.row();

	primary.add(hexagonDebug).left();
	primary.add(leander);
	primary.row();

	primary.add();
	primary.add(steveHead);
	primary.row();

	primary.add();
	primary.add(steve);
	primary.row();

	primary.add();
	primary.add(julieHead);
	primary.row();

	primary.add();
	primary.add(julie);
	primary.row();

	primary.add(apply).height(Values.R2_UI_SIZES_BUTTON_HEIGHT_PRIME).padTop(Values.R2_UI_PIXEL_PAD_MEDIUM)
		.width(Values.R2_UI_SIZES_BUTTON_WIDTH_PRIME);
	primary.add(back).height(Values.R2_UI_SIZES_BUTTON_HEIGHT_PRIME).padTop(Values.R2_UI_PIXEL_PAD_MEDIUM)
		.width(Values.R2_UI_SIZES_BUTTON_WIDTH_PRIME);

	stage.addActor(primary);
	this.makeListeners();
  }

  private void checkCurrentSettings() {
	if (prefs.contains(Values.PREFERENCE_SKIP_INTRO))
	  intro.setChecked(prefs.getBoolean(Values.PREFERENCE_SKIP_INTRO));

	if (prefs.contains(Values.PREFERENCE_PLAY_MUSIC))
	  music.setChecked(prefs.getBoolean(Values.PREFERENCE_PLAY_MUSIC));

	if (prefs.contains(Values.PREFERENCE_LAUNCH_FULLSCREEN))
	  fullscreen.setChecked(prefs.getBoolean(Values.PREFERENCE_LAUNCH_FULLSCREEN));

	if (prefs.contains(Values.PREFERENCE_USE_HEXAGON_DEBUGGING))
	  hexagonDebug.setChecked(prefs.getBoolean(Values.PREFERENCE_USE_HEXAGON_DEBUGGING));

  }

  private void makeListeners() {
	back.addListener(new ClickListener() {
	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		GameCore.getInstance().removeOverlay();
	  }
	});

	apply.addListener(new ClickListener() {
	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		updateSettings();
		GameCore.getInstance().removeOverlay();
	  }
	});

  }

  private void updateSettings() {
	prefs.putBoolean(Values.PREFERENCE_SKIP_INTRO, intro.isChecked());
	prefs.putBoolean(Values.PREFERENCE_PLAY_MUSIC, music.isChecked());
	prefs.putBoolean(Values.PREFERENCE_LAUNCH_FULLSCREEN, fullscreen.isChecked());
	prefs.putBoolean(Values.PREFERENCE_USE_HEXAGON_DEBUGGING, hexagonDebug.isChecked());
	prefs.flush();
  }

  @Override
  public void setInputFocus() {
	Gdx.input.setInputProcessor(stage);
  }

}
