package de.r2soft.empires.client.screens.overlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import de.r2soft.empires.client.graphics.R2Overlay;
import de.r2soft.empires.client.resources.Assets;

public class PlaygroundOverlay extends R2Overlay {

  public PlaygroundOverlay() {
	super(new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
  }

  Object[] listEntries = { "This is a list entry1", "And another one1", "The meaning of life1", "Is hard to come by1",
	  "This is a list entry2", "And another one2", "The meaning of life2", "Is hard to come by2", "This is a list entry3",
	  "And another one3", "The meaning of life3", "Is hard to come by3", "This is a list entry4", "And another one4",
	  "The meaning of life4", "Is hard to come by4", "This is a list entry5", "And another one5", "The meaning of life5",
	  "Is hard to come by5" };

  Label fpsLabel;
  Skin skin;

  @Override
  public void build() {
	skin = Assets.UI_SKIN;

	Label myLabel = new Label("this is some text.", skin);
	myLabel.setWrap(true);

	Table t = new Table();
	t.row();
	t.add(myLabel);

	t.layout();

	CheckBox checkBox = new CheckBox("Check me", skin);
	final Slider slider = new Slider(0, 10, 1, false, skin);
	TextField textfield = new TextField("", skin);
	textfield.setMessageText("Click here!");
	SelectBox dropdown = new SelectBox(listEntries, skin);
	dropdown.setSelection("Linux6");
	List list = new List(listEntries, skin);
	list.setItems(listEntries);
	// list.getSelection().setToggle(true);
	ScrollPane scrollPane2 = new ScrollPane(list, skin);
	scrollPane2.setFlickScroll(false);
	SplitPane splitPane = new SplitPane(scrollPane2, scrollPane2, false, skin, "default-horizontal");
	fpsLabel = new Label("fps:", skin);

	// configures an example of a TextField in password mode.
	final Label passwordLabel = new Label("Textfield in password mode: ", skin);
	final TextField passwordTextField = new TextField("", skin);
	passwordTextField.setMessageText("password");
	passwordTextField.setPasswordCharacter('*');
	passwordTextField.setPasswordMode(true);

	// window.debug();
	Window window = new Window("Dialog", skin);
	window.getButtonTable().add(new TextButton("X", skin)).height(window.getPadTop());
	window.setPosition(0, 0);
	window.defaults().spaceBottom(10);
	window.row().fill().expandX();
	window.add(checkBox);
	window.add(slider).minWidth(100).fillX().colspan(3);
	window.row();
	window.add(dropdown);
	window.add(textfield).minWidth(100).expandX().fillX().colspan(3);
	window.row();
	window.add(splitPane).fill().expand().colspan(4).maxHeight(200);
	window.row();
	window.add(passwordLabel).colspan(2);
	window.add(passwordTextField).minWidth(100).expandX().fillX().colspan(2);
	window.row();
	window.add(fpsLabel).colspan(4);
	window.pack();

	// stage.addActor(new Button("Behind Window", skin));
	stage.addActor(window);

	textfield.setTextFieldListener(new TextFieldListener() {
	  public void keyTyped(TextField textField, char key) {
		if (key == '\n')
		  textField.getOnscreenKeyboard().show(false);
	  }
	});

	slider.addListener(new ChangeListener() {
	  public void changed(ChangeEvent event, Actor actor) {
		Gdx.app.log("UITest", "slider: " + slider.getValue());
	  }
	});

  }

  @Override
  public void setInputFocus() {
	Gdx.input.setInputProcessor(stage);
  }

}
