package com.company.nadavhalevy.funmathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView result,topResult;
    Button newGameButt, finishButt;
    int score;
    int topScore1, topScore2, topScore3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result = findViewById(R.id.textViewFinishScore);
        newGameButt = findViewById(R.id.buttonNewGame);
        finishButt = findViewById(R.id.buttonFinish);

        topResult = findViewById(R.id.bestScore);

        Intent i = getIntent();
        score = i.getIntExtra("score", 0);
        result.setText("Your Score: " + score);

        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        topScore1 = sharedPreferences.getInt("best1", 0);
        topScore2 = sharedPreferences.getInt("best2", 0);
        topScore3 = sharedPreferences.getInt("best3", 0);

        if(score > topScore1){
            topScore3 = topScore2;
            topScore2 = topScore1;
            topScore1 = score;
            editor.putInt("best3", topScore3);
            editor.putInt("best2", topScore2);
            editor.putInt("best1", topScore1);
            editor.apply();
        }else if(score > topScore2){
            topScore3 = topScore2;
            topScore2 = score;
            editor.putInt("best3", topScore3);
            editor.putInt("best2", topScore2);
            editor.apply();
        }else if(score > topScore3){
            topScore3 = score;
            editor.putInt("best3", score);
            editor.apply();
        }

        topResult.setText(  "TOP SCORE" + "\n" +
                            "1: \t " + topScore1 + "\n" +
                            "2: \t" + topScore2 + "\n" +
                            "3: \t" + topScore3 + "\n" );










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