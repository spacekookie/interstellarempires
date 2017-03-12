package io.lonelyrobot.empires.fw.game.obj;

/**
 * This is a trait that marks an object as attackable. This means multiple things. First,
 * it needs to be assigned a combat ID for the AI tracking and target selection to work.
 * Secondly, it means that it can be destroyed via conventional methods (fleet combat/
 * first strike combat). There is an attack method that an object needs to implement which
 * (by default) should just call a static utility method in this interface which handles
 * combat maths.
 * 
 * @author Katharina 'spacekookie' Fey <kookie@spacekookie.de>
 */
public interface Attackable {

  /**
   * This function needs to be implemented by a child class that returns the to-be-used
   * combat ID for the object.
   * 
   * @return
   */
  public long combatID();

  /**
   * This function applies damage to the parent object. Normally that means that it
   * triggers {@link #attack(Object o, double damage)} in the implementing child class.
   * 
   * @param damage
   */
  public void attack(double damage);

  /**
   * A static utility function that applies damage to an object so that each object
   * doesn't need to duplicate code. This function also invokes combat math utility
   * functions to take into consideration things like weapon types, damage types as well
   * as armour and shields.
   * 
   * @param o
   * @param damage
   */
  static void attack(Object o, double damage) {
    // TODO: Implement combat maths here
  }
}
