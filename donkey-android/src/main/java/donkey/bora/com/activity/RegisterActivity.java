package donkey.bora.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import donkey.bora.com.R;
import donkey.bora.com.controller.RegisterController;
import donkey.bora.com.model.DepartmentListItemVO;
import donkey.bora.com.model.DepartmentListResponseVO;
import donkey.bora.com.model.RegisterResponseVO;
import donkey.bora.com.secure.TokenManager;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.edit_code) EditText policyEdit;
    @BindView(R.id.spinner_departments) Spinner departmentsSpinner;

    private RegisterController controller;

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

        String email = getIntent().getStringExtra("email");
        String pinCode = getIntent().getStringExtra("pinCode");

        controller = new RegisterController();
        controller.requestDepartmentList(onDepartmentListCallback, email, pinCode);
    }

    @OnClick(R.id.confirm)
    void confirm() {

        String email = getIntent().getStringExtra("email");
        String pinCode = getIntent().getStringExtra("pinCode");

        long departmentId = ((DepartmentListItemVO)departmentsSpinner.getSelectedItem()).getId();
        controller.requestRegister(onRegisterCallback, email, pinCode, departmentId);
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

    private RegisterController.OnDepartmentListCallback onDepartmentListCallback = new RegisterController.OnDepartmentListCallback() {
        @Override
        public void callback(DepartmentListResponseVO departmentListResponseVO) {
            if (departmentListResponseVO == null) {
                return;
            }
            if (departmentListResponseVO.getDepartment() == null) {
                return;
            }

            ArrayAdapter<DepartmentListItemVO> adapter
                    = new ArrayAdapter<>(RegisterActivity.this, android.R.layout.simple_spinner_item, departmentListResponseVO.getDepartment());
            departmentsSpinner.setAdapter(adapter);
        }
    };
}
