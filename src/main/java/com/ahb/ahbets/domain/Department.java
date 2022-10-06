package com.ahb.ahbets.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Department.
 */
@Entity
@Table(name = "department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "deprtId")
    private Long deprtId;




    @Column(name = "dep_name")
    private String depName;

    @Column(name = "available")
    private Integer available;

    @Column(name = "relesed")
    private Integer relesed;

    @Column(name = "assigned")
    private Integer assigned;


    private long hcode;

//    @ManyToOne
//    @JoinColumn(name = "hospId")
//    private String depHsp;
    public Long getDeprtId() {
        return this.deprtId;
    }

    public Department id(Long id) {
        this.setDeprtId(id);
        return this;
    }

    public void setDeprtId(Long deprtId) {
        this.deprtId = deprtId;
    }


    public String getDepName() {
        return this.depName;
    }

    public Department depName(String depName) {
        this.setDepName(depName);
        return this;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Integer getAvailable() {
        return this.available;
    }

    public Department available(Integer available) {
        this.setAvailable(available);
        return this;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getRelesed() {
        return this.relesed;
    }

    public Department relesed(Integer relesed) {
        this.setRelesed(relesed);
        return this;
    }

    public void setRelesed(Integer relesed) {
        this.relesed = relesed;
    }

    public Integer getAssigned() {
        return this.assigned;
    }

    public Department assigned(Integer assigned) {
        this.setAssigned(assigned);
        return this;
    }

    public void setAssigned(Integer assigned) {
        this.assigned = assigned;
    }

    public long getHcode() {
        return this.hcode;
    }

    public Department hcode(long hcode) {
        this.setHcode(hcode);
        return this;
    }

    public void setHcode(long hcode) {
        this.hcode = hcode;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here



    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Department{" +
             "id=" + getDeprtId() +
            ", depName='" + getDepName() + "'" +
            ", available=" + getAvailable() +
            ", relesed=" + getRelesed() +
            ", assigned=" + getAssigned() +
            ", hcode='" + getHcode() + "'" +
            "}";
    }
}
