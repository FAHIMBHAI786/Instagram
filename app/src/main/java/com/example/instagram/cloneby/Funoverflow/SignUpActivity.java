package com.example.instagram.cloneby.Funoverflow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUpActivity extends AppCompatActivity {

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
        ImageButton signUpBackBtn = findViewById(R.id.signUpBackBtn);
        signUpBackBtn.setOnClickListener(view -> onBackPressed());

        LinearLayout signUpWithEmailBtn = findViewById(R.id.signUpWithEmailBtn);
        signUpWithEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this,SignUpWithEmail.class);
                startActivity(i);
            }
        });

    }
    @Override
    public void onBackPressed() {
        // Handle custom back button behavior here
        // For example, navigate back to the previous activity or perform any custom action
        super.onBackPressed();
    }}