package com.ahb.ahbets.repository;

import com.ahb.ahbets.domain.Choice;
import com.ahb.ahbets.domain.ReqHosp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChoiceRepo extends JpaRepository<Choice,Long> {

}
