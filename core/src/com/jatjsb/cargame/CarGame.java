package com.jatjsb.cargame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jatjsb.cargame.helpers.AssetLoader;
import com.jatjsb.cargame.interfaces.IHandleAds;
import com.jatjsb.cargame.interfaces.IHandleGooglePlay;
import com.jatjsb.cargame.screens.GameScreen;

public class CarGame implements ApplicationListener {
    public final static int WIDTH = 840;
    public final static int HEIGHT = 450;

    public CarGame(IHandleGooglePlay googlePlayHandler, IHandleAds adsHandler) {
        super();
        this.googlePlayHandler = googlePlayHandler;
        this.adsHandler = adsHandler;
    }

    public IHandleGooglePlay googlePlayHandler;
    public IHandleAds adsHandler;

    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}