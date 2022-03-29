package com.david.EmployeeMSAPI.service;

import com.david.EmployeeMSAPI.dto.UpdateUserDto;
import com.david.EmployeeMSAPI.model.*;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface AppService {

    AppUser saveUser(AppUser user);
    AppUser getUser(String username);
    Page<AppUser> getAllUser();
    Optional<AppUser> getUserById(Long id);
    AppUser UpdateUser(UpdateUserDto updateUserDto);
    void deleteUserById(Long id) throws UsernameNotFoundException;

    Role saveRole(Role role);
    List<Role> getAllRoles();
    Role getRoleByRoleName(String roleName);
    void AddRoleToUser(String username, String roleName);
    void RemoveRoleFromUser(String username, String roleName);

    JobDept saveJobDept(JobDept jobDept);
    List<JobDept> getAllDept();
    Optional<JobDept> getJobDeptById(Long id);
    JobDept getJobDeptByName(String name);
    void deleteJobDeptById(Long id);
    //JobDept addUserToJobDept(Long userId, String name);

//    Wallet createWallet(Wallet wallet);
//    Optional<Wallet> getWalletById(Long id);

    Salary saveSalary(Salary salary);
    void deleteSalary(Long id);
    List<Salary> getAllSalary();
    Optional<Salary> getSalaryById(Long id);

    Payroll createPayroll(Payroll payroll);

}
