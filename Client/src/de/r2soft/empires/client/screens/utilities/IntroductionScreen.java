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
package de.r2soft.empires.client.screens.utilities;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import de.r2soft.empires.client.core.GameCore;
import de.r2soft.empires.client.graphics.IntroAnimator;
import de.r2soft.empires.client.graphics.R2Screen;
import de.r2soft.empires.client.resources.Assets;

/**
 * 
 * Intro screen to display studio, game and other animations and credits. Can be skipped easily by directly calling
 * 
 * @tweenCompleted()
 * 
 * @author: ***REMOVED***
 */

public class IntroductionScreen extends R2Screen {

  private Texture splashTitle;
  private Sprite splashSprite;
  private SpriteBatch batch;
  private TweenManager man;
  private TweenCallback tc;

  private Stage stage;

  public IntroductionScreen() {
  }

  @Override
  public void resize(int w, int h) {
	if (stage == null)
	  stage = new Stage(w, h, true);
	stage.clear();

	Table backToIntro = new Table();

	stage.addActor(backToIntro);
	backToIntro.setFillParent(true);

	TextButton backham = new TextButton("SKIP THIS INTRO", Assets.UI_SKIN);
	backham.addListener(new InputListener() {
	  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		return true;
	  }

	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		tweenCompleted();
	  }
	});

	backToIntro.add(backham);
	backToIntro.row();
	backToIntro.bottom().right();

  }

  @Override
  public void setInputFocus() {
	Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void show() {
	splashTitle = new Texture("gui/title_graphics/prot-splash-title.png");
	splashTitle.setFilter(TextureFilter.Linear, TextureFilter.Linear);

	splashSprite = new Sprite(splashTitle);
	splashSprite.setColor(1, 1, 1, 0);
	splashSprite.setX(Gdx.graphics.getWidth() / 2 - (splashSprite.getWidth() / 2));
	splashSprite.setY(Gdx.graphics.getHeight() / 2 - (splashSprite.getHeight() / 2));

	batch = new SpriteBatch();

	Tween.registerAccessor(Sprite.class, new IntroAnimator());

	man = new TweenManager();

	tc = new TweenCallback() {

	  @Override
	  public void onEvent(int type, BaseTween<?> source) {

		// Will be called when Tween is completed
		tweenCompleted();

	  }
	};

	Tween.to(splashSprite, IntroAnimator.ALPHA, 2.5f).target(1).ease(TweenEquations.easeInElastic).repeatYoyo(1, 0.5f).setCallback(tc)
		.setCallbackTriggers(TweenCallback.COMPLETE).start(man);

  }

  private void tweenCompleted() {
	GameCore.getInstance().setScreen(new LoginScreen());
  }

  public void render(float delta) {
	man.update(delta);

	stage.act(delta);
	stage.draw();

	batch.begin();

	splashSprite.draw(batch);

	batch.end();

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
