package donkey.bora.com.controller;

import android.text.TextUtils;

import donkey.bora.com.model.ArticleListVO;
import donkey.bora.com.network.ApiRequest;
import donkey.bora.com.network.IRequest;
import donkey.bora.com.network.JsonResponseWrapper;

public class BoardListFragmentController implements IController {

    public interface OnArticleListCallback {
        void callback(ArticleListVO articleListVO);
    }

    public void requestArticles(long boardId, final OnArticleListCallback onArticleListCallback) {

        IRequest request = ApiRequest.getInstance().requestWithToken(IRequest.class);
        request.getArticleList(boardId).enqueue(new JsonResponseWrapper<ArticleListVO>() {
            @Override
            public void callback(ArticleListVO data, boolean isSuccess) {
                onArticleListCallback.callback(data);
            }
        });
    }

    public interface OnNextArticleListCallback {
        void callback(ArticleListVO articleListVO);
    }

    public void requestNextArticleList(String url, final OnNextArticleListCallback onNextArticleListCallback) {
        if (TextUtils.isEmpty(url)) {
            onNextArticleListCallback.callback(null);
            return;
        }

        IRequest request = ApiRequest.getInstance().requestWithToken(IRequest.class);
        request.getNextArticleList(IRequest.BASE_URL + url).enqueue(new JsonResponseWrapper<ArticleListVO>() {
            @Override
            public void callback(ArticleListVO data, boolean isSuccess) {
                onNextArticleListCallback.callback(data);
            }
        });
    }
}
