/* #########################################################################
 * Copyright (c) 2014 RANDOM ROBOT SOFTWORKS
 * (See @authors file)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ######################################################################### */
package io.lonelyrobot.empires.client.maps.hex;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Disposable;

import io.lonelyrobot.empires.framework.map.SolarSystem;

/**
 * 
 * @author: ***REMOVED*** <***REMOVED***>
 */
public class R2HexCell extends Cell {
  private SolarSystem attachment;
  private Polygon bounds;
  private TextureRegion region;

  public R2HexCell(SolarSystem system) {
	this(system, null);
  }

  public R2HexCell(SolarSystem system, TextureRegion region) {
	bounds = new Polygon();
	this.attachment = system;
	if (region != null)
	  this.region = region;
  }

  public TextureRegion getRegion() {
	return region;
  }

  public void setRegion(TextureRegion region) {
	this.region = region;
  }

  public void setSystem(SolarSystem attachment) {
	this.attachment = attachment;
  }

  public SolarSystem getSystem() {
	return attachment;
  }

}
