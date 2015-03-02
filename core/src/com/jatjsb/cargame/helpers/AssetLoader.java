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
    private static Preferences prefs;
    public static TextureRegion logo, bg, player, redBalloon, greenBalloon, pinkBalloon, yellowBalloon, fruit1, fruit2, fruit3, fruit4, fruit5, spider, car, roadLine;
    public static Music backgroundMusic;
    public static Sound pop;
    private static ArrayList<TextureRegion> balloonTextureRegions = new ArrayList<TextureRegion>();
    private static ArrayList<TextureRegion> fruitTextureRegions = new ArrayList<TextureRegion>();

    public static void load() {
        Texture logoTexture = new Texture(Gdx.files.internal("logo.png"));
        logoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        logo = new TextureRegion(logoTexture, 0, 0, 512, 114);
        logo.flip(false, true);

        Texture bgTexture = new Texture(Gdx.files.internal("road.png"));

        bg = new TextureRegion(bgTexture, 0, 0, 480, 840);
        bg.flip(false, true);

        Texture redBalloonTexture = new Texture(Gdx.files.internal("balloon.png"));
        redBalloonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Texture greenBalloonTexture = new Texture(Gdx.files.internal("balloon_green.png"));
        greenBalloonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Texture pinkBalloonTexture = new Texture(Gdx.files.internal("balloon_pink.png"));
        pinkBalloonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Texture yellowBalloonTexture = new Texture(Gdx.files.internal("balloon_yellow.png"));
        yellowBalloonTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        redBalloon = new TextureRegion(redBalloonTexture, 0, 0, 100, 141);
        redBalloon.flip(false, true);
        balloonTextureRegions.add(redBalloon);
        greenBalloon = new TextureRegion(greenBalloonTexture, 0, 0, 100, 141);
        greenBalloon.flip(false, true);
        balloonTextureRegions.add(greenBalloon);
        pinkBalloon = new TextureRegion(pinkBalloonTexture, 0, 0, 100, 141);
        pinkBalloon.flip(false, true);
        balloonTextureRegions.add(pinkBalloon);
        yellowBalloon = new TextureRegion(yellowBalloonTexture, 0, 0, 100, 141);
        yellowBalloon.flip(false, true);
        balloonTextureRegions.add(yellowBalloon);

        Texture fruit1Texture = new Texture(Gdx.files.internal("fruit1.png"));
        fruit1Texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        fruit1 = new TextureRegion(fruit1Texture, 0, 0, 52, 72);
        fruit1.flip(false, true);
        fruitTextureRegions.add(fruit1);
        Texture fruit2Texture = new Texture(Gdx.files.internal("fruit2.png"));
        fruit2Texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        fruit2 = new TextureRegion(fruit2Texture, 0, 0, 60, 53);
        fruit2.flip(false, true);
        fruitTextureRegions.add(fruit2);
        Texture fruit3Texture = new Texture(Gdx.files.internal("fruit3.png"));
        fruit3Texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        fruit3 = new TextureRegion(fruit3Texture, 0, 0, 54, 75);
        fruit3.flip(false, true);
        fruitTextureRegions.add(fruit3);
        Texture fruit4Texture = new Texture(Gdx.files.internal("fruit4.png"));
        fruit4Texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        fruit4 = new TextureRegion(fruit4Texture, 0, 0, 58, 67);
        fruit4.flip(false, true);
        fruitTextureRegions.add(fruit4);
        Texture fruit5Texture = new Texture(Gdx.files.internal("fruit5.png"));
        fruit5Texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        fruit5 = new TextureRegion(fruit5Texture, 0, 0, 45, 76);
        fruit5.flip(false, true);
        fruitTextureRegions.add(fruit5);

        Texture spiderTexture = new Texture(Gdx.files.internal("spider.png"));
        spiderTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spider = new TextureRegion(spiderTexture, 0, 0, 75, 42);
        spider.flip(false, true);

        Texture carTexture = new Texture(Gdx.files.internal("garbagetruck.png"));
        carTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        car = new TextureRegion(carTexture, 0, 0, 19, 30);
        car.flip(false, true);

        Texture playerTexture = new Texture(Gdx.files.internal("car.png"));
        playerTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        player = new TextureRegion(playerTexture, 0, 0, 18, 24);
        player.flip(false, true);

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music.wav"));
        backgroundMusic.setLooping(true);

        pop = Gdx.audio.newSound(Gdx.files.internal("pop.ogg"));
    }

    public static TextureRegion getRandomBalloonTextureRegion(){
        int random = Utils.getRandomIntBetween(0, balloonTextureRegions.size());

        return balloonTextureRegions.get(random);
    }

    public static ParticleEffect getParticleEffect(){
        ParticleEffect particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal("particle/star.p"), Gdx.files.internal("particle"));
        particleEffect.setFlip(false, true);
        return particleEffect;
    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void dispose() {

    }

    public static TextureRegion getRandomFruitTextureRegion() {
        int random = Utils.getRandomIntBetween(0, fruitTextureRegions.size());

        return fruitTextureRegions.get(random);
    }
}
