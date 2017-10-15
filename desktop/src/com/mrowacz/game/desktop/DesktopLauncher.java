package com.mrowacz.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mrowacz.game.GameSettings;
import com.mrowacz.game.StarShipWarsGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = GameSettings.TITLE;
		config.width = GameSettings.V_WIDTH;
		config.height = GameSettings.V_HEIGHT;
		config.forceExit = false;
		new LwjglApplication(new StarShipWarsGame(), config);
	}
}
