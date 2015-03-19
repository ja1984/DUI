package com.jatjsb.cargame.helpers;

import com.jatjsb.cargame.gameobjects.cartypes.BaseCar;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by knepe on 2015-02-25.
 */
public class Utils {
    private static ArrayList<Class<? extends BaseCar>> baseCarSubTypes;

    public static float getRandomNumberBetween(float min, float max){
        Random random = new Random();
        return (random.nextFloat() * (max-min)) + min;
    }

    public static int getRandomIntBetween(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    private static void loadBaseCarSubTypes(){
        Reflections reflections = new Reflections( "com.jatjsb.cargame.gameobjects.cartypes", new SubTypesScanner(false), new TypeAnnotationsScanner());

        Set<Class<? extends BaseCar>> subTypes = reflections.getSubTypesOf(com.jatjsb.cargame.gameobjects.cartypes.BaseCar.class);
        baseCarSubTypes = new ArrayList<Class<? extends BaseCar>>(subTypes);
    }

    public static BaseCar getRandomCarType(){
        if(baseCarSubTypes == null || baseCarSubTypes.size() == 0)
            loadBaseCarSubTypes();

        int random = Utils.getRandomIntBetween(0, baseCarSubTypes.size());
        Class baseCarClass = (Class) baseCarSubTypes.get(random);
        BaseCar baseCar = null;
        try {
            baseCar = (BaseCar)baseCarClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return baseCar;
    }
}
