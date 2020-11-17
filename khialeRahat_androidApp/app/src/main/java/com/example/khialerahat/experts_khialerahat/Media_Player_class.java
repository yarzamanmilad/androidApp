package com.example.khialerahat.experts_khialerahat;

import android.content.Context;
import android.media.MediaPlayer;

public  class Media_Player_class
{
    Context context;
    static MediaPlayer mediaPlayer;

    public Media_Player_class(Context context) {
        this.context = context;
        mediaPlayer=MediaPlayer.create(context, R.raw.a);
    }

    public  static void start_media()
    {
        mediaPlayer.start();
    }

    public  static void stop_media()
    {
        mediaPlayer.stop();
    }


}
