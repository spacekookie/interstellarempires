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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Global resource loader for the game client. Will initialize and distribute all texture files.
 * 
 * @author Katharina
 * 
 */
public class ResPack {

	/** ########## TextureAtlas imports ########### */
	private static final TextureAtlas HEX_MAP = new TextureAtlas(Gdx.files.internal("assets/map/prot-map-tiles.pack"));
	private static final TextureAtlas SOLAR_MAP = new TextureAtlas(
			Gdx.files.internal("assets/solar/prot-solarsystem-icons.pack"));
	private static final TextureAtlas GUI_SELECTED = new TextureAtlas(Gdx.files.internal("assets/gui/prot-selected.atlas"));

	/** ########## Ui skin import ########### */
	public static final Skin UI_SKIN = new Skin(Gdx.files.internal("assets/gui/skins/defaults/uiskin.json"));

	/** ########## Stars ########### */
	public static final TextureRegion STAR_BROWN_DWARF = SOLAR_MAP.findRegion("prot-star-browndwarf");
	public static final TextureRegion STAR_RED_DWARF = SOLAR_MAP.findRegion("prot-star-reddwarf");
	public static final TextureRegion STAR_NEUTRON = SOLAR_MAP.findRegion("prot-star-neutron");

	/** ########## Fleets ########### */
	public static final TextureRegion FLEET_FIGHTER_ALLY = SOLAR_MAP.findRegion("prot-fleet-fighter-ally");
	public static final TextureRegion FLEET_FIGHTER_ENEMY = SOLAR_MAP.findRegion("prot-fleet-fighter-hostile");
	public static final TextureRegion FLEET_FIGHTER_PLAYER = SOLAR_MAP.findRegion("prot-fleet-fighter-player");
	public static final TextureRegion GUI_SELECTION_BOX = GUI_SELECTED.findRegion("selected");

	/** ########## Hex Tiles ########### */
	public static final TextureRegion HEX_TILE_ENEMY = HEX_MAP.findRegion("prot-map-tile-hostile");
	public static final TextureRegion HEX_TILE_FRIEND = HEX_MAP.findRegion("prot-map-tile-friend");
	public static final TextureRegion HEX_TILE_NEUTRAL = HEX_MAP.findRegion("prot-map-tile-neutral");
	public static final TextureRegion HEX_TILE_PLAYER = HEX_MAP.findRegion("prot-map-tile-player");

	/** ########## Star Sizes ########### */
	public static final float STAR_BROWN_DWARF_SIZE = 40f;
	public static final float STAR_RED_DWARF_SIZE = 80f;
	public static final float STAR_RED_GIANT_SIZE = 150f;
	public static final float STAR_BLUE_DWARF_SIZE = 25f;
	public static final float STAR_BLUE_GIANT_SIZE = 150f;

	/** ########## Fleet Sizes ########### */
	public static final float FLEET_FIGHTER_SIZE_SMALL = 32f;
	public static final float FLEET_FIGHTER_SIZE_MEDIUM = 48f;
	public static final float FLEET_FIGHTER_SIZE_LARGE = 64f;

	/** ########## Selection Sizes ########### */
	public static final float GUI_ELEMENT_SELECTION_SMALL = 36f;
	public static final float GUI_ELEMENT_SELECTION_MEDIUM = 52f;
	public static final float GUI_ELEMENT_SELECTION_LARGE = 70f;

	/** ########## System sizes ########### */
	public static final float SYSTEM_SIZE_BROWN_DWARF = 180f;
	public static final float SYSTEM_SIZE_BLUE_GIANT = 280f;
	public static final float SYSTEM_SIZE_BLUE_DWARF = 140f;
	public static final float SYSTEM_SIZE_RED_GIANT = 270f;
	public static final float SYSTEM_SIZE_RED_DWARF = 220f;

}
