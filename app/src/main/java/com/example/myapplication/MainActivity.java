package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureSingInActivity();
        configureSingUpActivity();
        }

        private void configureSingInActivity() {
            Button nextButton=(Button) findViewById(R.id.buttonSignIn);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, SignInActivity.class));
                }
            });
        }

        private void configureSingUpActivity() {
            Button nextButton=(Button) findViewById(R.id.buttonSignUp);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, SignUpActivity.class));
                }
            });
        }
}