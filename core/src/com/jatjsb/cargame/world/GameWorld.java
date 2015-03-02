package com.jatjsb.cargame.world;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.jatjsb.cargame.gameobjects.Player;
import com.jatjsb.cargame.gameobjects.ScrollHandler;
import com.jatjsb.cargame.helpers.AssetLoader;

/**
 * Created by knepe on 2015-02-25.
 */
public class GameWorld {
    private Player player;
    private ScrollHandler scroller;
    private Rectangle ground;
    private int score = 0;
    private float runTime = 0;
    private int midPointY;
    private GameRenderer renderer;

    private GameState currentState;

    public enum GameState {
        MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public GameWorld(int midPointY) {
        currentState = GameState.RUNNING;
        this.midPointY = midPointY;
        player = new Player(65, 120, 4, 6);
        // The sand should start 66 pixels below the midPointY
        scroller = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 137, 11);
    }

    public void update(float delta) {
        runTime += delta;

        switch (currentState) {
            case READY:
            case MENU:
                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }

    }

    private void updateReady(float delta) {
        player.updateReady(runTime);
        scroller.updateReady(delta);
    }

    public void updateRunning(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }

        player.update(delta);

        scroller.update(delta);
    }

    public Player getPlayer() {
        return player;

    }

    public int getMidPointY() {
        return midPointY;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void ready() {
        currentState = GameState.READY;
    }

    public void restart() {
        score = 0;
        scroller.onRestart();
        ready();
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    public void setRenderer(GameRenderer renderer) {
        this.renderer = renderer;
    }

    public GameRenderer getRenderer(){
        return this.renderer;
    }

}
