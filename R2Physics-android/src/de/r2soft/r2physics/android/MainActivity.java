package de.r2soft.r2physics.android;

import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import de.r2soft.r2physics.tests.GravityTest;

public class MainActivity extends AndroidApplication {
  @Override
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
	cfg.useGL20 = false;

	initialize(new GravityTest(), cfg);
	if (Gdx.files.internal("assets/planet.png").exists())
	  System.out.println("FUCK YOU LITTLE CUNT");
	else
	  System.out.println("Well at least you're consistent!");
  }
}