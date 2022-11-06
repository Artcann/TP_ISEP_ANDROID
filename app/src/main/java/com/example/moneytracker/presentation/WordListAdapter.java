package com.example.moneytracker.presentation;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.example.moneytracker.infrastructure.entities.Word;
import org.jetbrains.annotations.NotNull;

public class WordListAdapter extends ListAdapter<Word, WordViewHolder> {

    public WordListAdapter(@NonNull DiffUtil.ItemCallback<Word> wordItemCallback) {
        super(wordItemCallback);
    }

    @NotNull
    @Override
    public WordViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return WordViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        Word current = getItem(position);
        holder.bind(current.getWord());
    }

    public static class WordDiff extends DiffUtil.ItemCallback<Word> {

        @Override
        public boolean areItemsTheSame(@NonNull @NotNull Word oldItem, @NonNull @NotNull Word newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull Word oldItem, @NonNull @NotNull Word newItem) {
            return oldItem.getWord().equals(newItem.getWord());
        }
    }
}
