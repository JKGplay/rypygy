package com.example.rypygy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rypygy.models.Character;
import com.example.rypygy.models.Item;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etName;
    private Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        btnPlay = findViewById(R.id.btnPlay);

        btnPlay.setOnClickListener(view -> {
            if (!String.valueOf(etName.getText()).trim().isEmpty()) {
                Character.setName(String.valueOf(etName.getText()).trim());
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Enter a name for your character", Toast.LENGTH_SHORT).show();
            }
        });
    }
}