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
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    //for our hiscore (phase 4)
    SharedPreferences prefs;
    String dataName = "MyData";
    String intName = "MyString";
    int defaultInt = 0;
    //both activities can see this
    public static int hiScore;

    Button play;

    static SoundPool soundPool;
    static int roar = -1;
    static int lion = -1;

    static int currentScore  = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button) findViewById(R.id.buttonPlay);
        play.setOnClickListener(this);

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        try {
            //Create objects of the 2 required classes
            AssetManager assetManager = getAssets();
            AssetFileDescriptor descriptor;
            //create our three fx in memory ready for use
            descriptor = assetManager.openFd("lion.mp3");
            lion = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("cat.wav");
            roar = soundPool.load(descriptor, 0);

        } catch (IOException e) {
            //catch exceptions here
        }

        //for our high score (phase 4)
        //initialize our two SharedPreferences objects
        prefs = getSharedPreferences(dataName,MODE_PRIVATE);
        //Either load our High score or
        //if not available our default of 0
        hiScore = prefs.getInt(intName, defaultInt);
        //Make a reference to the Hiscore textview in our layout
        TextView textHiScore =(TextView) findViewById(R.id.textHiScore);
        //Display the hi score
        textHiScore.setText("Hi Score: "+ hiScore);

    }

    @Override
    public void onClick(View v) {

        soundPool.play(lion, 1, 1, 0, 0, (float)2);

        startActivity(new Intent(this, LevelOne.class));

    }
}
