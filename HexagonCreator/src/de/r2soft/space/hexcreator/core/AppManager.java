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

package de.r2soft.space.hexcreator.core;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.r2soft.space.hexcreator.backend.BackendManager;

public class AppManager extends BackendManager {

  private Display display;
  private Shell shell;
  private RowLayout layout;

  @Override
  public void onCreate() {
	super.onCreate();
	display = super.getDisplay();
	shell = super.getShell();
  }

  @Override
  public void onResize(int x, int y) {
	super.onResize(x, y);
	this.setupLayout();
	this.setupButtons();
  }

  private void setupButtons() {
  }

  private void setupLayout() {
	if (layout == null)
	  layout = new RowLayout(SWT.HORIZONTAL);
	layout.marginTop = 10;
	layout.marginBottom = 10;
	layout.marginLeft = 5;
	layout.marginRight = 5;
	shell.setLayout(layout);
  }

  @Override
  public void onPaint() {
	super.onPaint();

  }

  @Override
  public void onDestroy() {
	super.onDestroy();
  }

}
