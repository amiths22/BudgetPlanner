package com.example.budgetplanner;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.budgetplanner.util.Constants;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;




public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{


    private String TAG = DashboardActivity.class.getSimpleName();

    private ProgressBar progressBar;
    private Context context;

    Button btLogout;
    private TextView user_name, user_email;

    FirebaseAuth firebaseAuth;

    private FirebaseUser currentUser;
    private Dialog dialog;
    GoogleSignInClient googleSignInClient;

    public int getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(int authProvider) {
        this.authProvider = authProvider;
    }

    private int authProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        init();
        setClickListeners();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        setUserData();

        getAuthenticationProvider();

        ImageView dBudget = findViewById(R.id.budget);

        ImageView dExpense=findViewById(R.id.expense);

        ImageView dGoal=findViewById(R.id.goal);

        ImageView dActivity = findViewById(R.id.activity);

        btLogout = findViewById(R.id.BtnLogout);

        // Initialize firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize firebase user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();


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
               // Intent intent = new Intent(DashboardActivity.this, ActivityActivity.class);
                String email = firebaseUser.getEmail();
                String displayName = firebaseUser.getDisplayName();
                Intent intent = new Intent(context, ActivityActivity.class);
                intent.putExtra(Constants.KEY_USER_EMAIL, email);
                intent.putExtra(Constants.KEY_USER_DISPLAY_NAME, displayName);
                startActivity(intent);
            }
        });
    }

    private void getAuthenticationProvider() {
        List<? extends UserInfo> providerData = null;
        if (currentUser != null) {
            providerData = currentUser.getProviderData();
        }
        if (providerData != null) {
            for (UserInfo userInfo : providerData) {
                switch (userInfo.getProviderId()) {

                    case Constants.GOOGLE_PROVIDER:
                        Log.e(TAG, "User is signed in with Google");
                        disableAccountSettings();
                        setAuthProvider(2);
                        break;
                    case Constants.PASSWORD_PROVIDER:
                        Log.e(TAG, "User is signed in with Password");
                        setAuthProvider(1);
                        break;
                }

            }
        }
    }

    private void disableAccountSettings() {
      /*  btn_change_email.setEnabled(false);
        btn_change_email.setAlpha(0.3f);

        btn_change_password.setEnabled(false);
        btn_change_password.setAlpha(0.3f);

*/
    }

    private void setUserData() {
        String userEmail = getIntent().getStringExtra(Constants.KEY_USER_EMAIL);
        String userName = getIntent().getStringExtra(Constants.KEY_USER_DISPLAY_NAME);

/*        Log.d("username",userName);
        Log.d("userEmail",userEmail);*/

       // user_name.setText(userName);
       // user_email.setText(userEmail);
    }

    private void setClickListeners() {
        btLogout.setOnClickListener((View.OnClickListener) this);
        //btn_change_email.setOnClickListener(this);
       // btn_change_password.setOnClickListener(this);
        //btn_remove_user.setOnClickListener(this);

    }

    private void signOut() {
        progressBar.setVisibility(View.VISIBLE);

        int authProvider = getAuthProvider();
        if (authProvider == 1) {          // password
            FirebaseAuth.getInstance().signOut();
        } else if (authProvider == 2) {     // google
            GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();
            GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions);
            googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(context, "Log out successfully", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(context, "Log out failed :" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        startActivity(new Intent(context, LoginActivity.class));
        finish();
    }


    private void init() {
        context = this;
        progressBar = findViewById(R.id.progressBar);
        //tv_user_name = findViewById(R.id.tv_user_name);
        //tv_user_email = findViewById(R.id.tv_user_email);
        btLogout = findViewById(R.id.BtnLogout);
        //btn_change_email = findViewById(R.id.btn_change_email);
        //btn_change_password = findViewById(R.id.btn_change_password);
      //  btn_remove_user = findViewById(R.id.btn_remove_user);


    }

    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.BtnLogout:
                signOut();
                break;

        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        if (progressBar.getVisibility() == View.VISIBLE)
            progressBar.setVisibility(View.INVISIBLE);

    }
    /*googleSignInClient = GoogleSignIn.getClient(DashboardActivity.this, GoogleSignInOptions.DEFAULT_SIGN_IN);
    /*GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build();
     googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
            googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful()) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText( "Log out successfully", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText( "Log out failed :" + task.getException(), Toast.LENGTH_SHORT).show();
        }
    });*/


    /* btLogout.setOnClickListener(new View.OnClickListener() {
        googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                // Check condition
                if (task.isSuccessful()) {
                    // When task is successful sign out from firebase
                    firebaseAuth.signOut();
                    // Display Toast
                    Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_SHORT).show();
                    // Finish activity
                    finish();
                }
            }
        });
    });*/
}


