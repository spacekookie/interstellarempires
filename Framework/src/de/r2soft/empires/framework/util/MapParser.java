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

package de.r2soft.empires.framework.util;

import java.util.HashSet;
import java.util.Iterator;

import javax.swing.text.Element;

import de.r2soft.empires.framework.map.GalaxyMap;
import de.r2soft.empires.framework.map.GalaxyPosition;
import de.r2soft.empires.framework.map.SolarSystem;
import de.r2soft.empires.framework.objects.BaseObject.Category;
import de.r2soft.empires.framework.objects.Planet;
import de.r2soft.empires.framework.objects.Star;
import de.r2soft.empires.framework.objects.Star.StarType;
import de.r2soft.empires.framework.players.Player;
import de.r2soft.empires.framework.types.IntVec2;

/** Reads a map .XML file and returns the data in form of pretty HashSets */
public class MapParser {

  private GalaxyMap map;

  public MapParser() {

  }

  public void readXML(Element root) {
	HashSet<Planet> planetary = new HashSet<Planet>();
	map = new GalaxyMap();
	Element solarSystems = root.element("SolarSystems");

	for (Iterator<Element> s = solarSystems.elementIterator(); s.hasNext();) {
	  Element solarSystem = s.next();

	  SolarSystem temp = new SolarSystem();

	  Element position = solarSystem.element("Position");

	  temp.setPosition(new GalaxyPosition(Integer.parseInt(position.attribute("PosX").getText()), Integer.parseInt(position.attribute(
		  "PosY").getText())));

	  temp.setClaim(new Player(solarSystem.attribute("Owner").getText()));
	  temp.setStar(new Star(StarType.valueOf(solarSystem.attribute("Type").getText())));

	  Element planets = solarSystem.element("Planets");

	  for (Iterator<Element> p = planets.elementIterator(); p.hasNext();) {
		Element planet = p.next();
		planetary.add(new Planet(Category.PLANET, Float.parseFloat(planet.attribute("Distance").getValue()), Float.parseFloat(planet
			.attribute("Size").getValue())));

	  }
	  temp.setPlanets(planetary);

	  map.addSystem(temp);
	}

	map.setVersion(0);
	map.setSize(new IntVec2(1, 2));
  }

  public GalaxyMap getGalaxyMap() {
	return map;
  }
}
