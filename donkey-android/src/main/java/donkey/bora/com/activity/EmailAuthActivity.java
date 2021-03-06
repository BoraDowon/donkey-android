package donkey.bora.com.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import donkey.bora.com.model.IsExistUserResponseVO;
import donkey.bora.com.model.PinCodeCheckVO;
import donkey.bora.com.secure.TokenManager;

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
        controller.requestExistUser(emailEdit.getText().toString(), pinCodeEdit.getText().toString(), onIsExistUserCallback);
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

    private EmailAuthController.OnIsExistUserCallback onIsExistUserCallback = new EmailAuthController.OnIsExistUserCallback() {
        @Override
        public void callback(IsExistUserResponseVO isExistUserResponseVO) {
            if (isExistUserResponseVO == null || !isExistUserResponseVO.isSuccess()) {
                Toast.makeText(EmailAuthActivity.this, "유저 인증 에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 기존에 가입되어 있는 유저인 경우, 다른 기기에서는 접근이 제한될 수 있다는 경고 팝업을 보여준다.
            if (isExistUserResponseVO.isExist()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EmailAuthActivity.this);
                builder.setCancelable(false);
                builder.setTitle("알림");
                builder.setMessage(isExistUserResponseVO.getMsg());
                builder.setPositiveButton("진행", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        controller.requestPinCodeConfirm(emailEdit.getText().toString(), pinCodeEdit.getText().toString(), onPinCodeConfirmCallback);
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        EmailAuthActivity.this.finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                controller.requestPinCodeConfirm(emailEdit.getText().toString(), pinCodeEdit.getText().toString(), onPinCodeConfirmCallback);
            }
        }
    };

    private EmailAuthController.OnPinCodeConfirmCallback onPinCodeConfirmCallback = new EmailAuthController.OnPinCodeConfirmCallback() {
        @Override
        public void callback(PinCodeCheckVO pinCodeCheckVO) {
            if (pinCodeCheckVO.isConfirm()) {
                if (pinCodeCheckVO.isExistUser()) {
                    TokenManager.save(pinCodeCheckVO.getToken(), TokenManager.getDefaultPath());

                    Intent introIntent = new Intent(EmailAuthActivity.this, IntroActivity.class);
                    introIntent.putExtra("FROM_AUTH", true);
                    introIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(introIntent);
                } else {
                    Intent registerIntent = new Intent(EmailAuthActivity.this, RegisterActivity.class);
                    registerIntent.putExtra("email", emailEdit.getText().toString());
                    registerIntent.putExtra("pinCode", pinCodeEdit.getText().toString());
                    startActivity(registerIntent);
                }
            } else {
                Toast.makeText(EmailAuthActivity.this, pinCodeCheckVO.getMsg(), Toast.LENGTH_SHORT).show();
            }

        }
    };

}
