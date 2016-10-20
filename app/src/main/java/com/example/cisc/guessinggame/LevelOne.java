package com.example.cisc.guessinggame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

public class LevelOne extends AppCompatActivity implements View.OnClickListener {

    //buttons
    Button button1, button2, button3;

    //random number between 1 and 3
    Random rand = new Random();
    int answer = rand.nextInt(3) + 1;

    Animation wobble;
    int guess =0;

    int maxPoints = 3;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String dataName = "MyData";
    String intName = "MyInt";
    int defaultInt = 0;
    int hiScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);

        wobble = AnimationUtils.loadAnimation(this, R.anim.wobble);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);


        //initialize our two SharedPreferences objects
        prefs = getSharedPreferences(dataName,MODE_PRIVATE);
        editor = prefs.edit();
        hiScore = prefs.getInt(intName, defaultInt);

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
        }

    }

    public void doButtonStuff(Button button)
    {
        //grab the text from the button name, and check if it is equal to the answer
        guess = Integer.parseInt(button.getText().toString());

        //check if answer is right or wrong
        if (answer == guess)
        {
            MainActivity.currentScore += maxPoints;

            if (MainActivity.currentScore > hiScore)
            {
                hiScore = MainActivity.currentScore;
                editor.putInt(intName, hiScore);
                editor.commit();
                Toast.makeText(getApplicationContext(), "New Hiscore",
                        Toast.LENGTH_LONG).show();
            }

            startActivity(new Intent(this, LevelTwo.class));
        }
        else
        {
            maxPoints--;
            MainActivity.soundPool.play(MainActivity.roar, 1, 1, 0, 0, 1);
            button.startAnimation(wobble);
        }
    }
}
