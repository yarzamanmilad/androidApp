package com.example.khialerahat.experts_khialerahat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Const_Variable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import co.ronash.pushe.PusheListenerService;

//import co.ronash.pushe.PusheListenerService;

public class PusheClass extends PusheListenerService
{
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    private Calendar calendar;
    String order_id="";
private Handler handler=new Handler();
    private Handler handler_counterdown_notAccept=new Handler();
    @Override
    public void onMessageReceived(JSONObject jsonObject, JSONObject jsonObject1) {
        Log.i("pushe",jsonObject.toString());
        calendar=Calendar.getInstance();
        String temp =jsonObject.toString();

        if(temp.contains("order_id")) {
            try {
                order_id=jsonObject.getString("order_id");
                operation_when_recive_new_orders(order_id,getBaseContext());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    private void operation_when_recive_new_orders(final String order_id_temp, final Context context)
    {
        MotakhasesObject.temp_new_order_list=order_id_temp;
        MPlayer_CounterDown.current_time_sms_recive = calendar.getTimeInMillis();
        Log.i("new_order",order_id_temp);

        Log.i("sharpref_in pusheclass",""+ PreferenceManager.getDefaultSharedPreferences(context).getBoolean("main_activity_isActive", false));

        MPlayer_CounterDown.mediaPlayer=MediaPlayer.create(context, R.raw.a);
        MPlayer_CounterDown.mediaPlayer.start();
        Log.i("sms:", "player start");
        // MediaPlayer mPlayer = MediaPlayer.create(context, R.raw.a);
        // mPlayer.start();
        if (PreferenceManager.getDefaultSharedPreferences(context).getBoolean("main_activity_isActive", false))
        {
            Log.i("sms_mplay","start"+order_id_temp);
             try {

                 Intent intent=new Intent(context,MainActivityy.class);
                 String pckagename=getPackageName();
                 Log.i("sms_mplay","start"+order_id_temp+pckagename);
                 intent.putExtra("new_order",order_id_temp);
                // intent.setClassName(pckagename, "MainActivityy");
                 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 context.startActivity(intent);
             }catch (ActivityNotFoundException e)
             {
                 e.printStackTrace();
                 Log.i("sms_mplay","start"+e.getMessage());
             }
        }
        else
        {

            final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
            final SharedPreferences.Editor editor = preferences.edit();
            Log.i("sms_mplay","start"+order_id_temp);
            editor.putString("new_order_id",order_id_temp);
            editor.apply();
            MPlayer_CounterDown.mediaPlayer=MediaPlayer.create(context,R.raw.a);
            MPlayer_CounterDown.mediaPlayer.start();
            Log.i("new_ordercont",order_id_temp);
            handler.post(new Runnable() {
                @Override
                public void run() {

                    MPlayer_CounterDown.countDownTimer=new CountDownTimer(Const_Variable.MAX_MILISECND_WAIT_TO_ACCEPT_NEW_ORDERS
                            ,Const_Variable.MAX_MILISECND_WAIT_TO_ACCEPT_NEW_ORDERS) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            Log.i("counter_down","in sms reciver is ontick");

                        }

                        @Override
                        public void onFinish() {
                            Log.i("counter_down","in sms reciver is finish");
                            editor.putString("new_order_id",null);
                            editor.apply();

                            Log.i("new_order","sms counter is finished:    MotakhasesObject.new_order_id=0");
                            if(MPlayer_CounterDown.mediaPlayer!=null)
                            {

                                if (MPlayer_CounterDown.mediaPlayer.isPlaying())
                                {
                                    MPlayer_CounterDown.mediaPlayer.stop();
                                    MPlayer_CounterDown.mediaPlayer=null;
                                }
                                else
                                {

                                    MPlayer_CounterDown.mediaPlayer=null;
                                }
                                Log.i("sms:", "player stop");
                            }
                        }
                    }.start();
                }
            });


            Intent intent=new Intent(context,SplashActivity.class);
            String pckagename=getPackageName();

           /// intent.setClassName(pckagename, "SplashActivity");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);


        }


    }
}
