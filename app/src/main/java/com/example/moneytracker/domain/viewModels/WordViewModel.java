package com.example.moneytracker.domain.viewModels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.moneytracker.infrastructure.entities.Word;
import com.example.moneytracker.infrastructure.repository.WordRepository;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private final WordRepository wordRepository;

    private final LiveData<List<Word>> allWords;

    public WordViewModel(Application application) {
        super(application);
        wordRepository = new WordRepository(application);
        allWords = wordRepository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {return allWords;}

    public void insert(Word word) {wordRepository.insert(word);}
}
