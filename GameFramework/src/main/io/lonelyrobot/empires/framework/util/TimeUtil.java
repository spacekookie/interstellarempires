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

package io.lonelyrobot.empires.framework.util;

import java.util.Calendar;
import java.util.Date;

/**
 * What is this for?
 * 
 * @author Katharina <kookie@spacekookie.de>
 * 
 */
public class TimeUtil {

  /**
   * Get the current time as a Date object.
   * 
   * @return
   */
  public static Date getTimeNow() {
	return Calendar.getInstance().getTime();
  }

  /**
   * Add the passed values to the current Date.
   * 
   * @param years
   * @param days
   * @param hours
   * @param seconds
   * @return
   */
  public static Date getTimeThen(int years, int days, int hours, int seconds) {
	Calendar c = Calendar.getInstance();
	c.add(Calendar.YEAR, years);
	c.add(Calendar.DAY_OF_YEAR, days);
	c.add(Calendar.MINUTE, hours);
	c.add(Calendar.SECOND, seconds);
	return c.getTime();
  }

}
