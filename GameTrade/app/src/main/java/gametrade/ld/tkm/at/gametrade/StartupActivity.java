package gametrade.ld.tkm.at.gametrade;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent intent = null;

                if (isUserLoggedIn()) {
                    // show overview / main view
                } else {
                    // show login flow
                    intent = new Intent(StartupActivity.this, LoginActivity.class);
                }

                startActivity(intent);
            }

        }, 1500L);

    }

    private boolean isUserLoggedIn() {

        /**
         * return firebase auth user != null
         */

        return false; //Default for open login flow
    }
}
