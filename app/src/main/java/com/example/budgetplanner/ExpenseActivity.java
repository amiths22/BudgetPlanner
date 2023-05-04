package com.example.budgetplanner;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.budgetplanner.databinding.ActivityExpenseBinding;
import com.example.budgetplanner.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;

public class ExpenseActivity extends AppCompatActivity {

    ActivityExpenseBinding binding;
    String eName,eAmount;
    String eDate;
    FirebaseDatabase db;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityExpenseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                eName = binding.editTextTextPersonName.getText().toString();
                eAmount = binding.editTextNumberDecimal.getText().toString();
                eDate = binding.editTextDate.getText().toString();

                if(!eName.isEmpty() && !eAmount.isEmpty() && !eDate.isEmpty()){
                    Expense expense = new Expense(eName,eAmount,eDate);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Expense");
                    reference.child(eName).setValue(expense).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            binding.editTextTextPersonName.setText("");
                            binding.editTextNumberDecimal.setText("");
                            binding.editTextDate.setText("");

                            Toast.makeText(ExpenseActivity.this,"Pushed the data",Toast.LENGTH_LONG).show();

                        }
                    });
                }

            }
        });
    }
}
