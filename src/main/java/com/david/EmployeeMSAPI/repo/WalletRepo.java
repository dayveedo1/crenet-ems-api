package com.david.EmployeeMSAPI.repo;

import com.david.EmployeeMSAPI.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepo extends JpaRepository<Wallet, Long> {
}
