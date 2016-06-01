package com.game.main.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.engine.main.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Main.TITLE;
		config.width = Main.V_WIDTH * Main.SCALE;
		config.height = Main.V_HEIGHT * Main.SCALE;
		new LwjglApplication(new Main(), config);
	}
}
