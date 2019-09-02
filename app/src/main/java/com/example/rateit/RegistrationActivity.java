package com.example.rateit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void loginPageView(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }
    public void cancelClick(View view){
        onBackPressed();
    }
}
