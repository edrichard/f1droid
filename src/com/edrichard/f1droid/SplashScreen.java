package com.edrichard.f1droid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

/**
 * Class splash screen, activity start of application.
 * @author edrichard.
 */
public class SplashScreen extends Activity {
    /** Object pressBar. */
    private ProgressBar progressBar;
    /** Initialisation progress bar = 0.*/
    private static int progressbarstatut = 0;
    /** Init progress bar = 1.*/
    private static int initProgressBarStatus = 1;
    /** Time of. */
    private static final int TIME = 5;
    /** Duration of display the splash screen. */
    private static final int DURATION = 1000;
    /** Ratio. */
    private static final int RATIO = 100;
    /** New object handler for progress bar. */
    private Handler handler = new Handler();

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.splash_screen);

        progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (progressbarstatut < RATIO) {
                    progressbarstatut += initProgressBarStatus;
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressbarstatut);
                        }
                    });
                    try {
                        Thread.sleep((TIME * DURATION) / RATIO);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

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
