package donkey.bora.com.model;

import com.google.gson.annotations.SerializedName;

public class ArticleVO {

    private long id;
    private UserVO user;
    private BoardVO board;
    private long status;
    private String title;
    private String content;

    // TODO: naming is not good
    private long views;
    @SerializedName("yellow_cards")
    private long yellowCards;
    private String likes;

    public long getId() {
        return id;
    }

    public UserVO getUser() {
        return user;
    }

    public BoardVO getBoard() {
        return board;
    }

    public long getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getViews() {
        return views;
    }

    public long getYellowCards() {
        return yellowCards;
    }

    public String getLikes() {
        return likes;
    }
}
