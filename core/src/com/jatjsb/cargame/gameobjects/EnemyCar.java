package com.jatjsb.cargame.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.TimeUtils;
import com.jatjsb.cargame.helpers.AssetLoader;
import com.jatjsb.cargame.world.GameWorld;

import sun.rmi.runtime.Log;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.addAction;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;

/**
 * Created by knepe on 2015-02-25.
 */
public class EnemyCar extends Actor {

    private Rectangle bounds = new Rectangle();
    private Boolean isOncoming;
    private long lastCarTime = 0;
    private int lane;

    public EnemyCar(float x, float y, Boolean isOncoming, int lane) {
        this.isOncoming = isOncoming;
        this.lane = lane;
        setWidth(19);
        setHeight(30);
        Gdx.app.log("Height", "" + getHeight());
        setPosition(y, 835);

        if(!isOncoming)
            setRotation(180);

        //int rnd = MathUtils.random(0, 3);
        //if (rnd == 0)
        //    setColor(Color.RED);
        //if (rnd == 1)
        //    setColor(Color.GREEN);
        //if (rnd == 2)
        //    setColor(Color.WHITE);
        //if (rnd == 3)
        //    setColor(Color.BLUE);

        addAction(moveTo(getX(), -30, (isOncoming ? MathUtils.random(4.0f, 6.0f) : MathUtils.random(8.0f, 10.0f))));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //changeLane();
        updateBounds();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);
        batch.draw(AssetLoader.enemyCar, getX(), getY(), getWidth() / 2, getHeight() / 2, getWidth(), getHeight(), 1, 1, getRotation());
    }

    private void changeLane(){
        if(!shouldChangeLane()) return;

        if(isOncoming){
            //setPosition(lane == 0 ? GameWorld.lane1 : GameWorld.lane0,getY());
            addAction(moveTo(lane == 0 ? GameWorld.lane1 : GameWorld.lane0,(getY()-100),0.3f));
            lane = (lane == 0 ? 1 : 0);
            return;
        }

        //setPosition(lane == 2 ? GameWorld.lane3 : GameWorld.lane2,getY());
        addAction(moveTo(lane == 2 ? GameWorld.lane3 : GameWorld.lane2,(getY() - 100),0.3f));
        lane = (lane == 2 ? 3 : 2);
    }

    private Boolean timeForAction(){
        return (TimeUtils.nanoTime() - lastCarTime > (3000000000f));
    }

    private Boolean shouldChangeLane(){

        if(timeForAction()) {
            //Gdx.app.log("Should change","Yes");
            lastCarTime = TimeUtils.nanoTime();
            return MathUtils.random(0, 10) < 9;
        }

        return false;
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