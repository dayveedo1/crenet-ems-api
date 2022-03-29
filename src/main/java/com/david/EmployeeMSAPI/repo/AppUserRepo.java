package com.david.EmployeeMSAPI.repo;

import com.david.EmployeeMSAPI.model.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username);
    Page<AppUser> findAll(Pageable pageable);
}
