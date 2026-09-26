package com.priya.dto;

// ackage com.priya.expensetracker.dto;

import java.math.BigDecimal;

public class CategorySummaryResponse {
    private final String categoryName;
    private final BigDecimal total;

    public CategorySummaryResponse(String categoryName,
            BigDecimal total) {
        this.categoryName = categoryName;
        this.total = total;
    }

    public String getCategoryName() { return categoryName; }
    public BigDecimal getTotal() { return total; }
}