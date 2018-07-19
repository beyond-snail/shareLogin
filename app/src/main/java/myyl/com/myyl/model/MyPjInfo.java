package myyl.com.myyl.model;

import java.io.Serializable;

public class MyPjInfo implements Serializable{
    private String url;
    private String userName;
    private String content;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
