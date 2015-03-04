package com.jatjsb.cargame.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jatjsb.cargame.helpers.AssetLoader;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Created by knepe on 2015-02-25.
 */
public class EnemyCar extends Actor {

    private Rectangle bounds = new Rectangle();
    private Boolean isOncoming;

    public EnemyCar(float x, float y, Boolean isOncoming) {
        this.isOncoming = isOncoming;
        setWidth(160);
        setHeight(85);
        setPosition(x, y - getHeight() / 2);

        int rnd = MathUtils.random(0, 3);
        if (rnd == 0)
            setColor(Color.RED);
        if (rnd == 1)
            setColor(Color.GREEN);
        if (rnd == 2)
            setColor(Color.WHITE);
        if (rnd == 3)
            setColor(Color.BLUE);

        addAction(moveTo(-getWidth(), getY(), MathUtils.random(4.0f, 6.0f)));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updateBounds();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);
        batch.draw(AssetLoader.enemyCar, getX(), getY(), getWidth() / 2, getHeight() / 2, getWidth(), getHeight(), 1, 1, getRotation());
    }

    private void updateBounds() {
        bounds.set(getX(), getY(), getWidth(), getHeight());
    }

    public void crash(boolean front, boolean above) {
        clearActions();
        addAction(fadeOut(1f));
        removeActor();
    }

    public Rectangle getBounds() {
        return bounds;
    }
}