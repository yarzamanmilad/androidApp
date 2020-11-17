package com.example.khialerahat.experts_khialerahat;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khialerahat.experts_khialerahat.List_Sefareshha.A_secur_panel;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Const_Variable;
import com.example.khialerahat.experts_khialerahat.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class S_Security_Panel extends Service {
    private static final String CHANNEL_ID ="1111";
    private static final int NOTIFICATION_ID = 1111;
    MediaPlayer mediaPlayer;
    CountDownTimer countDownTimer;
    String order_id;
    String expert_name_family;
    int security_panel_number=2;
    private CountDownTimer countDownTimer_user_notresponse;
    private NotificationCompat.Builder builder;
    private Intent notifyIntent;
    private PendingIntent notifyPendingIntent;
    private int tick_number=0;
    private NotificationManagerCompat notificationManager;

    int number_of_time=2;
    public S_Security_Panel() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("service_secur","start service");
        if(intent!=null)
        {
            String temp_get_intent= intent.getStringExtra("notification");
            String tempe_order_id=intent.getStringExtra("order_id");
            expert_name_family=intent.getStringExtra("expert_name_family");
            Log.i("service_secur","start from  notification  name: "+expert_name_family+tempe_order_id);
            if(temp_get_intent!=null)
            {
                Log.i("service_secur","start from  notification");
                start_stop_alarm(false);
                send_time_to_server(order_id,String.valueOf(number_of_time));
                number_of_time++;
            }
        else if(tempe_order_id!=null && expert_name_family!=null )
            {
                order_id=tempe_order_id;
                call_countDownTimer();Log.i("service_secur","start  when notification is null"+tempe_order_id+"  "+expert_name_family);
            }
        }
       else
       {call_countDownTimer();Log.i("service_secur","start");Log.i("service_secur","null start from  notification");}


        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("service_secur","destroy");
        if(mediaPlayer!=null)
        {
            mediaPlayer.release();
            mediaPlayer=null;
        }
        else
        {}
        if(countDownTimer!=null)
        countDownTimer.cancel();
        if (countDownTimer_user_notresponse!=null)
        countDownTimer_user_notresponse.cancel();
    }

    private void call_countDownTimer()
    {

        countDownTimer=new CountDownTimer(Const_Variable.MAX_WORK_DURATION,Const_Variable.TIME_FRAME_CICLE_IN_SECUR_PANEL) {

            @Override
            public void onTick(long millisUntilFinished)
            {
                Log.i("counter_timer","countDownTimer ontick  in service");
                if(tick_number>0) {
                    if (mediaPlayer!=null)
                    start_stop_alarm(false);
                    // notificationManager.cancel(1111);  error
                    start_stop_alarm(true);
                }
                tick_number++;

            }

            @Override
            public void onFinish() {
                Log.i("counter_timer","countDownTimer finish  in service");
            }
        }.start();
    }

    private void start_stop_alarm(boolean start_or_stop)
    {
        if(start_or_stop)
        {
            mediaPlayer=MediaPlayer.create(getBaseContext(), R.raw.a);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
           countDownTimer_user_notresponse_func();
            Log.i("service_secur","start alarm  in service");
            Intent intent_secur_panel_activity=new Intent(this, A_secur_panel.class);
             Log.i("service_secur","start secur activity");
            intent_secur_panel_activity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent_secur_panel_activity);
          //  creat_intent_for_notification();
         //   build_notification();

        }
        else
        {
           if (mediaPlayer!=null) {
               mediaPlayer.release();
               mediaPlayer = null;
           }
            Log.i("service_secur","stop alarm in service");


        }
    }


    ///1
    public void creat_intent_for_notification()
    {
         notifyIntent = new Intent(this, S_Security_Panel.class);
         notifyIntent.putExtra("notification","true");
// Set the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
// Create the PendingIntent
         notifyPendingIntent = PendingIntent.getService(
                this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );
    }
    ////2
   public void build_notification()
    {
        builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentIntent(notifyPendingIntent);
        builder.setContentTitle("خیال راحت");
        builder.setSmallIcon(R.drawable.ic_security_black_24dp);
        builder.setContentText("تایید امنیتی");
        builder.setAutoCancel(true);
         notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void countDownTimer_user_notresponse_func()
    {
        countDownTimer_user_notresponse=new CountDownTimer(Const_Variable.TIME_FRAME_CICLE_IN_SECUR_PANEL_WHEN_USER_NOT_RESPONSE,
                Const_Variable.TIME_FRAME_CICLE_IN_SECUR_PANEL_WHEN_USER_NOT_RESPONSE) {
            @Override
            public void onTick(long millisUntilFinished)
            {
                Log.i("service_secur","countDownTimer_notresponse ontick  in service");
            }

            @Override
            public void onFinish()
            {
                start_stop_alarm(false);
                Log.i("service_secur","countDownTimer_notresponse onfinish  in service");
                    send_alarm_to_managment_and_server(order_id,expert_name_family);
            }
        }.start();
    }

    private void send_time_to_server(final String order_id, final String int_number_last_status)
    {

        String url="http://khialrahat.com/app_files/Motakhases_Api/security_panel.php";

        StringRequest stringRequest =new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("service_secur ",response.toString());

                        if(response!=null)
                        {
                            if(response.length()>0)
                            {
                                try {


                                    JSONArray jsonArray=new JSONArray(response);

                                    JSONObject jsonObject=jsonArray.getJSONObject(0);


                                    Log.i("service_secur ","دریافت موفق");

                                }catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }

                            }
                            else {
                                Log.i("service_secur","response have not nodat");
                            }
                        }
                        else
                        {Log.i("service_secur","response is null");}

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("order_id",order_id);
                parms.put("number",int_number_last_status);
                return parms;
            }
        };

        //10000 is the time in milliseconds adn is equal to 10 sec
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(stringRequest);

    }
    private void send_alarm_to_managment_and_server(final String order_id , final String expert_name_family)
    {

        String url="http://khialrahat.com/app_files/Motakhases_Api/security_panel_expert_alarm.php";

        StringRequest stringRequest =new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("alarm_to_managment ",response.toString());

                        if(response!=null)
                        {
                            if(response.length()>0)
                            {
                                try {


                                    JSONArray jsonArray=new JSONArray(response);

                                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                                    Log.i("alarm_to_managment ",jsonObject.getString("error"));
                                    Log.i("alarm_to_managment ",jsonObject.getString("message"));

                                    Log.i("alarm_to_managment ","دریافت موفق");

                                }catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }

                            }
                            else {
                                Log.i("alarm_to_managment","response have not nodat");
                            }
                        }
                        else
                        {Log.i("alarm_to_managment","response is null");}

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("order_id",order_id);
                parms.put("expert_name",expert_name_family);
                return parms;
            }
        };

        //10000 is the time in milliseconds adn is equal to 10 sec
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(stringRequest);

    }

}
