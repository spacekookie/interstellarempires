package io.lonelyrobot.empires.cl.gl.obj;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import io.lonelyrobot.empires.fw.game.obj.BaseObject;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public @RequiredArgsConstructor class SolObject {
  private TextureRegion region;
  private Vector2 position;

  private @NonNull @Getter BaseObject backend;

  {
    position = new Vector2();
  }

  public void draw(ShapeRenderer batch, float size) {
    updatePosition(backend.getSolPos());

    // float w = region.getRegionWidth();
    // float h = region.getRegionHeight();
    //
    // float xo = w / 2;
    // float yo = h / 2;
    // batch.draw(region, position.x - xo, position.y - yo, xo, yo, w, h, 1, 1, 0);

    batch.circle(position.x, position.y, size);
  }

  /**
   * A simple utility function which will take a GameFramework vector and update the
   * position of this SolObject on screen
   * 
   * @param x
   * @param y
   */
  public void updatePosition(Vector2D position) {
    this.position.x = (float) position.getX();
    this.position.y = (float) position.getY();
  }
}
