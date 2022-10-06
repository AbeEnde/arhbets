package com.ahb.ahbets.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

/**
 * A Request.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "requestTBL")
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
//    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "reqId")
    private Long reqId;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "profession")
    private String profession;

//    public Set<Hospital> getHosp() {
//        return hosp;
//    }
//
//    public void setHosp(Set<Hospital> hosp) {
//        this.hosp = hosp;
//    }

//    public Result getReslt() {
//        return reslt;
//    }
//
//    public void setReslt(Result reslt) {
//        this.reslt = reslt;
//    }

    @Column(name = "cwzone")
    private String cwzone;

    @Column(name = "cwworeda")
    private String cwworeda;

    @Column(name = "cwfacility")
    private String cwfacility;

    @Column(name = "firstchoice")
    private String firstchoice;

   //@ManyToOne(targetEntity = Hospital.class)
    //@JoinTable(name="hospital")
    @Column(name = "code_1")
    private Long code1;

    @Column(name = "secondchoice")
    private String secondchoice;


    @Column(name = "code_2")
    private String code2;

    @Column(name = "thirdchoice")
    private String thirdchoice;


   @Column(name = "code_3")
    private String code3;

    @Column(name = "expryear")
    private String expryear;

    @Column(name = "exprmonth")
    private String exprmonth;

    @Column(name = "exprday")
    private String exprday;

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

//    @OneToMany(mappedBy = "req")
//    private List<Hospital> hosp = new ArrayList();

//    @OneToMany(targetEntity = Hospital.class,cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "cf_hosp", referencedColumnName = "code_1", unique = false)
//    private List<Hospital> hosp ;

//    @OneToMany(mappedBy = "reqstHsp")
//    private Set<Hospital> hosp = new HashSet<>();

//    @ManyToMany(cascade = { CascadeType.ALL })
//    @JoinTable(
//        name = "reqHosptl",
//        joinColumns = { @JoinColumn(name = "code_1") },
//        inverseJoinColumns = { @JoinColumn(name = "hospId") })
//       private Set<Hospital> hosp = new HashSet<>();


    // @OneToOne(mappedBy = "reqst", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   //  private Result reslt;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getReqId() {
        return this.reqId;
    }

    public Request id(Long id) {
        this.setReqId(id);
        return this;
    }

    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }

    public String getName() {
        return this.name;
    }

    public Request name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return this.sex;
    }

    public Request sex(String sex) {
        this.setSex(sex);
        return this;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProfession() {
        return this.profession;
    }

    public Request profession(String profession) {
        this.setProfession(profession);
        return this;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCwzone() {
        return this.cwzone;
    }

    public Request cwzone(String cwzone) {
        this.setCwzone(cwzone);
        return this;
    }

    public void setCwzone(String cwzone) {
        this.cwzone = cwzone;
    }

    public String getCwworeda() {
        return this.cwworeda;
    }

    public Request cwworeda(String cwworeda) {
        this.setCwworeda(cwworeda);
        return this;
    }

    public void setCwworeda(String cwworeda) {
        this.cwworeda = cwworeda;
    }

    public String getCwfacility() {
        return this.cwfacility;
    }

    public Request cwfacility(String cwfacility) {
        this.setCwfacility(cwfacility);
        return this;
    }

    public void setCwfacility(String cwfacility) {
        this.cwfacility = cwfacility;
    }

    public String getFirstchoice() {
        return this.firstchoice;
    }

    public Request firstchoice(String firstchoice) {
        this.setFirstchoice(firstchoice);
        return this;
    }

    public void setFirstchoice(String firstchoice) {
        this.firstchoice = firstchoice;
    }

    public Long getCode1() {
        return this.code1;
    }

    public Request code1(Long code1) {
        this.setCode1(code1);
        return this;
    }

    public void setCode1(Long code1) {
        this.code1 = code1;
    }

    public String getSecondchoice() {
        return this.secondchoice;
    }

    public Request secondchoice(String secondchoice) {
        this.setSecondchoice(secondchoice);
        return this;
    }

    public void setSecondchoice(String secondchoice) {
        this.secondchoice = secondchoice;
    }

    public String getCode2() {
        return this.code2;
    }

    public Request code2(String code2) {
        this.setCode2(code2);
        return this;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public String getThirdchoice() {
        return this.thirdchoice;
    }

    public Request thirdchoice(String thirdchoice) {
        this.setThirdchoice(thirdchoice);
        return this;
    }

    public void setThirdchoice(String thirdchoice) {
        this.thirdchoice = thirdchoice;
    }

    public String getCode3() {
        return this.code3;
    }

    public Request code3(String code3) {
        this.setCode3(code3);
        return this;
    }

    public void setCode3(String code3) {
        this.code3 = code3;
    }

    public String getExpryear() {
        return this.expryear;
    }

    public Request expryear(String expryear) {
        this.setExpryear(expryear);
        return this;
    }

    public void setExpryear(String expryear) {
        this.expryear = expryear;
    }

    public String getExprmonth() {
        return this.exprmonth;
    }

    public Request exprmonth(String exprmonth) {
        this.setExprmonth(exprmonth);
        return this;
    }

    public void setExprmonth(String exprmonth) {
        this.exprmonth = exprmonth;
    }

    public String getExprday() {
        return this.exprday;
    }

    public Request exprday(String exprday) {
        this.setExprday(exprday);
        return this;
    }

    public void setExprday(String exprday) {
        this.exprday = exprday;
    }

    public String getSpexpryear() {
        return this.spexpryear;
    }

    public Request spexpryear(String spexpryear) {
        this.setSpexpryear(spexpryear);
        return this;
    }

    public void setSpexpryear(String spexpryear) {
        this.spexpryear = spexpryear;
    }

    public String getSpexprmonth() {
        return this.spexprmonth;
    }

    public Request spexprmonth(String spexprmonth) {
        this.setSpexprmonth(spexprmonth);
        return this;
    }

    public void setSpexprmonth(String spexprmonth) {
        this.spexprmonth = spexprmonth;
    }

    public String getSpexprday() {
        return this.spexprday;
    }

    public Request spexprday(String spexprday) {
        this.setSpexprday(spexprday);
        return this;
    }

    public void setSpexprday(String spexprday) {
        this.spexprday = spexprday;
    }

    public byte[] getFile() {
        return this.file;
    }

    public Request file(byte[] file) {
        this.setFile(file);
        return this;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return this.fileContentType;
    }

    public Request fileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
        return this;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Request)) {
            return false;
        }
        return reqId != null && reqId.equals(((Request) o).reqId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Request{" +
            "id=" + getReqId() +
            ", name='" + getName() + "'" +
            ", sex='" + getSex() + "'" +
            ", profession='" + getProfession() + "'" +
            ", cwzone='" + getCwzone() + "'" +
            ", cwworeda='" + getCwworeda() + "'" +
            ", cwfacility='" + getCwfacility() + "'" +
            ", firstchoice='" + getFirstchoice() + "'" +
            ", code1='" + getCode1() + "'" +
            ", secondchoice='" + getSecondchoice() + "'" +
            ", code2='" + getCode2() + "'" +
            ", thirdchoice='" + getThirdchoice() + "'" +
            ", code3='" + getCode3() + "'" +
            ", expryear='" + getExpryear() + "'" +
            ", exprmonth='" + getExprmonth() + "'" +
            ", exprday='" + getExprday() + "'" +
            ", spexpryear='" + getSpexpryear() + "'" +
            ", spexprmonth='" + getSpexprmonth() + "'" +
            ", spexprday='" + getSpexprday() + "'" +
            ", file='" + getFile() + "'" +
            ", fileContentType='" + getFileContentType() + "'" +
            "}";
    }
}
