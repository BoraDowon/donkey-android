package donkey.bora.com.controller;

import donkey.bora.com.model.ArticleListVO;
import donkey.bora.com.network.ApiRequest;
import donkey.bora.com.network.IRequest;
import donkey.bora.com.network.JsonResponseWrapper;

public class BoardListController implements IController {

    public interface OnArticleListCallback {
        void callback(ArticleListVO articleListVO);
    }

    public void requestArticles(long boardId, final OnArticleListCallback onArticleListCallback) {

        IRequest request = ApiRequest.getInstance().request(IRequest.class);
        request.getArticleList(boardId).enqueue(new JsonResponseWrapper<ArticleListVO>() {
            @Override
            public void callback(ArticleListVO data, boolean isSuccess) {
                onArticleListCallback.callback(data);
            }
        });
    }
}
