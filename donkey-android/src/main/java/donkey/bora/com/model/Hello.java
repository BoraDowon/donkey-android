package donkey.bora.com.model;

public class Hello {
    private String msg;

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "[msg]: " + msg;
    }
}
