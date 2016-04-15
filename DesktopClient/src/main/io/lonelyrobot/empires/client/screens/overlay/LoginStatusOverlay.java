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

package io.lonelyrobot.empires.client.screens.overlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import io.lonelyrobot.empires.client.core.GameCore;
import io.lonelyrobot.empires.client.graphics.R2Dialogue;
import io.lonelyrobot.empires.client.graphics.R2Overlay;
import io.lonelyrobot.empires.client.graphics.R2Dialogue.DialogueType;
import io.lonelyrobot.empires.client.networking.ConnectionHandler;
import io.lonelyrobot.empires.client.resources.Assets;
import io.lonelyrobot.empires.client.resources.Values;
import io.lonelyrobot.empires.client.util.Server;
import io.lonelyrobot.empires.framework.players.Player;

/**
 * @author ***REMOVED***
 */
public class LoginStatusOverlay extends R2Overlay {
	private enum ConStep {
		NULL, INIT, AUTH, FINISH;
	}

	private boolean attemptedLogin = false, start = false;
	private float progress = 0;
	private TextButton cancel;
	private Server server;
	private Player player;
	private ConStep prog = ConStep.NULL;
	private Label label;

	public LoginStatusOverlay(Server server, Player player) {
		super(new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())), Assets.R2_UI_SKIN);
		super.addAdditionalAlpha(new Vector2(0, 0), new Vector2(Values.NEW_WIDTH, Values.NEW_HEIGHT), 0.6f);
		this.server = server;
		this.player = player;
	}

	@Override
	public void build() {
		label = new Label("Attempting to connect to server "
				/* + server.getName() */ + " with username '" + this.player.getName() + "'", Assets.R2_UI_SKIN);
		cancel = new TextButton("Aboard connect", Assets.R2_UI_SKIN);

		main.debug();

		main.add(label).colspan(2).padBottom(150f);
		main.row();
		main.add(cancel).maxWidth(150);

		Skin skin = new Skin();
		Pixmap pixmap = new Pixmap(10, 10, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		TextureRegionDrawable textureBar = new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("barGreen_horizontalMid.png"))));
		ProgressBarStyle barStyle = new ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBar);
		barStyle.knobBefore = barStyle.knob;
		bar = new ProgressBar(0, 100, 0.5f, false, barStyle);
		// bar.setFillParent(true);
		bar.setSize(400, bar.getPrefHeight());
		bar.setAnimateDuration(0.75f);

		main.add(bar).left().minWidth(400);
		main.center().setY(75f);
	}

	public void start() {
		this.start = true;
	}

	ProgressBar bar;

	private void setupListeners() {
		cancel.clearListeners();
		cancel.addListener(new ClickListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				GameCore.getInstance().removeOverlay();
			}
		});
	}

	private void incrementProgress() {
		progress += 33.333333333f;
	}

	public void render(float delta) {
		super.render(delta);

		if (prog == ConStep.NULL)
			progress = 0f;

		if (prog == ConStep.INIT)
			progress = 1f / 3f;

		if (prog == ConStep.AUTH)
			progress = 2f / 3f;

		if (prog == ConStep.FINISH)
			progress = 3f / 3f;

		bar.setValue(progress);
		stage.act();

		if (start && !attemptedLogin)
			contactMothership(this.server);
	}

	private void contactMothership(Server server) {
		attemptedLogin = true;

		boolean init = ConnectionHandler.getInstance().connectInit(server);
		if (init)
			prog = ConStep.INIT;
		else {
			String[] con = new String[] { "The server you were trying to connect to wasn't reachable!",
					"Please check your network settings or try again later.", "" };
			final R2Dialogue m = new R2Dialogue("Connection Failed", con, Assets.R2_UI_SKIN, DialogueType.ERROR);
			m.button("OK", new ClickListener() {
				public void touchUp(InputEvent arg0, float arg1, float arg2, int arg3, int arg4) {
					m.remove();
					GameCore.getInstance().removeOverlay();
				}
			});
			m.setSize(450, 175);
			stage.addActor(m.center());
			return;
		}

		boolean handshake = ConnectionHandler.getInstance().connectHandshake();

		if (handshake)
			prog = ConStep.AUTH;
		else {
			String[] con = new String[] { "Authentication with the Server failed!",
					"Please check your username and password and try again.", "" };
			final R2Dialogue m = new R2Dialogue("Authentication Failed", con, Assets.R2_UI_SKIN, DialogueType.ERROR);
			m.button("OK", new ClickListener() {
				public void touchUp(InputEvent arg0, float arg1, float arg2, int arg3, int arg4) {
					m.remove();
					GameCore.getInstance().removeOverlay();
				}
			});
			m.setSize(450, 175);
			stage.addActor(m.center());
			return;
		}

		if (prog == ConStep.AUTH) {
			String[] con = new String[] { "Congratulations. You are the first person EVER to",
					"log into an Interstellar Empires server!", "" };
			final R2Dialogue m = new R2Dialogue("Authentication Successful", con, Assets.R2_UI_SKIN,
					DialogueType.FRIENDLY);
			m.button("OK", new ClickListener() {
				public void touchUp(InputEvent arg0, float arg1, float arg2, int arg3, int arg4) {
					m.remove();
					GameCore.getInstance().removeOverlay();
				}
			});
			m.setSize(450, 175);
			stage.addActor(m.center());
		}
		//
		// boolean finish = ConnectionHandler.getInstance().connectHandshake();
		//
		// if (finish)
		// prog = ConStep.FINISH;
		// else {
		// String[] con = new String[] { "Handshaking and verification with the
		// Server failed!",
		// "Please try again later or check with the server provider",
		// "for more information if the problem persists.", "" };
		// final R2Dialogue m = new R2Dialogue("Handshake Failed", con,
		// Assets.R2_UI_SKIN, DialogueType.ERROR);
		// m.button("OK", new ClickListener() {
		// public void touchUp(InputEvent arg0, float arg1, float arg2, int
		// arg3, int arg4) {
		// m.remove();
		// GameCore.getInstance().removeOverlay();
		// }
		// });
		// m.setSize(450, 200);
		// stage.addActor(m.center());
		// System.out.println("Stuff!");
		// return;
		// }

	}

	@Override
	public void setInputFocus() {
		Gdx.input.setInputProcessor(stage);
		this.setupListeners();
	}

}
