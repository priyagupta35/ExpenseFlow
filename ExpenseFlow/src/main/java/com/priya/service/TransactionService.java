package com.priya.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.priya.dto.TransactionRequest;
import com.priya.dto.TransactionResponse;
import com.priya.model.Category;
import com.priya.model.Transaction;
import com.priya.model.User;
import com.priya.repository.CategoryRepository;
import com.priya.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    public TransactionService(
            CategoryRepository categoryRepository,
            TransactionRepository transactionRepository) {

        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
    }

    // Get all transactions for the logged-in user
    public List<TransactionResponse> getAllTransactions(User user) {

        return transactionRepository
                .findByUserId(user.getId())
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Get transactions by type
    public List<TransactionResponse> getByType(
            User user,
            String type) {

        return transactionRepository
                .findByUserIdAndType(
                        user.getId(),
                        type.toUpperCase())
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Get transactions for a particular month
    public List<TransactionResponse> getByMonth(
            User user,
            int year,
            int month) {

        LocalDate start = LocalDate.of(year, month, 1);

        LocalDate end = start.withDayOfMonth(
                start.lengthOfMonth());

        return transactionRepository
                .findByUserIdAndDateBetween(
                        user.getId(),
                        start,
                        end)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Create a transaction
    public TransactionResponse createTransaction(
            TransactionRequest request,
            User user) {

        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException(
                        "Category not found"));

        Transaction transaction = new Transaction();

        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());
        transaction.setType(request.getType().toUpperCase());
        transaction.setDate(request.getDate());
        transaction.setCategory(category);
        transaction.setUser(user);

        Transaction savedTransaction =
                transactionRepository.save(transaction);

        return toResponse(savedTransaction);
    }

    // Delete a transaction
    public void deleteTransaction(
            int id,
            User user) {

        Transaction transaction = transactionRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Transaction not found"));

        // Make sure the logged-in user owns this transaction
        if (transaction.getUser().getId() != user.getId()) {
            throw new RuntimeException(
                    "Not authorised to delete this transaction");
        }

        transactionRepository.delete(transaction);
    }

    // Convert Entity to DTO
    private TransactionResponse toResponse(
            Transaction transaction) {

        return new TransactionResponse(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getType(),
                transaction.getDate(),
                transaction.getCategory().getName()
        );
    }
}