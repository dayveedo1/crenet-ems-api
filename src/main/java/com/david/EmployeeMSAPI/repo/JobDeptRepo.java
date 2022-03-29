package com.david.EmployeeMSAPI.repo;

import com.david.EmployeeMSAPI.model.JobDept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDeptRepo extends JpaRepository<JobDept, Long> {

    JobDept findByDeptName(String name);
}
