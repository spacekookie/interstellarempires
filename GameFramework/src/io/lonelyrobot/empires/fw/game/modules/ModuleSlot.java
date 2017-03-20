/* #########################################################################
 * Copyright (c) 2017 Lonely Robot
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

package io.lonelyrobot.empires.fw.game.modules;

/**
 * 
 * @author Katharina 'spacekookie' Fey <kookie@spacekookie.de>
 */
public class ModuleSlot {
  public static enum SLOT_TYPE {
    /** Small utility modules */
    S,

    /** Medium utility and weapon modules */
    M,

    /** Armour upgrades or large weapons */
    L,

    /** Large battleship weaponry */
    XL,

    /** Capital and super-capital weaponry */
    CAPITAL
  }
}
