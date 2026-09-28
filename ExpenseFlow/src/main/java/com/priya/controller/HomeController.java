package com.priya.controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> home() {
        return ResponseEntity.ok(Map.of(
            "project", "ExpenseFlow - Personal Finance REST API",
            "author", "Priya Gupta",
            "status", "UP & RUNNING",
            "swaggerUi", "/swagger-ui/index.html",
            "endpoints", Map.of(
                "auth", "/api/auth/register, /api/auth/login",
                "categories", "/api/categories",
                "transactions", "/api/transactions",
                "summary", "/api/summary/monthly, /api/summary/balance"
            )
        ));
    }
}