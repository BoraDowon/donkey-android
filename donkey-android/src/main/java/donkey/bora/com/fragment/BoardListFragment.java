package donkey.bora.com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import donkey.bora.com.R;
import donkey.bora.com.adapter.ArticlesListAdapter;
import donkey.bora.com.controller.BoardListController;
import donkey.bora.com.model.ArticleListVO;
import donkey.bora.com.model.ArticleVO;
import donkey.bora.com.model.BoardContentItemVO;

public class BoardListFragment extends Fragment {

    @BindView(R.id.description)
    TextView descTextView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private BoardContentItemVO content;

    public void setContent(BoardContentItemVO boardContentItemVO) {
        this.content = boardContentItemVO;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BoardListController controller = new BoardListController();
        controller.requestArticles(content.getId(), onArticleListCallback);
    }

    BoardListController.OnArticleListCallback onArticleListCallback = new BoardListController.OnArticleListCallback() {
        @Override
        public void callback(ArticleListVO articleListVO) {
            if (articleListVO != null && articleListVO.getArticles() != null) {
                ArticlesListAdapter adapter = new ArticlesListAdapter();
                adapter.setArticles(articleListVO.getArticles());

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);

            } else {
                ArticlesListAdapter adapter = new ArticlesListAdapter();
                adapter.setArticles(getDummy());

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
            }
        }
    };

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

        dummy.add(a1);
        dummy.add(a2);
        dummy.add(a3);

        return dummy;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.board_list_fragment, container, false);
        ButterKnife.bind(this, view);

        descTextView.setText(content.getDesc());

        return view;
    }
}
