package donkey.bora.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import donkey.bora.com.BackPressCloseHandler;
import donkey.bora.com.R;
import donkey.bora.com.model.InitResponseVO;

public class BoardMainActivity extends AppCompatActivity {

    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.board_main_layout);
        ButterKnife.bind(this);

        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent i = getIntent();
        InitResponseVO initResponseVO = (InitResponseVO) i.getSerializableExtra("init");
        if (initResponseVO != null) {
            String nickName = initResponseVO.getUser() != null ? initResponseVO.getUser().getNickname() : "guest";
            Toast.makeText(this, "환영합니다 " + nickName + "님", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }
}
