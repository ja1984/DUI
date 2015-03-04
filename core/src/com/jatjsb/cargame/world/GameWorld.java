package com.jatjsb.cargame.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.jatjsb.cargame.gameobjects.EnemyCar;
import com.jatjsb.cargame.gameobjects.InfiniteScrollBg;
import com.jatjsb.cargame.gameobjects.PlayerCar;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by jonathan on 2015-03-03.
 */
public class GameWorld extends Table {
    private InfiniteScrollBg backgroundRoad;
    private Array<EnemyCar> enemyCars;
    private long lastCarTime = 0;
    public final float lane2 = 390;
    public final float lane1 = 240;
    public final float lane0 = 90;
    public PlayerCar playerCar;
    private Random random;

    public GameWorld() {
        setBounds(0, 0, 800, 480);
        setClip(true);
        backgroundRoad = new InfiniteScrollBg(getWidth(), getHeight());
        addActor(backgroundRoad);
        playerCar = new PlayerCar(this);
        addActor(playerCar);
        random = new Random();
        enemyCars = new Array<EnemyCar>();
    }

    private void spawnCar() {
        int lane = MathUtils.random(0, 2);
        float yPos = 0;
        if (lane == 0)
            yPos = lane0;
        if (lane == 1)
            yPos = lane1;
        if (lane == 2)
            yPos = lane2;
        EnemyCar enemyCar = new EnemyCar(getWidth(), yPos, random.nextBoolean());
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

        if (TimeUtils.nanoTime() - lastCarTime > 3000000000f)
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
