package com.jatjsb.cargame.screens;

import com.badlogic.gdx.Game;
import com.jatjsb.cargame.helpers.AssetLoader;
import com.jatjsb.cargame.interfaces.IHandleAds;
import com.jatjsb.cargame.interfaces.IHandleGooglePlay;

/**
 * Created by jonathan on 2015-03-04.
 */
public class MyGame extends Game {
    public final static int WIDTH = 840;
    public final static int HEIGHT = 450;
    private GameScreen gameScreen;

    public MyGame(IHandleGooglePlay googlePlayHandler, IHandleAds adsHandler) {
        super();
        this.googlePlayHandler = googlePlayHandler;
        this.adsHandler = adsHandler;
    }

    public IHandleGooglePlay googlePlayHandler;
    public IHandleAds adsHandler;

    @Override
    public void create() {
        AssetLoader.load();
        gameScreen = new GameScreen();
        setScreen(gameScreen);
        adsHandler.showAds(true);
    }

    @Override
    public void dispose() {
        AssetLoader.dispose();
        gameScreen.dispose();
    }
}
