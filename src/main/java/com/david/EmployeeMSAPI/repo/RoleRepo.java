package com.david.EmployeeMSAPI.repo;

import com.david.EmployeeMSAPI.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);
}
