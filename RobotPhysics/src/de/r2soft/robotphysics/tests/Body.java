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

package de.r2soft.robotphysics.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.r2soft.robotphysics.instances.OrbitalBody;
import de.r2soft.robotphysics.instances.ParentBody;
import de.r2soft.robotphysics.instances.PhysicsBody;
import de.r2soft.robotphysics.primatives.R2Float;
import de.r2soft.robotphysics.primatives.R2Int;
import de.r2soft.robotphysics.primatives.R2P;

public class Body {

  public static enum TYPE {
	PLANET, STAR;
  }

  private Sprite sprite;
  private PhysicsBody body;

  /** Pixel position on screen */
  private R2Float position;

  /** TODO: Remove this! */
  @Deprecated
  public final R2Float spriteSize = new R2Float(128, 128);

  public Body(TYPE type) {
	if (type.equals(TYPE.PLANET)) {
	  sprite = new Sprite(new Texture(Gdx.files.internal("assets/planet.png")));
	  sprite.setScale(0.5f);
	  body = new OrbitalBody(R2P.R2_BODY_BIFUNCTION, this);
	  updatePosition(new R2Float(300, 150));
	}
	else if (type.equals(TYPE.STAR)) {
	  sprite = new Sprite(new Texture(Gdx.files.internal("assets/star.png")));
	  body = new ParentBody(100f);
	  ((ParentBody) body).setPosition(new R2Int(600, 300));
	  sprite.setPosition(600 - 64, 300 - 64);
	}
  }

  /** Make compatible with other object types */
  public void updatePosition(R2Float position) {
	this.position = position;
	sprite.setPosition(position.x - 64, position.y - 64);
	// ((OrbitalBody) body).updatePosition(position.x, position.y);
  }

  public R2Float getPosition() {
	return position;
  }

  public void update(SpriteBatch batch) {
	batch.begin();
	sprite.draw(batch);
	batch.end();
  }

  public Sprite getSprite() {
	return sprite;
  }

  public PhysicsBody getBody() {
	return body;
  }

}
