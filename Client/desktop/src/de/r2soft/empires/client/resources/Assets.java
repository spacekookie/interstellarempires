/* #########################################################################
 * Copyright (c) 2014 Random Robot Softworks
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
package de.r2soft.empires.client.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Global resource loader for the game client. Will distribute all texture files.
 * 
 * @author Katharina
 * 
 */
public class Assets {

  /*
   * ############### HEX TILES TEXTURES ###############
   */

  private static final TextureAtlas R2_ATLAS_TILES = new TextureAtlas(
	  Gdx.files.internal("r2_graphics/maps/galaxy/_atli/galaxy_tiles.atlas"));
  /** Blue (Friendly/ Ally) map tile for the Galaxy Map */
  public static final TextureRegion R2_TILES_BLUE = R2_ATLAS_TILES.findRegion("hextile_blue");
  /** Green (Player) map tile for the Galaxy Map */
  public static final TextureRegion R2_TILES_GREEN = R2_ATLAS_TILES.findRegion("hextile_green");
  /** Red (Hostile) map tile for the Galaxy Map */
  public static final TextureRegion R2_TILES_RED = R2_ATLAS_TILES.findRegion("hextile_red");
  /** White (Neutral) map tile for the Galaxy Map */
  public static final TextureRegion R2_TILES_WHITE = R2_ATLAS_TILES.findRegion("hextile_white");
  /** Purple (Swarm-Infected) map tile for the Galaxy Map */
  public static final TextureRegion R2_TILES_PURPLE = R2_ATLAS_TILES.findRegion("hextile_purple");

  /*
   * ############### USER INTERFACE IMPORTS ###############
   */

  /** Use {@link #R2_UI_SKIN} instead */
  @Deprecated
  public static final Skin UI_SKIN = new Skin(Gdx.files.internal("gui/skins/evolved/uiskin.json"));

  /** New default UI Skin for the entire application. */
  public static final Skin R2_UI_SKIN = new Skin(Gdx.files.internal("r2_graphics/ui/skin/uiskin.json"));
  private static final TextureAtlas V2_BACKGROUNDS = new TextureAtlas(Gdx.files.internal("gui/graphics/back.atlas"));

  /** A stolen background for the game. WILL NEED TO BE REPLACED! */
  public static final TextureRegion V2_BACKGROUND = V2_BACKGROUNDS.findRegion("spacebackgroundtemp");

  /*
   * ############### SOLAR SYSTEM ICONS ###############
   */
  private static final TextureAtlas R2_ATLAS_SOLAR = new TextureAtlas(Gdx.files.internal("r2_graphics/maps/solar/_atli/solar_icons.atlas"));
  /** Red Dwarf Star */
  public static final TextureRegion R2_SOLAR_STAR_REDDWARF = R2_ATLAS_SOLAR.findRegion("star_red_dwarf");
  /** Red Giant Star */
  public static final TextureRegion R2_SOLAR_STAR_REDGIANT = R2_ATLAS_SOLAR.findRegion("star_red_giant");
  /** Neutron-Star = Blue/ White Dwarf */
  public static final TextureRegion R2_SOLAR_STAR_BLUEDWARF = R2_ATLAS_SOLAR.findRegion("star_blue_dwarf");
  /** Blue super-giant Star (Class O) */
  public static final TextureRegion R2_SOLAR_STAR_BLUEGIANT = R2_ATLAS_SOLAR.findRegion("star_blue_giant");
  /** Brown Dwarf Mini-Star */
  public static final TextureRegion R2_SOLAR_STAR_BROWNDWARF = R2_ATLAS_SOLAR.findRegion("star_brown_dwarf");
  /** Black Hole Accretion Disk */
  public static final TextureRegion R2_SOLAR_STAR_BLACKHOLE = R2_ATLAS_SOLAR.findRegion("star_black_hole");

  /* PLANETS */
  /** Earth Like Planet (Classes undecided) */
  public static final TextureRegion R2_SOLAR_PLANET_EARTHY = R2_ATLAS_SOLAR.findRegion("planet_earthy");
  /** Gas Giant (Jupiter Sized) */
  public static final TextureRegion R2_SOLAR_PLANET_GASSY = R2_ATLAS_SOLAR.findRegion("planet_gassy");
  /** Solid Ice plane */
  public static final TextureRegion R2_SOLAR_PLANET_ICY = R2_ATLAS_SOLAR.findRegion("planet_icy");
  /** Volcanic planet (solid, toxic atmosphere) */
  public static final TextureRegion R2_SOLAR_PLANET_FLAMY = R2_ATLAS_SOLAR.findRegion("planet_flamy");
  /** Standard rocky boring old mun */
  public static final TextureRegion R2_SOLAR_MOON_ROCKY = R2_ATLAS_SOLAR.findRegion("moon_rocky");

