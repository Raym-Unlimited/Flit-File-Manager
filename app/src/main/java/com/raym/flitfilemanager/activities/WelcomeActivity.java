package com.raym.flitfilemanager.activities;

import android.content.Intent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.raym.flitfilemanager.HomeActivity;
import com.raym.flitfilemanager.R;

import java.util.Objects;

public class WelcomeActivity extends AppCompatActivity {

    Button continueButton;
    CheckBox agreementCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Objects.requireNonNull(getSupportActionBar()).hide();

        continueButton = findViewById(R.id.button_continue);
        agreementCheckBox = findViewById(R.id.check_box);

        continueButton.setOnClickListener(view -> {
            boolean isAgreed;
            if (isTermsAgreedTo(agreementCheckBox.isChecked())){
                isAgreed = true;
                Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }else{
                isAgreed = false;
                Toast.makeText(WelcomeActivity.this, "You have not agreed to our terms", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Boolean isTermsAgreedTo(Boolean bool){
        return bool;
    }
}