package com.jatjsb.cargame.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jatjsb.cargame.helpers.AssetLoader;
import com.jatjsb.cargame.world.GameRenderer;

public class HiddenObject {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    public GameRenderer renderer;
    private Rectangle bounds;
    private boolean isPressed;
    private boolean canBeRemoved;

    private int width;
    private float height;

    private TextureRegion textureRegion;
    private boolean isVisible = false;

    public HiddenObject(float x, float y, float scale, TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
        this.width = (int)(textureRegion.getRegionWidth() / scale);
        this.height = textureRegion.getRegionHeight() / scale;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 120);
        bounds = new Rectangle();
    }

    public void setRenderer(GameRenderer renderer){
        this.renderer = renderer;
    }

    public boolean canBeRemoved(){
        return this.canBeRemoved;
    }

    public void setPosition(float x, float y){
        this.position.set(x, y);
    }

    public void setVisible(boolean visible){
        this.isVisible = visible;
        velocity.y = -90;
    }

    public boolean getIsVisible(){
        return this.isVisible && this.position.y > -50;
    }

    public void update(float delta) {

        velocity.add(acceleration.cpy().scl(delta));

        if (velocity.y > 120) {
            velocity.y = 120;
        }

        position.add(velocity.cpy().scl(delta));


        bounds.set(position.x, position.y, width, height);

        if(this.isVisible && position.y > 300)
            this.canBeRemoved = true;
    }

    public boolean touchDown(int screenX, int screenY){
        if(bounds.contains(screenX, screenY)){
            isPressed = true;
        }

        return isPressed;
    }

    public boolean touchUp(int screenX, int screenY){
        if(bounds.contains(screenX, screenY) && isPressed){
            //play some sound?
            setVisible(false);
            canBeRemoved = true;
            onClickedEffect();
            return true;
        }

        isPressed = false;

        return false;
    }

    public void onClickedEffect(){

    }



    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
