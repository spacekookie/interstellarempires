package de.r2soft.empires.framework.research;

import java.util.TreeSet;
import java.util.UUID;

public abstract class ResearchNodeTree {

  public TreeSet<ResearchNode> nodes;
  public String name;
  public String id;

  public ResearchNodeTree(String name) {
	this.name = name;
	id = UUID.fromString(name).toString();
	nodes = new TreeSet<ResearchNode>();
  }

}
