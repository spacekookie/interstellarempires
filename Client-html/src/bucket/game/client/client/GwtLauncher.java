package bucket.game.client.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

import de.r2soft.space.client.core.MainClientLauncher;
import de.r2soft.space.client.settings.Resources;

public class GwtLauncher extends GwtApplication {
	@Override
	public GwtApplicationConfiguration getConfig() {
		GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(Resources.OLD_WIDTH,
				Resources.OLD_HEIGHT);
		return cfg;
	}

	@Override
	public ApplicationListener getApplicationListener() {
		return (ApplicationListener) new MainClientLauncher();
	}
}