  /* SHIPS */
  /** Single Ship Self (Green) */
  public static final TextureRegion R2_SOLAR_SHIP_SELF = R2_ATLAS_SOLAR.findRegion("ship_self");
  /** Single Ship Ally (Blue) */
  public static final TextureRegion R2_SOLAR_SHIP_ALLY = R2_ATLAS_SOLAR.findRegion("ship_ally");
  /** Single Ship Hostile (Red) */
  public static final TextureRegion R2_SOLAR_SHIP_HOSTILE = R2_ATLAS_SOLAR.findRegion("ship_hostile");
  /** Single Ship Neutral (White/ Grey) */
  public static final TextureRegion R2_SOLAR_SHIP_NEUTRAL = R2_ATLAS_SOLAR.findRegion("ship_neutral");
  /** Single Ship Infected (Purple) */
  public static final TextureRegion R2_SOLAR_SHIP_INFECTED = R2_ATLAS_SOLAR.findRegion("ship_infected");

  /* FLEETS */
  /** Fleet Icon Self (Green) */
  public static final TextureRegion R2_SOLAR_FLEET_SELF = R2_ATLAS_SOLAR.findRegion("fleet_self");
  /** Fleet Icon Ally (Blue) */
  public static final TextureRegion R2_SOLAR_FLEET_ALLY = R2_ATLAS_SOLAR.findRegion("fleet_ally");
  /** Fleet Icon Hostile (Red) */
  public static final TextureRegion R2_SOLAR_FLEET_HOSTILE = R2_ATLAS_SOLAR.findRegion("fleet_hostile");
  /** Fleet Icon Neutral (White/ Grey) */
  public static final TextureRegion R2_SOLAR_FLEET_NEUTRAL = R2_ATLAS_SOLAR.findRegion("fleet_neutral");
  /** Fleet Icon Infected (Purple) */
  public static final TextureRegion R2_SOLAR_FLEET_INFECTED = R2_ATLAS_SOLAR.findRegion("fleet_infected");

  /* STATIONS */
  /** Station Icon Self (Green) */
  public static final TextureRegion R2_SOLAR_STATION_SELF = R2_ATLAS_SOLAR.findRegion("station_self");
  /** Station Icon Ally (Blue) */
  public static final TextureRegion R2_SOLAR_STATION_ALLY = R2_ATLAS_SOLAR.findRegion("station_ally");
  /** Station Icon Hostile (Red) */
  public static final TextureRegion R2_SOLAR_STATION_HOSTILE = R2_ATLAS_SOLAR.findRegion("station_hostile");
  /** Station Icon Neutral (White/ Grey) */
  public static final TextureRegion R2_SOLAR_STATION_NEUTRAL = R2_ATLAS_SOLAR.findRegion("station_neutral");
  /** Station Icon Infected (Purple) */
  public static final TextureRegion R2_SOLAR_STATION_INFECTED = R2_ATLAS_SOLAR.findRegion("station_infected");

  /*
   * ############################################################################################
   * ############################################################################################
   * 
   * ################################### OLD AN DIRTY IMPORTS ###################################
   * 
   * ############################################################################################
   * ############################################################################################
   */

  /** Atlas imports */
  private static final TextureAtlas INITIAL_MAP_HEX = new TextureAtlas(Gdx.files.internal("map/prot-map-tiles.pack"));
  private static final TextureAtlas INITIAL_MAP_SOLAR = new TextureAtlas(Gdx.files.internal("solar/prot-solarsystem-icons.pack"));
  private static final TextureAtlas INITIAL_UI_SELECTION = new TextureAtlas(Gdx.files.internal("gui/prot-selected.atlas"));
  private static final TextureAtlas INITIAL_MAP_ADDITIONS = new TextureAtlas(Gdx.files.internal("map/adds/prot-tile-additions.atlas"));
  private static final TextureAtlas INITIAL_BASIC_UNITS = new TextureAtlas(Gdx.files.internal("ships/basic-ships.pack"));

