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

package de.r2soft.space.framework.ai;

public class Admiral extends BasicAI {

	/**
	 * Defines how the Admiral AI will handle stuff. Aggressive will take chances and lose more ships
	 * in the process, passive is more defensive and ecological. Balanced will try to mix aggressive
	 * and passive behavior to get the best results, can however be horribly wrong. Cheating AIs will
	 * get a 100% shield and attack speed bonus on all their ships. Will only be accessible if user
	 * 
	 * @isAdmin == true.
	 * */
	public static enum CommandType {
		AGGRESSVE, PASSIVE, BALANCED, CHEATING;
	}

	private CommandType type;
	private String name;

	public Admiral(String name, CommandType type) {
		this.name = name;
		this.type = type;
	}
}
