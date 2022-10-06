package com.ahb.ahbets.service;


import com.ahb.ahbets.domain.Employee;
import com.ahb.ahbets.domain.Hospital;
import com.ahb.ahbets.repository.EmployeeRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EmployeService {

    private final EmployeeRepo employeeRepo;


    public EmployeService(EmployeeRepo employeeRepo){
        this.employeeRepo = employeeRepo;
    }

    public Employee save(Employee emp) {
        return employeeRepo.save(emp);
    }
}
