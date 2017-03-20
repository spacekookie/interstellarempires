package io.lonelyrobot.empires.fw.game.players;

import java.util.HashMap;
import java.util.Map;

import io.lonelyrobot.empires.fw.game.utils.Logger;

public abstract class Sociable {
  private Map<Sociable, Double> standings;

  {
    standings = new HashMap<>();
  }

  public void changeStanding(Sociable s, double offset) {

    /** Add a new neutral standing if neccessary */
    if (!standings.containsKey(s))
      standings.put(s, 0.0);

    double curr = standings.get(s);
    double updated = curr + offset;

    if (updated <= 10 || updated >= -10)
      standings.put(s, curr + offset);
    else
      Logger.debug("Standing for " + s + " already at maximum offset");
  }
}
