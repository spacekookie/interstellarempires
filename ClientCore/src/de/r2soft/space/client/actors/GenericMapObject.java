/* 
 * Copyright (c) 2013 Katharina Fey
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
 */

package de.r2soft.space.client.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Disposable;

import de.r2soft.space.client.settings.Settings;
import de.r2soft.space.client.util.ResPack;

import framework.objects.Unit.TYPE;
import framework.players.Alliance;
import framework.players.Alliance.ALLEGIANCE;
import framework.players.Player;

/**
 * A generic MapObject that will be drawn onto the screen in the Solarmap. May
 * call sub-actors for specific shapes, sizes and habits of objects.
 * 
 * @author Katharina
 * 
 */
@SuppressWarnings("unused")
public class GenericMapObject extends Actor implements Disposable {

	/** The absolute position of the actor */
	private float posX, posY;

	/** Alliance of the object relative to the player */
	private Alliance alliance;

	/** Object information */
	private TYPE type;
	private String flag;
	private Player claim;
	private ALLEGIANCE allegiance;
	private boolean selected;

	/** Unit movement */
	private Vector2 target, trajectory, position;

	private ShapeRenderer renderer;

	private boolean moving;

		{
			renderer = new ShapeRenderer();
			target = new Vector2();
			position = new Vector2();
		}

	public GenericMapObject getInstance() {
		return this;
	}

	public GenericMapObject(float x, float y) {
		this.target.x = x;
		this.target.y = y;
	}

	public GenericMapObject(float x, float y, TYPE type, String flag, Player claim,
			ALLEGIANCE allegience) {
		this.position.x = x;
		this.position.y = y;
		this.type = type;
		this.flag = flag;
		this.claim = claim;
		this.allegiance = allegience;
	}

	public void draw(SpriteBatch batch, float parentAlpha) {

		batch.end();
		batch.begin();
		if (selected)
			{
				batch.draw(ResPack.GUI_FRAME_SELECTION, position.x
						- (ResPack.SIZE_GUI_SELECTION_BOX_MEDIUM - ResPack.SIZE_FLEET_MEDIUM), position.y
						- (ResPack.SIZE_GUI_SELECTION_BOX_MEDIUM - ResPack.SIZE_FLEET_MEDIUM), 0, 0,
						ResPack.SIZE_GUI_SELECTION_BOX_MEDIUM, ResPack.SIZE_GUI_SELECTION_BOX_MEDIUM, 1, 1, 0);
			}

		switch (type) {
			case FLEET:
				batch.draw(ResPack.FLEET_FIGHTER_PLAYER, position.x, position.y, 0, 0,
						ResPack.SIZE_FLEET_MEDIUM, ResPack.SIZE_FLEET_MEDIUM, 1, 1, 0);
				break;

			default:
				Gdx.app.log(Settings.LOG, "Error displaying MapTile");
				break;
		}
	}

	/**
	 * This will register clicks on the corresponding tile actor.
	 * 
	 * @param x
	 *         position of mouse on screen.
	 * 
	 * @param y
	 *         position of mouse on screen.
	 * 
	 * @param touchable
	 *         Whether the actor allows touch events.
	 * 
	 * @return null
	 */
	@Override
	public Actor hit(float x, float y, boolean touchable) {

		if (touchable && getTouchable() == Touchable.enabled)
			{
				if (Gdx.input.isButtonPressed(1))
					{
						if (selected)
							{
								target.x = Gdx.input.getX();
								target.y = Gdx.input.getY();
								moveLocally();
							}
					} else if (Gdx.input.isTouched(0))
					{
						if (x > (this.position.x - ResPack.SIZE_GUI_SELECTION_BOX_MEDIUM)
								&& x < (this.position.x + ResPack.SIZE_GUI_SELECTION_BOX_MEDIUM)
								&& y > (this.position.y - ResPack.SIZE_GUI_SELECTION_BOX_MEDIUM)
								&& y < (this.position.y + ResPack.SIZE_GUI_SELECTION_BOX_MEDIUM))
							{
								selected = true;
							} else
							{
								selected = false;
							}
					}
			}
		return null;
	}

	private void moveLocally() {
		if (!target.equals(position))
			{
				trajectory = new Vector2(target.sub(position));
				trajectory.nor();
				position.add(trajectory.mul(100));
			}
	}

	@Override
	public void dispose() {
	}

}
