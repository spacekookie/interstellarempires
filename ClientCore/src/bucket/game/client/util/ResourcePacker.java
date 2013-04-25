package bucket.game.client.util;

import bucket.game.client.core.ScreenHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ResourcePacker {

	private ScreenHandler handler;
	private TextureAtlas atlas;
	private Skin uiSkin;

	private TextureRegion hosTile, friendTile, neuTile, playTile;

	/**
	 * Initialises the types
	 * 
	 * @param handler
	 */
	public ResourcePacker(ScreenHandler handler) {
		this.handler = handler;
		uiSkin = new Skin();
	}

	public ResourcePacker() {

		atlas = new TextureAtlas(Gdx.files.internal("assets/map/prot-map-tiles.pack"));
	}

	public void loadTextures() {

		// See what I did there? ;)
		hosTile = atlas.findRegion("prot-map-tile-hostile");
		friendTile = atlas.findRegion("prot-map-tile-friend");
		neuTile = atlas.findRegion("prot-map-tile-neutral");
		playTile = atlas.findRegion("prot-map-tile-player");

	}

	/**
	 * Loads all resources from files
	 */
	public void loadResources() {

	}

}
