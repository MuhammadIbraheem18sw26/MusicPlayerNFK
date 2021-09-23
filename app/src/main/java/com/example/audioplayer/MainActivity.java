package com.example.audioplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer audioPlayer;
    AudioManager audioManager;

    public void play(View view){

        audioPlayer.start();
    }
    public void stop(View view){
        audioPlayer.stop();

    }
    public void pause(View view){
        audioPlayer.pause();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioPlayer=MediaPlayer.create(this,R.raw.song);
        audioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        SeekBar seekVolume=findViewById(R.id.seekVolume);

         int maxvol=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
         int curvol=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        seekVolume.setMax(maxvol);
        seekVolume.setProgress(curvol);

        seekVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

       final SeekBar seekProgress=findViewById(R.id.seekProgress);
        seekProgress.setMax(audioPlayer.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekProgress.setProgress(audioPlayer.getCurrentPosition());
            }
        },0,1200);
        seekProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}