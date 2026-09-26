package com.priya.dto;
// package com.priya.expensetracker.dto;

import java.math.BigDecimal;

public class MonthlySummaryResponse {
    private final String month;
    private final BigDecimal totalIncome;
    private final BigDecimal totalExpense;
    private final BigDecimal balance;

    public MonthlySummaryResponse(String month,
            BigDecimal totalIncome, BigDecimal totalExpense) {
        this.month = month;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.balance = totalIncome.subtract(totalExpense);
    }

    public String getMonth() { return month; }
    public BigDecimal getTotalIncome() { return totalIncome; }
    public BigDecimal getTotalExpense() { return totalExpense; }
    public BigDecimal getBalance() { return balance; }
}