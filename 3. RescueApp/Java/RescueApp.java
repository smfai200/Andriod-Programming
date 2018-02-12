package com.example.syedfaizan.rescueapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class RescueApp extends AppCompatActivity implements
        View.OnClickListener {
    private static final String TAG = "PhoneAuthActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescue__numbers__set);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_out_button:
                mAuth.signOut();
                startActivity(new Intent(RescueApp.this, PhoneAuthActivity.class));
                finish();
                break;
        }
    }

}
