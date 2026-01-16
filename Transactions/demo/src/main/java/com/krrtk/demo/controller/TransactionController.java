package com.krrtk.demo.controller;

import com.krrtk.demo.dto.TransferDto;
import com.krrtk.demo.service.BankingService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class TransactionController {

    private final BankingService bankingService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferDto dto) {
        bankingService.transferMoney(dto);
        return ResponseEntity.ok("Transfer successful");
    }
}
