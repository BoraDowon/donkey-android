package donkey.bora.com.model;

import java.io.Serializable;

public class JsonResponse<T> implements Serializable {

    private String code;
    private String msg;
    private T data;

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public T getData() {
        return data;
    }
}
