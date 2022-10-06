package com.ahb.ahbets.repository;

import com.ahb.ahbets.domain.Filtered;
import com.ahb.ahbets.domain.ReqHosp;
import com.ahb.ahbets.domain.Request;
import com.ahb.ahbets.domain.empSelected;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the Request entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
//  @Query(value = "SELECT new com.ahb.ahbets.domain.ReqHosp( r.name,r.sex,r.profession,r.cwzone,r.cwworeda,r.cwfacility,r.firstchoice,r.code1,r.secondchoice,r." +
//      "code2 ,r.thirdchoice,r.code3,r.expryear,r.exprmonth,r.exprday,r.spexpryear,r.spexprmonth,r.spexprday,r.file,r.fileContentType, h.hospitalName) FROM Request r JOIN r.hosp h"  )
//  List<ReqHosp> getBothTbl();
@Query(value = " SELECT new com.ahb.ahbets.domain.ReqHosp( e.userName, e.sex, e.zone,e.woreda,e.facility,e.exprYear,e.expMon,e.expDay,"+
    "    e.spexprday,e.spexprmonth,e.spexpryear," +
    "    c.hospitalName," +
    "    h.code)" +
    "    FROM Employee e"  +
    "    INNER JOIN Choice c ON c.empChoic = e.userName" +
    "    INNER JOIN Hospital h ON c.hos = h.code")
List<ReqHosp> getJoinData();

@Query(value = "SELECT new com.ahb.ahbets.domain.empSelected(e.userName,e.name)from Employee e")
    List<empSelected> getEmpSelected();

@Query(value = "SELECT new com.ahb.ahbets.domain.Filtered(r.userName, r.totalV)FROM Result r WHERE r.code =?#{[0]} ORDER BY r.totalV DESC")
    List<Filtered> getFiltered(long code);

}







