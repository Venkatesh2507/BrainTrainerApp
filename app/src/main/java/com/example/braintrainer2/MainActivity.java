package com.example.braintrainer2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
  Button goButton;
  Button button0;
  Button button1;
  Button button2;
  Button button3;
  TextView sumTextView;
  TextView timer;
  TextView counter;
  TextView answer;
  ArrayList<Integer> answers = new ArrayList<Integer>();
  int locationOfAns;
  int score=0;
  int noOfQuestions=0;
  Button playAgain;
  @SuppressLint("SetTextI18n")
  public void resetGame(View view){
      score=0;
      noOfQuestions=0;
      counter.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestions));
      newQuestion();
      new  CountDownTimer(30100,1000){
          @Override
          public void onTick(long millisUntilFinished) {
              timer.setText(String.valueOf(millisUntilFinished/1000)+"s");
          }

          @Override
          public void onFinish() {
              answer.setText("Done");
              button0.setEnabled(false);
              button1.setEnabled(false);
              button2.setEnabled(false);
              button3.setEnabled(false);
              playAgain.setVisibility(View.VISIBLE);
          }
      }.start();
      button0.setEnabled(true);
      button1.setEnabled(true);
      button2.setEnabled(true);
      button3.setEnabled(true);
      playAgain.setVisibility(View.INVISIBLE);
      answer.setText(" ");

  }
  public void chooseAnswer(View view){
      if (Integer.toString(locationOfAns).equals(view.getTag().toString())){
         answer.setText("Correct!");
         score++;
      }
      else
          answer.setText("Wrong!");
      noOfQuestions++;
      newQuestion();
      counter.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestions));
  }
  public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        counter.setVisibility(View.VISIBLE);
        answer.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
  }
  @SuppressLint("SetTextI18n")
  public void newQuestion(){
      Random rand = new Random();
      int n1;
      int n2;
      n1=rand.nextInt(50);
      n2=rand.nextInt(50);
      sumTextView = findViewById(R.id.textView9);
      sumTextView.setText(Integer.toString(n1)+" + "+Integer.toString(n2));
      button0 = findViewById(R.id.button1);
      button1 = findViewById(R.id.button2);
      button2 = findViewById(R.id.button3);
      button3 = findViewById(R.id.button4);
      timer = findViewById(R.id.textView7);
      counter = findViewById(R.id.textView8);
      answer = findViewById(R.id.textView);
      playAgain = findViewById(R.id.resetButton);
      locationOfAns= rand.nextInt(4);
      answers.clear();
      for (int i=0;i<4;i++){
          if (i==locationOfAns)
              answers.add(n1+n2);
          else{
              int wrongAnswer = rand.nextInt(101);
              while (wrongAnswer==n1+n2){
                  wrongAnswer=rand.nextInt(101);
              }
              answers.add(wrongAnswer);
          }
      }
      button0.setText(Integer.toString(answers.get(0)));
      button1.setText(Integer.toString(answers.get(1)));
      button2.setText(Integer.toString(answers.get(2)));
      button3.setText(Integer.toString(answers.get(3)));
  }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = findViewById(R.id.button);
        newQuestion();
        resetGame(findViewById(R.id.textView7));
    }
}