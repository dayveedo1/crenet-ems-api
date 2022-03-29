package com.david.EmployeeMSAPI.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "JobDept")
public class JobDept implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "deptName")
    private String deptName;
    @Column(name = "jobName")
    private String jobName;
    @Column(name = "description")
    private String description;
//    @Column(name = "salary")
//    private double salary;

//    @OneToMany
//    private List<Salary> salaries;

    public JobDept() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public double getSalary() {
//        return salary;
//    }
//
//    public void setSalary(double salary) {
//        this.salary = salary;
//    }
//
//    public List<Salary> getSalaries() {
//        return salaries;
//    }
//
//    public void setSalaries(List<Salary> salaries) {
//        this.salaries = salaries;
//    }

    @Override
    public String toString() {
        return "JobDept{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", jobName='" + jobName + '\'' +
                ", description='" + description + '\'' +
//                ", salary=" + salary +
//                ", salaries=" + salaries +
                '}';
    }
}
