package io.lonelyrobot.empires.fw.game.map;

import java.util.Set;

import io.lonelyrobot.empires.fw.game.obj.BaseObject;
import lombok.Data;

public @Data class OrbitData {
  private BaseObject parent;
  private Set<BaseObject> children;
  private double radius;
  private boolean leftHanded;

  private double step;
  private double stepSpeed;
}
