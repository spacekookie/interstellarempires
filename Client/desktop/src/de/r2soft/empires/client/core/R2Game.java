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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Logger;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import de.r2soft.empires.client.graphics.Overlay;
import de.r2soft.empires.client.graphics.R2Screen;
import de.r2soft.empires.client.input.InputMatrix;

public class R2Game implements ApplicationListener {

  private R2Screen screen;
  private Deque<Overlay> overlays;
  private Logger logger = Logger.getLogger(getClass().getSimpleName());

  public R2Game() {
	overlays = new ArrayDeque<Overlay>();
  }

  @Override
  public void create() {
  }

  @Override
  public void resize(int width, int height) {
	if (screen != null)
	  screen.resize(width, height);
  }

  @Override
  public void render() {
	if (screen != null)
	  screen.render(Gdx.graphics.getDeltaTime());

	if (!overlays.isEmpty())
	  overlays.peekLast().render(Gdx.graphics.getDeltaTime());

	InputMatrix.getInstance().checkForSlaves();
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
	  screen.hide();
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

  public void addOverlay(Overlay overlay) {
	if (!overlays.contains(overlay))
	  overlays.add(overlay);
	else
	  logger.info("That exact overlay is already being displayed");
  }

  public void removeSpecificOverlay(Overlay overlay) {
	if (overlays.contains(overlay))
	  overlays.remove(overlay);
	else
	  logger.info("Overlay wasn't found in Collection");
  }

  public void removeLastOverlay() {
	if (!overlays.isEmpty())
	  overlays.pop();
	else
	  logger.info("No overlays were found in Collection");
  }

  /** Gets a list of screens that are currently being displayed as overlays */
  public Deque<Overlay> getOverlays() {
	return overlays;
  }
}
