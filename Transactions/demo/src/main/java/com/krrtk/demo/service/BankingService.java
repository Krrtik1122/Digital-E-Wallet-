package com.krrtk.demo.service;

import com.krrtk.demo.dto.TransferDto;
import com.krrtk.demo.enums.TransactionStatus;
import com.krrtk.demo.dto.UserRequest;
import com.krrtk.demo.entity.Transaction;
import com.krrtk.demo.entity.User;
import com.krrtk.demo.enums.TransactionStatus;
import com.krrtk.demo.repository.TransactionRepository;
import com.krrtk.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.security.crypto.password.PasswordEncoder;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BankingService {

  //  private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    
    public User createUser(UserRequest userRequest) {

        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setBalance(userRequest.getInitialBalance());
        user.setPassword(userRequest.getPassword());


        return userRepository.save(user);
    }


    public BigDecimal getBalance(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getBalance();
    }

    public User addMoney(Long userId, BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be greater than zero");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setBalance(user.getBalance().add(amount));
        return userRepository.save(user);
    }

    
    @Transactional
    public void transferMoney(TransferDto dto) {

        Transaction tx = new Transaction();
        tx.setFromUserId(dto.getFromUserId());
        tx.setToUserId(dto.getToUserId());
        tx.setAmount(dto.getAmount());
        tx.setStatus(TransactionStatus.PENDING);
        tx.setCreatedAt(LocalDateTime.now());

        transactionRepository.save(tx);

        User fromUser = userRepository.findByIdForUpdate(dto.getFromUserId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        User toUser = userRepository.findById(dto.getToUserId())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        if (fromUser.getBalance().compareTo(dto.getAmount()) < 0) {
            tx.setStatus(TransactionStatus.FAILED);
            throw new RuntimeException("Insufficient balance");
        }

        fromUser.setBalance(fromUser.getBalance().subtract(dto.getAmount()));
        toUser.setBalance(toUser.getBalance().add(dto.getAmount()));

        userRepository.save(fromUser);
        userRepository.save(toUser);

        tx.setStatus(TransactionStatus.SUCCESS);
    }
    public UserDetails loadUserByUsername(String username) {

        User user = userRepository.findByUserName(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                new ArrayList<>()
        );
    }

}

