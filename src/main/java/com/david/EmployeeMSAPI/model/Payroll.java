package com.david.EmployeeMSAPI.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Payroll")
public class Payroll implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonManagedReference
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private AppUser userId;

    @JsonManagedReference
    @JoinColumn(name = "adminUserId", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private AppUser adminUserId;

    //@JsonIgnore
//    @JoinColumn(name = "jobId", referencedColumnName = "id")
//    @ManyToOne(cascade = CascadeType.REFRESH)
//    private JobDept jobId;

    @JsonManagedReference
    @JoinColumn(name = "salaryId", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Salary salaryId;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "amount")
    private double amount;

    public Payroll() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getUserId() {
        return userId;
    }

    public void setUserId(AppUser userId) {
        this.userId = userId;
    }

//    public JobDept getJobId() {
//        return jobId;
//    }
//
//    public void setJobId(JobDept jobId) {
//        this.jobId = jobId;
//    }

    public Salary getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Salary salaryId) {
        this.salaryId = salaryId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public AppUser getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(AppUser adminUserId) {
        this.adminUserId = adminUserId;
    }

    @Override
    public String toString() {
        return "Payroll{" +
                "id=" + id +
                ", userId=" + userId +
                ", adminUserId=" + adminUserId +
                ", salaryId=" + salaryId +
                ", date=" + date +
                ", amount=" + amount +
                '}';
    }
}
