package ir.shariaty.musicplayerjava;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView play,prev,next,ImageView;
    TextView songTitle;
    SeekBar mSeekBarTime, mSeekBarVol;
    static MediaPlayer mMediaPlayer;
    int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        play = findViewById(R.id.play);
        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        mSeekBarTime = findViewById(R.id.seekBarTime);
        mSeekBarVol = findViewById(R.id.seekBarVol);
        songTitle = findViewById(R.id.songTitle);

        ArrayList<Integer> songs = new ArrayList<>();

        songs.add(0 , R.raw.ahmadi);
        songs.add(1 , R.raw.zeinab);

        // initialize MediaPlayer
        mMediaPlayer = MediaPlayer.create(getApplicationContext(),songs.get(currentIndex));

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMediaPlayer != null && mMediaPlayer.isPlaying()){
                    mMediaPlayer.pause();
                    play.setImageResource(R.drawable.ic_play_arrow_24);
                } else {
                    mMediaPlayer.start();
                    play.setImageResource(R.drawable.ic_pause_24);
                }
                if (currentIndex < songs.size() - 1){
                    currentIndex++;
                } else{
                    currentIndex=0;
                }
                if (mMediaPlayer.isPlaying()){
                    mMediaPlayer.stop();
                }
                mMediaPlayer = mMediaPlayer.create(getApplicationContext(), songs.get(currentIndex));
                mMediaPlayer.start();
                SongsName();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMediaPlayer!=null){
                    play.setImageResource(R.drawable.ic_pause_24);
                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer != null) {
                    play.setImageResource(R.drawable.ic_pause_24);
                }
                if (currentIndex > 0) {
                    currentIndex--;
                } else {
                    currentIndex = songs.size() - 1;
                }
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                }
                mMediaPlayer = mMediaPlayer.create(getApplicationContext(), songs.get(currentIndex));
                mMediaPlayer.start();
                SongsName();
            }
        });

                ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void SongsName() {
        if (currentIndex==0){
            songTitle.setText("zeinab-nemidunam");
            ImageView.setImageResource(R.drawable.zeinab);
        } else if (currentIndex==1) {
            songTitle.setText("man-yadam nist");
            ImageView.setImageResource(R.drawable.ahmadi);
        }
    }
}