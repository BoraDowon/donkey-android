package donkey.bora.com.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import donkey.bora.com.R;
import donkey.bora.com.controller.IntroController;

public class IntroActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.intro_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntroController controller = new IntroController();
        controller.requestHello(onHelloCallback);
    }

    @Override
    protected void onResume() {
        super.onResume();


        final boolean isFromAuth = getIntent().getBooleanExtra("FROM_AUTH", false);
        if (isFromAuth) {
            titleTextView.setText("이제 시작합니다!");
        }

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
              @Override
              public void run() {
                  if (isFromAuth) {
                      startActivityForResult(new Intent(IntroActivity.this, BoardMainActivity.class), 1);
                  } else {
                      startActivityForResult(new Intent(IntroActivity.this, WelcomeActivity.class), 1);
                  }
              }
        }, 2000);

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

    private IntroController.OnHelloCallback onHelloCallback = new IntroController.OnHelloCallback() {
        @Override
        public void success() {
            // FIXME
            Log.d("doa", "success");
        }

        @Override
        public void fail() {
            // FIXME
        }
    };
}
