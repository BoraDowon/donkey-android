package donkey.bora.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import donkey.bora.com.BackPressCloseHandler;
import donkey.bora.com.R;
import donkey.bora.com.adapter.BoardViewPageAdapter;
import donkey.bora.com.model.BoardContentItemVO;
import donkey.bora.com.model.InitResponseVO;

public class BoardMainActivity extends AppCompatActivity {

    private BackPressCloseHandler backPressCloseHandler;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.board_main_layout);
        ButterKnife.bind(this);
        backPressCloseHandler = new BackPressCloseHandler(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.tool_bar_title);

        Intent i = getIntent();
        InitResponseVO initResponseVO = (InitResponseVO) i.getSerializableExtra("init");
        initTableLayout(initResponseVO);
        initViewPager(initResponseVO);

    }

    private void initViewPager(InitResponseVO initResponseVO) {
        BoardViewPageAdapter boardViewPageAdapter = new BoardViewPageAdapter(getSupportFragmentManager());
        boardViewPageAdapter.setInitResponseVO(initResponseVO);
        viewPager.setAdapter(boardViewPageAdapter);
    }

    private void initTableLayout(InitResponseVO initResponseVO) {
        if (initResponseVO != null) {
            String nickName = initResponseVO.getUser() != null ? initResponseVO.getUser().getNickname() : "guest";
            Toast.makeText(this, "환영합니다 " + nickName + "님", Toast.LENGTH_SHORT).show();

            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            tabLayout.setupWithViewPager(viewPager);
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }
}
