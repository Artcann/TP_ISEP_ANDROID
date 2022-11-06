package com.example.moneytracker.infrastructure;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.moneytracker.domain.enums.ExpenseType;
import com.example.moneytracker.infrastructure.DAO.ExpenseDAO;
import com.example.moneytracker.infrastructure.entities.Expense;
import com.example.moneytracker.infrastructure.utils.Converters;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Expense.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    public abstract ExpenseDAO expenseDAO();

    private static volatile RoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static RoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (RoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RoomDatabase.class, "money_tracker_database")
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final androidx.room.RoomDatabase.Callback roomDatabaseCallback = new androidx.room.RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                ExpenseDAO dao = INSTANCE.expenseDAO();
                dao.deleteAll();

                Expense expense = new Expense("Pizza",ExpenseType.FOOD, new Date(), 20.0);
                dao.insert(expense);
            });
        }
    };
}
