package com.example.instagram.cloneby.Funoverflow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.instagram.cloneby.Funoverflow.utils.AndroidUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class OtpConfirmation extends AppCompatActivity {
    String verificationCode;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otp_confirmation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText etOtp = findViewById(R.id.etOtp);
        Button verifyOtp = findViewById(R.id.verifyOtp);

        auth = FirebaseAuth.getInstance();
        verificationCode = getIntent().getStringExtra("verificationCode");
        verifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredOtp = etOtp.getText().toString().trim();
               PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode,enteredOtp);
               signIn(credential);
            }
        });
        TextView otpNumber = findViewById(R.id.otpNumber);
        Intent intent = getIntent();
        if (intent != null) {
            String phoneNumber = intent.getStringExtra("phoneNumber");
            otpNumber.setText("To confirm your account,enter the 6-digit code we sent via SMS to +91"+phoneNumber);
            // Now you have the phone number, you can use it as needed
        } else {
            // Handle case where intent is null or phoneNumber extra is missing
        }

    }

    private void signIn(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Intent i = new Intent(OtpConfirmation.this,MainActivity.class);
                    startActivity(i);
                    finish();

                }else{
                    AndroidUtils.showToast(getApplicationContext(),"Otp verification failed");

                }
            }
        });
    }
}