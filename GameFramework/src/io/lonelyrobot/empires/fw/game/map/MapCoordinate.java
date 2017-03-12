package io.lonelyrobot.empires.fw.game.map;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import lombok.Getter;

/**
 * This is a utility class which maps a tile against the broader aspect of the map around
 * it. Most notably it has a function that can get the accurate distance between any two
 * map coordinates, according to the hex tile map around it.
 * 
 * It can also take a map and thus soveregnity or other natural phenomenon into account.
 * 
 * @author Katharina 'spacekookie' Fey <kookie@spacekookie.de>
 *
 */
public class MapCoordinate {
  private @Getter Vector2D position;
}
