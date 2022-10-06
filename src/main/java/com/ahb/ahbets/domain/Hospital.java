package com.ahb.ahbets.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Hospital.
 */
@Entity
@Table(name = "hospitalTBL")
public class Hospital implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    //@SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "code")
    private Long code;



    @Column(name = "hospital_name")
    private String hospitalName;






//    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "hos")
//    @JsonManagedReference
//    private Choice choi;



//    @OneToMany(mappedBy = "depHsp")
//    private Set<Department> hosp = new HashSet<>();





    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }




    public Hospital(Long code, String hospitalName, Choice choi, Set<Request> reqs) {
        this.code = code;
        this.hospitalName = hospitalName;

    }

    public Hospital(){}
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here



    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Hospital{" +

            ", hospitalName='" + getHospitalName() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
