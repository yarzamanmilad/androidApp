package com.example.khialerahat.experts_khialerahat.List_Sefareshha;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khialerahat.experts_khialerahat.MPlayer_CounterDown;
import com.example.khialerahat.experts_khialerahat.MainActivityy;
import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Const_Variable;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.SmsReciver;
import com.example.khialerahat.experts_khialerahat.Progress_And_Dialog;
import com.example.khialerahat.experts_khialerahat.R;
import com.example.khialerahat.experts_khialerahat.app.AppController;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Temp_F_List_Sefaresh_Jadid extends Fragment  {

    ListView list1;
    TextView txt_sefarsh_jadid_vjod_nadarad
            ,txtv_zaman,txtv_address_name,txtv_services,txtv_total_price
            ,txtv_discont,txtv_final_price,txtv_myshred_amount,txtv_payment_status,txtv_payment_type;
     View view;
     ConstraintLayout constraintLayout;
       NestedScrollView nestedScrollView;
     Context myContext;
     Button btn_accept,btn_not_accept;
    private TextView txtv_wallet_status;
    private Progress_And_Dialog progress_and_dialog;
       AlertDialog alertDialog;
       Model_Sefaresh_Jadid model_sefaresh_jadid;
       String order_id_final;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.a_jozeiat_list_sefaresh_jadid, container, false);
        initials_view();


        progress_and_dialog=new Progress_And_Dialog(getContext(),alertDialog);
        nestedScrollView.setVisibility(View.INVISIBLE);
        Log.i("list_sefaresh","list_sefaresh_jadid_shode_oncreat");
        this.myContext=container.getContext();

        btn_accept_onclick();
        btn_not_accept_onclick();

        return view;
    }

    private void btn_not_accept_onclick()
    {
        btn_not_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 progress_and_dialog.new_order_not_accept_alertdialog("true","لغو سفارش","سفارش شما لغو شد");
                 nestedScrollView.setVisibility(View.INVISIBLE);
                 txt_sefarsh_jadid_vjod_nadarad.setVisibility(View.VISIBLE);

            }
        });
    }

    private void btn_accept_onclick()
    {
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("btn_accept",""+
                        ( Calendar.getInstance().getTimeInMillis()-SmsReciver.current_time_sms_recive)+"::"
                        +Const_Variable.MAX_MILISECND_WAIT_TO_ACCEPT_NEW_ORDERS+"::"
                        +Const_Variable.MILSEC_NEED_TO_SEND_AND_RECIVE_DATA);
                if(
                        Calendar.getInstance().getTimeInMillis()-SmsReciver.current_time_sms_recive
                                <=Const_Variable.MAX_MILISECND_WAIT_TO_ACCEPT_NEW_ORDERS
                                -Const_Variable.MILSEC_NEED_TO_SEND_AND_RECIVE_DATA)
                {

                    update_order_status_in_server(order_id_final,MotakhasesObject.id);


                }
                else
                {not_found_new_order_liftime_end();
                    MotakhasesObject.new_order_id=null;
                }

            }
        });
    }

    private void update_order_status_in_server(final String order_id_final,final String expert_id)

    {

        String url="http://khialrahat.com/app_files/Motakhases_Api/update_order_status.php";

        StringRequest stringRequest =new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("update_order_status ",response.toString());

                        if(response!=null)
                        {
                            if(response.length()>0)
                            {
                                try {


                                    JSONArray jsonArray=new JSONArray(response);

                                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                                      String error=jsonObject.getString("error");
                                      String message=jsonObject.getString("message");
                                      if (error.contentEquals("false"))
                                      {
                                         // start_activity_security_lanel();
                                          Log.i("update_order_status ",message);

                                           progress_and_dialog.new_order_not_accept_alertdialog("false","تغییر وضعیت ","لطفا لیست سفارشهای در حال انجام را بررسی کنید");

                                      }
                                      else if (message.contentEquals("متخصص انتخاب شده است"))
                                      {
                                          not_found_new_order_liftime_end();
                                          Log.i("update_order_status ",message);

                                      }

                                    Log.i("update_order_status ","دریافت موفق");

                                }catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }

                            }
                            else {
                                Log.i("update_order_status","response have not nodat");
                            }
                        }
                        else
                        {Log.i("update_order_status","response is null");}

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
                parms.put("order_id",order_id_final);
                parms.put("expert_id",expert_id);

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

    private void start_activity_security_lanel()
    {
        Intent intent=new Intent(getContext(),A_Security_Panel.class);
        intent.putExtra("order_id",order_id_final);
        Gson gson=new Gson();
        String myjson = gson.toJson(model_sefaresh_jadid);
        intent.putExtra("myjson_model_sefaresh_jadid", myjson);
        startActivity(intent);
        not_found_new_order();
        getActivity().finish();

    }

    private void initials_view()
    {

        txt_sefarsh_jadid_vjod_nadarad=view.findViewById(R.id.txt_f_list_sefaresh_jadid);
        constraintLayout=view.findViewById(R.id.mainconslay_sefarshjadid);
        btn_accept=view.findViewById(R.id.btn_accept_new_order);
        btn_not_accept=view.findViewById(R.id.btn_not_accept_new_order);
        txtv_address_name=view.findViewById(R.id.txt_address_sefaresh_jadid);
        txtv_zaman=view.findViewById(R.id.txt_zamane_sefaresh_jadid);
        txtv_services=view.findViewById(R.id.txt_khadamat_darkhasti_jadidh);
        txtv_total_price=view.findViewById(R.id.txt_hazineh_khadamat_jadid);
        txtv_discont=view.findViewById(R.id.txt_takhfif_jadid);
        txtv_final_price=view.findViewById(R.id.txt_mizan_daryafti);
        txtv_myshred_amount=view.findViewById(R.id.txt_sahme_motakhases_sefjadid);
        txtv_payment_status=view.findViewById(R.id.txt_vazeiat_pardakht_sefjadid);
        txtv_payment_type=view.findViewById(R.id.txt_noeh_pardakht_hazine_sefjadid);
        txtv_wallet_status=view.findViewById(R.id.txt_wallet_status_sefjadid);
        nestedScrollView=view.findViewById(R.id.neasted_sefarshjadid);
    }


    @Override
    public void onResume() {
        super.onResume();
           if (MPlayer_CounterDown.mediaPlayer!=null) {

            if(MPlayer_CounterDown.mediaPlayer.isPlaying())
            {

                MPlayer_CounterDown.mediaPlayer.release();
                MPlayer_CounterDown.mediaPlayer=null;
                Log.i("mplayer","in f_sefarsh_jadid onresum  is stop and set null");
            }
            else
            {

                Log.i("mplayer","in f_sefarsh_jadid onresum  set null");
                MPlayer_CounterDown.mediaPlayer=null;
            }
        }

        Log.i("list_sefarsh_f","on page selct not in jadid"+MotakhasesObject.new_order_id);
        if(MotakhasesObject.new_order_id.contentEquals("0"))
        {
             not_found_new_order();
        }
        else
        {
              /////////////////////////////محل مقایسه زمان دریافت اس ام اس
            order_id_final=MotakhasesObject.new_order_id;
            MotakhasesObject.new_order_id="0";

            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("new_order_id",null).commit();
            ((MainActivityy)getContext()).navigation.setNotification("",0);
            MPlayer_CounterDown.countDownTimer.cancel();
            MPlayer_CounterDown.countDownTimer=null;
            Log.i("counterdown","cancel in onresum sefarsh jadid");
            ((F_List_Sefareshha)getParentFragment()).get_new_order_details(order_id_final);
        }
        //Toast.makeText(getContext(), "jadid", Toast.LENGTH_SHORT).show();
        Log.i("list_sefaresh","list_sefaresh_jadid_onresum");
    }

    public void sefarsh_jadid(Model_Sefaresh_Jadid model_sefaresh_jadid)
    {
           this.model_sefaresh_jadid=new Model_Sefaresh_Jadid();
           this.model_sefaresh_jadid=model_sefaresh_jadid;
           nestedScrollView.setVisibility(View.VISIBLE);
           txt_sefarsh_jadid_vjod_nadarad.setVisibility(View.INVISIBLE);
           txtv_zaman.setText(model_sefaresh_jadid.getZaman_darkhasti_khadamat());
           txtv_address_name.setText(model_sefaresh_jadid.getAddress()+"\n"+"\n"+
                   model_sefaresh_jadid.getShomareh_hamrah_moshtari());

            String[] separated = model_sefaresh_jadid.getNoeh_khadamat_jozei().split("%");
        // this will contain "Fruit"
            String temp="";
            for(int x=0;x<separated.length;x++)
            {
                temp+=separated[x];
                temp+="\n";
            }

        txtv_services.setText(temp);
        txtv_total_price.setText(model_sefaresh_jadid.getHazineh());
        txtv_discont.setText(model_sefaresh_jadid.getTakhfif());
        txtv_myshred_amount.setText(model_sefaresh_jadid.getSahme_motakhases());

        if(model_sefaresh_jadid.getVazeiat_pardakht().contentEquals(Const_Variable.PAYMENT_STATUS_IS_PARDAKHT_SHODE))
        {
            txtv_payment_status.setText(Const_Variable.PARDAKHT_SHODE_WORD);
            txtv_payment_status.setTextColor(getResources().getColor(R.color.green));
        }
        else
        {
            txtv_payment_status.setText(Const_Variable.PARDAKHT_NASHODE_WORD);
            txtv_payment_status.setTextColor(getResources().getColor(R.color.red));
            txtv_payment_status.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious_ghermez));
        }
        if(model_sefaresh_jadid.getNahve_pardakht().contentEquals( Const_Variable.PAYMENT_TYPE_IS_ONLIN))
        {
            txtv_payment_type.setText(Const_Variable.ONLIN_WORD);
            txtv_payment_type.setTextColor(getResources().getColor(R.color.green));
            txtv_wallet_status.setText(Const_Variable.INCREASE_KIF_POL);
            txtv_wallet_status.setTextColor(getResources().getColor(R.color.green));
        }
        else
        {
            txtv_payment_type.setText(Const_Variable.NAGHDI_WORD);
            txtv_payment_type.setTextColor(getResources().getColor(R.color.red));
            txtv_wallet_status.setText(Const_Variable.DECREASE_KIF_POL);
            txtv_wallet_status.setTextColor(getResources().getColor(R.color.red));
            txtv_wallet_status.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious_ghermez));
            txtv_payment_type.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious_ghermez));
        }

        float float_total_price=Float.valueOf(model_sefaresh_jadid.getHazineh());
        float float_discount=Float.valueOf(model_sefaresh_jadid.getTakhfif());
        float float_final_price=float_total_price-((float_total_price*float_discount)/100);
        model_sefaresh_jadid.setMablagh_nahaei(String.valueOf(float_final_price));
        txtv_final_price.setText(String.valueOf(float_final_price));
        float float_percentage=Integer.parseInt(model_sefaresh_jadid.getSahme_motakhases());
        float float_my_shared_amount=(float_total_price*float_percentage)/100;
        txtv_myshred_amount.setText(String.valueOf(float_my_shared_amount));
          model_sefaresh_jadid.setMotakhases_percentage(String.valueOf(float_percentage));
          model_sefaresh_jadid.setMotakhases_amount(String.valueOf(float_my_shared_amount));
        model_sefaresh_jadid.setKhialerahat_percentage(String.valueOf(100-float_percentage));
        float khialerahat_shared=(float_final_price-float_my_shared_amount);
        model_sefaresh_jadid.setKhialerahat_amount(String.valueOf(khialerahat_shared));
        Log.i("float_total_price:  ",""+float_total_price);
        Log.i("float_discount:  ",""+float_discount);
        Log.i("float_final_price:  ",""+float_final_price);
        Log.i("float_percentage:  ",""+float_percentage);
        Log.i("float_my_shared_:  ",""+float_my_shared_amount);
        Log.i("float_Khialerahatp:  ",""+model_sefaresh_jadid.getKhialerahat_percentage());
        Log.i("float_Khialerahatam:  ",""+model_sefaresh_jadid.getKhialerahat_amount());

    }

    public void not_found_new_order()
    {

        nestedScrollView.setVisibility(View.INVISIBLE);
        txt_sefarsh_jadid_vjod_nadarad.setVisibility(View.VISIBLE);
        txt_sefarsh_jadid_vjod_nadarad.setText("سفارش جدید وجود ندارد");
        txt_sefarsh_jadid_vjod_nadarad.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious));
    }

    public void not_found_new_order_liftime_end()
    {

        nestedScrollView.setVisibility(View.INVISIBLE);
        txt_sefarsh_jadid_vjod_nadarad.setVisibility(View.VISIBLE);
        txt_sefarsh_jadid_vjod_nadarad.setText("مهلت زمانی پذیرش تمام شده است");
        txt_sefarsh_jadid_vjod_nadarad.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious_ghermez));
    }
}
