package com.example.moneytracker.infrastructure.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.moneytracker.domain.enums.ExpenseType;
import com.example.moneytracker.infrastructure.entities.Expense;

import java.util.List;

@Dao
public interface ExpenseDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Expense expense);

    @Delete
    void deleteAll(Expense... expenses);

    @Query("SELECT * FROM expense_table ORDER BY EXPENSE_DATE")
    LiveData<List<Expense>> getAllExpense();

    @Query("SELECT * FROM expense_table WHERE TYPE = :type")
    List<Expense> fetchExpenseByType(ExpenseType type);
}
