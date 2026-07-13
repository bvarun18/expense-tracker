package com.varun.expensetracker.service;

import com.varun.expensetracker.dto.ExpenseRequest;
import com.varun.expensetracker.dto.ExpenseResponse;
import com.varun.expensetracker.entity.Expense;
import com.varun.expensetracker.entity.User;
import com.varun.expensetracker.exception.ExpenseNotFoundException;
import com.varun.expensetracker.exception.UserNotFoundException;
import com.varun.expensetracker.repository.ExpenseRepository;
import com.varun.expensetracker.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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

    // Get Logged-in User
    private User getCurrentUser(UserDetails userDetails) {

        return userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));
    }

    // Entity -> DTO
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

    // Add Expense
    public ExpenseResponse addExpense(ExpenseRequest request,
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

    // Get All Expenses
    public List<ExpenseResponse> getAllExpenses(UserDetails userDetails) {

        User user = getCurrentUser(userDetails);

        return expenseRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Get Expense By Id
    public ExpenseResponse getExpenseById(Long id,
                                          UserDetails userDetails) {

        User user = getCurrentUser(userDetails);

        Expense expense = expenseRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ExpenseNotFoundException("Expense not found"));

        return mapToResponse(expense);
    }

    // Update Expense
    public ExpenseResponse updateExpense(Long id,
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

    // Delete Expense
    public String deleteExpense(Long id,
                                UserDetails userDetails) {

        User user = getCurrentUser(userDetails);

        Expense expense = expenseRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ExpenseNotFoundException("Expense not found"));

        expenseRepository.delete(expense);

        return "Expense deleted successfully";
    }

}