package com.priya.controller;
// package com.priya.expensetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.priya.dto.CategorySummaryResponse;
import com.priya.model.User;
import com.priya.service.SummaryService;


@RestController
@RequestMapping("/api/summary")
public class SummaryController {

    @Autowired
    private final SummaryService summaryService;

    public SummaryController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    // GET /api/summary/monthly?year=2026&month=9
    @GetMapping("/monthly")
    public ResponseEntity<Object> monthly(
            @AuthenticationPrincipal User user,
            @RequestParam int year,
            @RequestParam int month) {
        return ResponseEntity.ok(
            summaryService.getMonthlySummary(user, year, month));
    }

    // GET /api/summary/category-wise
    @GetMapping("/category-wise")
    public ResponseEntity<List<CategorySummaryResponse>> byCategory(
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(
            summaryService.getCategorySummary(user));
    }

    // GET /api/summary/balance
    @GetMapping("/balance")
    public ResponseEntity<Object> balance(
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(
            summaryService.getOverallBalance(user));
    }

    public SummaryService getSummaryService() {
        return summaryService;
    }
}