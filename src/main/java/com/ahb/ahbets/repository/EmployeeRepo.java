package com.ahb.ahbets.repository;

import com.ahb.ahbets.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, String> {
}
