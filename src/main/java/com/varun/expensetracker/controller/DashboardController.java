package com.varun.expensetracker.controller;

import com.varun.expensetracker.dto.ApiResponse;
import com.varun.expensetracker.dto.CategorySummaryResponse;
import com.varun.expensetracker.dto.DashboardSummaryResponse;
import com.varun.expensetracker.dto.ExpenseResponse;
import com.varun.expensetracker.dto.MonthlySummaryResponse;
import com.varun.expensetracker.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // Dashboard Summary
    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<DashboardSummaryResponse>> getSummary(
            @AuthenticationPrincipal UserDetails userDetails) {

        DashboardSummaryResponse response =
                dashboardService.getSummary(userDetails);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Dashboard summary fetched successfully",
                        response
                )
        );
    }

    // Category Summary
    @GetMapping("/category-summary")
    public ResponseEntity<ApiResponse<List<CategorySummaryResponse>>> getCategorySummary(
            @AuthenticationPrincipal UserDetails userDetails) {

        List<CategorySummaryResponse> response =
                dashboardService.getCategorySummary(userDetails);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Category summary fetched successfully",
                        response
                )
        );
    }

    // Monthly Summary
    @GetMapping("/monthly-summary")
    public ResponseEntity<ApiResponse<List<MonthlySummaryResponse>>> getMonthlySummary(
            @AuthenticationPrincipal UserDetails userDetails) {

        List<MonthlySummaryResponse> response =
                dashboardService.getMonthlySummary(userDetails);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Monthly summary fetched successfully",
                        response
                )
        );
    }

    // Recent Expenses
    @GetMapping("/recent-expenses")
    public ResponseEntity<ApiResponse<List<ExpenseResponse>>> getRecentExpenses(
            @AuthenticationPrincipal UserDetails userDetails) {

        List<ExpenseResponse> response =
                dashboardService.getRecentExpenses(userDetails);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Recent expenses fetched successfully",
                        response
                )
        );
    }
}