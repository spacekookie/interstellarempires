package io.lonelyrobot.empires.framework.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.lonelyrobot.empires.framework.objects.Fleet;
import io.lonelyrobot.empires.framework.objects.Ship;
import io.lonelyrobot.empires.framework.objects.BaseObject.Type;
import io.lonelyrobot.empires.framework.players.Player;

public class FleetTest {

  private Fleet single, multi;
  Ship fighter_one = new Ship(Type.SHIPS_FIGHTER_I, new Player("Bob"));
  Ship fighter_two = new Ship(Type.SHIPS_FIGHTER_I, new Player("Bob"));
  Ship fighter_three = new Ship(Type.SHIPS_FIGHTER_I, new Player("Bob"));
  Ship fighter_four = new Ship(Type.SHIPS_FIGHTER_I, new Player("Bob"));

  Ship fighter_five = new Ship(Type.SHIPS_FIGHTER_II, new Player("Jane"));
  Ship fighter_six = new Ship(Type.SHIPS_FIGHTER_II, new Player("Jane"));
  Ship fighter_seven = new Ship(Type.SHIPS_FIGHTER_II, new Player("Jane"));
  Ship fighter_eight = new Ship(Type.SHIPS_FIGHTER_II, new Player("Jane"));

  @Before
  public void setUp() throws Exception {

	single = new Fleet(fighter_one);
	single.setClaim(new Player("Bob"));

	multi = new Fleet(new Ship[] { fighter_five, fighter_six, fighter_seven, fighter_eight });
	multi.setClaim(new Player("Jane"));
  }

  @Test
  public void testInsertShip() {
	single.addUnit(fighter_two);
	Set<Ship> expected = new HashSet<Ship>();
	expected.add(fighter_one);
	expected.add(fighter_two);

	Assert.assertEquals(expected, single);
	single.removeUnit(fighter_two);
  }

  @Test
  public void testGetShips() {
	Set<Ship> temp = new HashSet<Ship>();
	temp.add(fighter_five);
	temp.add(fighter_six);
	temp.add(fighter_seven);
	temp.add(fighter_eight);

	Assert.assertEquals(fighter_one, single.getUnits());
	Assert.assertEquals(temp, multi.getUnits());
  }
}
