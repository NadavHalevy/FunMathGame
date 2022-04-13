package com.company.nadavhalevy.funmathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addButt, subButt,mulButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButt = findViewById(R.id.buttonAdd);
        subButt = findViewById(R.id.buttonSub);
        mulButt = findViewById(R.id.buttonMul);

        addButt.setOnClickListener(view -> {


            Intent intent = new Intent(MainActivity.this, Game.class);
            intent.putExtra("method", "addition");
            startActivity(intent);
            finish();

        });

        subButt.setOnClickListener(view -> {


            Intent intent = new Intent(MainActivity.this, Game.class);
            intent.putExtra("method", "subtraction");
            startActivity(intent);
            finish();

        });

        mulButt.setOnClickListener(view -> {


            Intent intent = new Intent(MainActivity.this, Game.class);
            intent.putExtra("method", "multiplication");
            startActivity(intent);
            finish();

        });
    }
}