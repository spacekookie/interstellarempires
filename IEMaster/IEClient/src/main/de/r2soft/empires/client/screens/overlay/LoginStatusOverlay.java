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

package de.r2soft.empires.client.screens.overlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import de.r2soft.empires.client.core.GameCore;
import de.r2soft.empires.client.graphics.R2Overlay;
import de.r2soft.empires.client.networking.ConnectionHandler;
import de.r2soft.empires.client.resources.Assets;
import de.r2soft.empires.client.resources.Values;
import de.r2soft.empires.client.util.Server;
import de.r2soft.empires.framework.players.Player;

/**
 * @author Katharina Fey
 */
public class LoginStatusOverlay extends R2Overlay {

  private Label label;
  private Server server;
  private TextButton cancel;
  private Player player;

  public LoginStatusOverlay(Server server, Player player) {
	super(new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())), Assets.R2_UI_SKIN);
	super.addAdditionalAlpha(new Vector2(0, 0), new Vector2(Values.NEW_WIDTH, Values.NEW_HEIGHT), 0.6f);
	this.server = server;
	this.player = player;
	ConnectionHandler.getInstance().connect(this.server);
  }

  @Override
  public void build() {
	label = new Label("Attempting to connect to server " + server.getName() + " with username "
		+ this.player.getName(), Assets.R2_UI_SKIN);
	cancel = new TextButton("Aboard connect", Assets.R2_UI_SKIN);

	main.add(label).colspan(4).padBottom(75f);
	main.row();
	main.add(cancel).left();
	main.add();
	main.add();
	main.add();
	main.center().setY(75f);

	this.setupListeners();
  }

  private void setupListeners() {
	cancel.addListener(new ClickListener() {
	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		ConnectionHandler.getInstance().stopConnection();
		GameCore.getInstance().removeOverlay();
	  }
	});
  }

  @Override
  public void setInputFocus() {
	Gdx.input.setInputProcessor(stage);
  }

}
