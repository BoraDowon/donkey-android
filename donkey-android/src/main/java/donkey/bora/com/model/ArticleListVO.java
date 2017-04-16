package donkey.bora.com.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleListVO {

    private List<ArticleVO> articles;
    @SerializedName("board_id")
    private long boardId;
    @SerializedName("next_url")
    private String nextUrl;

    public List<ArticleVO> getArticles() {
        return articles;
    }
    public long getBoardId() {
        return boardId;
    }
    public String getNextUrl() {
        return nextUrl;
    }
}
