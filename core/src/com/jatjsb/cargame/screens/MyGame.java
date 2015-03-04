package com.jatjsb.cargame.screens;

import com.badlogic.gdx.Game;
import com.jatjsb.cargame.helpers.AssetLoader;

/**
 * Created by jonathan on 2015-03-04.
 */
public class MyGame extends Game {
    public final static int WIDTH = 800;
    public final static int HEIGHT = 480;
    private GameScreen gameScreen;

    @Override
    public void create() {
        AssetLoader.load();
        gameScreen = new GameScreen();
        setScreen(gameScreen);
    }

    @Override
    public void dispose() {
        AssetLoader.dispose();
        gameScreen.dispose();
    }
}
