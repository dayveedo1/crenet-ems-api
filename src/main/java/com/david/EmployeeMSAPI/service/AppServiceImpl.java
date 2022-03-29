package com.david.EmployeeMSAPI.service;

import com.david.EmployeeMSAPI.dto.UpdateUserDto;
import com.david.EmployeeMSAPI.model.*;
import com.david.EmployeeMSAPI.repo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class AppServiceImpl implements AppService, UserDetailsService {

    private final AppUserRepo appUserRepo;
    private final JobDeptRepo jobDeptRepo;
    private final RoleRepo roleRepo;
    private final WalletRepo walletRepo;
    private final SalaryRepo salaryRepo;
    private final PayrollRepo payrollRepo;


    private final BCryptPasswordEncoder passwordEncoder;

    Logger log = LoggerFactory.getLogger(AppServiceImpl.class);

    public AppServiceImpl(AppUserRepo appUserRepo,
                          JobDeptRepo jobDeptRepo,
                          RoleRepo roleRepo,
                          WalletRepo walletRepo,
                          SalaryRepo salaryRepo,
                          PayrollRepo payrollRepo,
                          BCryptPasswordEncoder passwordEncoder) {

        this.appUserRepo = appUserRepo;
        this.jobDeptRepo = jobDeptRepo;
        this.roleRepo = roleRepo;
        this.walletRepo = walletRepo;
        this.salaryRepo = salaryRepo;
        this.payrollRepo = payrollRepo;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = getUser(username);

        if (user == null) {
            log.error("User Not Found");
            throw new UsernameNotFoundException("User Not Found");
        } else {
            log.info("User Found in DB: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });

        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public AppUser saveUser(AppUser user) {

//        AppUser isUserExisting = getUser(user.getUsername());
//        if (isUserExisting != null){
//            log.error("User {} is already existing", user.getUsername());
//            throw new RuntimeException("User Existing");
//        }

        log.info("Saving new User {} in DB", user.getUsername());
        AppUser u = new AppUser();
        u.setUsername(user.getUsername());
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setAge(user.getAge());
        u.setEmail(user.getEmail());
        u.setDateCreated(new Date());
        u.setGender(user.getGender());
        u.setPassword(passwordEncoder.encode(user.getPassword()));

        List<Role> roles = new ArrayList<>();
        Role getRoleUser = getRoleByRoleName("ROLE_USER");
        roles.add(getRoleUser);
        u.setRoles(roles);

        Role getRoleName = getRoleByRoleName("ROLE_USER");
        AddRoleToUser(user.getUsername(), getRoleName.toString());

      Wallet wallet = new Wallet();
////        Random rand = new Random();
////        wallet.setId(rand.nextLong());
//        wallet.setUserId(u);
          wallet.setBalance(0.0);
          u.setWalletId(wallet);

        appUserRepo.save(u);
        //walletRepo.save(wallet);

//        Optional<Wallet> getWallet = getWalletById(wallet.getId());



        Optional<AppUser> getUser = getUserById(u.getId());
//        getUser.get().setWalletId(getWallet.get());
        //appUserRepo.save(getUser.get());
        return getUser.get();

    }

    @Override
    public AppUser getUser(String username) {
        log.info("Retrieving User {} from DB", username);
        return appUserRepo.findByUsername(username);
    }

    @Override
    public Page<AppUser> getAllUser() {
        log.info("Fetching All Users from DB");
        Pageable paging = PageRequest.of(0, 20);
        return appUserRepo.findAll(paging);
    }

    @Override
    public Optional<AppUser> getUserById(Long id) {
        log.info("Retrieving User {} from DB", id);
        return appUserRepo.findById(id);
    }

    @Override
    public void deleteUserById(Long id) throws UsernameNotFoundException {
        log.info("Retrieving User {} from DB", id);
        Optional<AppUser> user = getUserById(id);
        if (user.isPresent()) {
            log.info("Deleting User {} from DB", user.get());
            appUserRepo.delete(user.get());
        } else {
            throw new UsernameNotFoundException("User {} Not Found");
        }
    }

    @Override
    public AppUser UpdateUser(UpdateUserDto updateUserDto) {
        Optional<AppUser> user = getUserById(updateUserDto.getId());
        if (!user.isPresent()){
            throw new UsernameNotFoundException("User Not Found");
        }else{
            user.get().setEmail(updateUserDto.getEmail());
            user.get().setFirstName(updateUserDto.getFirstName());
            user.get().setLastName(updateUserDto.getLastName());
            user.get().setAge(updateUserDto.getAge());
            user.get().setGender(updateUserDto.getGender());
            user.get().setPassword(passwordEncoder.encode(updateUserDto.getPassword()));

            appUserRepo.save(user.get());

            return user.get();
        }


    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new Role {} in DB", role.getRoleName());
        return roleRepo.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleRepo.findByRoleName(roleName);
    }

    @Override
    public void AddRoleToUser(String username, String roleName) {
        AppUser user = getUser(username);
        Role role = getRoleByRoleName(roleName);

        if (user != null) {
            user.getRoles().add(role);
        }
    }

    @Override
    public void RemoveRoleFromUser(String username, String roleName) {
        AppUser user = getUser(username);

        if (user != null) {
            user.getRoles().remove(roleName);
        }
    }

    @Override
    public JobDept saveJobDept(JobDept jobDept) {
        log.info("Saving new JobDept {} in DB", jobDept);
        return jobDeptRepo.save(jobDept);
    }

    @Override
    public List<JobDept> getAllDept() {
        return jobDeptRepo.findAll();
    }

    @Override
    public Optional<JobDept> getJobDeptById(Long id) {
        return jobDeptRepo.findById(id);
    }

    @Override
    public JobDept getJobDeptByName(String name) {
        return jobDeptRepo.findByDeptName(name);
    }

    @Override
    public void deleteJobDeptById(Long id) {
        Optional<JobDept> jobDept = getJobDeptById(id);
        if (jobDept.isPresent()) {
            jobDeptRepo.delete(jobDept.get());
        }
        else {
            throw new RuntimeException("Record Not Found");
        }
    }

//    @Override
//    public JobDept addUserToJobDept(Long userId, String name) {
//        Optional<AppUser> user = getUserById(userId);
//        JobDept jobDept = getJobDeptByName(name);
//    }

    @Override
    public Salary saveSalary(Salary salary){
        return salaryRepo.save(salary);
    }

    @Override
    public Optional<Salary> getSalaryById(Long id) {
        return salaryRepo.findById(id);
    }

    @Override
    public void deleteSalary(Long id) {
        Optional<Salary> salary = getSalaryById(id);
        if(salary.isPresent()){
            salaryRepo.delete(salary.get());
        }else{
            throw new RuntimeException("Record Not Found");
        }
    }

    @Override
    public List<Salary> getAllSalary() {
        return salaryRepo.findAll();
    }

    @Override
    public Payroll createPayroll(Payroll payroll) {

        Optional<AppUser> user = getUserById(payroll.getUserId().getId());
        Optional<AppUser> getAdmin = getUserById(payroll.getAdminUserId().getId());
        if(user.isPresent()){
            double adminBalance= getAdmin.get().getWalletId().getBalance();
            getAdmin.get().getWalletId().setBalance(adminBalance - payroll.getAmount());

            Wallet walletId = user.get().getWalletId();
            double getUserWalletBalance = walletId.getBalance();
            walletId.setBalance(getUserWalletBalance + payroll.getAmount());

            appUserRepo.save(user.get());
            appUserRepo.save(getAdmin.get());
        }

//        Optional<JobDept> jobId = getJobDeptById(payroll.getJobId().getId());
//        if (jobId.isPresent()){
//
//        }
        return payrollRepo.save(payroll);
    }

    //    @Override
//    public Wallet createWallet(Wallet wallet) {
//        return walletRepo.save(wallet);
//    }
//
//    @Override
//    public Optional<Wallet> getWalletById(Long id) {
//        log.info("Retrieving Wallet {} from DB", id);
//        return walletRepo.findById(id);
//    }
}
