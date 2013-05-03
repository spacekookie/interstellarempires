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

package bucket.game.client.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * You should know this from my Android applications. Will handle the SharedPreferences
 * databases and the setter/ getter in this instance.
 * 
 * @author Katharina
 * 
 */
public class AppSettingsHelper {

	Preferences prefs = Gdx.app.getPreferences("main_app_preferences");

	public AppSettingsHelper() {
		
	}

	public void setExample(boolean value) {
		Preferences prefs = Gdx.app.getPreferences("main_app_preferences");
		prefs.putBoolean("intro", value);
	}

	public boolean getExample() {
		return prefs.getBoolean("intro");
	}

}
