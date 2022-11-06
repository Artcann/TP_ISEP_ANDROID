package com.example.moneytracker.domain.viewModels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.moneytracker.infrastructure.entities.Expense;
import com.example.moneytracker.infrastructure.repository.ExpenseRepository;

import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {

    private final ExpenseRepository expenseRepository;

    private final LiveData<List<Expense>> allExpenses;

    public ExpenseViewModel(Application application) {
        super(application);
        expenseRepository = new ExpenseRepository(application);
        allExpenses = expenseRepository.getAllExpenses();
    }

    public LiveData<List<Expense>> getAllExpenses() {return allExpenses;}

    public void insert(Expense expense) {expenseRepository.insert(expense);}
}
