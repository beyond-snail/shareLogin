package myyl.com.myyl.model;

import java.util.List;

public class MyShuoInfo {
    private String date;
    private List<ShuoItemInfo> shuoItemInfoList;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ShuoItemInfo> getShuoItemInfoList() {
        return shuoItemInfoList;
    }

    public void setShuoItemInfoList(List<ShuoItemInfo> shuoItemInfoList) {
        this.shuoItemInfoList = shuoItemInfoList;
    }
}
