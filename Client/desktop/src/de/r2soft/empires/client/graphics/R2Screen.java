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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

/**
 * Screen class to implement the Input Priority method used by the InputMatrix master/slave system of input handling and multiplexing.
 * 
 * @author Katharina <kookie@spacekookie.de>
 * 
 */
public abstract class R2Screen implements Screen {
  private Long ID;

  public R2Screen() {
	// TODO: Actually generate Screen ID here
  }

  /**
   * Sets the Screen ID once during creation and will throw a RuntimeException if it is tried to set again
   */
  public void setID(long ID) {
	this.ID = this.ID == null ? ID : throw_();
  }

  public int throw_() {
	throw new RuntimeException("id is already set");
  }

  /** Get screen ID */
  public long getID() {
	return ID;
  }

  @Override
  public void render(float delta) {

  }

  @Override
  public void resize(int width, int height) {

  }

  @Override
  public void show() {

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

  }

  public void setInputFocus() {

  }

  public InputProcessor getInputFocus() {
	return Gdx.input.getInputProcessor();
  }

}
