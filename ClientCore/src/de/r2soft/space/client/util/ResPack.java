/* #########################################################################
 * Copyright (c) 2013 Random Robot Softworks
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
 * 
 ######################################################################### */
package de.r2soft.space.client.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Global resource loader for the game client. Will distribute all texture
 * files.
 * 
 * @author Katharina
 * 
 */
public class ResPack {

	// Textures

	/** Atlas imports */
	private static final TextureAtlas INITIAL_MAP_HEX = new TextureAtlas(
			Gdx.files.internal("assets/map/prot-map-tiles.pack"));
	private static final TextureAtlas INITIAL_MAP_SOLAR = new TextureAtlas(
			Gdx.files.internal("assets/solar/prot-solarsystem-icons.pack"));
	private static final TextureAtlas INITIAL_UI_SELECTION = new TextureAtlas(
			Gdx.files.internal("assets/gui/prot-selected.atlas"));
	private static final TextureAtlas INITIAL_MAP_ADDITIONS = new TextureAtlas(
			Gdx.files.internal("assets/map/adds/prot-tile-additions.atlas"));
	private static final TextureAtlas INITIAL_BASIC_UNITS = new TextureAtlas(
			Gdx.files.internal("assets/ships/basic-ships.pack"));

	/** Skins */

	/** The default skin for ui elements */
	public static final Skin UI_SKIN = new Skin(
			Gdx.files.internal("assets/gui/skins/defaults/uiskin.json"));

	/** UI regions */

	/** Selection box for units and planets */
	public static final TextureRegion GUI_FRAME_SELECTION = INITIAL_UI_SELECTION
			.findRegion("selected");

	/** Tile regions */

	/** Hostile occupied tile */
	public static final TextureRegion TILE_HEX_ENEMY = INITIAL_MAP_HEX
			.findRegion("prot-map-tile-hostile");
	/** Allied tile */
	public static final TextureRegion TILE_HEX_FRIEND = INITIAL_MAP_HEX
			.findRegion("prot-map-tile-friend");
	/** Neutral tile */
	public static final TextureRegion TILE_HEX_NEUTRAL = INITIAL_MAP_HEX
			.findRegion("prot-map-tile-neutral");
	/** Player owned tile */
	public static final TextureRegion TILE_HEX_PLAYER = INITIAL_MAP_HEX
			.findRegion("prot-map-tile-player");

	/** System contains player fleet */
	public static final TextureRegion TILE_ADD_FLEET_PLAYER = INITIAL_MAP_ADDITIONS
			.findRegion("prot-tile-fleet-player");
	/** System contains friendly fleet */
	public static final TextureRegion TILE_ADD_FLEET_FRIENDLY = INITIAL_MAP_ADDITIONS
			.findRegion("prot-tile-fleet-allied");
	/** System contains hostile fleet */
	public static final TextureRegion TILE_ADD_FLEET_ENEMY = INITIAL_MAP_ADDITIONS
			.findRegion("prot-tile-fleet-hostile");
	/** System contains player station */
	public static final TextureRegion TILE_ADD_STATION_PLAYER = INITIAL_MAP_ADDITIONS
			.findRegion("prot-tile-station");
	/** The tile addition size used for position and size */
	public static final float SIZE_TILE_ADD_SIZE = 20;

	/** Fleet regions */

	/** Player owned fighter fleet */
	public static final TextureRegion FLEET_FIGHTER_PLAYER = INITIAL_MAP_SOLAR
			.findRegion("prot-fleet-fighter-player");
	/** Hostile fighter fleet */
	public static final TextureRegion FLEET_FIGHTER_ENEMY = INITIAL_MAP_SOLAR
			.findRegion("prot-fleet-fighter-hostile");
	/** Allied fighter fleet */
	public static final TextureRegion FLEET_FIGHTER_FRIEND = INITIAL_MAP_SOLAR
			.findRegion("prot-fleet-fighter-ally");

	/** Single unit regions */

	/** Single basic fighter unit. No colour coding for alliances */
	public static final TextureRegion UNITS_FIGHTER_BASIC = INITIAL_BASIC_UNITS
			.findRegion("small_fighter");
	/** Single small cargo freighter unit. No colour coding for alliances */
	public static final TextureRegion UNITS_CARGO_SMALL = INITIAL_BASIC_UNITS
			.findRegion("cargo_freighter_small");

	/** Star regions */

	/**
	 * ### Star classification definitions: ###
	 * 
	 * Class O: 40'000K Class B: 20'000K Class A: 10'000K Class F: 7'500K Class G:
	 * 5'500K Class K: 4'500K Class M: 3'000K
	 * 
	 * Size magnitudes: 15: Neutron Stars 13: Brown Dwarfs 10: Red Dwarfs 8: Yellow
	 * Dwarfs 5: Pretty green stars 0: Red giants/ (Main sequence: teal giants) -5:
	 * Blue Giants, Red Super-giants -10: Super big ass stars (Red and blue)
	 */

	/** Brown dwarf star */
	public static final TextureRegion STARS_BROWN_DWARF = INITIAL_MAP_SOLAR
			.findRegion("prot-star-browndwarf");
	/** Red dwarf star */
	public static final TextureRegion STARS_RED_DWARF = INITIAL_MAP_SOLAR
			.findRegion("prot-star-reddwarf");
	/** Red giant star */
	public static final TextureRegion STARS_RED_GIANT = INITIAL_MAP_SOLAR
			.findRegion("prot-star-reddwarf");
	/** Neutron star */
	public static final TextureRegion STARS_BLUE_DWARF = INITIAL_MAP_SOLAR
			.findRegion("prot-star-neutron");
	/** Blue super-giant */
	public static final TextureRegion STARS_BLUE_GIANT = INITIAL_MAP_SOLAR
			.findRegion("prot-star-neutron");
}