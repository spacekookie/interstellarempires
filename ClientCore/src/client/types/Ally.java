package client.types;

/* 
 * Copyright (c) 2012 Katharina Fey
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
/**
 * This will be used by the client to determine how to colour code items on maps for the player. Moved to
 * @Framework.Player.Alliance
 * 
 * @author Katharina
 * 
 */
@Deprecated
public class Ally {

	private enum alliance {
		PLAYER, NEUTRAL, HOSTILE, FRIENDLY, UNKNOWN;
	}

	private alliance a;

	/**
	 * Will set the enum to @UNKNOWN which might occur in strange cases where the player has been sent data by the server that he/she
	 * wasn't supposed to get.
	 */
	public Ally() {
		a = alliance.UNKNOWN;
	}

	public void setFriendly() {
		a = alliance.FRIENDLY;
	}

	public void setHostile() {
		a = alliance.HOSTILE;
	}

	public void setNeutral() {
		a = alliance.NEUTRAL;
	}

	public void setPlayer() {
		a = alliance.PLAYER;
	}

	public alliance getAlliance() {
		return a;
	}

}