  /** Selection box for units and planets */
  public static final TextureRegion GUI_FRAME_SELECTION = INITIAL_UI_SELECTION.findRegion("selected");

  /** Tile regions */

  /** Hostile occupied tile */
  public static final TextureRegion TILE_HEX_ENEMY = INITIAL_MAP_HEX.findRegion("prot-map-tile-hostile");
  /** Allied tile */
  public static final TextureRegion TILE_HEX_FRIEND = INITIAL_MAP_HEX.findRegion("prot-map-tile-friend");
  /** Neutral tile */
  public static final TextureRegion TILE_HEX_NEUTRAL = INITIAL_MAP_HEX.findRegion("prot-map-tile-neutral");
  /** Player owned tile */
  public static final TextureRegion TILE_HEX_PLAYER = INITIAL_MAP_HEX.findRegion("prot-map-tile-player");

  /** System contains player fleet */
  public static final TextureRegion TILE_ADD_FLEET_PLAYER = INITIAL_MAP_ADDITIONS.findRegion("prot-tile-fleet-player");
  /** System contains friendly fleet */
  public static final TextureRegion TILE_ADD_FLEET_FRIENDLY = INITIAL_MAP_ADDITIONS.findRegion("prot-tile-fleet-allied");
  /** System contains hostile fleet */
  public static final TextureRegion TILE_ADD_FLEET_ENEMY = INITIAL_MAP_ADDITIONS.findRegion("prot-tile-fleet-hostile");
  /** System contains player station */
  public static final TextureRegion TILE_ADD_STATION_PLAYER = INITIAL_MAP_ADDITIONS.findRegion("prot-tile-station");
  /** The tile addition size used for position and size */
  public static final float SIZE_TILE_ADD_SIZE = 20;

  /** Fleet regions */

  /** Player owned fighter fleet */
  public static final TextureRegion FLEET_FIGHTER_PLAYER = INITIAL_MAP_SOLAR.findRegion("prot-fleet-fighter-player");
  /** Hostile fighter fleet */
  public static final TextureRegion FLEET_FIGHTER_ENEMY = INITIAL_MAP_SOLAR.findRegion("prot-fleet-fighter-hostile");
  /** Allied fighter fleet */
  public static final TextureRegion FLEET_FIGHTER_FRIEND = INITIAL_MAP_SOLAR.findRegion("prot-fleet-fighter-ally");

  /** Single unit regions */

  /** Single basic fighter unit. No colour coding for alliances */
  public static final TextureRegion UNITS_FIGHTER_BASIC = INITIAL_BASIC_UNITS.findRegion("small_fighter");
  /** Single small cargo freighter unit. No colour coding for alliances */
  public static final TextureRegion UNITS_CARGO_SMALL = INITIAL_BASIC_UNITS.findRegion("cargo_freighter_small");

  /** Star regions */

  /**
   * ### Star classification definitions: ###
   * 
   * Class O: 40'000K Class B: 20'000K Class A: 10'000K Class F: 7'500K Class G: 5'500K Class K: 4'500K Class M: 3'000K
   * 
   * Size magnitudes: 15: Neutron Stars 13: Brown Dwarfs 10: Red Dwarfs 8: Yellow Dwarfs 5: Pretty green stars 0: Red giants/ (Main
   * sequence: teal giants) -5: Blue Giants, Red Super-giants -10: Super big ass stars (Red and blue)
   */

  /** Brown dwarf star */
  public static final TextureRegion STARS_BROWN_DWARF = INITIAL_MAP_SOLAR.findRegion("prot-star-browndwarf");
  /** Red dwarf star */
  public static final TextureRegion STARS_RED_DWARF = INITIAL_MAP_SOLAR.findRegion("prot-star-reddwarf");
  /** Red giant star */
  public static final TextureRegion STARS_RED_GIANT = INITIAL_MAP_SOLAR.findRegion("prot-star-reddwarf");
  /** Neutron star */
  public static final TextureRegion STARS_BLUE_DWARF = INITIAL_MAP_SOLAR.findRegion("prot-star-neutron");
  /** Blue super-giant */
  public static final TextureRegion STARS_BLUE_GIANT = INITIAL_MAP_SOLAR.findRegion("prot-star-neutron");
}