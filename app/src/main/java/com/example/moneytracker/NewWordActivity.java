package com.example.moneytracker;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.moneytracker.domain.enums.ExpenseType;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText editWordView;
    private EditText amountView;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        editWordView = findViewById(R.id.edit_word);
        amountView = findViewById(R.id.edit_amount);

        spinner = findViewById(R.id.spinner);

        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ExpenseType.values()));

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if(TextUtils.isEmpty(editWordView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String word = editWordView.getText().toString();
                String amount = amountView.getText().toString();
                String spinnerResult = spinner.getSelectedItem().toString();
                replyIntent.putExtra(EXTRA_REPLY, word);
                replyIntent.putExtra("amount", amount);
                replyIntent.putExtra("spinner", spinnerResult);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}