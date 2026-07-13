package com.varun.expensetracker.controller;

import com.varun.expensetracker.dto.ApiResponse;
import com.varun.expensetracker.dto.ExpenseRequest;
import com.varun.expensetracker.dto.ExpenseResponse;
import com.varun.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // Add Expense
    @PostMapping
    public ResponseEntity<ApiResponse<ExpenseResponse>> addExpense(
            @Valid @RequestBody ExpenseRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {

        ExpenseResponse response =
                expenseService.addExpense(request, userDetails);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Expense added successfully",
                        response
                ));
    }

    // Get All Expenses
    @GetMapping
    public ResponseEntity<ApiResponse<List<ExpenseResponse>>> getAllExpenses(
            @AuthenticationPrincipal UserDetails userDetails) {

        List<ExpenseResponse> response =
                expenseService.getAllExpenses(userDetails);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Expenses fetched successfully",
                        response
                )
        );
    }

    // Get Expense By Id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ExpenseResponse>> getExpenseById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        ExpenseResponse response =
                expenseService.getExpenseById(id, userDetails);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Expense fetched successfully",
                        response
                )
        );
    }

    // Update Expense
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ExpenseResponse>> updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody ExpenseRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {

        ExpenseResponse response =
                expenseService.updateExpense(id, request, userDetails);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Expense updated successfully",
                        response
                )
        );
    }

    // Delete Expense
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteExpense(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        String response =
                expenseService.deleteExpense(id, userDetails);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        response,
                        null
                )
        );
    }
}