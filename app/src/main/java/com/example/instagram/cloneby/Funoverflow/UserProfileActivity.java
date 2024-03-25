package com.example.instagram.cloneby.Funoverflow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.instagram.cloneby.Funoverflow.models.Users;
import com.example.instagram.cloneby.Funoverflow.utils.FirebaseUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;

public class UserProfileActivity extends AppCompatActivity {
EditText userNameInput;
Button userNameNext;

String phoneNumber;

Users users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userNameInput = findViewById(R.id.etUserName);
        userNameNext = findViewById(R.id.userNameNext);
        
        getUserName();

        userNameNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(UserProfileActivity.this);
                progressDialog.setMessage("Logging in...");
                progressDialog.setCancelable(false); // Prevents user from cancelling the dialog
                progressDialog.show();
                setUserName();
            }
        });


    }
    void setUserName(){
        String userName = userNameInput.getText().toString();
        if (userName.isEmpty()|| userName.length()<4){
            userNameInput.setError("Username length should be at least 4 chars");
        return;
        }

        if (users!=null){
            users.setUserName(userName);
        }else {
            Intent intent = getIntent();
            phoneNumber = intent.getStringExtra("phoneNumber");
            users = new Users(userName,phoneNumber,Timestamp.now());
        }
        FirebaseUtils.currentUserDetails().set(users).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Intent i = new Intent(UserProfileActivity.this,MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
            }
        });

    }

    private void getUserName() {
        FirebaseUtils.currentUserDetails().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                   users =  task.getResult().toObject(Users.class);

                  if(users!=null){
                      userNameInput.setText(users.getUserName());
                  }
                }
            }
        });
    }
}