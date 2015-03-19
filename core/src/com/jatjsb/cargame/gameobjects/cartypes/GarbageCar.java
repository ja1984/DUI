package com.jatjsb.cargame.gameobjects.cartypes;

import com.jatjsb.cargame.helpers.AssetLoader;

/**
 * Created by knepe on 2015-03-19.
 */
public class GarbageCar extends BaseCar {
    public GarbageCar() {
        super(AssetLoader.garbageCar, AssetLoader.garbageCarFlipped, 35, 35);
    }
}
