package com.example.khialerahat.experts_khialerahat.List_Sefareshha;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Const_Variable;
import com.example.khialerahat.experts_khialerahat.Progress_And_Dialog;
import com.example.khialerahat.experts_khialerahat.R;
import com.example.khialerahat.experts_khialerahat.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class A_NewOrder_Details extends AppCompatActivity {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a__new_order__details);
        order_id_final=getIntent().getStringExtra("order_id");
                initials_view();


        progress_and_dialog=new Progress_And_Dialog(this,alertDialog);
        nestedScrollView.setVisibility(View.INVISIBLE);
        Log.i("list_sefaresh","list_sefaresh_jadid_shode_oncreat"+order_id_final);
          get_new_order_details(order_id_final);

        btn_accept_onclick();
        btn_not_accept_onclick();

    }

    private void btn_not_accept_onclick()
    {
        btn_not_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progress_and_dialog.new_order_not_accept_alertdialog("true1","لغو سفارش","سفارش شما لغو شد");
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
                update_order_status_in_server(order_id_final, MotakhasesObject.id);

//                Log.i("btn_accept",""+
//                        ( Calendar.getInstance().getTimeInMillis()- SmsReciver.current_time_sms_recive)+"::"
//                        +Const_Variable.MAX_MILISECND_WAIT_TO_ACCEPT_NEW_ORDERS+"::"
//                        +Const_Variable.MILSEC_NEED_TO_SEND_AND_RECIVE_DATA);
//                if(
//                        Calendar.getInstance().getTimeInMillis()-SmsReciver.current_time_sms_recive
//                                <=Const_Variable.MAX_MILISECND_WAIT_TO_ACCEPT_NEW_ORDERS
//                                -Const_Variable.MILSEC_NEED_TO_SEND_AND_RECIVE_DATA)
//                {
//
//                    update_order_status_in_server(order_id_final, MotakhasesObject.id);
//
//
//                }
//                else
//                {not_found_new_order_liftime_end();
//                    MotakhasesObject.new_order_id=null;
//                }

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
                                       // not_found_new_order_liftime_end();
                                        progress_and_dialog.new_order_not_accept_alertdialog("true","عدم پذیرش","سفارش توسط شخص دیکری پذیرش شده است");
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


    private void initials_view()
    {

       txt_sefarsh_jadid_vjod_nadarad=findViewById(R.id.txt_a_neworder_details);
        constraintLayout=findViewById(R.id.mainconslay_sefarshjadidd);
        btn_accept=findViewById(R.id.btn_accept_new_orderd);
        btn_not_accept=findViewById(R.id.btn_not_accept_new_orderd);
        txtv_address_name=findViewById(R.id.txt_address_sefaresh_jadidd);
        txtv_zaman=findViewById(R.id.txt_zamane_sefaresh_jadidd);
        txtv_services=findViewById(R.id.txt_khadamat_darkhasti_jadidhd);
        txtv_total_price=findViewById(R.id.txt_hazineh_khadamat_jadidd);
        txtv_discont=findViewById(R.id.txt_takhfif_jadidd);
        txtv_final_price=findViewById(R.id.txt_mizan_daryaftid);
        txtv_myshred_amount=findViewById(R.id.txt_sahme_motakhases_sefjadidd);
        txtv_payment_status=findViewById(R.id.txt_vazeiat_pardakht_sefjadidd);
        txtv_payment_type=findViewById(R.id.txt_noeh_pardakht_hazine_sefjadidd);
        txtv_wallet_status=findViewById(R.id.txt_wallet_status_sefjadidd);
        nestedScrollView=findViewById(R.id.neasted_sefarshjadidd);
    }

    public void sefarsh_jadid(Model_Sefaresh_Jadid model_sefaresh_jadid)
    {
        this.model_sefaresh_jadid=new Model_Sefaresh_Jadid();
        this.model_sefaresh_jadid=model_sefaresh_jadid;
        nestedScrollView.setVisibility(View.VISIBLE);
//        txt_sefarsh_jadid_vjod_nadarad.setVisibility(View.INVISIBLE);
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

    public void get_new_order_details(final String order_id)
    {
        Log.i("get_new_order_details","start"+order_id);
        String url="http://khialrahat.com/app_files/Motakhases_Api/get_order_experts.php";

        StringRequest stringRequest =new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("get_new_order_details",response.toString());
                        if(response!=null)
                        {
                            if(response.length()>0)
                            {
                                try {

                                    // JSONArray jsonArray=new JSONArray(response);
                                    JSONObject jsonObject=new JSONObject(response);

                                    Model_Sefaresh_Jadid model_sefarshe_jadid2
                                            =new Model_Sefaresh_Jadid();
                                    // JSONObject jsonObject=jsonArray.getJSONObject(i);
                                    Log.i("new_order_service",jsonObject.getString("service"));
                                    Log.i("new_order_service",jsonObject.getString("mobile"));
                                    Log.i("new_order_service",jsonObject.getString("time_in"));
                                    Log.i("new_order_service","tow_step_finish"+jsonObject.getString("tow_step_finish"));
                                    Log.i("new_order_service","group_order"+jsonObject.getString("group_order"));
                                    Log.i("new_order_service","security_active"+jsonObject.getString("security_active"));
                                    model_sefarshe_jadid2.setTime_in(jsonObject.getString("time_in"));
                                    model_sefarshe_jadid2.setNoeh_khadamat_jozei(jsonObject.getString("service"));
                                    model_sefarshe_jadid2.setShomareh_hamrah_moshtari(jsonObject.getString("mobile"));
                                    model_sefarshe_jadid2.setAddress(jsonObject.getString("address"));
                                    model_sefarshe_jadid2.setZaman_darkhasti_khadamat(jsonObject.getString("time_order"));
                                    model_sefarshe_jadid2.setSahme_motakhases(jsonObject.getString("percentage"));
                                    //model_sefarshe_jadid2.setSahme_motakhases("50");
                                    model_sefarshe_jadid2.setVazeiat_pardakht(jsonObject.getString("payment"));
                                    model_sefarshe_jadid2.setNahve_pardakht(jsonObject.getString("payment_type"));
                                    model_sefarshe_jadid2.setTakhfif(jsonObject.getString("discount"));
                                    model_sefarshe_jadid2.setHazineh(jsonObject.getString("total_price"));
                                    model_sefarshe_jadid2.setService_type(jsonObject.getString("group_order"));
                                    model_sefarshe_jadid2.setSecurity_panel(jsonObject.getString("security_active"));
                                    model_sefarshe_jadid2.setTow_step_finish(jsonObject.getString("tow_step_finish"));

                                    //list_model_sefarshe_jadid.add(model_list_sefaresh_anjam_shode);
                                    //        Log.i("list_sef_laghv_shode",model_list_sefaresh_anjam_shode.get(i).getZaman_anjamsefaresh());

                                    sefarsh_jadid(model_sefarshe_jadid2);
                                    //  FListSefareshJadid.sefarsh_jadid(model_sefarshe_jadid2);
//                                    for (int j=0;j<model_list_sefaresh_anjam_shode.size();j++)
//                                    {
//                                        Log.i("sef_laghv",model_list_sefaresh_anjam_shode.get(j).getZaman_anjamsefaresh());
//                                    }
                                }catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }

                            }
                            else {
                                Log.i("get_new_order_details","response have not nodat");
                            }
                        }
                        else
                        {Log.i("get_new_order_details","response is null");}

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // interface_list_sefarshha.error_sefarshha(error.getMessage());
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("orders_id",order_id);

                return parms;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
