package com.david.EmployeeMSAPI.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Wallet")
public class Wallet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "walletId")
    //@JoinColumn(name = "userId", referencedColumnName = "id")
    @JsonBackReference
    private AppUser userId;

    @Column(name = "balance")
    private double balance;

    public Wallet() {
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                //", userId=" + userId +
                ", balance=" + balance +
                '}';
    }
}
