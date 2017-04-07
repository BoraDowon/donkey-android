package donkey.bora.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import donkey.bora.com.R;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.edit_code) EditText policyEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_layout);
        ButterKnife.bind(this);

        policyEdit.setKeyListener(null);
    }

    @OnClick(R.id.confirm)
    void confirm() {
        Intent intent = new Intent(this, IntroActivity.class);
        intent.putExtra("FROM_AUTH", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
    }
}
