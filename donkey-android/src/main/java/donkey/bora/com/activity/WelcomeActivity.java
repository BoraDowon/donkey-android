package donkey.bora.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import donkey.bora.com.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.welcome_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.auth_bt)
    void onStartClick() {
        startActivity(new Intent(this, EmailAuthActivity.class));
    }

    @OnClick(R.id.just_look_bt)
    void onJustLook() {
        startActivity(new Intent(this, BoardMainActivity.class));
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
