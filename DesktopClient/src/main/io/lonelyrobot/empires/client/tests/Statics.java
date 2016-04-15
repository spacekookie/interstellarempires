package io.lonelyrobot.empires.client.tests;

import java.util.HashSet;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import io.lonelyrobot.empires.framework.map.GalaxyPosition;
import io.lonelyrobot.empires.framework.map.SolarSystem;
import io.lonelyrobot.empires.framework.objects.Fleet;
import io.lonelyrobot.empires.framework.objects.Moon;
import io.lonelyrobot.empires.framework.objects.Planet;
import io.lonelyrobot.empires.framework.objects.Ship;
import io.lonelyrobot.empires.framework.objects.Star;
import io.lonelyrobot.empires.framework.objects.BaseObject.Type;
import io.lonelyrobot.empires.framework.planetary.Orbit;
import io.lonelyrobot.empires.framework.planetary.Orbit.ORBITALTYPE;
import io.lonelyrobot.empires.framework.players.Player;

public class Statics {
  public SolarSystem system;

  public Statics() {

	this.setupSystem();

  }

  private void setupSystem() {

	Player player = new Player("Ashley");
	GalaxyPosition position = new GalaxyPosition(42, 42);
	Star star = new Star(Type.STAR_RED_DWARF);
	star.setAlias("Burning Heart");
	system = new SolarSystem(star);
	system.setPosition(position);
	system.setClaim(player);

	Planet planetA = new Planet(Type.PLANET_EARTHY, player, new Orbit(ORBITALTYPE.CIRCULAR, 200f));
	planetA.setContainer(system);
	planetA.setPosition(new Vector2D(200, 0));

	Planet planetB = new Planet(Type.PLANET_GASSY, player, new Orbit(ORBITALTYPE.CIRCULAR, 500f));
	planetB.setPosition(new Vector2D(500, 0));
	planetB.setContainer(system);

	HashSet<Planet> planets = new HashSet<Planet>();
	planets.add(planetA);
	planets.add(planetB);

	Moon moonA = new Moon(new Orbit(ORBITALTYPE.CIRCULAR, 50f, planetB));
	moonA.setType(Type.MOON_ROCKY);
	moonA.setPosition(new Vector2D(550, 0));

	planetB.addMoon(moonA);
	HashSet<Moon> moons = new HashSet<Moon>();
	moons.add(moonA);
	system.setMoons(moons);

	Ship shipA = new Ship(Type.SHIPS_FIGHTER_I, player);
	Ship shipB = new Ship(Type.SHIPS_FIGHTER_I, player);
	Ship shipC = new Ship(Type.SHIPS_FIGHTER_I, player);

	Fleet fleet = new Fleet(new Ship[] { shipA, shipB, shipC });
	fleet.setPosition(new Vector2D(200, 200));
	fleet.setOrbit(new Orbit(ORBITALTYPE.CIRCULAR, 282.84f));

	system.setStar(star);
	system.setPlanets(planets);
	system.addSingleFleet(fleet);

  }

  public SolarSystem getSystem() {
	return system;
  }
}
