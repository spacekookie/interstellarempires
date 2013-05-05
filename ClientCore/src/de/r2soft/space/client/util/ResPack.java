/* 
 * Copyright (c) 2013 ***REMOVED***
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

package de.r2soft.space.client.util;

import javax.tools.Diagnostic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

import de.r2soft.space.client.core.ScreenHandler;

/**
 * Global resource loader for the game client. Will distribute all texture files.
 * 
 * @author ***REMOVED***
 * 
 */
public class ResPack {

	// Textures

	/** Atlas imports */
	private static final TextureAtlas INITIAL_MAP_HEX = new TextureAtlas(Gdx.files.internal("assets/map/prot-map-tiles.pack"));
	private static final TextureAtlas INITIAL_MAP_SOLAR = new TextureAtlas(Gdx.files.internal("assets/solar/prot-solarsystem-icons.pack"));
	private static final TextureAtlas INITIAL_UI_SELECTION = new TextureAtlas(Gdx.files.internal("assets/gui/prot-selected.atlas"));

	/** Skins */
	public static final Skin UI_SKIN = new Skin(Gdx.files.internal("assets/gui/skins/defaults/uiskin.json"));

	/** UI regions */
	public static final TextureRegion GUI_FRAME_SELECTION = INITIAL_UI_SELECTION.findRegion("selected");

	/** Tile regions */
	public static final TextureRegion TILE_HEX_ENEMY = INITIAL_MAP_HEX.findRegion("prot-map-tile-hostile");
	public static final TextureRegion TILE_HEX_FRIEND = INITIAL_MAP_HEX.findRegion("prot-map-tile-friend");
	public static final TextureRegion TILE_HEX_NEUTRAL = INITIAL_MAP_HEX.findRegion("prot-map-tile-neutral");
	public static final TextureRegion TILE_HEX_PLAYER = INITIAL_MAP_HEX.findRegion("prot-map-tile-player");

	/** Fleet regions */
	public static final TextureRegion FLEET_FIGHTER_PLAYER = INITIAL_MAP_SOLAR.findRegion("prot-fleet-fighter-player");
	public static final TextureRegion FLEET_FIGHTER_ENEMY = INITIAL_MAP_SOLAR.findRegion("prot-fleet-fighter-hostile");
	public static final TextureRegion FLEET_FIGHTER_FRIEND = INITIAL_MAP_SOLAR.findRegion("prot-fleet-fighter-ally");

	/** Star regions */
	public static final TextureRegion STARS_BROWN_DWARF = INITIAL_MAP_SOLAR.findRegion("prot-star-browndwarf");
	public static final TextureRegion STARS_RED_DWARF = INITIAL_MAP_SOLAR.findRegion("prot-star-reddwarf");
	public static final TextureRegion STARS_RED_GIANT = INITIAL_MAP_SOLAR.findRegion("prot-star-reddwarf");
	public static final TextureRegion STARS_BLUE_DWARF = INITIAL_MAP_SOLAR.findRegion("prot-star-neutron");
	public static final TextureRegion STARS_BLUE_GIANT = INITIAL_MAP_SOLAR.findRegion("prot-star-neutron");

	// SIZES

	/** Star sizes */
	public static final float SIZE_CELESTIAL_BROWN_DWARF = 25;
	public static final float SIZE_CELESTIAL_RED_DWARF = 70;
	public static final float SIZE_CELESTIAL_RED_GIANT = 120;
	public static final float SIZE_CELESTIAL_BLUE_DWARF = 20;
	public static final float SIZE_CELESTIAL_BLUE_GIANT = 140;
	public static final float SIZE_CELESTIAL_BLACK_DWARF = 10;
	public static final float SIZE_CELESTIAL_BLACK_MODERATE = 40;
	public static final float SIZE_CELESTIAL_BLACK_GIANT = 120;

	/** Fleet sizes */
	public static final float SIZE_FLEET_TINY = 30;
	public static final float SIZE_FLEET_SMALL = 40;
	public static final float SIZE_FLEET_MEDIUM = 50;
	public static final float SIZE_FLEET_LARGE = 60;
	public static final float SIZE_FLEET_GIANT = 70;

	/** Planet sizes */
	public static final float SIZE_CELESTIAL_PLANET_CLASS_A = 0; // Asteroids & Rocks
	public static final float SIZE_CELESTIAL_PLANET_CLASS_B = 0; // Vulcanic Planet
	public static final float SIZE_CELESTIAL_PLANET_CLASS_C = 0; // Desert Planet
	public static final float SIZE_CELESTIAL_PLANET_CLASS_D = 0; // Earth Planet
	public static final float SIZE_CELESTIAL_PLANET_CLASS_E = 0; // Ice Planet
	public static final float SIZE_CELESTIAL_PLANET_CLASS_F = 0; // Gas Planets

}