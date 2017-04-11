package donkey.bora.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import donkey.bora.com.R;
import donkey.bora.com.controller.RegisterController;
import donkey.bora.com.model.RegisterResponseVO;
import donkey.bora.com.secure.TokenManager;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.edit_code) EditText policyEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_layout);
        ButterKnife.bind(this);

        policyEdit.setKeyListener(null);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @OnClick(R.id.confirm)
    void confirm() {

        String email = getIntent().getStringExtra("email");
        String pinCode = getIntent().getStringExtra("pinCode");

        RegisterController controller = new RegisterController();
        controller.requestRegister(onRegisterCallback, email, pinCode);
    }

    private RegisterController.OnRegisterCallback onRegisterCallback = new RegisterController.OnRegisterCallback() {
        @Override
        public void callback(RegisterResponseVO registerResponseVO) {
            if (registerResponseVO != null && registerResponseVO.isRegister()) {
                String token = registerResponseVO.getToken();
                if (!TextUtils.isEmpty(token)) {
                    TokenManager.save(token, TokenManager.getDefaultPath());
                    Toast.makeText(RegisterActivity.this, "정상적으로 가입 되었습니다.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, IntroActivity.class);
                    intent.putExtra("FROM_AUTH", true);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(intent);
                }
                Toast.makeText(RegisterActivity.this, "가입 실패 하였습니다. (토큰)", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterActivity.this, "가입 실패 하였습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
