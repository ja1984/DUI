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
        float[] values = hextoRGB("#0f2031");
        Gdx.gl.glClearColor(values[0], values[1], values[2], 1.0f);
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


    private float[] map(float[]rgb) {

    /* RGB is from 0 to 255 */
    /* THIS is from 0 to 1 (float) */

        // 1 : 2 = x : 4 >>> 2

    /*
    *
    * 240 : 255 = x : 1
    *
    * */

        float[] result = new float[3];
        result[0] = rgb[0] / 255;
        result[1] = rgb[1] / 255;
        result[2] = rgb[2] / 255;
        return result;
    }

    private float[] hextoRGB(String hex) {

        float[] rgbcolor = new float[3];
        rgbcolor[0] = Integer.valueOf( hex.substring( 1, 3 ), 16 );
        rgbcolor[1] = Integer.valueOf( hex.substring( 3, 5 ), 16 );
        rgbcolor[2] = Integer.valueOf( hex.substring( 5, 7 ), 16 );
        return map(rgbcolor);
    }


}