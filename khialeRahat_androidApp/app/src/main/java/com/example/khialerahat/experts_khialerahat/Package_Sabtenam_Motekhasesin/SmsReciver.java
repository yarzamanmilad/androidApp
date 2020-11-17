package com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.telephony.SmsMessage;
import android.util.Log;

import com.example.khialerahat.experts_khialerahat.MPlayer_CounterDown;
import com.example.khialerahat.experts_khialerahat.MainActivityy;
import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.R;
import com.example.khialerahat.experts_khialerahat.SplashActivity;

import java.util.Calendar;

public class SmsReciver extends BroadcastReceiver {


   public static Interface_checkKardanCodemeli_ShomareHamrah minterface_checkKardanCodemeli_shomareHamrah;
    private String messageBody="";
    private String temp_character=Const_Variable.CHARACTER_SPLIT;

    public static MediaPlayer mPlayer;
    public static long current_time_sms_recive;
    Calendar calendar ;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getExtras();

        calendar = Calendar.getInstance();

        Object[] pdus = (Object[]) data.get("pdus");



        for(int i=0;i<pdus.length;i++){

            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
            String sender = smsMessage.getDisplayOriginatingAddress();
            Log.i("sms","sender :"+sender);
            String code_taaid_temp=new String();
            messageBody = smsMessage.getMessageBody();
            Log.i("sms","body :"+messageBody);



            String order_id_temp=new String();
            if (messageBody.contains(temp_character))
            {
                Log.i("new_order","recived");
                int temp_tow=0;
                operation_when_recive_new_orders(messageBody,context);
                for (int z=0;z<messageBody.length();z++)
                {
                    char x=messageBody.charAt(z);
                    if (temp_character.contains(String.valueOf(x)))
                    {
                        temp_tow++;

                    }
                    else
                    {}

                    if (temp_tow==1&& !temp_character.contains(String.valueOf(x)))
                    {
                        order_id_temp+=x;
                    }
                    else if (temp_tow==2)
                    {
                       // operation_when_recive_new_orders(order_id_temp,context);

                    }
                    else
                    {}
                }
            }
            else
            {
                for (int j=messageBody.length()-1;j>0;j--)
                {
                    char temp=messageBody.charAt(j);
                    if(Character.isDigit(temp))
                    {
                        code_taaid_temp+=temp;
                    }

                    String code_taaid=new String();

                    for (int x=code_taaid_temp.length()-1;x>=0;x--)
                    {
                        code_taaid+= code_taaid_temp.charAt(x);
                        Log.i("messageBody", code_taaid_temp.charAt(x)+"");
                    }
                    Log.i("messageBody",messageBody);

                    //Pass on the text to our listener.
                    if (PreferenceManager.getDefaultSharedPreferences(context).getBoolean("isActive", false) && code_taaid.length()>=4) {
                        minterface_checkKardanCodemeli_shomareHamrah.checkKardan_shomarehHamrah_smsBroadcastReciver(code_taaid);
                    }
                    else
                    {Log.i("messageBody",messageBody+"activity sabtenam is stop");}


                }
            }

        }




    }

    private void operation_when_recive_new_orders(final String order_id_temp, final Context context)
    {   MotakhasesObject.temp_new_order_list=order_id_temp;
        current_time_sms_recive = calendar.getTimeInMillis();
        MPlayer_CounterDown.current_time_sms_recive=current_time_sms_recive;
        Log.i("new_order",order_id_temp);

        Log.i("sharpref_in sms",""+PreferenceManager.getDefaultSharedPreferences(context).getBoolean("main_activity_isActive", false));

        MPlayer_CounterDown.mediaPlayer=MediaPlayer.create(context,R.raw.a);
        MPlayer_CounterDown.mediaPlayer.start();
        Log.i("sms:", "player start");
       // MediaPlayer mPlayer = MediaPlayer.create(context, R.raw.a);
       // mPlayer.start();
        if (PreferenceManager.getDefaultSharedPreferences(context).getBoolean("main_activity_isActive", false))
        {
            Log.i("sms_mplay","start"+order_id_temp);
            Intent intent=new Intent(context, MainActivityy.class);
            intent.putExtra("new_order",order_id_temp);
          //  intent.setClassName("com.example.experts_khialerahat2", "MainActivityy");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             context.startActivity(intent);
        }
        else
        {

            final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
            final SharedPreferences.Editor editor = preferences.edit();

           // mPlayer = MediaPlayer.create(context, R.raw.a);
            //mPlayer.start();
            Log.i("sms_mplay","start"+order_id_temp);
            editor.putString("new_order_id",order_id_temp);
            editor.apply();
            MPlayer_CounterDown.mediaPlayer=MediaPlayer.create(context,R.raw.a);
            MPlayer_CounterDown.mediaPlayer.start();
            Log.i("new_ordercont",order_id_temp);
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


            Intent intent=new Intent(context, SplashActivity.class);

            //intent.setClassName("com.example.experts_khialerahat2", "SplashActivity");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
//            CountDownTimer countDownTimer=new CountDownTimer(Const_Variable.MAX_MILISECND_WAIT_TO_ACCEPT_NEW_ORDERS
//                    ,Const_Variable.MAX_MILISECND_WAIT_TO_ACCEPT_NEW_ORDERS) {
//                @Override
//                public void onTick(long millisUntilFinished) {
//
//                }
//
//                @Override
//                public void onFinish() {
//
//                    editor.putString("new_order_id",null);
//                    editor.apply();
//                    MotakhasesObject.new_order_id="0";
//                    Log.i("new_order","counter is finished:    MotakhasesObject.new_order_id=0");
//                    mPlayer.stop();
//                    mPlayer=null;
//                }
//            }.start();

        }


    }

    public static void bind_interface_checkKardanCodemeli_shomareHamrah(Interface_checkKardanCodemeli_ShomareHamrah
                                                                           interface_checkKardanCodemeli_shomareHamrah)
    {
        minterface_checkKardanCodemeli_shomareHamrah=interface_checkKardanCodemeli_shomareHamrah;
    }

}
