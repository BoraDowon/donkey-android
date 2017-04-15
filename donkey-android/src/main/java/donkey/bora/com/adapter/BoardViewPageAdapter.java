package donkey.bora.com.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import donkey.bora.com.fragment.BoardListFragment;
import donkey.bora.com.model.BoardContentItemVO;
import donkey.bora.com.model.InitResponseVO;

public class BoardViewPageAdapter extends FragmentStatePagerAdapter {

    private InitResponseVO initResponseVO;
    private List<BoardContentItemVO> boardContentItemVOs;

    public BoardViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setInitResponseVO(InitResponseVO initResponseVO) {
        this.initResponseVO = initResponseVO;
        this.boardContentItemVOs = initResponseVO.getBoard();
    }

    @Override
    public Fragment getItem(int position) {
        BoardListFragment fragment = new BoardListFragment();
        fragment.setContent(boardContentItemVOs.get(position));
        return fragment;
    }

    @Override
    public int getCount() {
        return boardContentItemVOs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return boardContentItemVOs.get(position).getTitle();
    }
}
