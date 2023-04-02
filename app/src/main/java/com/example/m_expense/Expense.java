package com.example.m_expense;

import java.io.Serializable;

public class Expense implements Serializable {
    private int expense_id;
    private final int expense_amount;
    private final String expense_type, expense_date, expense_description;

    public Expense(int expense_id, String expense_type, int expense_amount, String expense_date, String expense_description) {
        this.expense_id = expense_id;
        this.expense_type = expense_type;
        this.expense_amount = expense_amount;
        this.expense_date = expense_date;
        this.expense_description = expense_description;
    }

    public Expense(String expense_type, int expense_amount, String expense_date, String expense_description) {
        this.expense_type = expense_type;
        this.expense_amount = expense_amount;
        this.expense_date = expense_date;
        this.expense_description = expense_description;
    }

    public int getExpenseId() {return expense_id;}
    public String getExpenseType() {return expense_type;}
    public int getExpenseAmount() {return expense_amount;}
    public String getExpenseDate() {return expense_date;}
    public String getExpenseDescription() {return expense_description;}
}