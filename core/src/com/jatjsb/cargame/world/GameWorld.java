package com.jatjsb.cargame.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.jatjsb.cargame.gameobjects.EnemyCar;
import com.jatjsb.cargame.gameobjects.Lines;
import com.jatjsb.cargame.gameobjects.PlayerCar;
import com.jatjsb.cargame.gameobjects.Road;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by jonathan on 2015-03-03.
 */
public class GameWorld extends Table {
    private Road road;
    private Lines lines;
    private Array<EnemyCar> enemyCars;
    private long lastCarTime = 0;
    public static Vector2 lane0 = new Vector2(800,480);
    public static Vector2 lane0_end = new Vector2(0,80);

   public static Vector2 lane1 = new Vector2(800,450);
   public static Vector2 lane1_end = new Vector2(0,50);

    public static Vector2 lane2 = new Vector2(800,420);
   public static Vector2 lane2_end = new Vector2(0,20);

   public static Vector2 lane3 = new Vector2(800,390);
   public static Vector2 lane3_end = new Vector2(0,-10);

    public PlayerCar playerCar;
    private Random random;

    public GameWorld() {
        setBounds(0, 0, 840, 450);
        setClip(false);
        Gdx.app.log("", "Width:" + getWidth() + " Height: " + getHeight());
        road = new Road(getWidth(), getHeight());
        lines = new Lines(getWidth(), getHeight());
        addActor(road);
        //addActor(lines);
        playerCar = new PlayerCar(this);
        addActor(playerCar);

        random = new Random();
        enemyCars = new Array<EnemyCar>();
        Gdx.input.setInputProcessor(new InputAdapter() {
            public boolean touchDown (int x, int y, int pointer, int button) {
                // your touch down code here
                return true; // return true to indicate the event was handled
            }

            public boolean touchUp (int x, int y, int pointer, int button) {
                playerCar.touch();
                return true; // return true to indicate the event was handled
            }
        });
    }

    private void spawnCar() {

        Boolean isIncoming = random.nextBoolean();
        Vector2 carLane = new Vector2();
        Vector2 carEnd = new Vector2();
        int lane = MathUtils.random(isIncoming ? 0 : 2, isIncoming ? 1 : 3);
        if (lane == 0) {
            carLane = lane0;
            carEnd = lane0_end;
        }
        if (lane == 1) {
            carLane = lane1;
            carEnd = lane1_end;
        }
        if (lane == 2) {
            carLane = lane2;
            carEnd = lane2_end;
        }

        if (lane == 3) {
            carLane = lane3;
            carEnd = lane3_end;
        }

        EnemyCar enemyCar = new EnemyCar(carLane, carEnd,isIncoming, (int)lane);
        enemyCars.add(enemyCar);
        addActor(enemyCar);
        lastCarTime = TimeUtils.nanoTime();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(Color.WHITE);
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta){
        super.act(delta);

        if (TimeUtils.nanoTime() - lastCarTime > (1000000000f))
            spawnCar();

        Iterator<EnemyCar> iter = enemyCars.iterator();
        while (iter.hasNext()) {
            EnemyCar enemyCar = iter.next();
            //if (enemyCar.getBounds().x + enemyCar.getWidth() <= 0) {
            //    iter.remove();
            //    removeActor(enemyCar);
            //}
            if (Intersector.overlapConvexPolygons(enemyCar.getBounds(), playerCar.getBounds())) {
                Gdx.app.log("Craash","Crash");
                iter.remove();
                if (enemyCar.getX() > playerCar.getX()) {
                    if (enemyCar.getY() > playerCar.getY())
                        enemyCar.crash(true, true);
                    else
                        enemyCar.crash(true, false);
                } else {
                    if (enemyCar.getY() > playerCar.getY())
                        enemyCar.crash(false, true);
                    else
                        enemyCar.crash(false, false);
                }
            }
        }
    }

}
