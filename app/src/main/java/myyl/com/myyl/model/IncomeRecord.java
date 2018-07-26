package myyl.com.myyl.model;

import java.io.Serializable;
import java.util.List;

public class IncomeRecord implements Serializable{
    private int totalAmout;
    private String date;
    private List<IncomeItemInfo> incomeItemInfo;

    public int getTotalAmout() {
        return totalAmout;
    }

    public void setTotalAmout(int totalAmout) {
        this.totalAmout = totalAmout;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<IncomeItemInfo> getIncomeItemInfo() {
        return incomeItemInfo;
    }

    public void setIncomeItemInfo(List<IncomeItemInfo> incomeItemInfo) {
        this.incomeItemInfo = incomeItemInfo;
    }
}


