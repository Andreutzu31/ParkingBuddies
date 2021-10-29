package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText mFullName, mEmail, mPassword, mPassword2;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFullName=findViewById(R.id.NameEditField);
        mEmail=findViewById(R.id.emailEditField);
        mPassword=findViewById(R.id.editTextTextPassword);
        mPassword2=findViewById(R.id.editTextTextPassword2);
        mRegisterBtn=findViewById(R.id.buttonSignUp2);
        mLoginBtn=findViewById(R.id.buttonSignIn2);

        fAuth= FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            finish();
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();
                String password2=mPassword2.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required.");
                    return;
                }

                if(password.length()<6){
                    mPassword.setError("Password must be more than 6 characters long");
                    return;
                }

                if(password.equals(password2)==false){
                    mPassword2.setError("Passwords must match!");
                    return;
                }

                //register the user in firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
                        }else {
                            Toast.makeText(SignUpActivity.this, "Eroare"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //signUp();
    }

    private void signUp() {
        Button nextButton=(Button) findViewById(R.id.buttonSignUp2);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, MainMenuActivity.class));
            }
        });
    }
}