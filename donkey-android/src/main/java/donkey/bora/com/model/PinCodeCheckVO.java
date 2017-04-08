package donkey.bora.com.model;

import com.google.gson.annotations.SerializedName;

public class PinCodeCheckVO {

    @SerializedName("is_confirm")
    private boolean isConfirm;
    @SerializedName("is_exist")
    private boolean existUser;
    private String token;
    private String msg;


    public boolean isConfirm() {
        return isConfirm;
    }

    public boolean isExistUser() {
        return existUser;
    }

    public String getMsg() {
        return msg;
    }

    public String getToken() {
        return token;
    }
}
