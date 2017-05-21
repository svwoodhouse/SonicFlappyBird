package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.SonicFlappyBird;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = SonicFlappyBird.WIDTH;
		config.height = SonicFlappyBird.HEIGHT;
		config.title = SonicFlappyBird.TITLE;
		new LwjglApplication(new SonicFlappyBird(), config);
	}
}
