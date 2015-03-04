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
    public static TextureRegion road, enemyCar;
    public static Music backgroundMusic;
    public static Sound pop;
    private static ArrayList<TextureRegion> balloonTextureRegions = new ArrayList<TextureRegion>();
    private static ArrayList<TextureRegion> fruitTextureRegions = new ArrayList<TextureRegion>();

    public static void load() {

        Texture bgTexture = new Texture(Gdx.files.internal("road.png"));
        road = new TextureRegion(bgTexture, 0, 0, 480, 840);
        road.flip(false, true);

        Texture carTexture = new Texture(Gdx.files.internal("garbagetruck.png"));
        carTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        enemyCar = new TextureRegion(carTexture, 0, 0, 19, 30);
        enemyCar.flip(false, false);
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
