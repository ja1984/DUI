package com.jatjsb.cargame.gameobjects;

import com.jatjsb.cargame.helpers.Utils;
import com.jatjsb.cargame.world.GameWorld;

import java.util.ArrayList;

public class ScrollHandler {
    private ArrayList<EnemyCar> enemyCars;
    public static final int MAX_SCROLL_SPEED = 60;
    public static final int MIN_SCROLL_SPEED = 30;
    private static final int NUMBER_OF_ENEMY_CARS = 6;
    public static final int MIN_Y = -190;
    public static final int MAX_Y = -250;
    public static final int MIN_X = 30;
    public static final int MAX_X = 100;
    private GameWorld gameWorld;

    public ScrollHandler(GameWorld gameWorld, float yPos) {
        this.gameWorld = gameWorld;
        this.enemyCars = new ArrayList<EnemyCar>();
        for(int i=0;i < NUMBER_OF_ENEMY_CARS;i++){
            enemyCars.add(new EnemyCar(Utils.getRandomIntBetween(MIN_X, MAX_X), Utils.getRandomNumberBetween(MIN_Y, MAX_Y), 5, 8, Utils.getRandomNumberBetween(MIN_SCROLL_SPEED, MAX_SCROLL_SPEED), yPos));
        }
    }

    public void updateReady(float delta) {

    }

    public void update(float delta) {
        // Update our objects
        for(EnemyCar enemyCar : enemyCars){
            enemyCar.update(delta);

            if (enemyCar.isScrolledUp()) {
                enemyCar.reset(Utils.getRandomIntBetween(MIN_X, MAX_X), Utils.getRandomNumberBetween(MIN_Y, MAX_Y));
            }
        }
    }

    public void stop() {
        for(EnemyCar enemyCar : enemyCars){
            enemyCar.stop();
        }
    }

    private void addScore(int increment) {
        gameWorld.addScore(increment);
    }

    public void onRestart() {
        for(EnemyCar enemyCar : enemyCars){
            enemyCar.onRestart(Utils.getRandomNumberBetween(MIN_X, MAX_X), Utils.getRandomNumberBetween(MIN_Y, MAX_Y), Utils.getRandomNumberBetween(MIN_SCROLL_SPEED, MAX_SCROLL_SPEED));
        }
    }

    public ArrayList<EnemyCar> getEnemyCars() {
        return enemyCars;
    }
}
