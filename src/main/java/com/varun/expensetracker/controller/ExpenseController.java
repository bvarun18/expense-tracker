package com.varun.expensetracker.controller;

import com.varun.expensetracker.dto.ApiResponse;
import com.varun.expensetracker.dto.ExpenseRequest;
import com.varun.expensetracker.dto.ExpenseResponse;
import com.varun.expensetracker.entity.ExpenseCategory;
import com.varun.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // ==========================
    // Add Expense
    // ==========================

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

    // ==========================
    // Get All Expenses
    // ==========================

    @GetMapping("/all")
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

    // ==========================
    // Get Expense By Id
    // ==========================

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

    // ==========================
    // Update Expense
    // ==========================

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

    // ==========================
    // Delete Expense
    // ==========================

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

    // ==========================
    // Search Expenses
    // ==========================

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ExpenseResponse>>> searchExpenses(
            @RequestParam String keyword,
            @AuthenticationPrincipal UserDetails userDetails) {

        List<ExpenseResponse> response =
                expenseService.searchExpenses(keyword, userDetails);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Search completed successfully",
                        response
                )
        );
    }

    // ==========================
    // Filter By Category
    // ==========================

    @GetMapping("/filter/category")
    public ResponseEntity<ApiResponse<List<ExpenseResponse>>> filterByCategory(
            @RequestParam ExpenseCategory category,
            @AuthenticationPrincipal UserDetails userDetails) {

        List<ExpenseResponse> response =
                expenseService.filterByCategory(category, userDetails);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Category filter applied",
                        response
                )
        );
    }

    // ==========================
    // Filter By Date
    // ==========================

    @GetMapping("/filter/date")
    public ResponseEntity<ApiResponse<List<ExpenseResponse>>> filterByDate(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @AuthenticationPrincipal UserDetails userDetails) {

        List<ExpenseResponse> response =
                expenseService.filterByDate(
                        startDate,
                        endDate,
                        userDetails
                );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Date filter applied",
                        response
                )
        );
    }

    // ==========================
    // Filter By Amount
    // ==========================

    @GetMapping("/filter/amount")
    public ResponseEntity<ApiResponse<List<ExpenseResponse>>> filterByAmount(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max,
            @AuthenticationPrincipal UserDetails userDetails) {

        List<ExpenseResponse> response =
                expenseService.filterByAmount(
                        min,
                        max,
                        userDetails
                );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Amount filter applied",
                        response
                )
        );
    }

    // ==========================
    // Pagination + Sorting
    // ==========================

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ExpenseResponse>>> getExpenses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "expenseDate") String sortBy,
            @RequestParam(defaultValue = "desc") String direction,
            @AuthenticationPrincipal UserDetails userDetails) {

        Page<ExpenseResponse> response =
                expenseService.getExpenses(
                        page,
                        size,
                        sortBy,
                        direction,
                        userDetails
                );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Expenses fetched successfully",
                        response
                )
        );
    }
}