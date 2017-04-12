package donkey.bora.com.model;

import com.google.gson.annotations.SerializedName;

public class RegisterSendInfo {

    String email;

    @SerializedName("auth_code")
    String authCode;

    @SerializedName("department_id")
    long departmentId;

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }


}
