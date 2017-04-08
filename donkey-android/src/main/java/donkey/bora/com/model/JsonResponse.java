package donkey.bora.com.model;

public class JsonResponse<T> {

    private String msg;
    private String code;
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
