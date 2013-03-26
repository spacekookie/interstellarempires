/* 
 * Copyright (c) 2013 Leander Sabel
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

package test;


import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import datastructures.Interval2D;
import datastructures.QuadTree;

public class TestQuadTree {

  @Test
  public void testQuadTreeStatic() {
	QuadTree<Integer, Integer> tree = new QuadTree<Integer, Integer>();

	tree.insert(1, 1, 25);
	Set<Integer> result1 = tree.query2D(new Interval2D<Integer>(0, 0, 2, 2));
	Assert.assertEquals(true, result1.contains(25));
	Assert.assertEquals(1, result1.size());
	
	Set<Integer> result2 = tree.query2D(new Interval2D<Integer>(2, 2, 4, 4));
	Assert.assertEquals(0, result2.size());
	
	tree.insert(5, 5, 50);
	Set<Integer> result3 = tree.query2D(new Interval2D<Integer>(4, 4, 6, 6));
	Assert.assertEquals(true, result3.contains(50));
	Assert.assertEquals(1, result3.size());
	
	Set<Integer> result4 = tree.query2D(new Interval2D<Integer>(0, 0, 11, 11));
	Assert.assertEquals(true, result4.contains(25));
	Assert.assertEquals(true, result4.contains(50));
  }
  
}
