package com.ahb.ahbets.join;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DepHosp {

    public DepHosp(String depName, Integer available, Integer relesed, Integer assigned, String hcode) {
        this.depName = depName;
        this.available = available;
        this.relesed = relesed;
        this.assigned = assigned;
//        this.hcode = hcode;
//        this.hosp = hosp;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getRelesed() {
        return relesed;
    }

    public void setRelesed(Integer relesed) {
        this.relesed = relesed;
    }

    public Integer getAssigned() {
        return assigned;
    }

    public void setAssigned(Integer assigned) {
        this.assigned = assigned;
    }

//    public String getHcode() {
//        return hcode;
//    }
//
//    public void setHcode(String hcode) {
//        this.hcode = hcode;
//    }
//
//    public List<Hosp> getHosp() {
//        return hosp;
//    }
//
//    public void setHosp(List<Hosp> hosp) {
//        this.hosp = hosp;
//    }

    private String depName;

    private Integer available;

    private Integer relesed;

    private Integer assigned;


    private String hospitalName;
}
