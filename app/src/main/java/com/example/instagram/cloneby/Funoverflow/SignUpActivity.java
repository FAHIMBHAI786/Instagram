package com.example.instagram.cloneby.Funoverflow;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.instagram.cloneby.Funoverflow.utils.AndroidUtils;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    String verificationCode;
    PhoneAuthProvider.ForceResendingToken resendingToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView goToLogIn = findViewById(R.id.goToLogIn);
        goToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        auth = FirebaseAuth.getInstance();
        EditText etMobileNumber = findViewById(R.id.etMobileNumber);
        Button nextPhoneNumber = findViewById(R.id.nextPhoneNumber);
        nextPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String phoneNumber = etMobileNumber.getText().toString().trim();
                PhoneAuthOptions options =
                        PhoneAuthOptions.newBuilder(auth)
                                .setPhoneNumber("+91"+phoneNumber)       // Phone number to verify
                                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                .setActivity(SignUpActivity.this)                 // (optional) Activity for callback binding
                                // If no activity is passed, reCAPTCHA verification can not be used.
                                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                        signIn(phoneAuthCredential);
                                    }
                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        AndroidUtils.showToast(getApplicationContext(),"OTP verification failed");

                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        super.onCodeSent(s, forceResendingToken);
                                        verificationCode =s;
                                        resendingToken = forceResendingToken;
                                        AndroidUtils.showToast(getApplicationContext(),"OTP send successfully");

                                    }
                                })          // OnVerificationStateChangedCallbacks
                                .build();
                PhoneAuthProvider.verifyPhoneNumber(options);
            }
        });


        ImageButton signUpBackBtn = findViewById(R.id.signUpBackBtn);
        signUpBackBtn.setOnClickListener(view -> onBackPressed());

        LinearLayout signUpWithEmailBtn = findViewById(R.id.signUpWithEmailBtn);
        signUpWithEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this,SignUpWithEmail.class);
                startActivity(i);
                finish();
            }
        });


    }

    private void signIn(PhoneAuthCredential phoneAuthCredential) {

    }

    @Override
    public void onBackPressed() {
        // Handle custom back button behavior here
        // For example, navigate back to the previous activity or perform any custom action
        super.onBackPressed();
        finish();
    }

}