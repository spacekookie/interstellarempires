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
 * @author ***REMOVED***
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

	// SIZES

	/** Star sizes */

	/** Brown Dwarf */
	public static final float SIZE_CELESTIAL_BROWN_DWARF = 25;
	/** Red Dwarf */
	public static final float SIZE_CELESTIAL_RED_DWARF = 70;
	/** Red Giant */
	public static final float SIZE_CELESTIAL_RED_GIANT = 120;
	/** Neutron Star */
	public static final float SIZE_CELESTIAL_BLUE_DWARF = 20;
	/** Blue Supergiant */
	public static final float SIZE_CELESTIAL_BLUE_GIANT = 140;
	/** Small black hole */
	public static final float SIZE_CELESTIAL_BLACK_DWARF = 10;
	/** Medium black hole */
	public static final float SIZE_CELESTIAL_BLACK_MODERATE = 40;
	/** Large black hole */
	public static final float SIZE_CELESTIAL_BLACK_GIANT = 120;

	/** Fleet sizes */

	/** < 10 ships */
	public static final float SIZE_FLEET_TINY = 30;
	/** < 25 ships */
	public static final float SIZE_FLEET_SMALL = 40;
	/** < 50 ships */
	public static final float SIZE_FLEET_MEDIUM = 50;
	/** < 100 ships */
	public static final float SIZE_FLEET_LARGE = 60;
	/** < 250 ships */
	public static final float SIZE_FLEET_GIANT = 70;

	/** GUI sizes */

	/** Selection box sizes */
	public static final float SIZE_GUI_SELECTION_BOX_TINY = SIZE_FLEET_TINY + 4;
	public static final float SIZE_GUI_SELECTION_BOX_SMALL = SIZE_FLEET_SMALL + 4;
	public static final float SIZE_GUI_SELECTION_BOX_MEDIUM = SIZE_FLEET_MEDIUM + 4;
	public static final float SIZE_GUI_SELECTION_BOX_LARGE = SIZE_FLEET_LARGE + 4;
	public static final float SIZE_GUI_SELECTION_BOX_GIANT = SIZE_FLEET_GIANT + 4;

	/** Map tile sizes */
	public static final float SIZE_GUI_HEXAGON_TILE = 100;

	/** GUI ELEMENTS */
	/** How group elements (unwrapped) will be offset from one another */
	public static final float SIZE_UI_GROUP_OFFSET = -20;
	public static final float SIZE_SOLAR_GROUP_OFFSET_INITIAL = -30;

	/** Navigation button: exit, logout, settings, etc. */
	public static final float SIZE_UI_BUTTON_NAVIGON = 150;
	/** Content button: Build units, colonise planets, attack, destroy, etc. */
	public static final float SIZE_UI_BUTTON_CONTENT = 100;
	/** Login fields & explanations, tooltips, etc. */
	public static final float SIZE_UI_FIELD_CONTENT = 200;
	/** Frame offset */
	public static final float SIZE_UI_GLOBAL_FRAME_OFFSET = -35;

	/** Planet sizes */

	/** Asteroids & Rocks */
	public static final float SIZE_CELESTIAL_PLANET_CLASS_A = 0;
	/** Volcanic Planet */
	public static final float SIZE_CELESTIAL_PLANET_CLASS_B = 0;
	/** Desert Planet */
	public static final float SIZE_CELESTIAL_PLANET_CLASS_C = 0;
	/** Earth Planet */
	public static final float SIZE_CELESTIAL_PLANET_CLASS_D = 0;
	/** Ice Planet */
	public static final float SIZE_CELESTIAL_PLANET_CLASS_E = 0;
	/** Gas Planets */
	public static final float SIZE_CELESTIAL_PLANET_CLASS_F = 0;

	/** System sizes */

	/** Brown dwarf solar system */
	public static final float SIZE_SYSTEM_BROWN_DWARF = 140;
	/** Red dwarf solar system */
	public static final float SIZE_SYSTEM_RED_DWARF = 230;
	/** Red giant solar system */
	public static final float SIZE_SYSTEM_RED_GIANT = 280;
	/** Neutron star solar system */
	public static final float SIZE_SYSTEM_WHITE_DWARF = 100;
	/** Blue giant solar system */
	public static final float SIZE_SYSTEM_BLUE_GIANT = 280;
	/** Black hole solar system */
	public static final float SIZE_SYSTEM_BLACK_HOLE = 250;
}