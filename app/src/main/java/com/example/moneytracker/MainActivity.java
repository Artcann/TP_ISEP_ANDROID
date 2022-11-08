package com.example.moneytracker;

import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.moneytracker.domain.enums.ExpenseType;
import com.example.moneytracker.domain.viewModels.ExpenseViewModel;
import com.example.moneytracker.infrastructure.entities.Expense;
import com.example.moneytracker.presentation.ExpenseListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ExpenseViewModel expenseViewModel;

    public static final int NEW_EXPENSE_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ExpenseListAdapter adapter = new ExpenseListAdapter(new ExpenseListAdapter.ExpenseDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);

        expenseViewModel.getAllExpenses().observe(this, adapter::submitList);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
            startActivityForResult(intent, NEW_EXPENSE_ACTIVITY_REQUEST_CODE);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_EXPENSE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Expense expense = new Expense(data.getStringExtra(NewWordActivity.EXTRA_REPLY),
                    ExpenseType.valueOf(data.getStringExtra("spinner")),
                    new Date(),
                    Double.valueOf(data.getStringExtra("amount")));
            expenseViewModel.insert(expense);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}