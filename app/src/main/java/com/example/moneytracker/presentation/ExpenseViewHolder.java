package com.example.moneytracker.presentation;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.moneytracker.R;
import com.example.moneytracker.infrastructure.entities.Expense;

import java.text.DateFormat;
import java.util.Currency;
import java.util.Locale;

public class ExpenseViewHolder extends RecyclerView.ViewHolder {

    private final TextView wordItemView;
    private final EditText expenseDateView;
    private final EditText expenseAmountView;

    private ExpenseViewHolder(View itemView) {
        super(itemView);
        wordItemView = itemView.findViewById(R.id.expenseLabel);
        expenseAmountView = itemView.findViewById(R.id.expenseAmount);
        expenseDateView = itemView.findViewById(R.id.expenseDate);
    }

    public void bind(Expense expense) {
        DateFormat shortDate = DateFormat.getDateInstance(DateFormat.MEDIUM);

        wordItemView.setText(expense.getLabel());
        expenseDateView.setText(shortDate.format(expense.getDate()));

        //Mauvaise pratique, il faudrait récupérer le Context ici et injecter les données dans un placeholder, mais pour le faire proprement
        //il faudrait mettre en place l'injection de dépendance, et je fais le choix de rester simple et efficace ici.
        expenseAmountView.setText(expense.getAmount() + Currency.getInstance(Locale.getDefault()).getSymbol());
    }

    static ExpenseViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);

        return new ExpenseViewHolder(view);
    }
}
