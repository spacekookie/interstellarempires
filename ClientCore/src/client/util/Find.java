package client.util;

import java.util.HashMap;

import client.types.IntVec2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * 
 * Pixel coordination utility class.
 * 
 * @author Katharina
 * 
 */
public class Find {

	public static HashMap<IntVec2, Vector2> hexmap;

	/** @return: the screens center position */
	public static Vector2 getCenter() {
		return new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
	}

	/**
	 * Sets the standard pixel coordinates for tiles on the HexMap
	 * 
	 * @param size
	 *         Tiles pixel size to adjust pixel coordinates.
	 */
	public static void setHexMap(float size) {
		hexmap.put(new IntVec2(0, 0), new Vector2());
	}
}
