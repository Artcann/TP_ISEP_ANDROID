package com.example.moneytracker.infrastructure.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.example.moneytracker.domain.enums.ExpenseType;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity(tableName = "expense_table")
public class Expense {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private Integer id;

    @ColumnInfo(name = "LABEL")
    private String label;

    @ColumnInfo(name = "TYPE")
    private ExpenseType expenseType;

    //Utilisation de Date dépréciée, préférez LocalDate. On utilise Date ici par soucis de compatibilité avec le SDK qui se fait vieux.
    @ColumnInfo(name = "EXPENSE_DATE")
    private Date date;

    @ColumnInfo(name = "AMOUNT")
    private Double amount;

    public Expense(String label, ExpenseType expenseType, Date date, Double amount) {
        this.expenseType = expenseType;
        this.date = date;
        this.amount = amount;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public Date getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getLabel() {
        return label;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Objects.equals(id, expense.id) && Objects.equals(label, expense.label) && expenseType == expense.expenseType && Objects.equals(date, expense.date) && Objects.equals(amount, expense.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, expenseType, date, amount);
    }
}
