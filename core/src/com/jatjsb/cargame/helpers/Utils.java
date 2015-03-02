package com.jatjsb.cargame.helpers;

import java.util.Random;

/**
 * Created by knepe on 2015-02-25.
 */
public class Utils {
    public static float getRandomNumberBetween(float min, float max){
        Random random = new Random();
        return (random.nextFloat() * (max-min)) + min;
    }

    public static int getRandomIntBetween(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
