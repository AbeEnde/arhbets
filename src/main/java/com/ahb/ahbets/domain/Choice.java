package com.ahb.ahbets.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "choice")
public class Choice implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "choicId")
    private Long choicId;

    @Column(name = "hospitalName")
    private String hospitalName;


    private Long hos;


    private String empChoic;

    private String profession;

    public Long getChoicId() {
        return choicId;
    }

    public void setChoicId(Long choicId) {
        this.choicId = choicId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public long getHos() {
        return hos;
    }

    public void setHos(long hos) {
        this.hos = hos;
    }

    public String getEmpChoic() {
        return empChoic;
    }

    public void setEmpChoic(String empChoic) {
        this.empChoic = empChoic;
    }

    public void setHos(Long hos) {
        this.hos = hos;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Choice(Long choicId, String hospitalName, long hos, String empChoic,String profession) {
        this.choicId = choicId;
        this.hospitalName = hospitalName;
        this.hos = hos;
        this.empChoic = empChoic;
        this.profession=profession;
    }

    public Choice(){}
}
