package com.example.instagram.cloneby.Funoverflow;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.instagram.cloneby.Funoverflow.databinding.ActivitySignUpBinding;
import com.example.instagram.cloneby.Funoverflow.databinding.ActivitySignUpWithEmailBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SignUpWithEmail extends AppCompatActivity {
    private FirebaseAuth auth;
    ActivitySignUpWithEmailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpWithEmailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_with_email);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView goToLogIn = findViewById(R.id.goToLogIn);
        goToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpWithEmail.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        EditText emailSignup = findViewById(R.id.emailSignup);

        String email = emailSignup.getText().toString().trim();
        auth = FirebaseAuth.getInstance();

        ActionCodeSettings actionCodeSettings =ActionCodeSettings.newBuilder()
                // URL you want to redirect back to. The domain (www.example.com) for this
                // URL must be whitelisted in the Firebase Console.
                .setUrl("https://cloneby.page.link/zXbp")
                // This must be true
                .setHandleCodeInApp(true)
                .setIOSBundleId("com.example.ios")
                .setAndroidPackageName(
                        "com.example.instagram.cloneby.Funoverflow",
                        true, /* installIfNotAvailable */
                        "12"    /* minimumVersion */)
                .build();


        //SignUp with email
        Button signUpWithEmailNextBtn = findViewById(R.id.signUpWithEmailNextBtn);
        signUpWithEmailNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailSignup.getText().toString().trim();

                // Sending verification email
                auth.sendSignInLinkToEmail(email, actionCodeSettings)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUpWithEmail.this,"Email sent successfully",Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(SignUpWithEmail.this,task.getException().toString(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });


            }
        });

        ImageButton signUpWithEmailBackBtn = findViewById(R.id.signUpWithEmailBackBtn);
        signUpWithEmailBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        LinearLayout signUpWithMobile = findViewById(R.id.signUpWithMobileBtn);
        signUpWithMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpWithEmail.this,SignUpActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        // Handle custom back button behavior here
        // For example, navigate back to the previous activity or perform any custom action
        super.onBackPressed();
        finish();
    }
}