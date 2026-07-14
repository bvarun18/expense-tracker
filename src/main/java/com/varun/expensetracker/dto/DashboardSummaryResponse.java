package com.varun.expensetracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DashboardSummaryResponse {

    private BigDecimal totalExpenses;

    private Long totalTransactions;

    private BigDecimal highestExpense;

    private BigDecimal averageExpense;

}