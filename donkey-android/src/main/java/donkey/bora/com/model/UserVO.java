package donkey.bora.com.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserVO implements Serializable {
    private long id;
    private String email;
    private String username;
    private String nickname;
    @SerializedName("last_login")
    private String lastLogin;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getLastLogin() {
        return lastLogin;
    }
}
