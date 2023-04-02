package com.example.m_expense;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddExpense extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        Button backBtn = findViewById(R.id.backBtn2);
        backBtn.setOnClickListener(v -> {
            Intent backIntent = new Intent(AddExpense.this, DetailsActivity.class);
            startActivity(backIntent);
        });
        Button saveBtn = findViewById(R.id.saveExpenseBtn);
        saveBtn.setOnClickListener(v -> saveExpense());
    }

    private void saveExpense() {
        try (DatabaseHelper dbHelper = new DatabaseHelper(this)){

            EditText expenseType = findViewById(R.id.expenseTypeInput);
            EditText expenseAmount = findViewById(R.id.expenseAmountInput);
            EditText expenseDate = findViewById(R.id.expenseDateInput);
            EditText expenseDescription = findViewById(R.id.expenseDescriptionInput);

            String expenseType1 = expenseType.getText().toString();
            int expenseAmount1 = 0;
            String expenseAmount2 = expenseAmount.getText().toString();
            if (!expenseAmount2.isEmpty()) {
                expenseAmount1 = Integer.parseInt(expenseAmount2);
            }
            String expenseDate1 = expenseDate.getText().toString();
            String expenseDescription1 = expenseDescription.getText().toString();

            if (expenseType1.isEmpty() || expenseAmount2.isEmpty() || expenseDate1.isEmpty()) {
                Toast.makeText(AddExpense.this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Do you want to save this expense?");
                int finalExpense = expenseAmount1;
                builder.setPositiveButton("Yes", (dialog, which) -> {
                    try (DatabaseHelper db = new DatabaseHelper(this)) {
                        Expense e = new Expense(expenseType1, finalExpense, expenseDate1, expenseDescription1);
                        long expenseId = db.insertExpenses(e);
                        Toast.makeText(this, "Expense has been created with id: " + expenseId, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(this, DetailsActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }
}