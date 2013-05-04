/* 
 * Copyright (c) 2013 Katharina Fey
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
 */

package client.util;

import javax.tools.Diagnostic;

import client.core.ScreenHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

/**
 * Global resource loader for the game client. Will initialize and distribute all texture files.
 * 
 * @author Katharina
 * 
 */
@SuppressWarnings("unused")
public class ResourcePacker implements Disposable {

	private ScreenHandler handler;
	private TextureAtlas hexmap, solarmap, selected;
	private Skin uiSkin;

	/**
	 * Thats how I would do this class. @author Leander begin{example}
	 */
	private static final TextureAtlas hexmapEXAMPLE = new TextureAtlas(Gdx.files.internal("assets/map/prot-map-tiles.pack"));

	public static final TextureRegion hosTileEXAMPLE = hexmapEXAMPLE.findRegion("prot-map-tile-hostile");

	public static final Skin UI_SKIN = new Skin(Gdx.files.internal("assets/gui/skins/defaults/uiskin.json"));

	// TODO: stuff
	/** end{example} */

	public enum RENDER {
		HEXTILE, STARS, FLEET, GUI;
	}

	/** Tile regions */
	private TextureRegion hosTile, friendTile, neuTile, playTile;

	/** Solar regions */
	private TextureRegion fighterAlly, fighterHostile, fighterPlayer;
	private TextureRegion starBrownDwarf, starRedDwarf, starNeutron;
	private TextureRegion frame;

		{
			uiSkin = new Skin(Gdx.files.internal("assets/gui/skins/defaults/uiskin.json"));
			hexmap = new TextureAtlas(Gdx.files.internal("assets/map/prot-map-tiles.pack"));
			solarmap = new TextureAtlas(Gdx.files.internal("assets/solar/prot-solarsystem-icons.pack"));
			selected = new TextureAtlas(Gdx.files.internal("assets/gui/prot-selected.atlas"));
		}

	/** ScreenHandler for constructor */
	public ResourcePacker(ScreenHandler handler) {
		this.handler = handler;
	}

	/** Empty constructor */
	public ResourcePacker() {

	}

	/**
	 * Loads textures from atlas files. TODO: Replace with smart algorithm. TODO: Why do I have to call this method over and over
	 * again?
	 */
	public void loadTextures(RENDER r) {

		// Tile regions //
		if (r.equals(RENDER.HEXTILE))
			{
				hosTile = hexmap.findRegion("prot-map-tile-hostile");
				friendTile = hexmap.findRegion("prot-map-tile-friend");
				neuTile = hexmap.findRegion("prot-map-tile-neutral");
				playTile = hexmap.findRegion("prot-map-tile-player");
			}

		// Fleet regions //
		if (r.equals(RENDER.FLEET))
			{
				fighterAlly = solarmap.findRegion("prot-fleet-fighter-ally");
				fighterHostile = solarmap.findRegion("prot-fleet-fighter-hostile");
				fighterPlayer = solarmap.findRegion("prot-fleet-fighter-player");
				frame = selected.findRegion("selected");
			}

		// Star regions //
		if (r.equals(RENDER.STARS))
			{
				starBrownDwarf = solarmap.findRegion("prot-star-browndwarf");
				starRedDwarf = solarmap.findRegion("prot-star-reddwarf");
				starNeutron = solarmap.findRegion("prot-star-neutron");
			}
	}

	public TextureRegion getFrame() {
		return frame;
	}

	public void setFrame(TextureRegion frame) {
		this.frame = frame;
	}

	public Skin getUiSkin() {
		return uiSkin;
	}

	public TextureRegion getHosTile() {
		return hosTile;
	}

	public TextureRegion getFriendTile() {
		return friendTile;
	}

	public TextureRegion getNeuTile() {
		return neuTile;
	}

	public TextureRegion getPlayTile() {
		return playTile;
	}

	public TextureRegion getFighterAlly() {
		return fighterAlly;
	}

	public TextureRegion getFighterHostile() {
		return fighterHostile;
	}

	public TextureRegion getFighterPlayer() {
		return fighterPlayer;
	}

	public TextureRegion getStarBrownDwarf() {
		return starBrownDwarf;
	}

	public TextureRegion getStarRedDwarf() {
		return starRedDwarf;
	}

	public TextureRegion getStarNeutron() {
		return starNeutron;
	}

	@Override
	public void dispose() {
		hexmap.dispose();
		solarmap.dispose();
		uiSkin.dispose();
	}

}
