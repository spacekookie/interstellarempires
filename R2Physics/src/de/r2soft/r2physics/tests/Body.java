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

package de.r2soft.r2physics.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.r2soft.r2physics.instances.OrbitalBody;
import de.r2soft.r2physics.instances.ParentBody;
import de.r2soft.r2physics.instances.PhysicsBody;
import de.r2soft.r2physics.primatives.R2Float;
import de.r2soft.r2physics.primatives.R2Int;
import de.r2soft.r2physics.primatives.R2P;

public class Body {

  public static enum TYPE {
	PLANET, STAR;
  }

  private Sprite sprite;
  private PhysicsBody body;
  private TYPE type;

  /** Pixel position on screen */
  private R2Float position;

  /** TODO: Change this! */
  @Deprecated
  public final R2Float spriteSize = new R2Float(128, 128);

  public Body(TYPE type, R2Float position) {
	this.type = type;
	if (type.equals(TYPE.PLANET)) {
	  sprite = new Sprite(new Texture(Gdx.files.internal("planet.png")));
	  sprite.setScale(0.5f);
	  body = new OrbitalBody(R2P.R2_BODY_BIFUNCTION, this);
	  updatePosition(position);
	}
	else if (type.equals(TYPE.STAR)) {
	  sprite = new Sprite(new Texture(Gdx.files.internal("star.png")));
	  body = new ParentBody(R2P.R2_PHYSICS_MASS_FUN_MASSIVE);
	  updatePosition(position);
	  sprite.setPosition(600 - 64, 300 - 64);
	}
  }

  /** Make compatible with other object types */
  public void updatePosition(R2Float position) {
	this.position = position;
	sprite.setPosition(position.x - 64, position.y - 64);

	if (type.equals(TYPE.STAR))
	  ((ParentBody) body).setPosition(position);
	else
	  ((OrbitalBody) body).setPosition(position);
  }

  public R2Float getPosition() {
	return position;
  }

  /** Updating the unit every frame (animation and sprite) */
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
