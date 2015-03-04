package com.jatjsb.cargame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.jatjsb.cargame.CarGame;
import com.jatjsb.cargame.interfaces.EmptyHandleAds;
import com.jatjsb.cargame.interfaces.EmptyHandleGooglePlay;
import com.jatjsb.cargame.world.GameWorld;

/**
 * Created by knepe on 2015-02-25.
 */
public class GameScreen implements Screen{

    private Stage stage;
    private GameWorld game;

    // This is the constructor, not the class declaration
    public GameScreen() {
        stage = new Stage(new ScalingViewport(Scaling.fit, CarGame.WIDTH,CarGame.HEIGHT));
        game = new GameWorld();
        stage.addActor(game);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {

    }
}