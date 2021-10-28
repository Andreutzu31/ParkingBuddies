package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PushNotification extends AsyncTask<Void,Void,Void> {

    private String token;
    private String header;
    private String message;

    private static final String AUTH_KEY_FCM = "YOUR_SERVERKEY";
    private static final String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

    public PushNotification(String token, String header, String message) {
        this.token = token;
        this.header = header;
        this.message = message;

        Log.d("Error", token);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        LocationAdministration.setMock(11, -50, 10);
        try {
            Log.d("Error", "Intra");

            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            Log.d("Error", "Intra");

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "key=AAAAeyfhib4:APA91bHsPHLBVodVVcOh2_DfaGHZknpHOARTZXk1Bx-x5R3FiIcvlLPOhMQEhdSLaC7DDPfnMhH_MSF42WbzzN7GnLzzXwxIP0jI2jQ9Qisclm7lU2MAfvkc3mqMNJ7j8iwwb0Fdvcme");
            conn.setRequestProperty("Content-Type", "application/json");
            Log.d("Error", "Intra");

            JSONObject json = new JSONObject();
            Log.d("Error", "Intra");

            json.put("to", token);
            Log.d("Error", "Intra");


            JSONObject info = new JSONObject();
            info.put("title",  header);   // Notification title
            info.put("body", message); // Notification body
            Log.d("Error", "Intra");

            json.put("notification", info);
            Log.d("Error", "Intra");

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(json.toString());
            wr.flush();
            conn.getInputStream();
            Log.d("Error", "Intra");

        }
        catch (Exception e)
        {
            Log.d("Error",""+e);
        }


        return null;
    }
}