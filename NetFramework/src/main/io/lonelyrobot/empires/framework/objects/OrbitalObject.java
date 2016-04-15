package io.lonelyrobot.empires.framework.objects;

import io.lonelyrobot.empires.framework.planetary.Orbit;

/**
 * Orbital Object. All orbital objects can be claimed and belong to a player. Can't be moved but orbits a parent {@link #BaseObject} with a
 * velocity, radius. Orbital direction is the same for the entire solar system.
 * 
 * @author ***REMOVED***
 * 
 */
public abstract class OrbitalObject extends PlayerObject {

  @Deprecated
  /** Use the @Orbit object instead to store orbital data about an object. Makes it easier for rendering. */
  private double orbitalVelocity;

  @Deprecated
  /** Use the @Orbit object instead to store orbital data about an object. Makes it easier for rendering. */
  private double orbitalRadius;
  /** Deprecate this as well??? */
  private BaseObject orbitalParent;
  private Orbit orbit;

  /* Determines whether the object can change orbit (or orbital period). Applies for Stations, planets and moons */
  private boolean semiStaticObject;

  @Deprecated
  /** Use the @Orbit object instead to store orbital data about an object. Makes it easier for rendering. */
  public double getOrbitalV() {
	return orbitalVelocity;
  }

  @Deprecated
  /** Use the @Orbit object instead to store orbital data about an object. Makes it easier for rendering. */
  public void setOrbitalV(double orbitalV) {
	this.orbitalVelocity = orbitalV;
  }

  @Deprecated
  /** Use the @Orbit object instead to store orbital data about an object. Makes it easier for rendering. */
  public double getOrbitalR() {
	return orbitalRadius;
  }

  @Deprecated
  /** Use the @Orbit object instead to store orbital data about an object. Makes it easier for rendering. */
  public void setOrbitalR(double orbitalRadius) {
	this.orbitalRadius = orbitalRadius;
  }

  public BaseObject getOrbitalParent() {
	return orbitalParent;
  }

  /**
   * Sets a new parent for a movable object. Shouldn't be called on a semiStaticObject.
   */
  public void setOrbitalParent(BaseObject orbitalParent) {
	if (!semiStaticObject)
	  this.orbitalParent = orbitalParent;
	else
	  logger.info("Failed! SemiStaticObjects can't change orbital parent!");
  }

  public Orbit getOrbit() {
	return orbit;
  }

  public void setOrbit(Orbit orbit) {
	this.orbit = orbit;
  }

}
