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

package de.r2soft.empires.client.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

public class WebServiceUtil {

  private static Logger log = Logger.getLogger(WebServiceUtil.class);

  /**
   * Load a properties file from a filename.
   * 
   * @param name
   * @return The properties from the filename or empty properties if the file could not be read.
   */
  public static Properties loadProperties(String name) {
	Properties properties = new Properties();

	try {
	  InputStream stream = WebServiceUtil.class.getClassLoader().getResourceAsStream(name);
	  properties.load(stream);
	}
	catch (Exception e) {
	  log.error("Config file " + name + " could not be read.", e);
	}
	return properties;
  }

  /**
   * Return a new URL from a list of strings
   * 
   * @param strings
   * @return A valid URL or null
   */
  public static URL buildURL(List<String> strings) {
	StringBuilder sb = new StringBuilder();
	for (String s : strings) {
	  sb.append(s);
	}

	try {
	  return new URL(sb.toString());
	}
	catch (MalformedURLException e) {
	  log.error(e.getMessage(), e);
	  return null;
	}
  }
}
