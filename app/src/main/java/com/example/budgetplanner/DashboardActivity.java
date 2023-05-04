package com.example.budgetplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;




public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ImageView dBudget = findViewById(R.id.budget);

        ImageView dExpense=findViewById(R.id.expense);

        ImageView dGoal=findViewById(R.id.goal);

        ImageView dActivity = findViewById(R.id.activity);


        dExpense.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ExpenseActivity.class);
                startActivity(intent);
            }
        });

        dBudget.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, BudgetActivity.class);
                startActivity(intent);
            }
        });

        dGoal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, GoalActivity.class);
                startActivity(intent);
            }
        });
        dActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ActivityActivity.class);
                startActivity(intent);
            }
        });
    }
}

