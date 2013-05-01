/* 
 * Copyright (c) 2012 Leander Sabel
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

package framework.map;

import java.util.Set;

import framework.objects.Planet;
import framework.objects.Star;
import framework.objects.Structure;
import framework.objects.Unit;
import framework.players.Player;


public class SolarSystem {

	private Player claimed;
	private Set<Planet> planets;
	private Set<Unit> units;
	private Set<Structure> structures;
	private Star star;
	private float radius;

}
