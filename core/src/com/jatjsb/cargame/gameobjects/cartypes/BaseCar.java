package com.jatjsb.cargame.gameobjects.cartypes;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by knepe on 2015-03-19.
 */
public class BaseCar {
    TextureRegion textureRegion;

    public BaseCar(TextureRegion textureRegion, TextureRegion flippedTextureRegion, int width, int height) {
        this.textureRegion = textureRegion;
        this.flippedTextureRegion = flippedTextureRegion;
        this.width = width;
        this.height = height;
    }

    TextureRegion flippedTextureRegion;
    int width;
    int height;


    public TextureRegion getTextureRegion(){
        return this.textureRegion;
    }

    public TextureRegion getFlippedTextureRegion(){
        return this.flippedTextureRegion;
    }

    public int getWidth(){
        return this.textureRegion.getRegionWidth();
    }

    public int getHeight(){
        return this.textureRegion.getRegionHeight();
    }
}
