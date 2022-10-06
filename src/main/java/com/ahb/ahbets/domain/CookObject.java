package com.ahb.ahbets.domain;

public class CookObject {
    private String userName;
    private String choice;
    private int total;
    private long code;

    private String profession;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public CookObject(){}
    public CookObject(String userName, String choice, int total,long code,String profession) {
        this.userName = userName;
        this.choice = choice;
        this.total = total;
        this.code = code;
        this.profession = profession;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
