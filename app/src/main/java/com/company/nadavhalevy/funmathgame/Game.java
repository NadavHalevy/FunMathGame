package com.company.nadavhalevy.funmathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class Game extends AppCompatActivity {

    TextView scoreText;
    TextView lifeText;
    TextView timeText;
    TextView questionText;
    EditText answerText;
    Button submitButt;
    Button nextButt;

    Random random = new Random();

    int num1;
    int num2;
    int userAnswer;
    int correctAnswer;
    int userScore = 0;
    int userLife = 3;
    int countPressed = 0;

    CountDownTimer timer;
    private static final long START_TIMER_IN_MILLIS = 15000;
    Boolean timerRun;
    long time_left_in_millis = START_TIMER_IN_MILLIS;

    String operationMethod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        scoreText = findViewById(R.id.textViewScore);
        lifeText = findViewById(R.id.textViewLife);
        timeText = findViewById(R.id.textViewTime);
        questionText = findViewById(R.id.textViewQ);
        answerText = findViewById(R.id.editTextA);
        answerText.setInputType(InputType.TYPE_CLASS_NUMBER);
        submitButt = findViewById(R.id.buttonSubmit);
        nextButt = findViewById(R.id.buttonNext);

        Intent i = getIntent();
        operationMethod = i.getStringExtra("method").toString();
        resetTimer();
        theLogicGame(operationMethod);

        submitButt.setOnClickListener(view -> {
            countPressed += 1;
            userAnswer = Integer.valueOf(answerText.getText().toString());

            pauseTimer();

            if (userAnswer == correctAnswer){

                userScore += 10;
                scoreText.setText("" + userScore);
                questionText.setText("Well done, the answer is right.");

            }else{
                checkLife();
                questionText.setText("Sorry, the answer is wrong.");

            }

        });

            nextButt.setOnClickListener(view -> {
                if (countPressed > 0) {
                    answerText.setText("");
                    resetTimer();
                    theLogicGame(operationMethod);
                }
            });


    }

    public void theLogicGame(String operation){

        countPressed = 0;
        switch (operation){
            case "addition":
                num1 = random.nextInt(100);
                num2 = random.nextInt(100);
                correctAnswer= num1 + num2;
                questionText.setText(num1 + " + " + num2);
                startTime();
                break;
            case "subtraction":
                num1 = random.nextInt(100);
                num2 = random.nextInt(100);
                if( num1 > num2){
                    correctAnswer= num1 - num2;
                    questionText.setText(num1 + " - " + num2);
                }else{
                    correctAnswer= num2 - num1;
                    questionText.setText(num2 + " - " + num1);
                }
                startTime();
                break;
            case "multiplication":
                num1 = random.nextInt(100);
                num2 = random.nextInt(10);
                correctAnswer= num1 * num2;
                questionText.setText(num1 + " x " + num2);
                startTime();
                break;
            default:
                break;
        }
    }

    public void checkLife(){
        if(userLife >= 2 ){
            userLife-=1;
            lifeText.setText("" + userLife);
        }
        else{

            Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Game.this, Result.class);
            intent.putExtra("score", userScore);
            startActivity(intent);
            finish();
        }

    }

    public void startTime(){

        timer = new CountDownTimer(time_left_in_millis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                time_left_in_millis = millisUntilFinished;
                updateTimer();

            }

            @Override
            public void onFinish() {

                timerRun = false;
                pauseTimer();
                resetTimer();
                updateTimer();
                questionText.setText("Sorry, Time is up.");
                checkLife();

            }
        }.start();

        timerRun = true;
    }

    public void updateTimer(){
        int second = (int)(time_left_in_millis / 1000) % 60;
        String timeLeft = String.format(Locale.getDefault(), "%02d", second);
        timeText.setText(timeLeft);
    }

    public void pauseTimer(){
        timer.cancel();
        timerRun = false;
    }

    public void resetTimer(){
        time_left_in_millis = START_TIMER_IN_MILLIS;
        updateTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

}