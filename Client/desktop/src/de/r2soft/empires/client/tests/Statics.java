package de.r2soft.empires.client.tests;

import java.util.HashSet;

import de.r2soft.empires.framework.map.SolarSystem;
import de.r2soft.empires.framework.objects.Planet;
import de.r2soft.empires.framework.objects.BaseObject.Category;
import de.r2soft.empires.framework.objects.Star.StarType;
import de.r2soft.empires.framework.objects.Star;

public class Statics {
  public SolarSystem system;

  public Statics() {

	this.setupSystem();

  }

  private void setupSystem() {
	Star star = new Star(StarType.REDGIANT);

	Planet planetA = new Planet(Category.PLANET, 2, star);
	Planet planetB = new Planet(Category.PLANET, 2.6, star);

	HashSet<Planet> planets = new HashSet<Planet>();
	planets.add(planetA);
	planets.add(planetB);

  }
}
