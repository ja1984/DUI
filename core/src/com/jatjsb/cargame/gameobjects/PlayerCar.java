package com.jatjsb.cargame.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
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
    int rounds = 0;

    public PlayerCar(GameWorld trafficGame) {
        this.trafficGame = trafficGame;
        setWidth(32);
        setHeight(27);
        lane = 2;
        setPosition(215, 100);
        setColor(Color.YELLOW);
        bounds = new Rectangle(0,0, getWidth(), getHeight());
        vector2 = new Vector2(25f, 25f);
        polygon = new Polygon(new float[]{0,0,getHeight(),0,getHeight(),getWidth(),0,getWidth()});
        polygon.setOrigin(bounds.width/2, bounds.height/2);
        polygon.setRotation(90f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updateBounds();
        if(rounds > 0)
        {
            setPosition(getX() + (vector2.x * delta), getY() - (vector2.y * delta));
            rounds--;
        }else{
            if(getX() > 150)
                setPosition(getX() - (vector2.x * delta), getY() + (vector2.y * delta));
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);
        batch.draw(AssetLoader.playerCar, getX(), getY(), getWidth() / 2, getHeight() / 2, getWidth(), getHeight(), 1, 1, 0);
        batch.draw(AssetLoader.hitbox,polygon.getX(), polygon.getY(), polygon.getBoundingRectangle().width,polygon.getBoundingRectangle().height,getWidth(),getHeight(),1,1,polygon.getRotation());
    }

    private void updateBounds() {
        bounds.set(getX(), getY(), getWidth(), getHeight());
        polygon.setPosition(getX(), getY());
    }

    public Polygon getBounds() {
        return polygon;
    }

    public void touch() {
        rounds += 12;
    }
}
