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
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * An overlay to be placed over the screen stack of a game. Will handle camera and stage logic in this class. Sub-actors will need to be
 * added via protected getter for {@link #stage}
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

  public R2Overlay(Stage stage) {
	this.stage = stage;
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

  @Override
  public void render(float delta) {
	Gdx.gl20.glEnable(GL20.GL_BLEND);
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
	  stage.setViewport(width, height);
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
   * This method can be called instead of show to build an interface. It's the first call in the show() method. It's considered good
   * Practice to ONLY override this method for building!
   */
  public void build() {

  }

}
