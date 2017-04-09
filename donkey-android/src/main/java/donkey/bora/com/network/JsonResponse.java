package donkey.bora.com.network;

import java.io.Serializable;

public class JsonResponse<T> implements Serializable {

    private String code;
    private String msg;
    private String detail;
    private T data;

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public String getDetail() {
        return detail;
    }

    public T getData() {
        return data;
    }
}
