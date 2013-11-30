package de.r2soft.space.client.io;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import de.r2soft.space.client.types.GalaxyRenderer;

public class OrthoCamController extends InputAdapter {
  final OrthographicCamera camera;
  final Vector3 curr = new Vector3();
  final Vector3 last = new Vector3(-1, -1, -1);
  final Vector3 delta = new Vector3();

  private GalaxyRenderer render;

  public OrthoCamController(OrthographicCamera camera, GalaxyRenderer render) {
	this.camera = camera;
	this.render = render;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
	return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	Vector3 tmp = new Vector3(screenX, screenY, 0);
	camera.unproject(tmp);

	System.out.println(render.getTileAt(tmp.x, tmp.y).getSystem().getPosition().toString());

	return false;
	// Tile t = level.getTileMap().getTileAt(tmp.x, tmp.y);
  }

  @Override
  public boolean touchDragged(int x, int y, int pointer) {
	camera.unproject(curr.set(x, y, 0));
	if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
	  camera.unproject(delta.set(last.x, last.y, 0));
	  delta.sub(curr);
	  camera.position.add(delta.x, delta.y, 0);
	}
	last.set(x, y, 0);
	return false;
  }

  public void changeZoom(float zoom, float x, float y) {

	Vector3 before = new Vector3(x, y, 0);
	camera.unproject(before);

	camera.zoom = zoom;
	camera.update();
	Vector3 after = new Vector3(x, y, 0);
	camera.unproject(after);

	camera.translate(before.x - after.x, before.y - after.y, 0);
  }

  @Override
  public boolean scrolled(int amount) {
	float newZoom = camera.zoom * (1 + (amount < 0 ? 0.1f : -0.1f));
	changeZoom(newZoom, last.x, last.y);
	return true;
  }

  @Override
  public boolean touchUp(int x, int y, int pointer, int button) {
	last.set(-1, -1, -1);
	return false;
  }
}
