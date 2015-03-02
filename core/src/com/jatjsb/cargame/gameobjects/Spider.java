package com.jatjsb.cargame.gameobjects;

import com.jatjsb.cargame.helpers.AssetLoader;

public class Spider extends HiddenObject {
    public Spider(float x, float y) {
        super(x, y, 4, AssetLoader.spider);
    }

    @Override
    public void onClickedEffect() {
        this.renderer.shakeScreen();
    }
}
