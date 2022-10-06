package com.ahb.ahbets.domain;

import liquibase.pro.packaged.I;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hos {
    @Id
    @Column(name = "code")
    private long code;



}
