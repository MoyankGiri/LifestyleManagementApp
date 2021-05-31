package com.example.lifestylemanagementapp_moyank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    Button CancelButton,SignupButton;
    EditText UsernameEditText,PasswordEditText;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();
        CancelButton = findViewById(R.id.CancelButton);
        SignupButton = findViewById(R.id.signUpButton);
        UsernameEditText = findViewById(R.id.UserNameTextView);
        PasswordEditText = findViewById(R.id.PasswordTextView);

        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EmailId = UsernameEditText.getText().toString();
                String Password = PasswordEditText.getText().toString();
                firebaseAuth.createUserWithEmailAndPassword(EmailId,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(
                                    SignUpActivity.this.getApplicationContext(), "SignUp unsuccessful: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(
                                    SignUpActivity.this, MainActivity.class));
                        }
                    }
                });
            }
        });
    }
}
