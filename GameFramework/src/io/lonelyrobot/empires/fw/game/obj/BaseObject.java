package io.lonelyrobot.empires.fw.game.obj;

import java.util.Set;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import io.lonelyrobot.empires.fw.game.map.MapCoordinate;
import io.lonelyrobot.empires.fw.game.modules.ModuleSlot;

/**
 * This is a base object in the game that is the parent of pretty much every parent
 * object. This does **not** apply to Modules and submodules of a ship. In fact this
 * object OWNS modules which does not allow for infinite nesting.
 * 
 * Any additional functionality beyond obvious types is handled via the Traits system.
 * 
 * @author Katharina 'spacekookie' Fey <kookie@spacekookie.de>
 */
public abstract class BaseObject {

  /** Combat variables that are important for all objects */
  protected double health;
  protected double armour;
  protected double shields;

  /** How long can a ship deal with radiation/ boarding */
  protected int crew;
  protected double radResistence;

  /** Movement & positioning related */
  protected MapCoordinate galaxyPos;
  protected Vector2D solPos;
  protected double mass;

  /** Other miscelanious fields */
  protected Set<ModuleSlot> slots;
  protected String name;
}
