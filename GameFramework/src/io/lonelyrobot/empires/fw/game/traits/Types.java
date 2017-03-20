package io.lonelyrobot.empires.fw.game.traits;

public class Types {

  /**
   * Star types for easy classification and matching from config files. Additional data
   * about star-types (such as baseline radiation output or gravity well radius) can be
   * read from the game config files.
   */
  public enum Stars {

    /** Run of the mill dwarves */
    RED_DWARF, YELLOW_DWARF,

    /** Special dwarf stars */
    BROWN_DWARF, WHITE_DWARF,

    /** Giant sequence (O, B, A, F) */
    WHITE_GIANT, BLUE_GIANT, BLUE_SUPERGIANT,

    /** Special "stars" */
    BLACK_HOLE, SUPERNOVA, PULSAR
  }

  public enum Planetoids {

  }

  public enum Nebulae {

  }

}
