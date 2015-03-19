package com.jatjsb.cargame.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
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
    private Polygon polygon;
    private Boolean isOncoming;
    private long lastCarTime = 0;
    private int lane;

    public EnemyCar(Vector2 position,Vector2 endPosition, Boolean isOncoming, int lane) {
        this.isOncoming = isOncoming;
        this.lane = lane;
        setWidth(35);
        setHeight(33);
        this.setZIndex(lane);
        setPosition(position.x,position.y);
        polygon = new Polygon(new float[]{0,0,getHeight(),0,getHeight(),getWidth(),0,getWidth()});
        polygon.setOrigin(getWidth()/2, getHeight()/2);
        polygon.setRotation(isOncoming ? 45f : -45f);
        addAction(moveTo(endPosition.x, endPosition.y, (isOncoming ? MathUtils.random(4.0f, 6.0f) : MathUtils.random(8.0f, 10.0f))));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //changeLane();
        updateBounds();
        if(getX() <= (lane * 30))
            crash(false, false);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);
        batch.draw(isOncoming ? AssetLoader.oncomingEnemyPlayer :  AssetLoader.enemyCar, getX(), getY(), getWidth() / 2, getHeight() / 2, getWidth(), getHeight(), 1, 1, 0);

        if(GameWorld.debug)
            batch.draw(AssetLoader.hitbox,polygon.getX(), polygon.getY(), polygon.getOriginX(),polygon.getOriginY(),getWidth(),getHeight(),1,1,polygon.getRotation());

    }

    private void changeLane(){
        if(!shouldChangeLane()) return;

        if(isOncoming){
            //setPosition(lane == 0 ? GameWorld.lane1 : GameWorld.lane0,getY());
            //addAction(moveTo(lane == 0 ? GameWorld.lane1 : GameWorld.lane0,(getY()-100),0.3f));
            lane = (lane == 0 ? 1 : 0);
            return;
        }

        //setPosition(lane == 2 ? GameWorld.lane3 : GameWorld.lane2,getY());
        //addAction(moveTo(lane == 2 ? GameWorld.lane3 : GameWorld.lane2,(getY() - 100),0.3f));
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
        polygon.setPosition(getX(), getY());
    }

    public void crash(boolean front, boolean above) {
        clearActions();
        addAction(fadeOut(1f));
        removeActor();
    }

    //public Rectangle getBounds() {
    //    return bounds;
    //}

    public Polygon getBounds(){
        return polygon;
    }
}