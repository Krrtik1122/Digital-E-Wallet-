package com.krrtk.demo.repository;

import com.krrtk.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Transactions initiated by a user
    List<Transaction> findByFromUserId(Long fromUserId);

    // Transactions received by a user
    List<Transaction> findByToUserId(Long toUserId);
}
