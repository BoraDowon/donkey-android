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
}
