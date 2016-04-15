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
package io.lonelyrobot.empires.framework.xmlio;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author Leander
 * 
 */
public class MapReader {

  private final Logger log = Logger.getLogger(MapReader.class.getName());

  private void readMap(String xmlFile) {
	Document doc = parseXML(xmlFile);

	NodeList solarSystems = doc.getElementsByTagName("SolarSystem");

	for (int i = 0; i < solarSystems.getLength(); i++) {
	  Node solarSystem = solarSystems.item(i);

	  NodeList systemChildNodes = solarSystem.getChildNodes();
	}

  }

  /**
   * Parse an XML file and return the document.
   * 
   * @param xmlFile
   * @return
   */
  private Document parseXML(String xmlFile) {

	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	try {
	  DocumentBuilder db = dbf.newDocumentBuilder();
	  return db.parse(xmlFile);
	}
	catch (SAXException e) {
	  log.error(e.getMessage(), e);
	  return null;
	}
	catch (IOException e) {
	  log.error(e.getMessage(), e);
	  return null;
	}
	catch (ParserConfigurationException e) {
	  log.error(e.getMessage(), e);
	  return null;
	}
  }

}
