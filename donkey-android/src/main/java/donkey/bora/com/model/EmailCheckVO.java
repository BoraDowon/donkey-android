package donkey.bora.com.model;

import com.google.gson.annotations.SerializedName;

public class EmailCheckVO {

    @SerializedName("is_valid")
    private boolean isValid;
    private String msg;
    private String name;

    public boolean isValid() {
        return isValid;
    }

    public String getMsg() {
        return msg;
    }

    public String getName() {
        return name;
    }


}
