package com.jatjsb.cargame.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.jatjsb.cargame.CarGame;
import com.jatjsb.cargame.gameobjects.EnemyCar;
import com.jatjsb.cargame.gameobjects.HiddenObject;
import com.jatjsb.cargame.gameobjects.ScrollHandler;
import com.jatjsb.cargame.world.GameWorld;

import java.util.ArrayList;

/**
 * Created by knepe on 2015-02-25.
 */
public class InputHandler implements InputProcessor {
    private ArrayList<EnemyCar> enemyCars;
    private GameWorld myWorld;
    private CarGame game;
    private ScrollHandler scroller;
    EnemyCar currentPressedEnemyCar = null;
    private float scaleFactorX;
    private float scaleFactorY;

    public InputHandler(GameWorld myWorld, CarGame game, float scaleFactorX,
                        float scaleFactorY) {
        this.myWorld = myWorld;
        this.scroller = myWorld.getScroller();
        this.enemyCars = scroller.getEnemyCars();
        this.game = game;

        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if (myWorld.isRunning()) {

            for(EnemyCar enemyCar : enemyCars){
                if(currentPressedEnemyCar == null && enemyCar.touchDown(screenX, screenY)){
                    currentPressedEnemyCar = enemyCar;
                }
            }

            currentPressedEnemyCar = null;

            for(HiddenObject o : myWorld.getRenderer().hiddenObjects){
                o.touchDown(screenX, screenY);
            }
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if (myWorld.isRunning()) {
            for(EnemyCar enemyCar : enemyCars){
                if(enemyCar.touchUp(screenX, screenY) && currentPressedEnemyCar != null && enemyCar.getBalloon().equals(currentPressedEnemyCar.getBalloon())){
                    currentPressedEnemyCar = null;
                }
            }

            currentPressedEnemyCar = null;

            for(HiddenObject o : myWorld.getRenderer().hiddenObjects){
                o.touchUp(screenX, screenY);
            }
        }

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {

        // Can now use Space Bar to play the game
        if (keycode == Input.Keys.SPACE) {

            if (myWorld.isMenu()) {
                myWorld.ready();
            } else if (myWorld.isReady()) {
                myWorld.start();
            }

            if (myWorld.isGameOver() || myWorld.isHighScore()) {
                myWorld.restart();
            }

        }
        if(keycode == Input.Keys.BACK && (myWorld.isMenu() || myWorld.isReady()))
            Gdx.app.exit();

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }
}
