package com.jatjsb.cargame.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

/**
 * Created by knepe on 2015-02-25.
 */
public class AssetLoader {
    public static TextureRegion road, enemyCar, oncomingEnemyPlayer, playerCar, lines;
    public static Music backgroundMusic;
    public static Sound pop;
    private static ArrayList<TextureRegion> balloonTextureRegions = new ArrayList<TextureRegion>();
    private static ArrayList<TextureRegion> fruitTextureRegions = new ArrayList<TextureRegion>();

    public static void load() {

        Texture bgTexture = new Texture(Gdx.files.internal("road_background_3lanes_old_new.png"));
        bgTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        road = new TextureRegion(bgTexture, 0, 0, 840, 450);
        road.flip(false, false);

        Texture linesTexture = new Texture(Gdx.files.internal("lines.png"));
        lines = new TextureRegion(linesTexture, 0, 0, 480, 840);
        lines.flip(false, true);

        Texture carTexture = new Texture(Gdx.files.internal("garbage_NE.png"));
        carTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        enemyCar = new TextureRegion(carTexture, 0, 0, 35, 33);
        enemyCar.flip(false, false);


        Texture oncomingcarTexture = new Texture(Gdx.files.internal("garbage_SW.png"));
        carTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        oncomingEnemyPlayer = new TextureRegion(oncomingcarTexture, 0, 0, 35, 33);
        oncomingEnemyPlayer.flip(false, false);


        Texture playerTexture = new Texture(Gdx.files.internal("player.png"));
        carTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        playerCar = new TextureRegion(playerTexture, 0, 0, 32, 27);
        playerCar.flip(false, false);
    }

    public static void setHighScore(int val) {
        //prefs.putInteger("highScore", val);
        //prefs.flush();
    }

    public static int getHighScore() {
        return 0;
        //return prefs.getInteger("highScore");
    }

    public static void dispose() {

    }

    public static TextureRegion getRandomFruitTextureRegion() {
        int random = Utils.getRandomIntBetween(0, fruitTextureRegions.size());

        return fruitTextureRegions.get(random);
    }
}
