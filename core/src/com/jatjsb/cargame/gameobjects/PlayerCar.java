package com.jatjsb.cargame.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
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
    private Polygon polygon;
    private int lane;
    private Vector2 vector2;
    Boolean touched = false;
    private ShapeRenderer sr = new ShapeRenderer();
    private ParticleEffect sparksParticleEffect;


    public PlayerCar(GameWorld trafficGame) {
        this.trafficGame = trafficGame;
        setWidth(32);
        setHeight(27);
        lane = 2;
        setPosition(215, 100);
        setColor(Color.YELLOW);
        bounds = new Rectangle(0,0, getWidth(), getHeight());
        vector2 = new Vector2(25f, 25f);

        polygon = new Polygon(new float[]{0,0,getWidth(),0,getWidth(),getHeight(),0,getHeight()});
        polygon.setOrigin(getWidth()/2, getHeight()/2);
        polygon.setRotation(30f);
        sparksParticleEffect = AssetLoader.sparksParticleEffect;
        sparksParticleEffect.start();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updateBounds();
        if(touched)
        {
            if(getX() < 244)
            setPosition(getX() + (vector2.x * delta), getY() - (vector2.y * delta));
        }else{
            if(getX() > 135)
                setPosition(getX() - (vector2.x * delta), getY() + (vector2.y * delta));
        }
        sparksParticleEffect.setPosition(getX() + 5, getY() +5);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sparksParticleEffect.draw(batch, Gdx.graphics.getDeltaTime());
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);
        batch.draw(AssetLoader.playerCar, getX(), getY(), getWidth() / 2, getHeight() / 2, getWidth(), getHeight(), 1, 1, 0);

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

    private void updateBounds() {
        bounds.set(getX(), getY(), getWidth(), getHeight());
        polygon.setPosition(getX(), getY());
    }

    public Polygon getBounds() {
        return polygon;
    }

    public void release(){
        touched = false;
    }

    public void touch() {
        touched = true;
    }

}
