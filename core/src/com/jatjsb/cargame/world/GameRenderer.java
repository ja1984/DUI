package com.jatjsb.cargame.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jatjsb.cargame.CarGame;
import com.jatjsb.cargame.gameobjects.EnemyCar;
import com.jatjsb.cargame.gameobjects.HiddenObject;
import com.jatjsb.cargame.gameobjects.Player;
import com.jatjsb.cargame.gameobjects.Rumble;
import com.jatjsb.cargame.gameobjects.ScrollHandler;
import com.jatjsb.cargame.helpers.AssetLoader;

import java.util.ArrayList;

/**
 * Created by knepe on 2015-02-25.
 */
public class GameRenderer {
    private GameWorld myWorld;
    public OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    public CarGame game;

    private Player player;

    private Rumble rumble;

    public SpriteBatch batcher;

    private int midPointY;

    // Game Objects
    private ScrollHandler scroller;
    private ArrayList<EnemyCar> enemyCars;

    // Game Assets
    private TextureRegion bg, playerAsset;
    public ArrayList<ParticleEffect> particleEffects;
    public ArrayList<HiddenObject> hiddenObjects;

    public GameRenderer(GameWorld world, CarGame game, int gameHeight, int midPointY) {
        myWorld = world;
        this.game = game;

        this.midPointY = midPointY;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        this.rumble = new Rumble(cam);
        initGameObjects();
        initAssets();
    }

    private void initGameObjects() {
        player = myWorld.getPlayer();
        scroller = myWorld.getScroller();
        enemyCars = scroller.getEnemyCars();
        for(EnemyCar b : enemyCars){
            b.setRenderer(this);
        }
        particleEffects = new ArrayList<ParticleEffect>();
        hiddenObjects = new ArrayList<HiddenObject>();
    }

    public void shakeScreen(){
        Gdx.input.vibrate(300);
        rumble.rumble(5f, .2f);
    }

    private void initAssets() {
        playerAsset = AssetLoader.player;
        bg = AssetLoader.bg;
    }

    private void drawEnemyCars() {

        for(EnemyCar b : enemyCars){
            batcher.draw(b.getTextureRegion(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
        }
    }

    private void drawPlayerCentered(float runTime) {
        batcher.draw(playerAsset, 59, player.getY(),
                player.getWidth() / 2.0f, player.getHeight() / 2.0f,
                player.getWidth(), player.getHeight(), 1, 1, player.getRotation());
    }

    private void drawPlayer(float runTime) {
        batcher.draw(playerAsset, player.getX(), player.getY(),
                player.getWidth() / 2.0f, player.getHeight() / 2.0f,
                player.getWidth(), player.getHeight(), 1, 1, player.getRotation());
    }

    private void drawParticleEffects(float delta){
        ArrayList<ParticleEffect> effects = (ArrayList<ParticleEffect>)particleEffects.clone();
        for(ParticleEffect particleEffect : effects){
            particleEffect.draw(batcher, delta);

            if(particleEffect.isComplete())
                particleEffects.remove(particleEffect);
        }

    }

    private void drawHiddenObjects(float delta){
        ArrayList<HiddenObject> objects = (ArrayList<HiddenObject>)hiddenObjects.clone();
        for(HiddenObject o : objects){
            o.update(delta);
            if(o.getIsVisible())
                batcher.draw(o.getTextureRegion(), o.getX(), o.getY(), o.getWidth(),o.getHeight());

            if(o.canBeRemoved()){
                Gdx.app.log("remove", "fruit, y = " + o.getY());
                hiddenObjects.remove(o);
            }

        }
    }

    private void updateRumble(float delta){
        if(rumble.time > 0)
            rumble.tick(delta, this);
    }


    public void render(float delta, float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        batcher.setProjectionMatrix(cam.combined);
        batcher.begin();
        batcher.disableBlending();

        batcher.draw(bg, 0, 0, 136, 300);

        batcher.enableBlending();
        drawEnemyCars();
        drawHiddenObjects(delta);
        drawParticleEffects(delta);
        updateRumble(delta);
        drawPlayerCentered(runTime);

        batcher.end();
    }
}
