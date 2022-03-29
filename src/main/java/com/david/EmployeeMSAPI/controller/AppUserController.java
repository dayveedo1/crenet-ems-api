package com.david.EmployeeMSAPI.controller;

import com.david.EmployeeMSAPI.model.AppUser;
import com.david.EmployeeMSAPI.model.JobDept;
import com.david.EmployeeMSAPI.model.Payroll;
import com.david.EmployeeMSAPI.model.Salary;
import com.david.EmployeeMSAPI.service.AppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@Api(tags = "user")
@RequestMapping("/api/user")
public class AppUserController {

    private final AppService service;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public static final String SECRET = "secret";

    Logger log = LoggerFactory.getLogger(AppUserController.class);

    public AppUserController(AppService service, AuthenticationManager authenticationManager, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @ApiOperation("To return list of users")
    @GetMapping("/getAllUser")
    public ResponseEntity<Page<AppUser>> getAllUser(){
        return ResponseEntity.ok().body(service.getAllUser());
    }

   // @Secured("ROLE_ADMIN")
    @ApiOperation("To save a user")
    @PostMapping("/saveUser")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/saveUser").toUriString());
        return ResponseEntity.created(uri).body(service.saveUser(user));
    }

    @ApiOperation("To return user by ID")
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable("id") Long id){
        Optional<AppUser> user = service.getUserById(id);
        return ResponseEntity.ok().body(user.get());
    }

    @ApiOperation("To delete a user by Id")
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") Long id){
        service.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body("User Deleted Successfully");
    }

    @ApiOperation("To create a payroll record")
    @PostMapping("/payroll")
    public ResponseEntity<Payroll> savePayroll(@RequestBody Payroll payroll){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/payroll").toUriString());
        return ResponseEntity.created(uri).body(service.createPayroll(payroll));

    }

    @ApiOperation("To save a salary record")
    @PostMapping("/saveSalary")
    public ResponseEntity<Salary> saveSalary(@RequestBody Salary salary){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/saveSalary").toUriString());
        return ResponseEntity.created(uri).body(service.saveSalary(salary));

    }

    @ApiOperation("To save job record")
    @PostMapping("/saveJobDept")
    public ResponseEntity<JobDept> saveJobDept(@RequestBody JobDept jobDept){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/saveJobDept").toUriString());
        return ResponseEntity.created(uri).body(service.saveJobDept(jobDept));
    }
//    @ApiOperation("To return wallet by ID")
//    @GetMapping("/getWalletById/{id}")
//    public ResponseEntity<Wallet> getWalletById(@PathVariable("id") Long id){
//        Optional<Wallet> getWallet = service.getWalletById(id);
//        return ResponseEntity.ok().body(getWallet.get());
//    }
}
