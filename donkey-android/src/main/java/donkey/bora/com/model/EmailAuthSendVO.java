package donkey.bora.com.model;

import com.google.gson.annotations.SerializedName;

public class EmailAuthSendVO {

    @SerializedName("is_sent")
    private boolean isSent;
    private String msg;

    public boolean isSent() {
        return isSent;
    }

    public String getMsg() {
        return msg;
    }
}
