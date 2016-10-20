package com.example.cisc.guessinggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LevelTwo extends AppCompatActivity implements View.OnClickListener{

    Button button1, button2, button3, button4, button5;
    TextView score;

    //random number between 1 and 5
    Random rand = new Random();
    int answer = rand.nextInt(5) + 1;

    Animation wobble;
    int guess =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_two);

        wobble = AnimationUtils.loadAnimation(this, R.anim.wobble);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);

        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);

        score = (TextView) findViewById(R.id.textScore);

        score.setText("Current Score: " + MainActivity.currentScore);



    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case (R.id.button1):
                doButtonStuff(button1);
                break;
            case (R.id.button2):
                doButtonStuff(button2);
                break;
            case (R.id.button3):
                doButtonStuff(button3);
                break;
            case (R.id.button4):
                doButtonStuff(button4);
                break;
            case (R.id.button5):
                doButtonStuff(button5);
                break;
        }

    }

    public void doButtonStuff(Button button)
    {
        //grab the text from the button name, and check if it is equal to the answer
        guess = Integer.parseInt(button.getText().toString());

        //check if answer is right or wrong
        if (answer == guess)
        {
            Toast.makeText(getApplicationContext(), "YOU WIN",
                    Toast.LENGTH_LONG).show();
        }
        else
        {
            button.startAnimation(wobble);
        }
    }
}
