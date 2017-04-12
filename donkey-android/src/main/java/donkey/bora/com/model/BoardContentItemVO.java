package donkey.bora.com.model;

import java.io.Serializable;

public class BoardContentItemVO implements Serializable {
    private long id;
    private String title;
    private String desc;
    private String link;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getLink() {
        return link;
    }
}
