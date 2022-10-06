package com.ahb.ahbets.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private Long code;
    @Column(name = "userName")
    private String userName;

    @Column(name = "expValue")
    private String expValue;

    @Column(name = "PlaceV")
    private String PlaceV;




    @Column(name = "genderV")
    private String genderV;

    @Column(name = "sapauseV")
    private String spauseV;

    @Column(name = "medicalV")
    private String medicalV;

    @Column(name = "totalV")
    private int totalV;

    @Column(name = "resultV")
    private String resultV;

    public String getExpValue() {
        return expValue;
    }

    public void setExpValue(String expValue) {
        this.expValue = expValue;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaceV() {
        return PlaceV;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public void setPlaceV(String placeV) {
        this.PlaceV = placeV;
    }

    public String getGenderV() {
        return genderV;
    }

    public void setGenderV(String genderV) {
        this.genderV = genderV;
    }

    public String getSpauseV() {
        return spauseV;
    }

    public void setSpauseV(String spauseV) {
        this.spauseV = spauseV;
    }

    public String getMedicalV() {
        return medicalV;
    }

    public void setMedicalV(String medicalV) {
        this.medicalV = medicalV;
    }

    public int getTotalV() {
        return totalV;
    }

    public void setTotalV(int totalV) {
        this.totalV = totalV;
    }

    public String getResultV() {
        return resultV;
    }

    public void setResultV(String resultV) {
        this.resultV = resultV;
    }

    public Result(){}
    public Result(Long id, Long code, String userName, String expValue, String placeV, String genderV, String spauseV, String medicalV, int totalV, String resultV) {
        this.id = id;
        this.code = code;
        this.userName = userName;
        this.expValue = expValue;
        PlaceV = placeV;
        this.genderV = genderV;
        this.spauseV = spauseV;
        this.medicalV = medicalV;
        this.totalV = totalV;
        this.resultV = resultV;
    }

    //    public Request getReq() {
//        return req;
//    }
//
//    public void setReq(Request req) {
//        this.req = req;
//    }



//    @OneToOne(targetEntity = Request.class,cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "cf_emp", referencedColumnName = "id", nullable = false )
//    private Request req ;

//    public Request getReqst() {
//        return reqst;
//    }
//
//    public void setReqst(Request reqst) {
//        this.reqst = reqst;
//    }

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "reqId")
//    private Request reqst;

}
