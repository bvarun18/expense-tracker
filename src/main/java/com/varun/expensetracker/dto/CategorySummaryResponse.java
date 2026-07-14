package com.varun.expensetracker.dto;

import com.varun.expensetracker.entity.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CategorySummaryResponse {

    private ExpenseCategory category;

    private BigDecimal totalAmount;

}