package de.r2soft.space.framework.objects;

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

  /*
   * Determines whether the object can change orbit (or orbital period). Applies for Stations, planets and moons
   */
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
   * If you call this on a semi-static object AFTER creation I will fucking kill you! @author ***REMOVED***
   */
  public void setParent(BaseObject orbitalParent) {
	this.orbitalParent = orbitalParent;
  }

}
