package donkey.bora.com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import donkey.bora.com.R;
import donkey.bora.com.adapter.ArticlesListAdapter;
import donkey.bora.com.controller.BoardListFragmentController;
import donkey.bora.com.model.ArticleListVO;
import donkey.bora.com.model.ArticleVO;
import donkey.bora.com.model.BoardContentItemVO;

public class BoardListFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipyRefreshLayout swipyRefreshLayout;

    private BoardContentItemVO content;
    private ArticlesListAdapter adapter;
    private BoardListFragmentController controller;
    private String nextUrl;

    public void setContent(BoardContentItemVO boardContentItemVO) {
        this.content = boardContentItemVO;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = new BoardListFragmentController();
        controller.requestArticles(content.getId(), onArticleListCallback);
    }

    private BoardListFragmentController.OnArticleListCallback onArticleListCallback = new BoardListFragmentController.OnArticleListCallback() {
        @Override
        public void callback(ArticleListVO articleListVO) {
            if (swipyRefreshLayout.isRefreshing()) {
                swipyRefreshLayout.setRefreshing(false);
            }
            reload(articleListVO);
        }
    };

    private void reload(ArticleListVO articleListVO) {
        if (articleListVO != null && articleListVO.getArticles() != null) {
            adapter = new ArticlesListAdapter();
            adapter.addArticles(articleListVO.getArticles());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            nextUrl = articleListVO.getNextUrl();

        } else {
            // TEST mode
            adapter = new ArticlesListAdapter();
            adapter.addArticles(getDummy());

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
        }
    }

    private List<ArticleVO> getDummy() {

        //TEST
        List<ArticleVO> dummy = new ArrayList<>();
        ArticleVO a1 = new ArticleVO();
        a1.setTitle("첫번째 글입니다.");
        a1.setContent("오늘 정말 재미있는 일이 있었어요. 어머니랑 장을 보러 갔는데~오늘 정말 재미있는 일이 있었어요. 어머니랑 장을 보러 갔는데~");

        ArticleVO a2 = new ArticleVO();
        a2.setTitle("두번째 글입니다.");
        a2.setContent("오늘 정말 재미있는 일이 있었어요. 어머니랑 장을 보러 갔는데~오늘 정말 재미있는 일이 있었어요. 어머니랑 장을 보러 갔는데~");

        ArticleVO a3 = new ArticleVO();
        a3.setTitle("세번째 글입니다.");
        a3.setContent("오늘 정말 재미있는 일이 있었어요. 어머니랑 장을 보러 갔는데~오늘 정말 재미있는 일이 있었어요. 어머니랑 장을 보러 갔는데~");

        for (int i = 0; i < 10; i++) {
            dummy.add(a1);
            dummy.add(a2);
            dummy.add(a3);
        }

        return dummy;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.board_list_fragment, container, false);
        ButterKnife.bind(this, view);

        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                if (direction == SwipyRefreshLayoutDirection.TOP) {
                    controller.requestArticles(content.getId(), onArticleListCallback);
                } else {
                    controller.requestNextArticleList(nextUrl, onNextArticleListCallback);
                }
            }
        });
        return view;
    }

    private BoardListFragmentController.OnNextArticleListCallback onNextArticleListCallback = new BoardListFragmentController.OnNextArticleListCallback() {
        @Override
        public void callback(ArticleListVO articleListVO) {
            if (articleListVO != null) {
                nextUrl = articleListVO.getNextUrl();
                adapter.addArticles(articleListVO.getArticles());
                adapter.notifyDataSetChanged();
            }

            if (swipyRefreshLayout.isRefreshing()) {
                swipyRefreshLayout.setRefreshing(false);
            }
        }
    };
}
