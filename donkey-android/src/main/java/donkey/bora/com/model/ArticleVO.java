package donkey.bora.com.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ArticleVO {

    private long id;
    private String nickName;
    private String title;
    private long views;
    private String likes;
    private String content;
    @SerializedName("created_at")
    private Date createTime;
    @SerializedName("yellow_cards")
    private long yellowCards;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

}
