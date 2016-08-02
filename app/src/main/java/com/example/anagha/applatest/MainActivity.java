package com.example.anagha.applatest;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
    private Button decrementButton;
    private Button incrementButton;
    private TextView countertext;
    private int counter = 0;
    private ProgressBar progressBar;
    private Button playButton;
    private MediaPlayer MediaPlayer;
    private Handler handler = new Handler();
    private TextView songTimer ;
    private long songStartTime;
    private ImageView imageView;
    private ImageView imageView2;
    private ImageButton imageButton;
    private TextView textView;
    private ImageView imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListeners();

    }
    //for moving progress bar every 1sec
    private  void initHandler(){
        handler.postDelayed(updateUI,1000);

    }
private Runnable updateUI = new Runnable(){
    @Override
    public void run(){

        double seekPercentage = 100 * MediaPlayer.getCurrentPosition() / MediaPlayer.getDuration();
        progressBar.setProgress((int) seekPercentage);
        long seconds = (System.currentTimeMillis() -songStartTime) / 1000;
        songTimer.setText(String.format("%02d:%02d", seconds/ 60, seconds % 60));
        handler.postDelayed(this,1000);
    }


};
    private void initView() {
        //decrementButton = (Button) findViewById(R.id.decrementButton);
        //incrementButton = (Button) findViewById(R.id.incrementButton);
        //countertext = (TextView) findViewById(R.id.countertext);
        //progressBar = (ProgressBar) findViewById(R.id.progressBar);
        playButton = (Button) findViewById(R.id.playButton);
        MediaPlayer = MediaPlayer.create(this, R.raw.music);
        songTimer = (TextView) findViewById(R.id.songTimer);
        songTimer.setText(String.format("%02d:%02d", 0, 0));
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        textView= (TextView) findViewById(R.id.textView);

        imageView.setImageDrawable(getResources().getDrawable(R.drawable.images));
        imageView2.setImageDrawable(getResources().getDrawable(R.drawable.now_playing));
        imageView3.setImageDrawable(getResources().getDrawable(R.drawable.song_title_place_holder));

    }


    private void initListeners() {

       // decrementButton.setOnClickListener(new View.OnClickListener() {
                                           //    @Override
                                             //  public void onClick(View v) {
                                               //    countertext.setText(Integer.toString(--counter));
//
  //                                             }
    //                                       }
      //  );


//        incrementButton.setOnClickListener(new View.OnClickListener() {
//                                               @Override
//                                               public void onClick(View v) {
//                                                   countertext.setText(Integer.toString(++counter));
//                                                   progressBar.setProgress(counter);
//                                               }
//                                           }
//
//        );
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MediaPlayer.isPlaying()) {
                    handler.removeCallbacks(updateUI);
                    MediaPlayer.stop();
                    playButton.setText("Play");
                } else {

                    initHandler();
                    MediaPlayer.start();
                    songStartTime = System.currentTimeMillis();
                    playButton.setText("Stop");
                }

            }
        }


        );
       // imageButton.setOnClickListener(new View.OnClickListener() {
           // @Override
            //public void onClick(View v) {
              //  Toast.makeText(getApplicationContext(),"You download is resumed",Toast.LENGTH_LONG).show();
           // }
       // });
    }

}



