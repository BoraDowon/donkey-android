package donkey.bora.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import donkey.bora.com.R;

public class EmailAuthActivity extends AppCompatActivity {

    @BindView(R.id.edit_code)
    EditText authCodeEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.email_auth_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_send_email)
    void onSendEmail(View view) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        authCodeEdit.requestFocus();
    }

    @OnClick(R.id.confirm)
    void onConfirm() {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
