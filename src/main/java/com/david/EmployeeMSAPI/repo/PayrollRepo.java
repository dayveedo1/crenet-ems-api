package com.david.EmployeeMSAPI.repo;

import com.david.EmployeeMSAPI.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayrollRepo extends JpaRepository<Payroll, Long> {
}
