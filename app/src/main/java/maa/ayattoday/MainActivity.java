package maa.ayattoday;

import android.annotation.SuppressLint;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

import maa.ayattoday.Interfaces.AyahInterface;
import maa.ayattoday.Interfaces.WallpapersInterface;
import maa.ayattoday.Models.Ayah;
import maa.ayattoday.Models.WallpapersIslamic;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    String mBaseUrl, mBaseUrlWallpaper = "https://wall.alphacoders.com/api2.0/";
    Retrofit mRetrofit, mRetrofitWallpapers, mRetrofitAudio;
    Call<Ayah> mCallAyah;
    Call<WallpapersIslamic> mCallWallpapers;
    AyahInterface mAyahInterface;
    WallpapersInterface mWallpapersInterface;
    TextView mArabic, mEnglish, mSurha;
    int AyahNumber;
    ProgressBar mProgressBar, mLoading;
    LinearLayout mViews;
    ImageView img;
    ImageButton playTrigger;
    MediaPlayer mediaPlayer;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mArabic = findViewById(R.id.arabicEdition);
        mEnglish = findViewById(R.id.englishEdition);
        mSurha = findViewById(R.id.soura);
        mProgressBar = findViewById(R.id.mProgressBar);
        mViews = findViewById(R.id.Views);
        img = findViewById(R.id.img);
        playTrigger = findViewById(R.id.playTrigger);
        mLoading = findViewById(R.id.laoding);
        AyahNumber = new Random().nextInt(6239 - 1) + 1;
        mBaseUrl = "https://api.alquran.cloud/v1/ayah/" + AyahNumber + "/editions/";
        mRetrofit = getRetrofitClient(mBaseUrl);
        mAyahInterface = mRetrofit.create(AyahInterface.class);
        mCallAyah = mAyahInterface.getAyahInformation();
        mCallAyah.enqueue(new Callback<Ayah>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<Ayah> call, @NotNull Response<Ayah> response) {
                mProgressBar.setVisibility(View.GONE);
                mViews.setVisibility(View.VISIBLE);
                if (response.body() != null) {
                    mArabic.setText("" + response.body().getData()[0].getText());
                    mEnglish.setText("" + response.body().getData()[1].getText());
                    mSurha.setText("" + response.body().getData()[0].getSurah().getEnglishName() + " : " + response.body().getData()[0].getNumber());
                }
            }

            @Override
            public void onFailure(@NotNull Call<Ayah> call, @NotNull Throwable t) {

            }
        });

        mRetrofitWallpapers = getRetrofitClient(mBaseUrlWallpaper);
        mWallpapersInterface = mRetrofitWallpapers.create(WallpapersInterface.class);
        mCallWallpapers = mWallpapersInterface.getWallpapers();
        mCallWallpapers.enqueue(new Callback<WallpapersIslamic>() {
            @Override
            public void onResponse(@NotNull Call<WallpapersIslamic> call, @NotNull Response<WallpapersIslamic> response) {
                String URL = null;
                if (response.body() != null) {
                    URL = response.body().getWallpapers()[new Random().nextInt(30 + 1) - 1].getUrl_image().toString();
                    Glide.with(getApplicationContext())
                            .load(URL).into(img);
                }

            }

            @Override
            public void onFailure(Call<WallpapersIslamic> call, Throwable t) {

            }
        });
        Uri uri = Uri.parse("http://cdn.alquran.cloud/media/audio/ayah/ar.alafasy/" + AyahNumber + "/low");
        mLoading.setVisibility(View.VISIBLE);
        playTrigger.setVisibility(View.GONE);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
        mediaPlayer = MediaPlayer.create(MainActivity.this, uri);
        mediaPlayer.setAudioAttributes(
                new AudioAttributes
                        .Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build());
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mLoading.setVisibility(View.GONE);
                playTrigger.setVisibility(View.VISIBLE);

            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playTrigger.setImageDrawable(getResources().getDrawable(R.drawable.toggle_d));
            }
        });
        playTrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    playTrigger.setImageDrawable(getResources().getDrawable(R.drawable.toggle_d));
                } else {
                    mediaPlayer.start();
                    playTrigger.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_black));
                }
            }
        });

    }
    public Retrofit getRetrofitClient(String URL) {
        return new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
}
