package bucket.game.client.util;

/* 
 * Copyright (c) 2012 Katharina Fey
 * 
 package bucket.game.client.gui;
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
 */
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
