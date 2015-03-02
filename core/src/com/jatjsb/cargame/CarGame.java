package com.jatjsb.cargame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.jatjsb.cargame.helpers.AssetLoader;
import com.jatjsb.cargame.interfaces.IHandleAds;
import com.jatjsb.cargame.interfaces.IHandleGooglePlay;
import com.jatjsb.cargame.screens.GameScreen;

public class CarGame extends Game {
    public CarGame(IHandleGooglePlay googlePlayHandler, IHandleAds adsHandler) {
        super();

        this.googlePlayHandler = googlePlayHandler;
        this.adsHandler = adsHandler;
    }

    public IHandleGooglePlay googlePlayHandler;
    public IHandleAds adsHandler;

    @Override
    public void create() {
        Gdx.input.setCatchBackKey(true);
        AssetLoader.load();
        setScreen(new GameScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}