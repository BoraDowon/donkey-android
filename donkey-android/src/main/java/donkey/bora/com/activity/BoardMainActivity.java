package donkey.bora.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import donkey.bora.com.BackPressCloseHandler;
import donkey.bora.com.R;
import donkey.bora.com.model.BoardContentItemVO;
import donkey.bora.com.model.InitResponseVO;

public class BoardMainActivity extends AppCompatActivity {

    private BackPressCloseHandler backPressCloseHandler;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.board_main_layout);
        ButterKnife.bind(this);
        backPressCloseHandler = new BackPressCloseHandler(this);

        initTableLayout();
    }

    private void initTableLayout() {
        Intent i = getIntent();
        InitResponseVO initResponseVO = (InitResponseVO) i.getSerializableExtra("init");
        if (initResponseVO != null) {
            String nickName = initResponseVO.getUser() != null ? initResponseVO.getUser().getNickname() : "guest";
            Toast.makeText(this, "환영합니다 " + nickName + "님", Toast.LENGTH_SHORT).show();

            for (BoardContentItemVO itemVO: initResponseVO.getBoard()) {
                tabLayout.addTab(tabLayout.newTab().setText(itemVO.getTitle()));
            }
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        }
    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }
}
