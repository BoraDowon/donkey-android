package donkey.bora.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import donkey.bora.com.R;
import donkey.bora.com.controller.EmailAuthController;
import donkey.bora.com.model.EmailCheckVO;

public class EmailAuthActivity extends AppCompatActivity {

    @BindView(R.id.edit_code)
    EditText authCodeEdit;
    @BindView(R.id.university_title)
    TextView universityText;
    @BindView(R.id.edit_email)
    EditText emailEdit;

    private EmailAuthController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.email_auth_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        controller = new EmailAuthController();
    }

    @OnClick(R.id.bt_send_email)
    void onSendEmail(View view) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        controller.requestCheckEmail(emailEdit.getText().toString(), onCheckEmailCallback);
    }

    @OnClick(R.id.confirm)
    void onConfirm() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private EmailAuthController.OnCheckEmailCallback onCheckEmailCallback = new EmailAuthController.OnCheckEmailCallback() {
        @Override
        public void callback(EmailCheckVO emailCheckVO) {
            if (emailCheckVO.isValid()) {
                authCodeEdit.requestFocus();
                universityText.setText(emailCheckVO.getName());
            } else {
                universityText.setText(emailCheckVO.getMsg());
            }
        }
    };
}
