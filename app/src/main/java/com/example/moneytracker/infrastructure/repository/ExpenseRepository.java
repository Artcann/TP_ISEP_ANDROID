package com.example.moneytracker.infrastructure.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.moneytracker.infrastructure.DAO.ExpenseDAO;
import com.example.moneytracker.infrastructure.RoomDatabase;
import com.example.moneytracker.infrastructure.entities.Expense;

import java.util.List;

public class ExpenseRepository {

    private final ExpenseDAO expenseDAO;

    private final LiveData<List<Expense>> allExpenses;

    public ExpenseRepository(Application application) {
        RoomDatabase db = RoomDatabase.getDatabase(application);
        expenseDAO = db.expenseDAO();
        allExpenses = expenseDAO.getAllExpense();
    }

    public LiveData<List<Expense>> getAllExpenses() {return allExpenses;}

    public void insert(Expense expense) {RoomDatabase.databaseWriteExecutor.execute(() -> expenseDAO.insert(expense));}
}
