package com.pay.repository;

import com.pay.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    List<Wallet> findByUserId(Long userId);

    Optional<Wallet> findByBankAccountNumber(String bankAccountNumber);

    boolean existsByBankAccountNumber(String bankAccountNumber);

    boolean existsByUserIdAndName(Long userId, String name);

    Wallet getReferenceByBankAccountNumber(String bankAccountNumber);
}
