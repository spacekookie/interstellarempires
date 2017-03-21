package io.lonelyrobot.empires.fw.game.utils;

import java.util.Collections;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Tools {

  public static Vector2D computeOrbit(Vector2D parent, double r, Vector2D self, double step) {
    double x = parent.getX() + r * Math.cos(step);
    double y = parent.getY() + r * Math.sin(step);

    return new Vector2D(x, y);
  }

  public static <T> Iterable<T> safe(Iterable<T> iterable) {
    return iterable == null ? Collections.<T>emptyList() : iterable;
  }
}
