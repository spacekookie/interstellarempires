/* #########################################################################
 * Copyright (c) 2014 RANDOM ROBOT SOFTWORKS
 * (See @authors file)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ######################################################################### */

package de.r2soft.empires.client.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import de.r2soft.empires.client.types.Colour;

public class R2Dialogue extends Dialog {
  public static enum DialogueType {
	ERROR("Error"), HINT("Hint"), FRIENDLY("Message"), QUESTION("Verify");

	private final String name;

	private DialogueType(String s) {
	  name = s;
	}
  }

  /** Preferred sizes to be overwritten */
  private float w = 0, h = 0;
  private String[] contents;
  private Skin skin;

  public R2Dialogue(String title, Skin skin, DialogueType type) {
	this(title, null, skin, type);
  }

  public R2Dialogue(String title, String[] contents, Skin skin, DialogueType type) {
	super(type.toString() + ": " + title, skin);

	/** Saving additional info */
	this.contents = contents;
	this.skin = skin;

	init(type);
  }

  public void setLongContent(String content) {
	Label l = new Label(content, this.skin);
	l.setWrap(true);
	l.setWidth(w);
	super.text(l);
  }

  /**
   * Adds a text button to the button table.
   *
   * @param listener
   *          the input listener that will be attached to the button.
   */
  public R2Dialogue button(String buttonText, InputListener listener) {
	TextButton button = new TextButton(buttonText, skin);
	button.addListener(listener);
	button(button);
	return this;
  }

  /**
   * Positions the Dialogue at the centre of the game screen.
   * 
   * @return instance of self for method chaining.
   */
  public R2Dialogue center() {
	float xc = Gdx.graphics.getWidth() / 2;
	float yc = Gdx.graphics.getHeight() / 2;
	xc -= getWidth() / 2;
	yc -= getHeight() / 2;
	setPosition(xc, yc);
	return this;
  }

  private void init(DialogueType type) {
	super.padTop(60); // set padding on top of the dialog title
	super.getButtonTable().defaults().height(50); // set buttons height
	super.getButtonTable().defaults().width(150);
	super.setModal(true);
	super.setMovable(false);
	super.setResizable(false);
	this.setSize(350f, 175f);

	if (type == DialogueType.ERROR)
	  setColor(Colour.RED);

	if (type == DialogueType.QUESTION)
	  setColor(Colour.YELLOW);

	if (type == DialogueType.FRIENDLY || type == DialogueType.HINT)
	  setColor(Colour.GREEN);

	if (contents != null) {
	  StringBuilder sb = new StringBuilder();
	  for (String s : contents)
		sb.append(s + "\n");
	  super.text(new Label(sb.toString(), skin));
	}
  }

  @Override
  public void setSize(float w, float h) {
	super.setSize(w, h);
	this.w = w;
	this.h = h;
  }

  @Override
  public float getPrefWidth() {
	return (w == 0) ? 350f : w;
  }

  @Override
  public float getPrefHeight() {
	return (h == 0) ? 180f : h;
  }
}
