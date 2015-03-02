package com.jatjsb.cargame.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.jatjsb.cargame.world.GameRenderer;

import java.util.Random;

/**
 * Created by knepe on 2015-03-01.
 */
public class Rumble {

    public float time;
    Random random;
    float x, y;
    float current_time;
    float power;
    float current_power;
    float originalX;
    float originalY;

    public Rumble(Camera camera){
        time = 0;
        current_time = 0;
        power = 0;
        current_power = 0;
        originalX = camera.position.x;
        originalY = camera.position.y;
    }

    // Call this function with the force of the shake
    // and how long it should last
    public void rumble(float power, float time) {
        random = new Random();
        this.power = power;
        this.time = time;
        this.current_time = 0;
        Gdx.app.log("RUMBLE", "start rumble");
    }

    public void tick(float delta, GameRenderer gc){
        // GameController contains the camera
        // Hero is the character centre screen

        if(current_time <= time) {
            //Gdx.app.log("Rumble", "rumbling");
            current_power = power * ((time - current_time) / time);
            // generate random new x and y values taking into account
            // how much force was passed in
            x = (random.nextFloat() - 0.5f) * 2 * current_power;
            y = (random.nextFloat() - 0.5f) * 2 * current_power;

            // Set the camera to this new x/y position
            gc.cam.translate(-x, -y);
            Gdx.app.log("RUMBLE", "x: " + gc.cam.position.x);
            current_time += delta;
        } else {
            // When the shaking is over move the camera back to the hero position
            gc.cam.position.x = originalX;
            gc.cam.position.y = originalY;
        }
    }
}
