package com.varun.expensetracker.service;

import com.varun.expensetracker.dto.ExpenseRequest;
import com.varun.expensetracker.dto.ExpenseResponse;
import com.varun.expensetracker.entity.Expense;
import com.varun.expensetracker.entity.ExpenseCategory;
import com.varun.expensetracker.entity.User;
import com.varun.expensetracker.exception.ExpenseNotFoundException;
import com.varun.expensetracker.exception.UserNotFoundException;
import com.varun.expensetracker.repository.ExpenseRepository;
import com.varun.expensetracker.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository,
                          UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    // ==========================
    // Logged-in User
    // ==========================

    private User getCurrentUser(UserDetails userDetails) {

        return userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));
    }

    // ==========================
    // Entity -> DTO
    // ==========================

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

    // ==========================
    // Add Expense
    // ==========================

    public ExpenseResponse addExpense(
            ExpenseRequest request,
            UserDetails userDetails) {

        User user = getCurrentUser(userDetails);

        Expense expense = new Expense();

        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setDescription(request.getDescription());
        expense.setExpenseDate(request.getExpenseDate());
        expense.setUser(user);

        Expense savedExpense = expenseRepository.save(expense);

        return mapToResponse(savedExpense);
    }

    // ==========================
    // Get All Expenses
    // ==========================

    public List<ExpenseResponse> getAllExpenses(
            UserDetails userDetails) {

        User user = getCurrentUser(userDetails);

        return expenseRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ==========================
    // Get Expense By Id
    // ==========================

    public ExpenseResponse getExpenseById(
            Long id,
            UserDetails userDetails) {

        User user = getCurrentUser(userDetails);

        Expense expense = expenseRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ExpenseNotFoundException("Expense not found"));

        return mapToResponse(expense);
    }

    // ==========================
    // Update Expense
    // ==========================

    public ExpenseResponse updateExpense(
            Long id,
            ExpenseRequest request,
            UserDetails userDetails) {

        User user = getCurrentUser(userDetails);

        Expense expense = expenseRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ExpenseNotFoundException("Expense not found"));

        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setDescription(request.getDescription());
        expense.setExpenseDate(request.getExpenseDate());

        Expense updatedExpense = expenseRepository.save(expense);

        return mapToResponse(updatedExpense);
    }

    // ==========================
    // Delete Expense
    // ==========================

    public String deleteExpense(
            Long id,
            UserDetails userDetails) {

        User user = getCurrentUser(userDetails);

        Expense expense = expenseRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ExpenseNotFoundException("Expense not found"));

        expenseRepository.delete(expense);

        return "Expense deleted successfully";
    }

    // ==========================
    // Search Expense
    // ==========================

    public List<ExpenseResponse> searchExpenses(
            String keyword,
            UserDetails userDetails) {

        User user = getCurrentUser(userDetails);

        return expenseRepository
                .findByUserAndTitleContainingIgnoreCase(user, keyword)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ==========================
    // Filter By Category
    // ==========================

    public List<ExpenseResponse> filterByCategory(
            ExpenseCategory category,
            UserDetails userDetails) {

        User user = getCurrentUser(userDetails);

        return expenseRepository
                .findByUserAndCategory(user, category)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ==========================
    // Filter By Date
    // ==========================

    public List<ExpenseResponse> filterByDate(
            LocalDate startDate,
            LocalDate endDate,
            UserDetails userDetails) {

        User user = getCurrentUser(userDetails);

        return expenseRepository
                .findByUserAndExpenseDateBetween(
                        user,
                        startDate,
                        endDate
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ==========================
    // Filter By Amount
    // ==========================

    public List<ExpenseResponse> filterByAmount(
            BigDecimal min,
            BigDecimal max,
            UserDetails userDetails) {

        User user = getCurrentUser(userDetails);

        return expenseRepository
                .findByUserAndAmountBetween(
                        user,
                        min,
                        max
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ==========================
    // Pagination + Sorting
    // ==========================

    public Page<ExpenseResponse> getExpenses(
            int page,
            int size,
            String sortBy,
            String direction,
            UserDetails userDetails) {

        User user = getCurrentUser(userDetails);

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return expenseRepository
                .findByUser(user, pageable)
                .map(this::mapToResponse);
    }

}