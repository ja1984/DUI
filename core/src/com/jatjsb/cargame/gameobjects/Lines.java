package com.jatjsb.cargame.gameobjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jatjsb.cargame.helpers.AssetLoader;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Created by jonathan on 2015-03-03.
 */
public class Lines extends Actor {
    public Lines(float width, float height) {
        setWidth(width);
        setHeight(height);
        setPosition(0, 0);
        //addAction(forever(sequence(moveTo(0, 0, 1f), moveTo(0, height))));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(AssetLoader.lines, getX(), getY(), getWidth(), getHeight());
    }
}
