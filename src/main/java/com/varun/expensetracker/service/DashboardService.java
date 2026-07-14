package com.varun.expensetracker.service;

import com.varun.expensetracker.dto.CategorySummaryResponse;
import com.varun.expensetracker.dto.DashboardSummaryResponse;
import com.varun.expensetracker.dto.ExpenseResponse;
import com.varun.expensetracker.dto.MonthlySummaryResponse;
import com.varun.expensetracker.entity.Expense;
import com.varun.expensetracker.entity.User;
import com.varun.expensetracker.exception.UserNotFoundException;
import com.varun.expensetracker.repository.ExpenseRepository;
import com.varun.expensetracker.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public DashboardService(ExpenseRepository expenseRepository,
                            UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser(UserDetails userDetails) {

        return userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));
    }

    private ExpenseResponse mapToResponse(Expense expense) {

        return new ExpenseResponse(
                expense.getId(),
                expense.getTitle(),
                expense.getAmount(),
                expense.getCategory(),
                expense.getDescription(),
                expense.getExpenseDate()
        );
    }

    // Dashboard Summary
    public DashboardSummaryResponse getSummary(UserDetails userDetails) {

        User user = getCurrentUser(userDetails);

        return new DashboardSummaryResponse(
                expenseRepository.getTotalExpense(user),
                expenseRepository.getTotalTransactions(user),
                expenseRepository.getHighestExpense(user),
                expenseRepository.getAverageExpense(user)
        );
    }

    // Category Summary
    public List<CategorySummaryResponse> getCategorySummary(
            UserDetails userDetails) {

        User user = getCurrentUser(userDetails);

        return expenseRepository.getCategorySummary(user);
    }

    // Monthly Summary
    public List<MonthlySummaryResponse> getMonthlySummary(
            UserDetails userDetails) {

        User user = getCurrentUser(userDetails);

        return expenseRepository.getMonthlySummary(user);
    }

    // Recent Expenses
    public List<ExpenseResponse> getRecentExpenses(
            UserDetails userDetails) {

        User user = getCurrentUser(userDetails);

        return expenseRepository.findTop5ByUserOrderByExpenseDateDesc(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
}