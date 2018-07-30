package myyl.com.myyl.model;

import java.io.Serializable;

public class MyCollect implements Serializable{
    private String userUrl;
    private String userName;
    private String drugStr;
    private int personCount;


    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDrugStr() {
        return drugStr;
    }

    public void setDrugStr(String drugStr) {
        this.drugStr = drugStr;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }
}
