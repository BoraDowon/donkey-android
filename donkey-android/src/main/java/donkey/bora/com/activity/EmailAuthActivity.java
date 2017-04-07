package donkey.bora.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import donkey.bora.com.R;

public class EmailAuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.email_auth_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.confirm)
    void onConfirm() {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
