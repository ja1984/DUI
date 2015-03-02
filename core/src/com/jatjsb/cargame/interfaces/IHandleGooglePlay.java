package com.jatjsb.cargame.interfaces;

/**
 * Created by knepe on 2015-02-25.
 */
public interface IHandleGooglePlay {
    public void login();
    public void logout();
    public boolean getSignedIn();
    public void submitScore(int score);
    public void showLeaderboard();
    public void unlockAchievement(java.lang.String id);
    public void showAchievements();
}
