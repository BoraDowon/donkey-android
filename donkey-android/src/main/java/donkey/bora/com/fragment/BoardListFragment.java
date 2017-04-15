package donkey.bora.com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import donkey.bora.com.R;
import donkey.bora.com.adapter.ArticlesListAdapter;
import donkey.bora.com.controller.BoardListController;
import donkey.bora.com.model.ArticleListVO;
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
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.board_list_fragment, container, false);
        ButterKnife.bind(this, view);

        descTextView.setText(content.getDesc());

        return view;
    }
}
