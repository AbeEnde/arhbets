package com.ahb.ahbets.domain;

public class Disp {
     private String userName;
     private int total;
     private String hospName;

    public Disp(){}
    public Disp(String userName, int total, String hospName) {
        this.userName = userName;
        this.total = total;
        this.hospName = hospName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getHospName() {
        return hospName;
    }

    public void setHospName(String hospName) {
        this.hospName = hospName;
    }
}
