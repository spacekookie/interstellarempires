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
package de.r2soft.empires.framework.objects.factory;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import de.r2soft.empires.framework.objects.BaseObject.Category;
import de.r2soft.empires.framework.objects.BaseObject.Type;
import de.r2soft.empires.framework.objects.Fleet;
import de.r2soft.empires.framework.objects.OrbitalStructure;
import de.r2soft.empires.framework.objects.Ship;
import de.r2soft.empires.framework.objects.Ship.ShipType;
import de.r2soft.empires.framework.objects.modules.ModuleSlot;
import de.r2soft.empires.framework.players.Player;
import de.r2soft.empires.framework.players.Sociable;

/**
 * This class provides static methods to build default units.
 * 
 * @author Leander
 * 
 */
public class UnitFactory {

  private Sociable claim;
  private OrbitalStructure parent;
  private Logger logger = Logger.getLogger(getClass().getSimpleName());

  /** Constructor to be called from unit producing structures and planets. */
  public UnitFactory(Sociable owner, OrbitalStructure parent) {
	this.claim = claim;
	this.parent = parent;
  }

  /**
   * Requsition a single unit by the Unit object
   * 
   * @param type
   * @return
   * 
   * @author Katharina
   */
  @Deprecated
  public Ship requisitionUnit(ShipType type, Player Claim) {
	if (type == ShipType.FIGHTER) {
	  float damage = ShipValues.Fighter_Damage;
	  float speed = ShipValues.Fighter_Speed;

	  return new Ship(); // damage, speed, ...
	}

	Ship ship = new Ship(Category.SHIP, type, null, null, parent.getPosition());
	return ship;
  }

  public Ship requisitionUnit(Type type) {
	logger.info("Yea...this doesn't do a whole lot yet. Come back later. Or consult with your System Administrator. Wink Wink!");
	return null;
  }

  @Deprecated
  public Ship requisitionUnit(ShipType type) {
	if (type == ShipType.FIGHTER) {
	  float damage = ShipValues.Fighter_Damage;
	  float speed = ShipValues.Fighter_Speed;
	  float hp = ShipValues.Fighter_Hitpoints;
	  float armour = ShipValues.Fighter_Armour;
	  return new Ship();
	}
	else if (type == ShipType.CARGO_SMALL) {
	  float damage = ShipValues.Cargo_Small_Damage;
	  float speed = ShipValues.Cargo_Small_Speed;
	  float hp = ShipValues.Cargo_Small_Hitpoints;
	  float armour = ShipValues.Cargo_Small_Armour;
	  Set<ModuleSlot> slots = new HashSet<ModuleSlot>();

	  // Adds empty slots
	  for (int a = 0; a < ShipValues.Cargo_Small_SlotAmount; a++) {
		slots.add(new ModuleSlot());
	  }
	  return new Ship();
	}
	else if (type == ShipType.MOTHERSHIP) {
	  float damage = ShipValues.MotherShip_Damage;
	  float speed = ShipValues.MotherShip_Speed;
	  float hp = ShipValues.MotherShip_Hitpoints;
	  float armour = ShipValues.MotherShip_Armour;
	  Set<ModuleSlot> slots = new HashSet<ModuleSlot>();

	  // Adds empty slots
	  for (int a = 0; a < ShipValues.MotherShip_SlotAmount; a++) {
		slots.add(new ModuleSlot());
	  }
	  return new Ship();
	}
	else
	  return null;
  }

  /**
   * Requisitions a fleet of units from a HashSet of shiptypes.
   * 
   * @param types
   *          of ships wanted. Multiple entries mean multiple instances of that requested unit.
   * @return Fleet object with requested ships. Base constructor without admiral.
   * 
   * @author Katharina
   */
  @Deprecated
  public Fleet requisitionFleet(Set<ShipType> types) {

	Set<Ship> requested = new HashSet<Ship>();

	for (ShipType type : types) {
	  requested.add(new Ship(Category.SHIP, type, null, null, parent.getPosition()));
	}

	Fleet fleet = new Fleet(requested);
	return fleet;
  }

  /**
   * Build a new default unit.
   * 
   * @param type
   * @return
   */
  @Deprecated
  public static Ship buildUnit(ShipType type) {
	if (type == ShipType.FIGHTER) {
	  Ship fighter = new Ship();
	  return fighter;
	}
	else {
	  // Log the error
	  return null;
	}
  }

}
