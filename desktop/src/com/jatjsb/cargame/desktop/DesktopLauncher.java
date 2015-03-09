package com.jatjsb.cargame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jatjsb.cargame.CarGame;
import com.jatjsb.cargame.interfaces.EmptyHandleAds;
import com.jatjsb.cargame.interfaces.EmptyHandleGooglePlay;
import com.jatjsb.cargame.screens.MyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.width = 840;
        cfg.height = 450;

		new LwjglApplication( new MyGame(new EmptyHandleGooglePlay(), new EmptyHandleAds()), cfg);
	}
}
