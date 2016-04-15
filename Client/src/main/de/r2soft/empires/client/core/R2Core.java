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

package de.r2soft.empires.client.core;

import java.util.Stack;
import java.util.logging.Logger;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.r2soft.empires.client.graphics.R2Overlay;
import de.r2soft.empires.client.graphics.R2Screen;
import de.r2soft.empires.client.input.InputMatrix;
import de.r2soft.empires.client.resources.Assets;

public class R2Core implements ApplicationListener {

  private SpriteBatch batch;

  private R2Screen screen;
  private Stack<R2Overlay> overlays;
  private Logger log = Logger.getLogger(getClass().getSimpleName());

  public R2Core() {
	overlays = new Stack<R2Overlay>();
  }

  @Override
  public void create() {
	batch = new SpriteBatch();
  }

  @Override
  public void resize(int width, int height) {
	if (screen != null)
	  screen.resize(width, height);

	if (!overlays.isEmpty())
	  for (R2Overlay o : overlays)
		o.resize(width, height);

  }

  @Override
  public void render() {
	Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

	batch.begin();
	batch.draw(Assets.V2_BACKGROUND, 0, 0, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1, 1, 0);
	batch.end();

	if (screen != null)
	  screen.render(Gdx.graphics.getDeltaTime());

	if (!overlays.isEmpty())
	  for (R2Overlay o : overlays)
		o.render(Gdx.graphics.getDeltaTime());

	InputMatrix.getInstance().updateSlave();
  }

  @Override
  public void pause() {
	if (screen != null)
	  screen.pause();
  }

  @Override
  public void resume() {
	if (screen != null)
	  screen.resume();
  }

  @Override
  public void dispose() {
	if (screen != null)
	  screen.dispose();
	if (!overlays.isEmpty())
	  for (R2Overlay o : overlays)
		o.dispose();
  }

  public void setScreen(R2Screen screen) {
	if (this.screen != null)
	  this.screen.hide();
	this.screen = screen;
	if (this.screen != null) {
	  this.screen.show();
	  this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
  }

  public R2Screen getScreen() {
	return screen;
  }

  public void addOverlay(R2Overlay overlay) {
	overlays.add(overlay);
	overlay.show();
	overlay.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
  }

  /**
   * Try to avoid use of this method as it can remove an overlay from the middle of a stack. Use
   * {@link #removeOverlay()} instead and don't fuck around with mid-overlay removal!
   */
  @Deprecated
  public void removeOverlay(R2Overlay overlay) {
	if (overlays.contains(overlay)) {
	  overlays.remove(overlay);
	  overlay.dispose();
	}
	else
	  log.info("Overlay wasn't found in Collection");
  }

  /** Pops the last overlay from the Stack */
  public void removeOverlay() {
	if (!overlays.isEmpty())
	  overlays.pop().dispose();
	else
	  log.info("No overlays were found in Collection");
  }

  /**
   * Avoid using this unless you need to iterate over all the overlays. Use {@link #peekOverlays()} instead!
   */
  public Stack<R2Overlay> getOverlays() {
	return overlays;
  }

  /** Returns last item of stack */
  public R2Overlay peekOverlays() {
	return overlays.peek();
  }

  /**
   * Returns if the Overlays Stack is empty to avoid fetching the entire object to another class
   */
  public boolean isOverlaysEmpty() {
	return overlays.isEmpty();
  }
}
