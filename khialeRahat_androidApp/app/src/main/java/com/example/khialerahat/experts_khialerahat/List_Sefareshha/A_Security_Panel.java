package com.example.khialerahat.experts_khialerahat.List_Sefareshha;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khialerahat.experts_khialerahat.MainPackage.Model_Profile;
import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.My_interface;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Const_Variable;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Model_Takhasos;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Model_Takhasos_level2;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Model_Time;
import com.example.khialerahat.experts_khialerahat.Progress_And_Dialog;
import com.example.khialerahat.experts_khialerahat.R;
import com.example.khialerahat.experts_khialerahat.Web_Service.Valley_Api;
import com.example.khialerahat.experts_khialerahat.app.AppController;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A_Security_Panel extends AppCompatActivity implements My_interface, View.OnClickListener{

    private Model_Sefaresh_Jadid model_sefaresh_jadid;
    String order_id;
    TextView customer_mobile,customer_address,final_price,
    payment_type,payment_status,number_last_status,services,txt_alarm;
    Button btn_start,btn_end;
  public   CountDownTimer countDownTimer,countDownTimer_user_notresponse;
    private int int_number_last_status;
    boolean boolean_work_is_started=false;////////برای استفاده دوگانه از دکمه   حضور در محل
    boolean boolean_temp_for_not_response_alarm=false;
    MediaPlayer mediaPlayer;
    private Animation shake;
    Progress_And_Dialog progress_and_dialog;
    AlertDialog alertDialog;
    private Valley_Api valley_api;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a__security__panel);
         progress_and_dialog=new Progress_And_Dialog(this,alertDialog);
         valley_api=new Valley_Api(this);
         shake = (Animation) AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake2);
        Gson gson = new Gson();
         model_sefaresh_jadid = gson.fromJson(getIntent().getStringExtra("myjson_model_sefaresh_jadid"), Model_Sefaresh_Jadid.class);
         order_id=getIntent().getStringExtra("order_id");

        preferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
         preferences.edit().putString("new_order_id",null).apply();

        initial_views();

    }

    private void initial_views()
      {
        txt_alarm=findViewById(R.id.txt_alarm_secuer_panel);
        txt_alarm.setVisibility(View.INVISIBLE);
        number_last_status=findViewById(R.id.txt_number_last_status);
        customer_mobile=findViewById(R.id.txt_mobile_secuer_panel);
        customer_address=findViewById(R.id.txt_address_secuer_panel);
        final_price=findViewById(R.id.txt_final_price_secuer_panel);
        payment_type=findViewById(R.id.txt_payment_type_secuer_panel);
        payment_status=findViewById(R.id.txt_payment_status_secuer_panel);
        services=findViewById(R.id.txt_services_secuer_panel);
        btn_start=findViewById(R.id.start_work_secplan);
        btn_start.setOnClickListener(this);
        btn_end=findViewById(R.id.end_work_secue_plan);
        btn_end.setOnClickListener(this);
        btn_end.setEnabled(false);

        number_last_status.setText("0");
        customer_mobile.setText(model_sefaresh_jadid.getShomareh_hamrah_moshtari());
        customer_address.setText(model_sefaresh_jadid.getAddress());

        String[] separated = model_sefaresh_jadid.getNoeh_khadamat_jozei().split("%");
        // this will contain "Fruit"
        String temp="";
        for(int x=0;x<separated.length;x++)
        {
            temp+=separated[x];
            temp+="\n";
        }
        services.setText(temp);



        if(model_sefaresh_jadid.getVazeiat_pardakht()== Const_Variable.PAYMENT_STATUS_IS_PARDAKHT_SHODE)
        {
            payment_status.setText(Const_Variable.PARDAKHT_SHODE_WORD);
            payment_status.setTextColor(getResources().getColor(R.color.green));
            payment_type.setTextColor(getResources().getColor(R.color.green));
            final_price.setTextColor(getResources().getColor(R.color.green));
            payment_status.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious));

            final_price.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious));
        }
        else
        {
            payment_status.setText(Const_Variable.PARDAKHT_NASHODE_WORD);

            payment_status.setTextColor(getResources().getColor(R.color.red));
            payment_type.setTextColor(getResources().getColor(R.color.red));
            final_price.setTextColor(getResources().getColor(R.color.red));

            payment_status.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious_ghermez));

            final_price.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious_ghermez));
        }


        if(model_sefaresh_jadid.getNahve_pardakht()== Const_Variable.PAYMENT_TYPE_IS_ONLIN)
        {
            payment_type.setText(Const_Variable.ONLIN_WORD);
            payment_type.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious));
          }
        else
        {
            payment_type.setText(Const_Variable.NAGHDI_WORD);
            payment_type.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious_ghermez));
           }

        final_price.setText(model_sefaresh_jadid.getMablagh_nahaei());


    }

    @Override
    public void onClick(View v)
    {
     switch (v.getId())
     {
         case R.id.start_work_secplan:
             Log.i("btn_start","boolean"+boolean_work_is_started);
             if(boolean_work_is_started)
             {

                 int_number_last_status=Integer.parseInt( number_last_status.getText().toString());
                 int_number_last_status++;

                 send_time_to_server(order_id,String.valueOf(int_number_last_status));
                 start_stop_alarm(false);
                 number_last_status.setText(""+int_number_last_status);
                 boolean_temp_for_not_response_alarm=true;

             }
             else
             {

                 int_number_last_status=Integer.parseInt( number_last_status.getText().toString());
                 int_number_last_status++;
                 btn_start.setText("بدون مشکل");
                 btn_start.setEnabled(false);
                 btn_end.setEnabled(true);
                 send_time_to_server(order_id,String.valueOf(int_number_last_status));
                 call_countDownTimer();
                 number_last_status.setText(""+int_number_last_status);

             }
             break;
         case R.id.end_work_secue_plan:
             Log.i("btn_start","boolean"+boolean_work_is_started);
             operation_when_work_finished();
             if (countDownTimer_user_notresponse!=null) {
                 countDownTimer_user_notresponse.cancel();
                 countDownTimer_user_notresponse=null;
             }

             if(countDownTimer!=null)
             {
                 countDownTimer.cancel();
                 countDownTimer=null;
             }
             break;
         case R.id.txt_mobile_secuer_panel:
             Log.i("btn_start","boolean"+boolean_work_is_started);
             break;

     }
    }

    private void operation_when_work_finished()
    {
        valley_api.get_order_payment_type_from_server(order_id);

    }
    @Override
    public void get_order_payment_type_from_server(String payment_type, String name)
    {

        model_sefaresh_jadid.setNahve_pardakht(payment_type);
        Log.i("getpayment_typ",payment_type);
        if(payment_type.contentEquals(Const_Variable.PAYMENT_TYPE_IS_NOT_ONLIN))
        {

            Log.i("getpayment_typ",payment_type);
//            int percentage= Integer.parseInt(model_sefaresh_jadid.getSahme_motakhases());
//            int discount= Integer.parseInt(model_sefaresh_jadid.getTakhfif());
//            float amount=Float.valueOf(model_sefaresh_jadid.getHazineh());
//            float final_price=(amount*discount)/100;
//            float khialerahat_shaired=( final_price*(100-percentage))/100;
          progress_and_dialog.end_work_and_payment_type_is_naghdi("","دریافت وجه نقدی",
                    "مبلغ"+model_sefaresh_jadid.getKhialerahat_amount()+"تومان از کبف پول شما کسر می شود. ");
        }
        else
        {
        }
    }

    public void final_update_when_work_is_finish()
    {
        Log.i("final_update_when_","start");
        valley_api.update_final_status_work_is_finish(order_id,Const_Variable.WORK_STATUS_IS_OK,
                MotakhasesObject.id,model_sefaresh_jadid.getKhialerahat_amount()
        ,model_sefaresh_jadid.getMotakhases_amount(),model_sefaresh_jadid.getNahve_pardakht());
        countDownTimer_user_notresponse=null;
        countDownTimer=null;
        if (mediaPlayer!=null)
        {

            mediaPlayer.release();
            mediaPlayer=null;
        }
        MotakhasesObject.new_order_id="0";
    }

    private void send_time_to_server(final String order_id, final String int_number_last_status)
    {

        String url="http://khialrahat.com/app_files/Motakhases_Api/security_panel.php";

        StringRequest stringRequest =new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("security_panel ",response.toString());

                        if(response!=null)
                        {
                            if(response.length()>0)
                            {
                                try {


                                    JSONArray jsonArray=new JSONArray(response);

                                    JSONObject jsonObject=jsonArray.getJSONObject(0);


                                    Log.i("security_panel ","دریافت موفق");

                                }catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }

                            }
                            else {
                                Log.i("security_panel","response have not nodat");
                            }
                        }
                        else
                        {Log.i("security_panel","response is null");}

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

    private void call_countDownTimer()
    {

        countDownTimer=new CountDownTimer(Const_Variable.MAX_WORK_DURATION,Const_Variable.TIME_FRAME_CICLE_IN_SECUR_PANEL) {

            @Override
            public void onTick(long millisUntilFinished)
            {
                Log.i("counter_timer",""+ A_Security_Panel.this.boolean_work_is_started);
                 if(A_Security_Panel.this. boolean_work_is_started)
                 {
                     Log.i("counter_",""+ A_Security_Panel.this.boolean_work_is_started);
                 start_stop_alarm(true);

                                  }

                A_Security_Panel.this. boolean_work_is_started=true;

            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void start_stop_alarm(boolean start_or_stop)
    {
        if(start_or_stop)
        {
            mediaPlayer=MediaPlayer.create(getBaseContext(),R.raw.b);
            mediaPlayer.start();
            txt_alarm.setVisibility(View.VISIBLE);


            shake.setRepeatCount(Animation.INFINITE);
            txt_alarm.startAnimation(shake);
            btn_start.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious_ghermez));
            boolean_temp_for_not_response_alarm=false;
            countDownTimer_user_notresponse_func();
            btn_start.setEnabled(true);
        }
        else
        {

            mediaPlayer.stop();
            txt_alarm.clearAnimation();
            txt_alarm.setVisibility(View.INVISIBLE);
            btn_start.setEnabled(false);
            btn_start.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious));


        }
    }

    private void countDownTimer_user_notresponse_func()
    {
        countDownTimer_user_notresponse=new CountDownTimer(Const_Variable.TIME_FRAME_CICLE_IN_SECUR_PANEL_WHEN_USER_NOT_RESPONSE,
                Const_Variable.TIME_FRAME_CICLE_IN_SECUR_PANEL_WHEN_USER_NOT_RESPONSE) {
            @Override
            public void onTick(long millisUntilFinished)
            {

            }

            @Override
            public void onFinish()
            {
                if(!boolean_temp_for_not_response_alarm)
                    send_alarm_to_managment_and_server(order_id);
            }
        }.start();
    }

    private void send_alarm_to_managment_and_server(final String order_id)
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






    @Override
    public void txtfilter(Boolean harfe_vrodi_sahih_ast, int textInputLayout, int edtxt_id) {

    }

    @Override
    public void greftan_list_manategh_entkhabi_as_recycler_view_adapter(String manategh_ya_takhasos, int item_position, int meghdar) {

    }

    @Override
    public void greftan_list_shahrha(List<String> list_shahrha, List<String> list_id_shahrha) {

    }

    @Override
    public void greftan_list_ostanha(List<String> list_ostanha) {

    }

    @Override
    public void greftan_list_manategh(List<String> list_manategh) {

    }

    @Override
    public void greftan_list_takhasos(Model_Takhasos[] list_takhasos) {

    }

    @Override
    public void greftan_list_takhasos_level2(Model_Takhasos_level2[] list_takhasos_level2) {

    }

    @Override
    public void greftan_list_times(Model_Time[] list_time) {

    }

    @Override
    public void greftan_id_motakhases(String id) {

    }

    @Override
    public void upload_status(Boolean status, String feild_name) {

    }

    @Override
    public void recive_data_from_server_failur(String error_status, String title_error, String error_message) {

    }

    @Override
    public void username_athuntication(String status_username) {

    }

    @Override
    public void password_athuntication(MotakhasesObject motakhasesObject) {

    }

    @Override
    public void get_profile_data(Model_Profile model_profile) {

    }

    @Override
    public void greftan_list_manategh_entkhabi_have_childeren_as_recycler_view_adapter(String takhasos_name, int index, boolean chtxt_ischecked) {

    }

    @Override
    public void failure_in_athunticaation(boolean boolean_exit_or_repeat) {

    }


    @Override
    public void c1(String c) {

    }

    @Override
    public void a1(String a) {

    }

    @Override
    public void b11(String b) {

    }

    @Override
    public void c11(String c) {

    }

    @Override
    public void a11(String a) {

    }
}
