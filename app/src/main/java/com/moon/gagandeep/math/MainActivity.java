package com.moon.gagandeep.math;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton, playAgainButton;
    Button buttonAnswer0, buttonAnswer1, buttonAnswer2, buttonAnswer3;
    TextView textViewTime, textViewResult, textViewScore, textViewSum, textViewResult2;
    TextView whiteCircle, blackCircle, colorCircle;
    ConstraintLayout gameLayout, homeLayout;
    int correctAnswerLocation;
    int score = 0;
    int count = 0;
    int numberOfQuestions = 0;
    int chooseCount=0;
    ArrayList<Integer> answers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        whiteTheme();
        mainOperations();
        generateQuestions();


    }

    private void generateQuestions() {
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        int incorrectAnswer;
        textViewSum.setText(a + " + " + b);
        answers.clear();
        correctAnswerLocation = random.nextInt(4);  //decide an index which will contain correct Answer
        for (int i=0; i<4; i++){
            if (i==correctAnswerLocation){  //put the correct answer in that particular index
                answers.add(a + b);
            }
            else{
                incorrectAnswer = random.nextInt(41);   //put wrong answer in other indexes
                while (incorrectAnswer==a + b){
                    incorrectAnswer = random.nextInt(41);   //if another  correct answer is generated then again random number will be generated
                }
                answers.add(incorrectAnswer);
            }
        }
        buttonAnswer0.setText("" + answers.get(0));
        buttonAnswer1.setText("" + answers.get(1));
        buttonAnswer2.setText("" + answers.get(2));
        buttonAnswer3.setText("" + answers.get(3));

    }

    private void mainOperations() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setVisibility(v.INVISIBLE);
                gameLayout.setVisibility(v.VISIBLE);
                whiteCircle.setVisibility(v.INVISIBLE);
                blackCircle.setVisibility(v.INVISIBLE);
                colorCircle.setVisibility(v.INVISIBLE);
            }
        });

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();

            }
        });

        whiteCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whiteTheme();
            }
        });

        blackCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blackTheme();
            }
        });

        colorCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorTheme();
            }
        });
    }

    private void findViews() {
        startButton = findViewById(R.id.startButton);
        buttonAnswer0 = findViewById(R.id.buttonAnswer);
        buttonAnswer1 = findViewById(R.id.buttonAnswer2);
        buttonAnswer2 = findViewById(R.id.buttonAnswer3);
        buttonAnswer3 = findViewById(R.id.buttonAnswer4);
        playAgainButton = findViewById(R.id.buttonPlayAgain);
        textViewTime = findViewById(R.id.textViewTimer);
        textViewResult = findViewById(R.id.textViewResult);
        textViewScore = findViewById(R.id.textViewScore);
        textViewSum = findViewById(R.id.textViewExpression);
        gameLayout = findViewById(R.id.gameLayout);
        homeLayout = findViewById(R.id.homeLayout);
        textViewResult2 = findViewById(R.id.textViewResult2);
        whiteCircle = findViewById(R.id.textViewWhite);
        blackCircle = findViewById(R.id.textViewBlack);
        colorCircle = findViewById(R.id.textViewColor);

    }

    public void chooseAnswer(View view){
        chooseCount++;
        Log.i("TAG", "chooseAnswer: " + view.getTag());
        if (view.getTag().toString().equals(Integer.toString(correctAnswerLocation))){
            textViewResult.setVisibility(View.VISIBLE);
            textViewResult.setTextColor(getResources().getColor(R.color.green));
            textViewResult.setText("Correct :D");
            ++score;

        }
        else{
            textViewResult.setVisibility(View.VISIBLE);
            textViewResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            textViewResult.setText("Wrong :(");
        }

        numberOfQuestions++;
        textViewScore.setText(score + " / " + numberOfQuestions);
        generateQuestions();
        if (count==0){
            count++;
            playAgain();
        }

    }

    private void playAgain() {
        gameLayout.setVisibility(View.VISIBLE);
        homeLayout.setVisibility(View.INVISIBLE);
        numberOfQuestions = 0;
        if(chooseCount==1){
            numberOfQuestions=1;
        }
        textViewTime.setText("30s");
        if (numberOfQuestions==0){
            score=0;
            textViewScore.setText( score +" / 0");
        }
        else
        textViewScore.setText( score +" / 1");
        textViewResult.setText("");
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textViewTime.setText(millisUntilFinished/1000 + "s");
            }

            @Override
            public void onFinish() {
                gameLayout.setVisibility(View.INVISIBLE);
                homeLayout.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
                textViewResult2.setVisibility(View.VISIBLE);
                textViewResult2.setText("Score : " +score);
                blackCircle.setVisibility(View.VISIBLE);
                whiteCircle.setVisibility(View.VISIBLE);
                colorCircle.setVisibility(View.VISIBLE);
                count = 0;
                chooseCount = 0;

            }
        }.start();
        generateQuestions();
    }
    private void colorTheme() {
        Toast.makeText(this, "In Progress...", Toast.LENGTH_SHORT).show();


    }

    private void blackTheme() {

        startButton.setBackgroundResource(R.drawable.ripple_white);
        playAgainButton.setBackgroundResource(R.drawable.ripple_white);
        buttonAnswer0.setBackgroundResource(R.drawable.ripple_white);
        buttonAnswer1.setBackgroundResource(R.drawable.ripple_white);
        buttonAnswer2.setBackgroundResource(R.drawable.ripple_white);
        buttonAnswer3.setBackgroundResource(R.drawable.ripple_white);
        startButton.setTextColor(getResources().getColor(android.R.color.white));
        playAgainButton.setTextColor(getResources().getColor(android.R.color.white));
        buttonAnswer0.setTextColor(getResources().getColor(android.R.color.white));
        buttonAnswer1.setTextColor(getResources().getColor(android.R.color.white));
        buttonAnswer2.setTextColor(getResources().getColor(android.R.color.white));
        textViewTime.setTextColor(getResources().getColor(android.R.color.white));
        buttonAnswer3.setTextColor(getResources().getColor(android.R.color.white));
        gameLayout.setBackgroundColor(getResources().getColor(R.color.darkBlack));
        homeLayout.setBackgroundColor(getResources().getColor(R.color.darkBlack));
        textViewScore.setTextColor(getResources().getColor(android.R.color.white));
        textViewSum.setTextColor(getResources().getColor(android.R.color.white));
        textViewResult2.setTextColor(getResources().getColor(android.R.color.white));

    }

    private void whiteTheme() {
        startButton.setBackgroundResource(R.drawable.ripple);
        playAgainButton.setBackgroundResource(R.drawable.ripple);
        buttonAnswer0.setBackgroundResource(R.drawable.ripple);
        buttonAnswer1.setBackgroundResource(R.drawable.ripple);
        buttonAnswer2.setBackgroundResource(R.drawable.ripple);
        buttonAnswer3.setBackgroundResource(R.drawable.ripple);
        startButton.setTextColor(getResources().getColor(android.R.color.black));
        playAgainButton.setTextColor(getResources().getColor(android.R.color.black));
        buttonAnswer0.setTextColor(getResources().getColor(android.R.color.black));
        buttonAnswer1.setTextColor(getResources().getColor(android.R.color.black));
        buttonAnswer2.setTextColor(getResources().getColor(android.R.color.black));
        buttonAnswer3.setTextColor(getResources().getColor(android.R.color.black));
        textViewSum.setTextColor(getResources().getColor(android.R.color.black));
        gameLayout.setBackgroundColor(getResources().getColor(android.R.color.white));
        homeLayout.setBackgroundColor(getResources().getColor(android.R.color.white));
        textViewResult2.setTextColor(getResources().getColor(android.R.color.black));
        textViewScore.setTextColor(getResources().getColor(R.color.darkBlack));
        textViewTime.setTextColor(getResources().getColor(R.color.darkBlack));
    }
}
