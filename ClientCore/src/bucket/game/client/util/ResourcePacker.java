package bucket.game.client.util;

import bucket.game.client.core.ScreenHandler;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ResourcePacker {

	private ScreenHandler handler;
	private Skin uiSkin;

	/**
	 * Initialises the types
	 * 
	 * @param handler
	 */
	public ResourcePacker(ScreenHandler handler) {
		this.handler = handler;
		uiSkin = new Skin();
	}

	/**
	 * Loads all resources from files
	 */
	public void loadResources() {

	}

}
