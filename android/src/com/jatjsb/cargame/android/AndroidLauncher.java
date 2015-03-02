package com.jatjsb.cargame.android;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.games.Games;
import com.jatjsb.cargame.CarGame;
import com.jatjsb.cargame.interfaces.EmptyHandleAds;
import com.jatjsb.cargame.interfaces.EmptyHandleGooglePlay;
import com.jatjsb.cargame.interfaces.IHandleAds;
import com.jatjsb.cargame.interfaces.IHandleGooglePlay;

public class AndroidLauncher extends AndroidApplication implements IHandleAds {
    private final String TAG = "FLAPPYFISH";
    final public static int RC_ACHIEVMENTS = 5002;
    final public static int RC_LEADER_BOARD = 5001;

    //admob
    protected AdView adView;
    private final int SHOW_ADS = 1;
    private final int HIDE_ADS = 0;

    protected Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case SHOW_ADS:
                {
                    adView.setVisibility(View.VISIBLE);
                    break;
                }
                case HIDE_ADS:
                {
                    adView.setVisibility(View.GONE);
                    break;
                }
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useWakelock = true;

        // Create the layout
        RelativeLayout layout = new RelativeLayout(this);

        // Do the stuff that initialize() would do for you
        // Create the libgdx View
        View gameView = initializeForView(new BalloonPopper(new EmptyHandleGooglePlay(), this), config);

        // Create and setup the AdMob view
        adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(getString(R.string.admobId));
        adView.setVisibility(View.VISIBLE);
        adView.setBackgroundColor(Color.TRANSPARENT);

        adView.loadAd(new AdRequest.Builder().build());

        // Add the libgdx view
        layout.addView(gameView);

        // Add the AdMob view
        RelativeLayout.LayoutParams adParams =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        adParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        layout.addView(adView, adParams);

        // Hook it all up
        setContentView(layout);
    }

    @Override
    public void showAds(boolean show) {
        handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);
    }
}
