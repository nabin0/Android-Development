package com.nabin.otpverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ProcessOtpActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText otpHolder;
    AppCompatButton btn;

    String otpId;
    String PhoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_otp);
        otpHolder = findViewById(R.id.otpHolder);
        btn = findViewById(R.id.gotoDashboard);
        mAuth = FirebaseAuth.getInstance();

        PhoneNo = getIntent().getStringExtra("phone_no");

        processOTP();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val = otpHolder.getText().toString();
                if(val.isEmpty()){
                    Toast.makeText(ProcessOtpActivity.this, "Otp cannot be empty", Toast.LENGTH_SHORT).show();
                }else if(val.length() != 6){
                    Toast.makeText(ProcessOtpActivity.this, "Otp is invalid must be of 6 character", Toast.LENGTH_SHORT).show();
                }else{
                    signInWithAuthCredentials(PhoneAuthProvider.getCredential(otpId, otpHolder.getText().toString()));
                }
            }
        });
    }

    private void processOTP() {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(PhoneNo)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signInWithAuthCredentials(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(getApplicationContext(), "Failed " + e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        otpId = s;
                        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private void signInWithAuthCredentials(PhoneAuthCredential phoneAuthCredential){
        mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(ProcessOtpActivity.this, "Not successfull", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}