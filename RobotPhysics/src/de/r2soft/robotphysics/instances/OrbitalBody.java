/* #########################################################################
 * Copyright (c) 2014 Random Robot Softworks
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

package de.r2soft.robotphysics.instances;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.r2soft.robotphysics.primatives.R2Float;
import de.r2soft.robotphysics.primatives.R2P;
import de.r2soft.robotphysics.tests.Body;

public class OrbitalBody extends PhysicsBody {

  /**
   * Determines if the body can have children of it's own. If @FALSE it will act as an end-instance. If @TRUE it will initiate it's Set for
   * own children
   */
  private boolean bifunction = false;

  private Body parent;
  private PhysicsBody orbitalParent;
  private Set<OrbitalBody> children;
  private R2Float position;
  private R2Float velocity;
  private R2Float acceleration;
  private float angle;

  public OrbitalBody(int bifunction, Body parent) {
	super();
	this.parent = parent;
	position = parent.getPosition();
	velocity = new R2Float(0, 88); // 92.75
	acceleration = new R2Float(0, 0);
	renderer = new ShapeRenderer();
	if (bifunction == R2P.R2_BODY_BIFUNCTION)
	  children = new HashSet<OrbitalBody>();
  }

  public void update(boolean clicked, OrthographicCamera camera) {
	if (clicked)
	  return;
	calculateAngle();
	computeGravity(camera);
  }

  public void updatePosition(float x, float y) {
	position.set(x, y);
  }

  private void calculateAngle() {
	double parentX = ((ParentBody) orbitalParent).getPosition().x;
	double parentY = ((ParentBody) orbitalParent).getPosition().y;

	angle = (float) Math.toDegrees(Math.atan2(parentY - position.y, parentX - position.x));
	if (angle < 0)
	  angle += 360;
  }

  ShapeRenderer renderer;

  /** Called every tick to compute gravity for this object */
  private void computeGravity(OrthographicCamera camera) {
	if (bifunction) {
	  // Compute gravity for children
	  for (OrbitalBody body : children) {
		body.computeGravity(null);
	  }
	}
	else {
	  float tempX = ((ParentBody) orbitalParent).getPosition().x - position.x;
	  float tempY = ((ParentBody) orbitalParent).getPosition().y - position.y;
	  float distance = (float) trigeometry(tempX, tempY);
	  float force = (float) ((R2P.R2_PHYSICS_GRAVITY * getOrbitaParent().getMass() * getMass()) / Math.pow(distance, 2));

	  /** Assigning forces proportionally to the acceleration vector */
	  acceleration.x = 5000 * tempX * (force / distance);
	  acceleration.y = 5000 * tempY * (force / distance);

	  this.applyForce(camera);
	}
  }

  private void applyForce(OrthographicCamera camera) {
	R2Float AccDT = new R2Float(acceleration.x, acceleration.y);
	AccDT.scl(Gdx.graphics.getDeltaTime()); // todo get delta t
	velocity.add(AccDT);
	R2Float VelDT = new R2Float(velocity.x, velocity.y);
	VelDT.scl(Gdx.graphics.getDeltaTime()); // todo get delta t
	position.add(VelDT);

	// Vector2 tmp = parent.getPosition().cpy();
	// renderer.setProjectionMatrix(camera.combined);
	// renderer.begin(ShapeType.Line);
	// renderer.setColor(1, 1, 1, 1);
	// renderer.line(new Vector2(tmp.x, tmp.y), tmp.add(acceleration.scl(10f)));
	// renderer.end();

	parent.updatePosition(position);
  }

  /** Determines the distance between two objects */
  private double trigeometry(double x, double y) {
	return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
  }

  public boolean isBifunction() {
	return bifunction;
  }

  public PhysicsBody getOrbitaParent() {
	return orbitalParent;
  }

  public void setOrbitalParent(PhysicsBody orbitalParent) {
	this.orbitalParent = orbitalParent;
  }

  public Set<OrbitalBody> getOrbitalChildren() {
	return children;
  }

  public void addOrbitalChild(OrbitalBody child) {
	if (!children.contains(child))
	  children.add(child);
  }

  public void removeOrbitalChild(OrbitalBody child) {
	if (children.contains(child))
	  children.remove(child);
	else
	  System.out.println("No such child in orbit");
  }

  public void setOrbitalChildren(Set<OrbitalBody> children) {
	this.children = children;
  }

  public R2Float getPosition() {
	return position;
  }

  public void setInitialPosition(R2Float initialPosition) {
	this.position = initialPosition;
  }

}
