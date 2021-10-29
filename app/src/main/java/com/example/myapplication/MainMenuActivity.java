package com.example.myapplication;

import static com.example.myapplication.FirebaseService.getToken;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainMenuActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private final String TAG="token";
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        startService(new Intent(this,FirebaseService.class));
        mSharedPreferences = getSharedPreferences("AAAAeyfhib4:APA91bHsPHLBVodVVcOh2_DfaGHZknpHOARTZXk1Bx-x5R3FiIcvlLPOhMQEhdSLaC7DDPfnMhH_MSF42WbzzN7GnLzzXwxIP0jI2jQ9Qisclm7lU2MAfvkc3mqMNJ7j8iwwb0Fdvcme", MODE_PRIVATE);
        token=mSharedPreferences.getString("AAAAeyfhib4:APA91bHsPHLBVodVVcOh2_DfaGHZknpHOARTZXk1Bx-x5R3FiIcvlLPOhMQEhdSLaC7DDPfnMhH_MSF42WbzzN7GnLzzXwxIP0jI2jQ9Qisclm7lU2MAfvkc3mqMNJ7j8iwwb0Fdvcme","");


        new PushNotification(token, "Message", "location").execute();

        sendNotification();
        openMaps();
    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), SignInActivity.class));
        finish();
    }

    private void sendNotification() {
        Button nextButton=(Button) findViewById(R.id.buttonNotificaSofer);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, NotificaSoferActivity.class));
            }
        });
    }

    private void openMaps() {
        Button nextButton=(Button) findViewById(R.id.inregistreazaLocDeParcare);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, MapsActivity.class));
            }
        });
    }

}