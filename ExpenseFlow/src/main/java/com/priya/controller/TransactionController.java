package com.priya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.priya.dto.TransactionRequest;
import com.priya.dto.TransactionResponse;
import com.priya.model.User;
import com.priya.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    public TransactionController() {
    }

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Get transactions
    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getTransactions(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month) {

        if (type != null) {
            return ResponseEntity.ok(
                    transactionService.getByType(user, type)
            );
        }

        if (year != null && month != null) {
            return ResponseEntity.ok(
                    transactionService.getByMonth(user, year, month)
            );
        }

        return ResponseEntity.ok(
                transactionService.getAllTransactions(user)
        );
    }

    // Create transaction
    @PostMapping
    public ResponseEntity<TransactionResponse> create(
            @RequestBody TransactionRequest request,
            @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(
                transactionService.createTransaction(request, user)
        );
    }

    // Delete transaction
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable int id,
            @AuthenticationPrincipal User user) {

        transactionService.deleteTransaction(id, user);

        return ResponseEntity.noContent().build();
    }
}