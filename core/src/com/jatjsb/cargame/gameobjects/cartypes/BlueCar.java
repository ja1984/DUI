package com.jatjsb.cargame.gameobjects.cartypes;

import com.jatjsb.cargame.helpers.AssetLoader;

public class BlueCar extends BaseCar {
    public BlueCar() {
        super(AssetLoader.blueCar, AssetLoader.blueCarFlipped, 35, 35);
    }
}
