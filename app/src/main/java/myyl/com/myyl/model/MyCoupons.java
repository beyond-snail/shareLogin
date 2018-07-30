package myyl.com.myyl.model;

import java.io.Serializable;

public class MyCoupons implements Serializable{
    private String userUrl;

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }
}
