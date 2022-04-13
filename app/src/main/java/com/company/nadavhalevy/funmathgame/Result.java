package com.company.nadavhalevy.funmathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView result;
    Button newGameButt, finishButt;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result = findViewById(R.id.textViewFinishScore);
        newGameButt = findViewById(R.id.buttonNewGame);
        finishButt = findViewById(R.id.buttonFinish);

        Intent i = getIntent();
        score = i.getIntExtra("score", 0);
        result.setText("Your Score: " + score);
        newGameButt.setOnClickListener(view -> {

            Intent intent = new Intent(Result.this, MainActivity.class);
            startActivity(intent);
            finish();

        });

        finishButt.setOnClickListener(view -> {

            finish();

        });
    }
}