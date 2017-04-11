package donkey.bora.com.model;

import com.google.gson.annotations.SerializedName;

public class RegisterResponseVO {

    @SerializedName("is_register")
    private boolean isRegister;
    private String msg;
    private String token;

    public boolean isRegister() {
        return isRegister;
    }

    public String getMsg() {
        return msg;
    }

    public String getToken() {
        return token;
    }

}
