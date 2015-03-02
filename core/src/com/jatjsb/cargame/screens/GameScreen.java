package com.jatjsb.cargame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.jatjsb.cargame.CarGame;
import com.jatjsb.cargame.helpers.AssetLoader;
import com.jatjsb.cargame.helpers.InputHandler;
import com.jatjsb.cargame.world.GameRenderer;
import com.jatjsb.cargame.world.GameWorld;

/**
 * Created by knepe on 2015-02-25.
 */
public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;
    private CarGame game;

    // This is the constructor, not the class declaration
    public GameScreen(CarGame game) {

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        int y = (int) screenHeight + 50;
        Gdx.app.log("GameScreen", "y: " + y);
        this.game = game;
        world = new GameWorld(y);
        Gdx.input.setInputProcessor(new InputHandler(world, this.game,  screenWidth / gameWidth, screenHeight / gameHeight));
        renderer = new GameRenderer(world, game, (int) gameHeight, y);
        world.setRenderer(renderer);
        game.adsHandler.showAds(true);
        AssetLoader.backgroundMusic.play();
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(delta, runTime);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        /*if(!game.googlePlayHandler.getSignedIn())
            game.googlePlayHandler.login();*/
    }

    @Override
    public void hide() {
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