package com.example.moneytracker.infrastructure.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.moneytracker.infrastructure.DAO.WordDao;
import com.example.moneytracker.infrastructure.WordRoomDatabase;
import com.example.moneytracker.infrastructure.entities.Word;

import java.util.List;

public class WordRepository {

    private WordDao wordDao;
    private LiveData<List<Word>> allWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        wordDao = db.wordDao();
        allWords = wordDao.getAlphabetizedOrder();
    }

    public LiveData<List<Word>> getAllWords() {
        return allWords;
    }

    public void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> wordDao.insert(word));
    }
}
