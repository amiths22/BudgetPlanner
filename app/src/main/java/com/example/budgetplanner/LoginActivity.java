package com.example.budgetplanner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.budgetplanner.util.Util;
import com.example.budgetplanner.util.Constants;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private Button googleLogin;
    private Button forgotPassword;

    private SQLiteDatabase db;

    private Context context;


    private ProgressBar progress_bar;





    private static final int RC_SIGN_IN=1;
    private static final String TAG="GOOGLEAUTH";
   // GoogleSignInClient mGoogleSignInClient;
    SignInButton btSignIn;
    GoogleSignInClient googleSignInClient;
    FirebaseAuth firebaseAuth;

    private EditText emailTextView, passwordTextView;
    private Button Btn;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;


    private static final int REQ_ONE_TAP = 2;  // Can be any integer unique to the Activity.
    private boolean showOneTapUI = true;
    // ...

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        SignInClient oneTapClient = null;
        switch (requestCode) {
            case REQ_ONE_TAP:
                try {
                    SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(data);
                    String idToken = credential.getGoogleIdToken();
                    if (idToken !=  null) {
                        // Got an ID token from Google. Use it to authenticate
                        // with Firebase.
                        AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
                        mAuth.signInWithCredential(firebaseCredential)
                                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d(TAG, "signInWithCredential:success");
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            Log.d("user",user.toString());
                                            //updateUI(user);
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                                            Log.d("user",null);
                                            // updateUI(null);
                                        }
                                    }
                                });
                       // Log.d(TAG, "Got ID token.");
                    }
                } catch (ApiException e) {
                    // ...
                }
                break;
        }

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        setClickListeners();
        configureGoogleSignIn();
        firebaseAuth = FirebaseAuth.getInstance();

    }
        /*super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // mAuth=FirebaseAuth.getInstance();

        // Assign variable
        btSignIn = findViewById(R.id.google_signIn);

        // Initialize sign in options the client-id is copied form google-services.json file
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("51131025647-ardgai7ho8jjbtjjab65aioi1a8j9vdf.apps.googleusercontent.com")
                .requestEmail()
                .build();

        // Initialize sign in client
        googleSignInClient = GoogleSignIn.getClient(LoginActivity.this, googleSignInOptions);

        btSignIn.setOnClickListener((View.OnClickListener) view -> {
            // Initialize sign in intent
            Intent intent = googleSignInClient.getSignInIntent();
            // Start activity for result
            startActivityForResult(intent, 100);
        });

        // Initialize firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        // Initialize firebase user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        // Check condition
        if (firebaseUser != null) {
            // When user already sign in redirect to profile activity
            Log.d("user", firebaseUser.toString());
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

// ...
// Initialize Firebase Auth


       /* GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient=GoogleSignIn.getClient(this,gso);

        Button signInbtn = findViewById(R.id.google_signIn);
        signInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });


        emailEditText = findViewById(R.id.emailId);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        // Create or open the database for the app
        db = openOrCreateDatabase("userDB", MODE_PRIVATE, null);

        // Create the user table if it doesn't exist
        //db.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT);");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Check if the user exists in the database
                Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[]{username, password});

                if (cursor.getCount() > 0) {
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/

    private void setClickListeners() {
//        google_sign_in_btn.setOnClickListener(this);
        loginButton.setOnClickListener(this);

        forgotPassword.setOnClickListener(this);
        googleLogin.setOnClickListener(this);

    }

    private void configureGoogleSignIn() {

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("51131025647-ardgai7ho8jjbtjjab65aioi1a8j9vdf.apps.googleusercontent.com")        // for firebase web server authentication
                .requestEmail()
                .build();

        // for custom developed API i.e own server
//        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();

        googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions);
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            updateUI(currentUser);
        }
    }

    protected void onResume() {
        super.onResume();
        Log.e(TAG, "on resume Called");
        if (progress_bar.getVisibility() == View.VISIBLE)
            progress_bar.setVisibility(View.GONE);

    }

    private void updateUI(FirebaseUser firebaseUser) {
        String email = firebaseUser.getEmail();
        String displayName = firebaseUser.getDisplayName();
        Intent intent = new Intent(context, DashboardActivity.class);    // launch DashBoardActivity  if exist  // hide google sign in button if not
        intent.putExtra(Constants.KEY_USER_EMAIL, email);
        intent.putExtra(Constants.KEY_USER_DISPLAY_NAME, displayName);
        startActivity(intent);
        finish();
    }

    private void init() {

        context = this;
        progress_bar = findViewById(R.id.progress_bar);
        emailEditText = findViewById(R.id.emailId);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        forgotPassword = findViewById(R.id.forgotPassword);
//        google_sign_in_btn = findViewById(R.id.google_sign_in_btn);

        googleLogin = findViewById(R.id.google_signIn);
    }

    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.google_sign_in_btn:
//                googleSignIn();
//                break;
            case R.id.loginButton:
                loginWithEmailPassword();
                break;
            case R.id.forgotPassword:
                startActivity(new Intent(context, ResetPasswordActivity.class));
                break;
            case R.id.google_signIn:
//                google_sign_in_btn.performClick();
                googleSignIn();
                break;

        }
    }

    private void loginWithEmailPassword() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email))
            Util.showSnackBar(emailEditText, "Enter Email");
        else if (TextUtils.isEmpty(password))
            Util.showSnackBar(passwordEditText, "Enter Password");
        else {
            progress_bar.setVisibility(View.VISIBLE);
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progress_bar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                        if (currentUser != null) {
                            updateUI(currentUser);
                        }
                    } else {
                        Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show();
                        Util.showSnackBar(emailEditText, "Authentication Failed");
//                        updateUI(null);
                    }
                }
            });
        }

    }



    private void googleSignIn() {
        progress_bar.setVisibility(View.VISIBLE);
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(signInAccountTask);
        } else {
           // callbackManager.onActivityResult(requestCode, resultCode, data);    // for facebook integration
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount googleSignInAccount = completedTask.getResult(ApiException.class);
            if (googleSignInAccount != null) {
                fireBaseAuthWithGoogle(googleSignInAccount);   // signed in successfully, authenticate with firebase
            }
        } catch (ApiException e) {
            Log.e(TAG, "signInResult:failed code= " + e.getStatusCode());
//            updateUI(null);
        }

    }

    private void fireBaseAuthWithGoogle(GoogleSignInAccount googleSignInAccount) {
        Log.e(TAG, "fireBaseAuthWithGoogle : " + googleSignInAccount.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progress_bar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Log.e(TAG, "signInWithCredential success");
                    FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                    if (currentUser != null) {
                        updateUI(currentUser);
                    }
                } else {
                    Log.e(TAG, "signInWithCredential : failure", task.getException());
                  //  Util.showSnackBar(findViewById(R.id.), getString(R.string.authentication_failed));
                }
            }
        });
    }

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check condition
        if (requestCode == 100) {
            // When request code is equal to 100 initialize task
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            // check condition
            if (signInAccountTask.isSuccessful()) {
                // When google sign in successful initialize string
                String s = "Google sign in successful";
                // Display Toast
                displayToast(s);
                // Initialize sign in account
                try {
                    // Initialize sign in account
                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                    // Check condition
                    if (googleSignInAccount != null) {
                        // When sign in account is not equal to null initialize auth credential
                        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                        // Check credential
                        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // Check condition
                                if (task.isSuccessful()) {
                                    // When task is successful redirect to profile activity display Toast
                                    Log.d("login","login succes");
                                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                    displayToast("Firebase authentication successful");
                                } else {
                                    // When task is unsuccessful display Toast
                                    displayToast("Authentication Failed :" + task.getException().getMessage());
                                    Log.d("authFail",task.getException().getMessage());
                                }
                            }
                        });
                    }
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void displayToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    } */
   /* @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }*/

    /*private  void signIn(){
        Intent signInIntent=mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }*/


/*
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database if it's open
        if (db != null && db.isOpen()) {
            db.close();
        }
    }*/


}
