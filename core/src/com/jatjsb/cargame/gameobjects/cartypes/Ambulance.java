package com.jatjsb.cargame.gameobjects.cartypes;

import com.jatjsb.cargame.helpers.AssetLoader;

public class Ambulance extends BaseCar {
    public Ambulance() {
        super(AssetLoader.ambulance, AssetLoader.ambulanceFlipped, 35, 35);
    }
}
