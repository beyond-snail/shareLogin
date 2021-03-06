package myyl.com.myyl.model;


/**
 * 首页横向数据
 */
public class HomePageHdata {
    private String title;
    private String img;
    private String content;

    public HomePageHdata(String title, String img, String content) {
        this.title = title;
        this.img = img;
        this.content = content;
    }

    public HomePageHdata(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
