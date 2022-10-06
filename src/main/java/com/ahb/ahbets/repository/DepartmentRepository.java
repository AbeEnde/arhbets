package com.ahb.ahbets.repository;

import com.ahb.ahbets.domain.DepHosp;
import com.ahb.ahbets.domain.Department;
import com.ahb.ahbets.domain.ReqHosp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the Department entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
//    @Query(value = "SELECT new com.ahb.ahbets.domain.DepHosp( d.depName,d.available,d.relesed,d.assigned, h.hospitalName) FROM Department d JOIN d.depHsp h"  )
//    List<DepHosp> getBothTbl();

}


