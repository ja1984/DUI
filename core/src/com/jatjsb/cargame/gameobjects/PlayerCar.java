package com.jatjsb.cargame.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jatjsb.cargame.helpers.AssetLoader;
import com.jatjsb.cargame.world.GameWorld;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

/**
 * Created by jonathan on 2015-03-03.
 */
public class PlayerCar extends Actor {
    private GameWorld trafficGame;
    private Rectangle bounds = new Rectangle();
    private Rectangle safetyBounds = new Rectangle();
    private int lane;

    public PlayerCar(GameWorld trafficGame) {
        this.trafficGame = trafficGame;
        setWidth(18);
        setHeight(24);
        lane = 2;
        setPosition(trafficGame.lane2,150);
        setColor(Color.YELLOW);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updateBounds();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);
        batch.draw(AssetLoader.playerCar, getX(), getY(), getWidth() / 2, getHeight() / 2,
                getWidth(), getHeight(), 1, 1, getRotation());
    }

    private void updateBounds() {
        bounds.set(getX(), getY(), getWidth(), getHeight());
    }

    private void updateSafetyBounds() {
        safetyBounds.set(getX(), getY(), getWidth(), (getHeight() + 10));
    }

    public void tryMoveUp() {
        if ((getActions().size == 0) && (lane != 2))
            moveToLane(lane + 1);
    }

    public void tryMoveDown() {
        if ((getActions().size == 0) && (lane != 0))
            moveToLane(lane - 1);
    }

    private void moveToLane(int lane) {
        this.lane = lane;

        switch (lane) {
            case 0:
                addAction(moveTo(getX(), trafficGame.lane0 - getHeight() / 2, 0.5f));
                break;
            case 1:
                addAction(moveTo(getX(), trafficGame.lane1 - getHeight() / 2, 0.5f));
                break;
            case 2:
                addAction(moveTo(getX(), trafficGame.lane2 - getHeight() / 2, 0.5f));
                break;
        }
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Rectangle getSafetyBounds() {
        return safetyBounds;
    }
}
