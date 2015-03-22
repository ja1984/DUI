package com.jatjsb.cargame.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetLoader {
    public static TextureRegion road, playerCar, lines, hitbox;

    public static Skin menuSkin;
    public static TextureRegion ambulance;
    public static TextureRegion ambulanceFlipped;
    public static TextureRegion blueCarFlipped;
    public static TextureRegion blueCar;
    public static TextureRegion redCarFlipped;
    public static TextureRegion redCar;
    public static TextureRegion garbageCarFlipped;
    public static TextureRegion garbageCar;
    public static ParticleEffect sparksParticleEffect;

    public static void load() {
        road = new TextureRegion(loadTexture("road_background_3lanes_old_new.png"), 0, 0, 840, 450);
        road.flip(false, false);

        lines = new TextureRegion(loadTexture("lines.png"), 0, 0, 480, 840);
        lines.flip(false, true);

        //cartypes
        garbageCar = new TextureRegion(loadTexture("garbage_NE.png"), 0, 0, 35, 31);
        garbageCar.flip(false, false);
        garbageCarFlipped = new TextureRegion(loadTexture("garbage_SW.png"),0 ,0 ,35, 36);
        garbageCarFlipped.flip(false, false);

        ambulance = new TextureRegion(loadTexture("ambulance_NE.png"), 0, 0, 35, 31);
        ambulance.flip(false, false);
        ambulanceFlipped = new TextureRegion(loadTexture("ambulance_SW.png"),0 ,0 ,35, 36);
        ambulanceFlipped.flip(false, false);

        blueCar = new TextureRegion(loadTexture("carBlue6_006.png"), 0, 0, 32, 30);
        blueCar.flip(false, false);
        blueCarFlipped = new TextureRegion(loadTexture("carBlue6_010.png"),0 ,0 ,33, 32);
        blueCarFlipped.flip(false, false);

        redCar = new TextureRegion(loadTexture("carRed5_000.png"), 0, 0, 32, 24);
        redCar.flip(false, false);
        redCarFlipped = new TextureRegion(loadTexture("carRed5_004.png"),0 ,0 ,32, 27);
        redCarFlipped.flip(false, false);

        hitbox = new TextureRegion(loadTexture("hitbox.png"), 0, 0, 30, 30);

        playerCar = new TextureRegion(loadTexture("player.png"), 0, 0, 32, 27);
        playerCar.flip(false, false);

        sparksParticleEffect = new ParticleEffect();
        sparksParticleEffect.load(Gdx.files.internal("particle/sparks.p"), Gdx.files.internal("particle"));
        sparksParticleEffect.setFlip(false, true);

        menuSkin = new Skin(Gdx.files.internal("skins/menuskin.json"), new TextureAtlas(Gdx.files.internal("skins/menuskin.atlas")));
    }

    private static Texture loadTexture(String file){
        Texture texture = new Texture(Gdx.files.internal(file));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return texture;
    }

    public static ParticleEffect getParticleEffect(){
        ParticleEffect particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal("particle/explosion.p"), Gdx.files.internal("particle"));
        particleEffect.setFlip(false, true);
        return particleEffect;
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
}
