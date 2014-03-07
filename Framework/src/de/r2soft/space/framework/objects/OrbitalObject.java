package de.r2soft.space.framework.objects;

import org.apache.log4j.Priority;

import de.r2soft.space.framework.planetary.Orbit;

/**
 * Orbital Object. All orbital objects can be claimed and belong to a player. Can't be moved but orbits a parent {@link #BaseObject} with a
 * velocity, radius. Orbital direction is the same for the entire solar system.
 * 
 * @author AreusAstarte
 * 
 */
public abstract class OrbitalObject extends PlayerObject {

  private float orbitalVelocity;
  private float orbitalRadius;
  private BaseObject orbitalParent;
  protected Orbit orbit;

  /* Determines whether the object can change orbit (or orbital period). Applies for Stations, planets and moons */
  private boolean semiStaticObject;

  public float getOrbitalV() {
	return orbitalVelocity;
  }

  public void setOrbitalV(float orbitalV) {
	this.orbitalVelocity = orbitalV;
  }

  public float getOrbitalR() {
	return orbitalRadius;
  }

  public void setOrbitalR(float orbitalRadius) {
	this.orbitalRadius = orbitalRadius;
  }

  public BaseObject getParent() {
	return orbitalParent;
  }

  /**
   * Sets a new parent for a movable object. Shouldn't be called on a semiStaticObject.
   */
  public void setParent(BaseObject orbitalParent) {
	if (!semiStaticObject)
	  this.orbitalParent = orbitalParent;
	else
	  logger.info("Failed! SemiStaticObjects can't change orbital parent!");
  }
}
