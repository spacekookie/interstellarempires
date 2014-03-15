package de.r2soft.empires.client.html;

import de.r2soft.empires.client.core.CoreGame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class CoreGameHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new CoreGame();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}
