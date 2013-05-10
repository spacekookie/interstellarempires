/* #########################################################################
 * Copyright (c) 2013 Random Robot Softworks
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

package de.r2soft.space.hexcreator.backend;

import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.r2soft.space.hexcreator.utility.BaseSettings;

/**
 * The application managing class. Will be extended by frontent user ApplicationManager.
 * 
 * @author ***REMOVED***
 * 
 */
public class BackendManager implements Paintings {

	private Paintings painting;
	private Shell shell;
	private Display display;
	private boolean dying;

	public BackendManager() {
	}

	@Override
	public void onCreate() {
		display = new Display();
		shell = new Shell(display);
		shell.setText(BaseSettings.APPLICATION_TITLE);
		shell.setSize(BaseSettings.SIZE_WINDOW_USER.x, BaseSettings.SIZE_WINDOW_USER.y);
		shell.setLocation(400, 400);

		/** Should disable resizing of the window */
		if (!BaseSettings.APPLICATION_RESIZABLE)
			shell.addControlListener(new ControlAdapter() {

				@Override
				public void controlResized(ControlEvent e) {
				}
			});

		shell.setFullScreen(BaseSettings.APPLICATION_FULLSCREEN);
		shell.open();
	}

	@Override
	public void onResize(int x, int y) {
		shell.setSize(x, y);
	}

	@Override
	public void onPaint() {

	}

	/**
	 * Will completely and utterly kill the application FOREVER! ... or you know. Until it is being
	 * re-launched.
	 * 
	 * @author ***REMOVED***
	 */
	@Override
	public void onDestroy() {
		this.setDying(true);
		if (getDisplay() != null)
			display.dispose();
		if (getShell() != null)
			getShell().dispose();
		if (getPainting() != null)
			getPainting().onDestroy();
		System.exit(0);
	}

	public Paintings getPainting() {
		return painting;
	}

	protected void setPainting(Paintings painting) {
		this.painting = painting;
	}

	public Shell getShell() {
		return shell;
	}

	protected void setShell(Shell shell) {
		this.shell = shell;
	}

	public Display getDisplay() {
		return display;
	}

	/** Set new paintable object */
	public void setDisplay(Paintings painting) {
		if (this.painting != null)
			this.painting.onDestroy();
		this.painting = painting;
		if (this.painting != null) {
			this.painting.onCreate();
			this.painting.onResize(BaseSettings.SIZE_WINDOW_USER.x, BaseSettings.SIZE_WINDOW_USER.y);
		}
	}

	public boolean isDying() {
		return dying;
	}

	public void setDying(boolean dying) {
		this.dying = dying;
	}

}
