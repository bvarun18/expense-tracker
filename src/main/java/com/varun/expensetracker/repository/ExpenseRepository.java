package com.varun.expensetracker.repository;

import com.varun.expensetracker.dto.CategorySummaryResponse;
import com.varun.expensetracker.dto.MonthlySummaryResponse;
import com.varun.expensetracker.entity.Expense;
import com.varun.expensetracker.entity.ExpenseCategory;
import com.varun.expensetracker.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // CRUD
    List<Expense> findByUser(User user);

    Optional<Expense> findByIdAndUser(Long id, User user);

    List<Expense> findTop5ByUserOrderByExpenseDateDesc(User user);

    // Dashboard
    @Query("SELECT COALESCE(SUM(e.amount),0) FROM Expense e WHERE e.user=?1")
    BigDecimal getTotalExpense(User user);

    @Query("SELECT COUNT(e) FROM Expense e WHERE e.user=?1")
    Long getTotalTransactions(User user);

    @Query("SELECT COALESCE(MAX(e.amount),0) FROM Expense e WHERE e.user=?1")
    BigDecimal getHighestExpense(User user);

    @Query("SELECT COALESCE(AVG(e.amount),0) FROM Expense e WHERE e.user=?1")
    BigDecimal getAverageExpense(User user);

    @Query("""
            SELECT new com.varun.expensetracker.dto.CategorySummaryResponse(
            e.category,
            SUM(e.amount))
            FROM Expense e
            WHERE e.user=?1
            GROUP BY e.category
            """)
    List<CategorySummaryResponse> getCategorySummary(User user);

    @Query("""
            SELECT new com.varun.expensetracker.dto.MonthlySummaryResponse(
            MONTH(e.expenseDate),
            SUM(e.amount))
            FROM Expense e
            WHERE e.user=?1
            GROUP BY MONTH(e.expenseDate)
            ORDER BY MONTH(e.expenseDate)
            """)
    List<MonthlySummaryResponse> getMonthlySummary(User user);

    // SEARCH
    List<Expense> findByUserAndTitleContainingIgnoreCase(User user, String keyword);

    // CATEGORY FILTER
    List<Expense> findByUserAndCategory(User user, ExpenseCategory category);

    // DATE FILTER
    List<Expense> findByUserAndExpenseDateBetween(
            User user,
            LocalDate startDate,
            LocalDate endDate
    );

    // AMOUNT FILTER
    List<Expense> findByUserAndAmountBetween(
            User user,
            BigDecimal min,
            BigDecimal max
    );

    // PAGINATION + SORTING
    Page<Expense> findByUser(User user, Pageable pageable);

}