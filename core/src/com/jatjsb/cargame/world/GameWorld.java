package com.jatjsb.cargame.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
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
    public static final float lane3 = 345;
    public static final float lane2 = 270;
    public static final float lane1 = 190;
    public static final float lane0 = 120;
    public PlayerCar playerCar;
    private Random random;

    public GameWorld() {
        setBounds(0, 0, 480, 800);
        setClip(true);
        road = new Road(getWidth(), getHeight());
        lines = new Lines(getWidth(), getHeight());
        addActor(road);
        addActor(lines);
        playerCar = new PlayerCar(this);
        addActor(playerCar);

        random = new Random();
        enemyCars = new Array<EnemyCar>();
    }

    private void spawnCar() {

        Boolean isIncoming = random.nextBoolean();

        int lane = MathUtils.random(isIncoming ? 0 : 2, isIncoming ? 1 : 3);
        float yPos = 0;
        if (lane == 0)
            yPos = lane0;
        if (lane == 1)
            yPos = lane1;
        if (lane == 2)
            yPos = lane2;
        if (lane == 3)
            yPos = lane3;
        EnemyCar enemyCar = new EnemyCar(getHeight(), yPos, isIncoming, (int)yPos);
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
            if (enemyCar.getBounds().x + enemyCar.getWidth() <= 0) {
                iter.remove();
                removeActor(enemyCar);
            }
            if (enemyCar.getBounds().overlaps(playerCar.getBounds())) {
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
