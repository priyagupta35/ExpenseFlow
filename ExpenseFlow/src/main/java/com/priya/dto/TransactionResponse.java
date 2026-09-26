package com.priya.dto;

// package com.priya.expensetracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionResponse {
    private final int id;
    private final BigDecimal amount;
    private final String description;
    private final String type;
    private final LocalDate date;
    private final String categoryName;

    public TransactionResponse(int id, BigDecimal amount,
            String description, String type,
            LocalDate date, String categoryName) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.type = type;
        this.date = date;
        this.categoryName = categoryName;
    }

    public int getId() { return id; }
    public BigDecimal getAmount() { return amount; }
    public String getDescription() { return description; }
    public String getType() { return type; }
    public LocalDate getDate() { return date; }
    public String getCategoryName() { return categoryName; }
}
