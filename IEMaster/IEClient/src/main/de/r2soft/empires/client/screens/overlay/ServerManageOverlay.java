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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import lombok.Data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import de.r2soft.empires.client.core.GameCore;
import de.r2soft.empires.client.graphics.R2Overlay;
import de.r2soft.empires.client.resources.Assets;
import de.r2soft.empires.client.resources.SettingsInterface;
import de.r2soft.empires.client.resources.Values;
import de.r2soft.empires.client.screens.utilities.LoginScreen;
import de.r2soft.empires.client.util.Server;

/**
 * @author spacekookie
 */
public class ServerManageOverlay extends R2Overlay {

  static @Data class ServerCombo {
	private Label label;
	private CheckBox box;

	public ServerCombo(Label label, CheckBox box) {
	  this.label = label;
	  this.box = box;
	}
  }

  private TextField serverName, serverURL, serverPortA, serverPortB;
  private TextButton addServer, removeServers, save, cancel, resetServers;
  private Map<Server, ServerCombo> servers;
  private Label header;
  private Table main;

  public ServerManageOverlay() {
	super(new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));
	servers = new HashMap<Server, ServerCombo>();

	this.buildServerList();

	this.reinforce();
  }

  private void buildServerList() {
	for (Server s : fetchServers()) {
	  servers.put(s, new ServerCombo(new Label(buildServerName(s), Assets.R2_UI_SKIN), new CheckBox("",
		  Assets.R2_UI_SKIN)));
	}
  }

  private void reinforce() {
	float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	Vector2 start = new Vector2((width / 2) - 350, 0);
	Vector2 size = new Vector2(700, height);
	float alpha = 0.9f;
	Color color = new Color(Color.BLACK);
	super.addAdditionalAlpha(start, size, alpha, color);
  }

  @Override
  public void build() {
	this.updateServerUI();
  }

  private void setupListeners() {
	addServer.addListener(new ClickListener() {
	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		String name = serverName.getText();
		String url = serverURL.getText();
		int tcp = Integer.parseInt(serverPortA.getText());
		int udp = Integer.parseInt(serverPortB.getText());
		Server s = new Server(name, url, tcp, udp);

		servers.put(s, new ServerCombo(new Label(buildServerName(s), Assets.R2_UI_SKIN), new CheckBox("",
			Assets.R2_UI_SKIN)));

		updateServerUI();
	  }
	});

	removeServers.addListener(new ClickListener() {
	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

		Iterator<Entry<Server, ServerCombo>> it = servers.entrySet().iterator();
		while (it.hasNext()) {
		  Map.Entry<Server, ServerCombo> pairs = it.next();

		  if (pairs.getValue().getBox().isChecked()) {
			it.remove();
		  }
		}
		updateServerUI();
	  }
	});

	cancel.addListener(new ClickListener() {
	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		GameCore.getInstance().removeOverlay();
	  }
	});

	resetServers.addListener(new ClickListener() {
	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		String[] servers = new String[1];
		servers[0] = "Ergosphere (Official):empires.2rsoftworks.de/ergosphere:52001:52011";
		SettingsInterface.getInstance().putList(servers, Values.PREFERENCE_LIST_SERVERS);

		// buildServerList(); // Trigger fetch from database
		//
		// updateServerUI(); // Trigger redraw on slave
		GameCore.getInstance().removeOverlay();
		((LoginScreen) GameCore.getInstance().getScreen()).redraw(); // Trigger redraw on master
	  }
	});

	save.addListener(new ClickListener() {
	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

		String[] tmp = new String[servers.size()];
		int index = 0;

		for (Server s : servers.keySet()) {
		  tmp[index] = s.toCompactString();
		  index++;
		}

		// Write new list of Servers to the DB
		SettingsInterface.getInstance().putList(tmp, Values.PREFERENCE_LIST_SERVERS);

		String[] mega = SettingsInterface.getInstance().getList(Values.PREFERENCE_LIST_SERVERS);

		for (String s : mega)
		  System.out.println(s);

		// Close the overlay and return to the Login screen
		GameCore.getInstance().removeOverlay();

		((LoginScreen) GameCore.getInstance().getScreen()).redraw();
	  }
	});
  }

  private void updateServerUI() {
	stage.clear();

	serverName = new TextField("", Assets.R2_UI_SKIN);
	serverName.setMessageText("Server Name");

	serverURL = new TextField("", Assets.R2_UI_SKIN);
	serverURL.setMessageText("Server URL");

	serverPortA = new TextField("", Assets.R2_UI_SKIN);
	serverPortA.setMessageText("TCP Port");

	serverPortB = new TextField("", Assets.R2_UI_SKIN);
	serverPortB.setMessageText("UDP Port");

	addServer = new TextButton("+", Assets.R2_UI_SKIN);
	addServer.setColor(Color.GREEN);

	resetServers = new TextButton("Revert Settings", Assets.R2_UI_SKIN);
	resetServers.setColor(Color.YELLOW);

	removeServers = new TextButton("Remove selected", Assets.R2_UI_SKIN);
	save = new TextButton("Apply", Assets.R2_UI_SKIN);
	cancel = new TextButton("Cancel", Assets.R2_UI_SKIN);

	header = new Label("Manage Servers", Assets.R2_UI_SKIN);
	header.setFontScale(2f);

	main = new Table();
	main.setFillParent(true);
	main.center();

	main.add(header).colspan(5).padBottom(Values.R2_UI_PIXEL_PAD_SMALL);
	main.row();
	main.add(serverName).padBottom(Values.R2_UI_PIXEL_PAD_SMALL);
	main.add(serverURL).padBottom(Values.R2_UI_PIXEL_PAD_SMALL);
	main.add(serverPortA).width(100f).padBottom(Values.R2_UI_PIXEL_PAD_SMALL);
	main.add(serverPortB).width(100f).padBottom(Values.R2_UI_PIXEL_PAD_SMALL);
	main.add(addServer).width(25f).padBottom(Values.R2_UI_PIXEL_PAD_SMALL);
	main.row();

	if (!servers.isEmpty())
	  for (ServerCombo sc : servers.values()) {
		main.add(sc.getLabel()).colspan(4).left();
		main.add(sc.getBox()).colspan(1).right();
		main.row();
	  }
	else {
	  main.add(
		  new Label("> No servers registered. Check 'empires.2rsoftworks.de/servers' for help", Assets.R2_UI_SKIN))
		  .colspan(5).left();
	  main.row();
	}

	Table subrow = new Table();
	float bottomrow = 175f;
	subrow.add(cancel).minWidth(bottomrow);
	subrow.add(save).minWidth(bottomrow);
	subrow.add(removeServers).minWidth(bottomrow);
	subrow.row();
	subrow.add();
	subrow.add();
	subrow.add(resetServers).minWidth(bottomrow);

	main.add(subrow).colspan(5).fillX().padTop(Values.R2_UI_PIXEL_PAD_MEDIUM);
	main.add();

	stage.addActor(main);
	setupListeners();
  }

  /**
   * Transforms a server into a single line string.
   * 
   * @param Server
   *          s
   * @return String with all important Server information
   */
  private String buildServerName(Server s) {
	return "[ " + s.toString() + " ]";
  }

  public void render(float delta) {
	super.render(delta);

	// System.out.println(servers);

	/** Paints all of the labels red that are going to be removed. */
	for (ServerCombo sc : servers.values()) {
	  if (sc.getBox().isChecked()) {
		// System.out.println(sc.getLabel().getText() + " is checked!");
		sc.getLabel().setColor(Color.RED);
		removeServers.setColor(Color.PINK);
		removeServers.setDisabled(false);
		break;
	  }
	  else {
		sc.getLabel().setColor(Color.WHITE);
		removeServers.setColor(Color.LIGHT_GRAY);
		removeServers.setDisabled(true);
	  }
	}
  }

  private Server[] fetchServers() {
	String[] tmp = SettingsInterface.getInstance().getList(Values.PREFERENCE_LIST_SERVERS);
	Server[] servers = new Server[tmp.length];

	for (int n = 0; n < servers.length; n++) {
	  servers[n] = new Server(tmp[n]);
	}
	return servers;
  }

  @Override
  public void setInputFocus() {
	Gdx.input.setInputProcessor(stage);
  }

}
