package de.r2soft.empires.framework.research;

import java.util.Set;

/**
 * A research node that can be extended to populate a @ResearchNodeTree
 * 
 * @author ***REMOVED*** <***REMOVED***>
 * 
 */
public abstract class ResearchNode {

  private Long id;
  private String name;
  private ResearchNode parent;
  private Set<ResearchNode> children;

}
