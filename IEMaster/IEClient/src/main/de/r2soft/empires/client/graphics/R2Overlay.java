/* #########################################################################
 * Copyright (c) 2014 Random Robot Softworks
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

package de.r2soft.empires.client.graphics;

import org.apache.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * An overlay to be placed over the screen stack of a game. Will handle camera and stage logic in this class. Sub-actors
 * will need to be added via protected getter for {@link #stage}
 * 
 * @author Katharina <kookie@spacekookie.de>
 * 
 */
public abstract class R2Overlay extends R2Screen {
  private ShapeRenderer renderer;
  private OrthographicCamera camera;
  private final Logger log = Logger.getLogger(getClass().getSimpleName());

  /** Additional overlay settings */
  private boolean overlay;
  private Vector2 start, size;
  private float alpha;
  private Color color;

  /** Stage to handle all UI items for an overlay in the stack */
  protected Stage stage;

  /** Main default table to hold most UI children on overlay */
  protected Table main;

  /**
   * You should use {@link #R2Overlay(Stage, Skin)} instead as it is the preferred constructor.
   * 
   * @param stage
   */
  @Deprecated
  public R2Overlay(Stage stage) {
	this(stage, null);
  }

  /**
   * Preferred constructor for an overlay. It initialises itself with a stage and also a default UI skin to be used for
   * the table parent.
   * 
   * @param stage
   *          Stage to hold children. Can be passed down from Screen.
   * @param uiskin
   *          Default UI skin for {@link #main} Table to hold.
   */
  public R2Overlay(Stage stage, Skin uiskin) {
	this.stage = stage;
	main = new Table(uiskin);
	main.setFillParent(true); // Just check this. It makes sense in 99% of cases.
	stage.addActor(main);
	renderer = new ShapeRenderer();
	this.camera = (OrthographicCamera) stage.getCamera();
  }

  public void addAdditionalAlpha(Vector2 start, Vector2 size, float alpha) {
	overlay = true;
	this.start = start;
	this.size = size;
	this.alpha = alpha;
	this.color = Color.BLACK;
  }

  public void addAdditionalAlpha(Vector2 start, Vector2 size, float alpha, Color color) {
	overlay = true;
	this.start = start;
	this.size = size;
	this.alpha = alpha;
	this.color = color;
  }

  public void disableAdditionalAlpha() {
	overlay = false;
  }

  /** Clears all sub actors from this table. Useful for Overlay repaints. */
  public void clearTable() {
	main.clear();
  }

  @Override
  public void render(float delta) {
	Gdx.gl20.glEnable(GL30.GL_BLEND);
	renderer.setProjectionMatrix(camera.combined);
	renderer.begin(ShapeType.Filled);
	renderer.setColor(0, 0, 0, 0.5f);
	renderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	renderer.end();

	if (overlay) {
	  renderer.begin(ShapeType.Filled);
	  renderer.setColor(color.r, color.g, color.b, alpha);
	  renderer.rect(start.x, start.y, size.x, size.y);
	  renderer.end();
	}

	stage.draw();
  }

  @Override
  public void resize(int width, int height) {
	if (stage != null)
	  stage.getViewport().update(width, height);
  }

  @Override
  public void show() {
	this.build();
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
	stage.dispose();
  }

  protected Stage getStage() {
	return stage;
  }

  protected void setStage(Stage stage) {
	this.stage = stage;
	log.info("Stage was changed. Did you really want to do that?");
  }

  /**
   * This method can be called instead of show to build an interface. It's the first call in the show() method. It's
   * considered good Practice to ONLY override this method for building!
   */
  public void build() {

  }

}
