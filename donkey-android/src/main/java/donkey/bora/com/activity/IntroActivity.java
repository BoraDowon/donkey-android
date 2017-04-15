package donkey.bora.com.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import donkey.bora.com.R;
import donkey.bora.com.controller.IntroController;
import donkey.bora.com.model.InitResponseVO;
import donkey.bora.com.secure.TokenManager;

public class IntroActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView titleTextView;

    private IntroController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.intro_layout);
        ButterKnife.bind(this);
        
        TokenManager.init(getFilesDir().getAbsolutePath() + "/PREF/");
    }

    @Override
    protected void onStart() {
        super.onStart();

        controller = new IntroController();
        controller.requestPreCheck(onAuthorizationCallback);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }

    @Override
    public void onBackPressed() {
        // Nothing. To block back key
    }

    private IntroController.OnAuthorizationCallback onAuthorizationCallback = new IntroController.OnAuthorizationCallback() {
        @Override
        public void callback(final boolean isAuthorizedUser) {
            final boolean isFromAuth = getIntent().getBooleanExtra("FROM_AUTH", false);
            if (isFromAuth) {
                titleTextView.setText("이제 시작합니다!");
            }

            if (isAuthorizedUser) {
                controller.requestInit(onInitCallback);
            } else {
                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivityForResult(new Intent(IntroActivity.this, WelcomeActivity.class), 1);
                    }
                }, 2000);
            }
        }
    };

    private IntroController.OnInitCallback onInitCallback = new IntroController.OnInitCallback() {
        @Override
        public void callback(final InitResponseVO initResponseVO) {
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(IntroActivity.this, BoardMainActivity.class);
                    i.putExtra("init", initResponseVO);

                    startActivityForResult(i, 1);
                }
            }, 1000);
        }
    };
}
