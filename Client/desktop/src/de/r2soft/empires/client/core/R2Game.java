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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import de.r2soft.empires.framework.util.TimeUtil;

public class R2Game implements ApplicationListener {

  private Screen screen;
  private List<Screen> overlays;
  private Logger logger = Logger.getLogger(getClass().getSimpleName());

  public R2Game() {
	overlays = new ArrayList<Screen>();
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

  public void setScreen(Screen screen) {
	if (this.screen != null)
	  this.screen.hide();
	this.screen = screen;
	if (this.screen != null) {
	  this.screen.show();
	  this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
  }

  public Screen getScreen() {
	return screen;
  }

  public void addOverlay(Screen overlay) {
	if (!overlays.contains(overlay))
	  overlays.add(overlay);
	else
	  logger.info("That exact overlay was already being displayed at " + TimeUtil.getTimeNow());
  }
}
