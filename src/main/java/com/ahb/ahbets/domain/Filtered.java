package com.ahb.ahbets.domain;

public class Filtered {
    private String userName;
    private int totalV;

public Filtered(){}
    public Filtered(String userName, int total) {
        this.userName = userName;
        this.totalV = total;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTotal() {
        return totalV;
    }

    public void setTotal(int total) {
        this.totalV = total;
    }
}
