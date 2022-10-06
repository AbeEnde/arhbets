package com.ahb.ahbets.repository;

import com.ahb.ahbets.domain.Hospital;
import com.ahb.ahbets.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ResultRepo extends JpaRepository<Result, Long> {}

