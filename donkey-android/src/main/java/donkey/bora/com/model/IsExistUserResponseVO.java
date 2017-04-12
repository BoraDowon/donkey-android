package donkey.bora.com.model;

import com.google.gson.annotations.SerializedName;

public class IsExistUserResponseVO {

    @SerializedName("is_success")
    private boolean isSuccess;
    @SerializedName("is_exist")
    private boolean isExist;
    private String msg;

    public boolean isSuccess() {
        return isSuccess;
    }

    public boolean isExist() {
        return isExist;
    }

    public String getMsg() {
        return msg;
    }
}
