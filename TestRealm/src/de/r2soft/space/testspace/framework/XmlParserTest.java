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

package de.r2soft.space.testspace.framework;

import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import de.r2soft.space.framework.map.SolarSystem;

public class XmlParserTest {

  /**
   * @param args
   * @throws DocumentException
   * @throws IOException
   * @throws SAXException
   * @throws ParserConfigurationException
   */
  public static void main(String[] args) throws DocumentException {
	SAXReader reader = new SAXReader();
	Document document = reader
		.read("/Users/AreusAstarte/Documents/Projekte/RandomRobots/Game Development/SpaceGame/Framework/res/MapDemo.xml");

	Element root = document.getRootElement();
	Element solarSystems = root.element("SolarSystems");

	for (Iterator<Element> s = solarSystems.elementIterator(); s.hasNext();) {
	  Element solarSystem = s.next();

	  SolarSystem temp = new SolarSystem();

	  System.out.println("Type: " + solarSystem.getName());

	  Element position = solarSystem.element("Position");

	  System.out.println("PosX: " + position.attribute("PosX").getText());
	  System.out.println("PosY: " + position.attribute("PosY").getText());

	  System.out.println(solarSystem.attribute("Name").getText());
	  System.out.println(solarSystem.attribute("Type").getText());

	  Element planets = solarSystem.element("Planets");

	  for (Iterator<Element> p = planets.elementIterator(); p.hasNext();) {
		Element planet = p.next();
		for (Attribute a : planet.attributes()) {
		  System.out.println(a.getText());
		}
	  }

	}
  }
}