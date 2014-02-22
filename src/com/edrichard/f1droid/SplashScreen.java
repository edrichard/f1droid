package com.edrichard.f1droid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Class splash screen, activity start of application.
 * @author edrichard.
 */
public class SplashScreen extends Activity {

    /** Time of. */
    private static final int TIME = 5;
    /** Duration of display the splash screen. */
    private static final int DURATION = 1000;

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME * DURATION);
    }

    @Override
    protected final void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}
