package com.jatjsb.cargame.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.jatjsb.cargame.helpers.AssetLoader;
import com.jatjsb.cargame.helpers.Utils;
import com.jatjsb.cargame.world.GameRenderer;

import java.util.Random;

/**
 * Created by knepe on 2015-02-25.
 */
public class EnemyCar extends Scrollable {

    private Random r;

    private Rectangle balloon;
    private boolean isPressed;
    public ParticleEffect particleEffect;
    public GameRenderer renderer;
    private TextureRegion textureRegion;
    public HiddenObject hiddenObject;

    // When Pipe's constructor is invoked, invoke the super (Scrollable)
    // constructor
    public EnemyCar(float x, float y, int width, int height, float scrollSpeed,
                    float groundY) {
        super(x, y, width, height, scrollSpeed);
        // Initialize a Random object for Random number generation
        r = new Random();
        balloon = new Rectangle();
        this.particleEffect = AssetLoader.getParticleEffect();
        this.textureRegion = AssetLoader.car;
        createHiddenObject();

    }

    @Override
    public void update(float delta) {
        // Call the update method in the superclass (Scrollable)
        super.update(delta);

        // The set() method allows you to set the top left corner's x, y
        // coordinates,
        // along with the width and height of the rectangle
        balloon.set(position.x, position.y, width, height);

        if(this.hiddenObject != null && !this.hiddenObject.getIsVisible())
        {
            this.hiddenObject.setPosition(position.x, position.y);
            this.hiddenObject.update(delta);
        }
    }

    @Override
    public void reset(float newX, float newY) {
        // Call the reset method in the superclass (Scrollable)
        super.reset(newX, newY);

        isPressed = false;
        this.textureRegion = AssetLoader.car;
    }

    private void createHiddenObject(){
        if(Utils.getRandomIntBetween(1, 25) < 10){
            hiddenObject = new Fruit(position.x, position.y);
        }
        else if(Utils.getRandomIntBetween(25, 50) < 30){
            hiddenObject = new Spider(position.x, position.y);
        }
        else{
            hiddenObject = null;
        }
    }

    public void onRestart(float x, float y, float scrollSpeed) {
        createHiddenObject();
        velocity.y = scrollSpeed;
        reset(x, y);
        isPressed = false;
    }

    public Rectangle getBalloon() {
        return balloon;
    }

    public boolean touchDown(int screenX, int screenY){
        if(balloon.contains(screenX, screenY)){
            isPressed = true;
        }

        return isPressed;
    }

    public boolean touchUp(int screenX, int screenY){
        if(balloon.contains(screenX, screenY) && isPressed){
            if(this.hiddenObject != null){
                this.hiddenObject.setRenderer(this.renderer);
                this.hiddenObject.setVisible(true);
                renderer.hiddenObjects.add(this.hiddenObject);
            }

            AssetLoader.pop.play();
            onRestart(Utils.getRandomNumberBetween(ScrollHandler.MIN_X, ScrollHandler.MAX_X), Utils.getRandomNumberBetween(ScrollHandler.MIN_Y, ScrollHandler.MAX_Y), Utils.getRandomNumberBetween(ScrollHandler.MIN_SCROLL_SPEED, ScrollHandler.MAX_SCROLL_SPEED));
            return true;
        }

        isPressed = false;

        return false;
    }

    public void setRenderer(GameRenderer renderer) {
        this.renderer = renderer;
    }

    public TextureRegion getTextureRegion(){
        return textureRegion;
    }
}