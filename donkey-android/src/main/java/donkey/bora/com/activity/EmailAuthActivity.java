package donkey.bora.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import donkey.bora.com.R;
import donkey.bora.com.controller.EmailAuthController;
import donkey.bora.com.model.EmailAuthSendVO;
import donkey.bora.com.model.EmailCheckVO;
import donkey.bora.com.model.PinCodeCheckVO;

public class EmailAuthActivity extends AppCompatActivity {

    @BindView(R.id.university_title)
    TextView universityText;
    @BindView(R.id.edit_email)
    EditText emailEdit;
    @BindView(R.id.edit_pincode)
    EditText pinCodeEdit;

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

    @OnClick(R.id.pincode_confirm)
    void onPinCodeConfirm() {

        controller.requestPinCodeConfirm(emailEdit.getText().toString(), pinCodeEdit.getText().toString(),
                onPinCodeConfrimCallback);
    }

    private EmailAuthController.OnCheckEmailCallback onCheckEmailCallback = new EmailAuthController.OnCheckEmailCallback() {
        @Override
        public void callback(EmailCheckVO emailCheckVO) {
            if (emailCheckVO.isValid()) {
                universityText.setText(emailCheckVO.getName());

                controller.requestSendEmail(emailEdit.getText().toString(), onSendEmailCallback);
            } else {
                universityText.setText(emailCheckVO.getMsg());
            }
        }
    };

    private EmailAuthController.OnSendEmailCallback onSendEmailCallback = new EmailAuthController.OnSendEmailCallback() {
        @Override
        public void callback(EmailAuthSendVO emailAuthSendVO) {
            Toast.makeText(EmailAuthActivity.this, emailAuthSendVO.getMsg(), Toast.LENGTH_SHORT).show();

            if (emailAuthSendVO.isSent()) {
                pinCodeEdit.requestFocus();
            }
        }
    };

    private EmailAuthController.OnPinCodeConfrimCallback onPinCodeConfrimCallback = new EmailAuthController.OnPinCodeConfrimCallback() {
        @Override
        public void callback(PinCodeCheckVO pinCodeCheckVO) {
            if (pinCodeCheckVO.isConfirm()) {
                if (pinCodeCheckVO.isExistUser()) {
                    // FIXME: save token
                    Intent introIntent = new Intent(EmailAuthActivity.this, IntroActivity.class);
                    introIntent.putExtra("FROM_AUTH", true);
                    introIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(introIntent);
                } else {
                    startActivity(new Intent(EmailAuthActivity.this, RegisterActivity.class));
                }
            } else {
                Toast.makeText(EmailAuthActivity.this, pinCodeCheckVO.getMsg(), Toast.LENGTH_SHORT).show();
            }


        }
    };

}
