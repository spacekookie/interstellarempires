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

package io.lonelyrobot.empires.fw.game.players;

import java.util.Set;

import lombok.Getter;

/**
 * This class represents a player in game with all of their standings and relationships.
 * Additional information can be provided to draw an avatar or determine the players
 * gender.
 * 
 * @author Katharina 'spacekookie' Fey <kookie@spacekookie.de>
 */
public class Player extends Sociable {

  /** Crucial for gameplay */
  private @Getter String username;
  private @Getter Alliance alliance;

  /** Optional for populating the player profile */
  private @Getter String first, last;
  private @Getter String gender;
  private @Getter Byte[] avatar;

  /** Keep a private contact book in here too */
  private Set<Sociable> contacts;
}
