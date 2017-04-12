package donkey.bora.com.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class InitResponseVO implements Serializable {

    @SerializedName("is_anonymous")
    private boolean isAnonymous;
    private List<BoardContentItemVO> board;
    private UserVO user;

    public InitResponseVO() {
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public List<BoardContentItemVO> getBoard() {
        return board;
    }

    public UserVO getUser() {
        return user;
    }
}
