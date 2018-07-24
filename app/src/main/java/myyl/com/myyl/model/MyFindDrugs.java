package myyl.com.myyl.model;

import java.io.Serializable;

public class MyFindDrugs implements Serializable{
    private String userUrl;
    private String userName;
    private String drugStr;
    private int personCount;
    private int percent;


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

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
