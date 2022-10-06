package com.ahb.ahbets.domain;






public class ReqHosp {

    private String userName;
    private String sex;


    private String expryear;

    private String exprmonth;

    private String exprday;

    private String spexpryear;

    private String spexprmonth;

    private String spexprday;

    private String zone;

    private String woreda;

    private String facilty;

    private String hospitalName;

    private Long code;

    public ReqHosp(String userName, String sex,  String zone, String woreda, String facilty, String expryear, String exprmonth, String exprday, String spexpryear, String spexprmonth, String spexprday, String hospitalName,Long code) {
        this.userName = userName;
        this.sex = sex;
        this.code = code;
        this.zone = zone;
        this.woreda = woreda;
        this.facilty = facilty;
        this.expryear = expryear;
        this.exprmonth = exprmonth;
        this.exprday = exprday;
        this.spexpryear = spexpryear;
        this.spexprmonth = spexprmonth;
        this.spexprday = spexprday;
        this.hospitalName = hospitalName;
    }

    public ReqHosp(){}

    public void setCode(Long code) {
        this.code = code;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getWoreda() {
        return woreda;
    }

    public void setWoreda(String woreda) {
        this.woreda = woreda;
    }

    public String getFacilty() {
        return facilty;
    }

    public void setFacilty(String facilty) {
        this.facilty = facilty;
    }



    public String getExpryear() {
        return expryear;
    }

    public void setExpryear(String expryear) {
        this.expryear = expryear;
    }

    public String getExprmonth() {
        return exprmonth;
    }

    public void setExprmonth(String exprmonth) {
        this.exprmonth = exprmonth;
    }

    public String getExprday() {
        return exprday;
    }

    public void setExprday(String exprday) {
        this.exprday = exprday;
    }

    public String getSpexpryear() {
        return spexpryear;
    }

    public void setSpexpryear(String spexpryear) {
        this.spexpryear = spexpryear;
    }

    public String getSpexprmonth() {
        return spexprmonth;
    }

    public void setSpexprmonth(String spexprmonth) {
        this.spexprmonth = spexprmonth;
    }

    public String getSpexprday() {
        return spexprday;
    }

    public void setSpexprday(String spexprday) {
        this.spexprday = spexprday;
    }


    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }



}
