package com.jatjsb.cargame.gameobjects.cartypes;

import com.jatjsb.cargame.helpers.AssetLoader;

public class RedCar extends BaseCar {
    public RedCar() {
        super(AssetLoader.redCar, AssetLoader.redCarFlipped, 35, 35);
    }
}
