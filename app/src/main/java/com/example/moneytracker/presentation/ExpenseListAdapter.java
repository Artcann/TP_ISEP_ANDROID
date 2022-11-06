package com.example.moneytracker.presentation;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.example.moneytracker.infrastructure.entities.Expense;
import org.jetbrains.annotations.NotNull;

public class ExpenseListAdapter extends ListAdapter<Expense, ExpenseViewHolder> {

    public ExpenseListAdapter(@NonNull DiffUtil.ItemCallback<Expense> wordItemCallback) {
        super(wordItemCallback);
    }

    @NotNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return ExpenseViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ExpenseViewHolder holder, int position) {
        Expense current = getItem(position);
        holder.bind(current.getLabel());
    }

    public static class ExpenseDiff extends DiffUtil.ItemCallback<Expense> {

        @Override
        public boolean areItemsTheSame(@NonNull @NotNull Expense oldItem, @NonNull @NotNull Expense newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull Expense oldItem, @NonNull @NotNull Expense newItem) {
            return oldItem.equals(newItem);
        }
    }
}
