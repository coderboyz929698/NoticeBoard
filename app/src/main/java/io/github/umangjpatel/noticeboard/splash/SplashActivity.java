package io.github.umangjpatel.noticeboard.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.github.umangjpatel.noticeboard.R;
import io.github.umangjpatel.noticeboard.signup.SignUpActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.SplashTheme);
        startActivity(SignUpActivity.newIntent(this));
        finish();
    }
}
