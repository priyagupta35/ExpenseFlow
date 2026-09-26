package com.priya.dto;

// package com.priya.expensetracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionRequest {
    private BigDecimal amount;
    private String description;
    private String type;
    private LocalDate date;
    private int categoryId;

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
}
