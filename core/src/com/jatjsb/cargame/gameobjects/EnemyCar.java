package com.jatjsb.cargame.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.TimeUtils;
import com.jatjsb.cargame.gameobjects.cartypes.BaseCar;
import com.jatjsb.cargame.helpers.AssetLoader;
import com.jatjsb.cargame.helpers.Utils;
import com.jatjsb.cargame.world.GameWorld;

import sun.rmi.runtime.Log;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.addAction;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;

/**
 * Created by knepe on 2015-02-25.
 */
public class EnemyCar extends Actor {
    private BaseCar carType;
    private Rectangle bounds = new Rectangle();
    private Polygon polygon;
    private Boolean isOncoming;
    private long lastCarTime = 0;
    private int lane;
    private ShapeRenderer sr = new ShapeRenderer();
    private ParticleEffect particleEffect;
    private boolean explode = false;

    public EnemyCar(Vector2 position,Vector2 endPosition, Boolean isOncoming, int lane) {
        this.isOncoming = isOncoming;
        this.lane = lane;
        this.carType = Utils.getRandomCarType();
        setWidth(this.carType.getWidth());
        setHeight(this.carType.getHeight());
        this.setZIndex(lane);
        setPosition(position.x,position.y);
        this.particleEffect = AssetLoader.getParticleEffect();

        // an array where every even element represents the horizontal part of a point,
        // and the following element representing the vertical part
        polygon = new Polygon(new float[]{         // Four vertices
                0,0,                               // Vertex 0         3--2
                getWidth(),0,                     // Vertex 1         | /|
                getWidth(),getHeight(),            // Vertex 2         |/ |
                0,getHeight()                       // Vertex 3         0--1
        });

        polygon.setOrigin(getWidth()/2, getHeight()/2);
        polygon.setRotation(30f);
        addAction(moveTo(endPosition.x, endPosition.y, (isOncoming ? MathUtils.random(4.0f, 6.0f) : MathUtils.random(8.0f, 10.0f))));
    }

    private void explode(){
        explode = true;
        particleEffect.start();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //changeLane();
        updateBounds();
        particleEffect.setPosition(getX(), getY());
        if(getX() <= (lane * 30))
            crash(false, false, false);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);
        batch.draw(isOncoming ? this.carType.getFlippedTextureRegion() :  this.carType.getTextureRegion(), getX(), getY(), getWidth() / 2, getHeight() / 2, getWidth(), getHeight(), 1, 1, 0);
        if(explode)
            particleEffect.draw(batch, Gdx.graphics.getDeltaTime());
        if(GameWorld.debug)
        {
            batch.end();
            sr.setColor(Color.RED);
            sr.begin(ShapeRenderer.ShapeType.Line);
            sr.polygon(polygon.getTransformedVertices());
            sr.end();
            batch.begin();
        }
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

    public void crash(boolean front, boolean above, boolean explode) {
        clearActions();
        if(explode)
            explode();
        addAction(fadeOut(0.2f));
        removeActor();
    }

    //public Rectangle getBounds() {
    //    return bounds;
    //}

    public Polygon getBounds(){
        return polygon;
    }
}