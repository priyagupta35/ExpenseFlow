package com.priya.service;

// package com.priya.expensetracker.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.priya.dto.CategorySummaryResponse;
import com.priya.dto.MonthlySummaryResponse;
import com.priya.model.User;
import com.priya.repository.TransactionRepository;


@Service
public class SummaryService {

    @Autowired
    private TransactionRepository transactionRepository;

    public SummaryService() {
    }

    public SummaryService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public MonthlySummaryResponse getMonthlySummary(
            User user, int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(
            start.lengthOfMonth());
        BigDecimal income = transactionRepository
            .sumByUserIdAndTypeAndDateBetween(
                user.getId(), "INCOME", start, end);
        BigDecimal expense = transactionRepository
            .sumByUserIdAndTypeAndDateBetween(
                user.getId(), "EXPENSE", start, end);
        String monthLabel = YearMonth.of(year, month)
            .getMonth().name() + " " + year;
        return new MonthlySummaryResponse(
            monthLabel, income, expense);
    }

    public List<CategorySummaryResponse> getCategorySummary(
            User user) {
        return transactionRepository
            .sumByCategoryForUser(user.getId())
            .stream()
            .map(row -> new CategorySummaryResponse(
                (String) row[0],
                (BigDecimal) row[1]))
            .collect(Collectors.toList());
    }

    public MonthlySummaryResponse getOverallBalance(User user) {
        BigDecimal income = transactionRepository
            .sumByUserIdAndType(user.getId(), "INCOME");
        BigDecimal expense = transactionRepository
            .sumByUserIdAndType(user.getId(), "EXPENSE");
        return new MonthlySummaryResponse(
            "Overall", income, expense);
    }
}