package com.david.EmployeeMSAPI.repo;

import com.david.EmployeeMSAPI.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalaryRepo extends JpaRepository<Salary, Long> {
    Optional<Salary> findById(Long id);
}
