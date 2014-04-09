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

package de.r2soft.empires.framework.test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import junit.framework.Assert;
import net.sf.javaml.core.kdtree.KDTree;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import de.r2soft.empires.framework.map.ObjectTree;
import de.r2soft.empires.framework.objects.BaseObject;

public class ObjectTreeTest {
  
  private ObjectTree<Object> tree;

  @Before
  public void setUp() throws Exception {
	tree = new ObjectTree<Object>();
  }

  @Test
  public void testInsertAndSearch() {
	Object obj = new Object();
	tree.insert(new Vector2D(10, 10), obj);
	Object objT = tree.search(new Vector2D(10, 10));
	Assert.assertEquals(obj, objT);
  }

  @Test
  public void testDelete() {
	tree.insert(new Vector2D(20, 20), new Object());
	tree.delete(new Vector2D(20, 20));
	Object obj = tree.search(new Vector2D(20, 20));
	Assert.assertNull(obj);
  }

  @Test
  public void testNearestOne() {
	Object obj = new Object();
	tree.insert(new Vector2D(30, 30), obj);
	Object objT = tree.nearest(new Vector2D(31, 31));
	Assert.assertEquals(obj, objT);
  }

  @Test
  public void testNearestN() {
	Object obj1 = new Object();
	Object obj2 = new Object();
	tree.insert(new Vector2D(40, 40), obj1);
	tree.insert(new Vector2D(41, 41), obj2);
	Object[] objT = tree.nearest(new Vector2D(41, 41),2);
	Assert.assertEquals(2, objT.length);
	Assert.assertEquals(obj1, objT[1]);
	Assert.assertEquals(obj2, objT[0]);
  }

  @Test
  public void testRange() {
	Object obj = new Object();
	tree.insert(new Vector2D(100, 100), obj);
	Object[] objT = tree.range(new Vector2D(90,90) ,  new Vector2D(110,110));
	Assert.assertEquals(1, objT.length);
	Assert.assertEquals(obj, objT[0]);
  }


  @Test
  public void testMove() {
	Object obj = new Object();
	tree.insert(new Vector2D(50, 50), obj);
	tree.move(new Vector2D(50,50), new Vector2D(60,60));
	Object objT = tree.search(new Vector2D(60,60));
	Assert.assertEquals(obj, objT);
  }

}
