package com.example.groupproject;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public class WelcomeActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private Button Register;
    private Button Register2;
    private Button signin;
    private Button signIn2;
    private ImageView image;

    private TextToSpeech speaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Register2 = (Button) findViewById(R.id.Register_2);
        signIn2 = (Button) findViewById(R.id.SignIn_2);
        image = (ImageView) findViewById(R.id.animation);
        image.setBackgroundResource(R.drawable.animation);
        speaker = new TextToSpeech(this, this);

        // Ninja animation
        startAnimation start = new startAnimation();
        stopAnimation stop = new stopAnimation();

        Timer start_timer = new Timer();
        start_timer.schedule(start, 500);

        Timer stop_timer = new Timer();
        stop_timer.schedule(stop, 6500);

        // Sign-in/register buttons
        Register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak("Welcome to UNIDOM!");
                startActivity(new Intent(WelcomeActivity.this, Registration2.class));
            }
        });

        signIn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak("Welcome back!");
                startActivity(new Intent(WelcomeActivity.this, SigninActivity2.class));
            }
        });
    }

    // Methods for Animation and TextToSpeech
    class startAnimation extends TimerTask {

        @Override
        public void run() {
            // Get the background, which has been compiled to an AnimationDrawable object.
            AnimationDrawable frameAnimation = (AnimationDrawable) image.getBackground();
            frameAnimation.start();
        }
    }

    class stopAnimation extends TimerTask {

        @Override
        public void run() {
            // Get the background, which has been compiled to an AnimationDrawable object.
            AnimationDrawable frameAnimation = (AnimationDrawable) image.getBackground();
            frameAnimation.stop();
        }
    }

    public void speak (String output) {
        speaker.speak(output, TextToSpeech.QUEUE_FLUSH, null, "Id 0");
    }


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = speaker.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                Log.d("SpeakerLog", "Language is unavailable.");
            else
                Log.d("SpeakerLog", "TTS successful.");
        }
        else
            Log.d("SpeakerLog", "TTS failed.");
    }



}