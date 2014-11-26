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

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class R2Dialogue extends Dialog {

  public static Table factory() {
	return null;
  }

  public R2Dialogue(String title, Skin skin) {
	super(title, skin);
	init();
  }

  private void init() {
	padTop(50f);
	getButtonTable().defaults().height(75f);
	setModal(true);
	setResizable(false);
  }

  @Override
  public float getPrefWidth() {
	// force dialog width
	return 480f;
  }

  @Override
  public float getPrefHeight() {
	// force dialog height
	return 240f;
  }

}

/**
 * A compact and well formatted Dialogue for small hints and message texts using the default UI skin set in the
 * application config.
 * 
 * @author Katharina Fey
 */
// public class R2Dialogue extends Actor {
// public static enum DialogueType {
// ERROR("Error"), HINT("Hint"), FRIENDLY("Message"), QUESTION("Verify");
//
// private final String name;
//
// private DialogueType(String s) {
// name = s;
// }
//
// public String toString() {
// return name;
// }
//
// }
//
// private TextButton success, fail;
// private String title;
// private DialogueType type;
// private String[] contents;
// private Table master, inner;
//
// public static Table factory(String title, String[] contents, DialogueType type) {
// return new R2Dialogue(title, contents, type).master;
// }
//
// private R2Dialogue(String title, String[] contents, DialogueType type) {
// this.contents = contents;
// this.title = title;
// this.type = type;
//
// Dialog slave = new Dialog(type.toString() + ": " + title, Assets.R2_UI_SKIN);
// slave.setWidth(500f);
// // slave.setFillParent(true);
// // slave.setMovable(false);
//
// master = new Table();
//
// // Inner table holding the UI references
// inner = new Table();
// inner.setFillParent(true);
// inner.left();
//
// if (contents != null)
// for (String s : contents) {
// inner.add(new Label(s, Assets.R2_UI_SKIN));
// inner.row();
// }
//
// switch (type) {
// case ERROR:
//
// break;
//
// default:
// break;
// }
//
// success = new TextButton("OK", Assets.R2_UI_SKIN);
// inner.add(success);
// inner.debug();
// System.out.println(inner.getChildren());
// inner.add();
//
// // inner.setPosition(Gdx.graphics.getWidth() / 2 - (300), Gdx.graphics.getHeight() / 2 - (125));
//
// slave.add(inner);
//
// // TODO: Fix this! FIX THIS NOW!
// // master.setSize(350, 150);
// // master.setFillParent(true);
// // master.setPosition(Gdx.graphics.getWidth() / 2 - (300), Gdx.graphics.getHeight() / 2 - (125));
//
// master.add(slave);
//
// }
// }
