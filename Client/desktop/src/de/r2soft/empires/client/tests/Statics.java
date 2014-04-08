package de.r2soft.empires.client.tests;

import java.util.HashSet;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import de.r2soft.empires.framework.map.SolarSystem;
import de.r2soft.empires.framework.objects.BaseObject.Type;
import de.r2soft.empires.framework.objects.Fleet;
import de.r2soft.empires.framework.objects.Planet;
import de.r2soft.empires.framework.objects.Ship;
import de.r2soft.empires.framework.objects.Star;
import de.r2soft.empires.framework.objects.Star.StarType;
import de.r2soft.empires.framework.players.Player;

public class Statics {
  public SolarSystem system;

  public Statics() {

	this.setupSystem();

  }

  private void setupSystem() {

	Star star = new Star(StarType.REDGIANT);
	system = new SolarSystem(star);

	Player player = new Player("Ashley");

	Planet planetA = new Planet(Type.EARTH, player, null);
	planetA.setContainer(system);
	Planet planetB = new Planet(Type.GAS_GIANT, player, null);
	planetB.setContainer(system);

	HashSet<Planet> planets = new HashSet<Planet>();
	planets.add(planetA);
	planets.add(planetB);

	Ship shipA = new Ship(Type.FIGHTER_I, player);
	Ship shipB = new Ship(Type.FIGHTER_I, player);
	Ship shipC = new Ship(Type.FIGHTER_I, player);

	Fleet fleet = new Fleet(new Ship[] { shipA, shipB, shipC });
	fleet.setPosition(new Vector2D(200, 200));

	system.setStar(star);
	system.setPlanets(planets);
	system.addSingleFleet(fleet);

  }
}
