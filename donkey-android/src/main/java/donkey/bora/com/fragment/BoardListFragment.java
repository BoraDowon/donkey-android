package donkey.bora.com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import donkey.bora.com.R;
import donkey.bora.com.adapter.ArticlesListAdapter;
import donkey.bora.com.controller.BoardListFragmentController;
import donkey.bora.com.model.ArticleListVO;
import donkey.bora.com.model.BoardContentItemVO;

public class BoardListFragment extends Fragment {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;

    private BoardContentItemVO content;
    private ArticlesListAdapter adapter;
    private BoardListFragmentController controller;

    private String nextUrl;
    private boolean hasNextUrlRequest;

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
            if (swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
            }
            reload(articleListVO);
        }
    };

    private void reload(ArticleListVO articleListVO) {
        if (articleListVO != null && articleListVO.getArticles() != null) {
            adapter = new ArticlesListAdapter();
            adapter.addArticles(articleListVO.getArticles());
            nextUrl = articleListVO.getNextUrl();
            hasNextUrlRequest = true;
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);

                    float percent = computeScrollVerticalPercentage(recyclerView);
                    if (hasNextUrlRequest && percent > 80f) {
                        controller.requestNextArticleList(nextUrl, onNextArticleListCallback);
                        hasNextUrlRequest = false;
                    }
                }
            });
        }
    }

    private float computeScrollVerticalPercentage(RecyclerView recyclerView) {
        int extent = recyclerView.computeVerticalScrollExtent();
        int range = recyclerView.computeVerticalScrollRange();
        int offset = recyclerView.computeVerticalScrollOffset();
        return 100f * offset / (range - extent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.board_list_fragment, container, false);
        ButterKnife.bind(this, view);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                controller.requestArticles(content.getId(), onArticleListCallback);
            }
        });
        return view;
    }

    private BoardListFragmentController.OnNextArticleListCallback onNextArticleListCallback = new BoardListFragmentController.OnNextArticleListCallback() {
        @Override
        public void callback(ArticleListVO articleListVO) {
            if (articleListVO != null) {
                nextUrl = articleListVO.getNextUrl();
                if (!TextUtils.isEmpty(nextUrl)) {
                    hasNextUrlRequest = true;
                }

                adapter.addArticles(articleListVO.getArticles());
                adapter.notifyDataSetChanged();
            }
        }
    };
}
