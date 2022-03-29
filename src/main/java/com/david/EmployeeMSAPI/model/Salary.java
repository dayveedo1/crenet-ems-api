package com.david.EmployeeMSAPI.model;

import javax.persistence.*;

@Entity
@Table(name = "Salary")
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "jobId", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private JobDept jobId;

    @Column(name = "amount")
    private double amount;

    public Salary() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobDept getJobId() {
        return jobId;
    }

    public void setJobId(JobDept jobId) {
        this.jobId = jobId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", jobId=" + jobId +
                ", amount=" + amount +
                '}';
    }
}
