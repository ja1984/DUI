package com.jatjsb.cargame.gameobjects;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.jatjsb.cargame.helpers.AssetLoader;

public class Fruit extends HiddenObject {
    public ParticleEffect particleEffect;

    public Fruit(float x, float y) {
        super(x, y, 4, AssetLoader.getRandomFruitTextureRegion());
        this.particleEffect = AssetLoader.getParticleEffect();
    }

    private void addParticleEffect(){
        particleEffect.setPosition(getX() + (getWidth() / 2), getY() + (getHeight() / 2));
        renderer.particleEffects.add(particleEffect);
        particleEffect.start();
    }

    @Override
    public void onClickedEffect() {
        AssetLoader.pop.play();
        addParticleEffect();
    }
}
