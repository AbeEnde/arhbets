package com.ahb.ahbets.domain;

import liquibase.pro.packaged.I;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "userName")
    private String userName;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "profession")
    private String profession;

    @Column(name = "zone")
    private String zone;

    @Column(name = "woreda")
    private String woreda;

    @Column(name = "facilty")
    private String facility;

    @Column(name = "exprYear")
    private String exprYear;

    @Column(name = "expMon")
    private String expMon;

    @Column(name = "expDay")
    private String expDay;

    @Column(name = "spexpryear")
    private String spexpryear;

    @Column(name = "spexprmonth")
    private String spexprmonth;

    @Column(name = "spexprday")
    private String spexprday;

    @Lob
    @Column(name = "file")
    // @Transient
    private byte[] file;

    @Column(name = "file_content_type")
    private String fileContentType;

//    @OneToMany(mappedBy = "empChoic")
//    private Set<Choice> choic = new HashSet<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
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

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getExprYear() {
        return exprYear;
    }

    public void setExprYear(String exprYear) {
        this.exprYear = exprYear;
    }

    public String getExpMon() {
        return expMon;
    }

    public void setExpMon(String expMon) {
        this.expMon = expMon;
    }

    public String getExpDay() {
        return expDay;
    }

    public void setExpDay(String expDay) {
        this.expDay = expDay;
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

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public Employee(String userName, String name, String sex, String profession,
                    String zone, String woreda, String facility, String exprYear,
                    String expMon, String expDay, String spexpryear, String spexprmonth,
                    String spexprday, byte[] file, String fileContentType) {
        this.userName = userName;
        this.name = name;
        this.sex = sex;
        this.profession = profession;
        this.zone = zone;
        this.woreda = woreda;
        this.facility = facility;
        this.exprYear = exprYear;
        this.expMon = expMon;
        this.expDay = expDay;
        this.spexpryear = spexpryear;
        this.spexprmonth = spexprmonth;
        this.spexprday = spexprday;
        this.file = file;
        this.fileContentType = fileContentType;
    }
    public Employee(){}

}



