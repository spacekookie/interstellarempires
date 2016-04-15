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

package io.lonelyrobot.empires.framework.objects.factory;

import io.lonelyrobot.empires.framework.objects.Ship.ShipType;

@Deprecated
public class ShipValues {

  // About measurements and units.
  // Damage is measured in HP/Volley Damage
  // Speed is measured in Km/Second
  // Hitpoints is measured in...Hitpoints. (BASE UNIT)
  // Armour is measured in Meters. (0.1f = 10cm)

  /** BASIC FIGHTER */
  public static final ShipType Fighter_Type = ShipType.FIGHTER;
  public static final float Fighter_Damage = 10f;
  public static final float Fighter_Speed = 8f;
  public static final float Fighter_Hitpoints = 100f;
  public static final float Fighter_Armour = 0.2f;
  public static final int Fighter_SlotAmount = 0;

  /** SMALL CARGOSHIP */
  public static final ShipType Cargo_Small_Type = ShipType.CARGO_SMALL;
  public static final float Cargo_Small_Damage = 0f;
  public static final float Cargo_Small_Speed = 4f;
  public static final float Cargo_Small_Hitpoints = 800f;
  public static final float Cargo_Small_Armour = 1.2f;
  public static final int Cargo_Small_SlotAmount = 3;

  /** MOTHERSHIP */
  public static final ShipType MotherShip_Type = ShipType.MOTHERSHIP;
  public static final float MotherShip_Damage = 1200f;
  public static final float MotherShip_Speed = 1f;
  public static final float MotherShip_Hitpoints = 12000f;
  public static final float MotherShip_Armour = 12f;
  public static final int MotherShip_SlotAmount = 16;

}
