package com.nabin.blog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {
    private EditText inputUsername, inputPassword;
    private Button loginButton;
    private ProgressBar loginProgress;
    private BlogPreferences blogPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // If already logged in redirect to MainActivity
        blogPreferences = new BlogPreferences(this);
        if(blogPreferences.isLoggedIn()){
            startMainActivity();
            finish();
            return;
        }

        // Bind layout with activity
        setContentView(R.layout.login_layout);

        // Init Views
        initViews();

        // OnclickListener on login Button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginClicked();
            }
        });

        // Edit text change listener
        inputUsername.addTextChangedListener(textWatcher(inputUsername));
        inputPassword.addTextChangedListener(textWatcher(inputPassword));

    }


    private void initViews(){
        inputUsername = findViewById(R.id.inputUsername);
        inputPassword = findViewById(R.id.inputPassword);
        loginButton = findViewById(R.id.loginButton);
        loginProgress = findViewById(R.id.loginProgress);
    }

    private void onLoginClicked() {
        String username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();

        if(username.isEmpty()){
            inputUsername.setError("Username must not be empty.");
        }else if(password.isEmpty()){
            inputPassword.setError("Password must not be empty.");
        }else if(!username.equals("a") || !password.equals("a")){
            showErrorDialogue();
        }else {
            performLogin();
        }
    }

    private void performLogin() {
        blogPreferences.setLoggedIn(true);

        inputUsername.setEnabled(false);
        inputPassword.setEnabled(false);
        loginButton.setVisibility(View.INVISIBLE);
        loginProgress.setVisibility(View.VISIBLE);

        new Handler().postDelayed((Runnable) () -> {
            LoginActivity.this.startMainActivity();
            finish();
        }, 2000);

    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void showErrorDialogue() {
        new AlertDialog.Builder(this)
                .setTitle("Login Failed")
                .setMessage("Username or Password not correct. Please try again.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }


    // TextWatcher for input fields
    private TextWatcher textWatcher(EditText inputLayout){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
}
