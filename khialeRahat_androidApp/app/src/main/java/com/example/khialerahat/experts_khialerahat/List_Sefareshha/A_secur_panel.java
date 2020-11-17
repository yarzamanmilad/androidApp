package com.example.khialerahat.experts_khialerahat.List_Sefareshha;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Const_Variable;
import com.example.khialerahat.experts_khialerahat.R;
import com.example.khialerahat.experts_khialerahat.S_Security_Panel;
import com.example.khialerahat.experts_khialerahat.SplashActivity;

import android.os.Vibrator;

import java.util.Random;

public class A_secur_panel extends AppCompatActivity {
 TextView txtview;
    private Animation shake;
    private Vibrator vibre;
    private CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_secur_panel);
        initial_view();
        shake = (Animation) AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake2);

        shake.setRepeatCount(Animation.INFINITE);
        txtview.startAnimation(shake);


         vibre = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        txtview_onclick();
        conterdown_timer();
// Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            long[] patern={0,100,1000};
                vibre.vibrate(patern,3);


        } else {
            //deprecated in API 26
            long[] patern={0,100,1000};

                vibre.vibrate(patern,3);


        }

    }

    private void conterdown_timer()
    {

        countDownTimer=new CountDownTimer(Const_Variable.TIME_FRAME_CICLE_IN_SECUR_PANEL_WHEN_USER_NOT_RESPONSE,
                Const_Variable.TIME_FRAME_CICLE_IN_SECUR_PANEL_WHEN_USER_NOT_RESPONSE) {
            @Override
            public void onTick(long millisUntilFinished)
            {
                Log.i("A_securPanel","countDownTimer_notresponse ontick  in service");
            }

            @Override
            public void onFinish()
            {

                Log.i("A_securPanel","countDownTimer_notresponse onfinish  in service");
                Intent intent = new Intent(getBaseContext(), SplashActivity.class);
                startActivity(intent);
            }
        }.start();
    }

    private void txtview_onclick() {
        txtview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                Intent intent=new Intent(getBaseContext(), S_Security_Panel.class);
                intent.putExtra("notification","true");
                startService(intent);
                if(v!=null)
                {
                    vibre.cancel();
                }
                else
                {}
               // A_secur_panel.super.finish();
                intent=new Intent(getBaseContext(), SplashActivity.class);
                startActivity(intent);

            }
        });
    }

    private void initial_view() {
        txtview=findViewById(R.id.txt_alarm_secuer_panel_main);
    }
}
