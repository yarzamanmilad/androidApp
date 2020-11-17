package com.example.khialerahat.experts_khialerahat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khialerahat.experts_khialerahat.A_Tamasbama;
import com.example.khialerahat.experts_khialerahat.MainActivityy;
import com.example.khialerahat.experts_khialerahat.MainPackage.Model_Profile;
import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Const_Variable;
import com.example.khialerahat.experts_khialerahat.R;
import com.example.khialerahat.experts_khialerahat.Web_Service.Valley_Api;
import com.example.khialerahat.experts_khialerahat.app.AppController;
import com.github.ybq.android.spinkit.SpinKitView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile_Fragment extends Fragment implements  View.OnClickListener{
    private ImageView imgv_setting;
    private CircleImageView avatar;
    public static final int a=1;
    Button btn_tasvieh_hsab;
    boolean status_transaction_request=true;
    private CardView list_sefareshha,gozareshat,list_alaghemandiha,tamasbama;
    private TextView
             txt_name,txt_takhsos
            ,txt_shomareh_hamrah,
            txt_eetbar;
     View view;
     ConstraintLayout constraintLayout;
    private Intent intent;
    Valley_Api valley_api;
    private AlertDialog alert_dialog;
    SpinKitView spinKitView;
    private int repeat_try_to_getdata_profile=0;
    private int repeat_try_to_request_transaction=0;
    private int repeat_try_to_request_send_notification=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.activity_moshakhasat_profile, container, false);
        initialize_my_view(view);
        ((MainActivityy)getContext()).spinKitView.setVisibility(View.INVISIBLE);
        valley_api=new Valley_Api(getContext());
        valley_api.getMotakhasesdataProfile(MotakhasesObject.id,MotakhasesObject.token);
        spinKitView.setVisibility(View.VISIBLE);
       return view;
}

    private void initialize_my_view(View view)
    {
        spinKitView=view.findViewById(R.id.progress_in_lay_profile);
        constraintLayout=view.findViewById(R.id.conslay_profile);
        imgv_setting=view.findViewById(R.id.imgv_setting_profile);
        avatar=view.findViewById(R.id.avatar_profile);
        txt_name=view.findViewById(R.id.txt_name_profile);
        txt_takhsos=view.findViewById(R.id.txt_takhasos_profile);
        txt_shomareh_hamrah=view.findViewById(R.id.txt_shomare_hamrah_profile);
        txt_eetbar=view.findViewById(R.id.txt_eetbar_profile);
        tamasbama=view.findViewById(R.id.crdv_tamas_bama_profile);
        btn_tasvieh_hsab=view.findViewById(R.id.btn_tasvieh_hesab);
        btn_tasvieh_hsab.setOnClickListener(this);
         constraintLayout.setVisibility(View.INVISIBLE);

        ////////////////////////////////


        imgv_setting.setOnClickListener(this);

        tamasbama.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.crdv_tamas_bama_profile:
                intent=new Intent(getContext(), A_Tamasbama.class);
                startActivity(intent);

                break;
            case  R.id.btn_tasvieh_hesab:

                    if (Integer.parseInt(MotakhasesObject.kifePol) > Const_Variable.MAXIMUM_WALLET_AMOUNT_TO_TASVIEH_HESAB) {
                        request_transaction(MotakhasesObject.id,
                                MotakhasesObject.token,txt_name.getText().toString());
                    } else {
                        alert_dialog_method("error", "عملیات ناموفق","موجودی شما کمتر از"+
                                Const_Variable.MAXIMUM_WALLET_AMOUNT_TO_TASVIEH_HESAB+"می باشد");

                    }

                break;


            default:
        }
    }

    private void alert_dialog_method(String error, String title, String message)
    {
        switch (error)
        {
            case "error":


                Log.i("transaction_request",message);
                LayoutInflater inflater = getLayoutInflater();
                View dialoglayout = inflater.inflate(R.layout.alert_dialog, null);
                TextView dialog_title=dialoglayout.findViewById(R.id.codemeli_dialog_txt_ersalmojadad);
                dialog_title.setText(title);
                TextView dialog_body=dialoglayout.findViewById(R.id.codemeli_dialog_txt_virayesh_shomareh);
                dialog_body.setText(message);
                Button button=dialoglayout.findViewById(R.id.codemeli_dialog_btn_taaid);

                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                alert_dialog=builder.create();
                alert_dialog.setView(dialoglayout);
                alert_dialog.show();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert_dialog.dismiss();

                    }
                });

                break;
            case "ok":
                   status_transaction_request=false;

                LayoutInflater inflater1 = getLayoutInflater();
                View dialoglayout1 = inflater1.inflate(R.layout.alert_dialog_ok, null);
                TextView dialog_title1=dialoglayout1.findViewById(R.id.ok_dialog_txt_ersalmojadad);
                dialog_title1.setText(title);
                TextView dialog_body1=dialoglayout1.findViewById(R.id.ok_dialog_txt_virayesh_shomareh);
                dialog_body1.setText(message);
                Button button1=dialoglayout1.findViewById(R.id.ok_dialog_btn_taaid);

                final AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                alert_dialog=builder1.create();
                alert_dialog.setView(dialoglayout1);
                alert_dialog.show();
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert_dialog.dismiss();

                    }
                });

                break;
            default:
        }

    }


    public void request_transaction(final String id, final String token, final String name)
    {

        spinKitView.setVisibility(View.VISIBLE);

        String url="http://khialrahat.com/app_files/Motakhases_Api/transactionRequest.php";

        StringRequest stringRequest =new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("transactionReq ",response.toString());
                        spinKitView.setVisibility(View.INVISIBLE);

                        if(response!=null)
                        {
                            if(response.length()>0)
                            {
                                try {


                                    JSONArray jsonArray=new JSONArray(response);

                                    JSONObject jsonObject=jsonArray.getJSONObject(0);


                                    String error=(jsonObject.getString("error"));
                                    String message=jsonObject.getString("message");
                                    String title=jsonObject.getString("title");
                                    if(error.contains("true"))
                                    {
                                        alert_dialog_method("error",title,message);
                                    }
                                    else
                                    {
                                       // request_send_notification_to_managment(MotakhasesObject.name,MotakhasesObject.family);
                                        alert_dialog_method("ok",title,message);

                                    }



                                    //                                    for (int j=0;j<model_list_sefaresh_anjam_shode.size();j++)
//                                    {
//                                        Log.i("sef_laghv",model_list_sefaresh_anjam_shode.get(j).getZaman_anjamsefaresh());
//                                    }
                                }catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }

                            }
                            else
                                {
                                Log.i("volley_sef_laghv","response have not nodat");
                            }
                        }
                        else
                        {Log.i("transaction_request","response is null");}

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // interface_list_sefarshha.error_sefarshha(error.getMessage());
                spinKitView.setVisibility(View.INVISIBLE);
                if (repeat_try_to_request_transaction<Const_Variable.MAX_LENGTH_REPEAT_TRAY_TO_GET_DATA)
                {
                    repeat_try_to_request_transaction++;
                    request_transaction(MotakhasesObject.id, MotakhasesObject.token,MotakhasesObject.name+""+MotakhasesObject.family);
                    spinKitView.setVisibility(View.VISIBLE);
                }
                else
                {
                    alert_dialog_method("error","درخواست ناموفق","در خواست شما ثبت نشد");
                }
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("id_motakhases",id);
                parms.put("expert_name_family",name);
                parms.put("token",token);
                return parms;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

    }

    public void request_send_notification_to_managment(final String expert_name, final String expert_family)
    {
        Log.i("request_send_notific ","start");
        spinKitView.setVisibility(View.VISIBLE);

        String url="http://khialrahat.com/pushe/send_not_to_managment2.php";

        StringRequest stringRequest =new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("request_send_notific ",response.toString());
                        spinKitView.setVisibility(View.INVISIBLE);

                        if(response!=null)
                        {
                            if(response.length()>0)
                            {
//                                try {
//
//
//                                    JSONArray jsonArray=new JSONArray(response);
//
//                                    JSONObject jsonObject=jsonArray.getJSONObject(0);
//
//
//                                    String error=(jsonObject.getString("error"));
//                                    String message=jsonObject.getString("message");
//                                    String title=jsonObject.getString("title");
//                                    if(error.contains("true"))
//                                    {
//                                        alert_dialog_method("error",title,message);
//                                    }
//                                    else
//                                    {
//                                        alert_dialog_method("ok",title,message);
//                                    }
//
//
//
//                                    //                                    for (int j=0;j<model_list_sefaresh_anjam_shode.size();j++)
////                                    {
////                                        Log.i("sef_laghv",model_list_sefaresh_anjam_shode.get(j).getZaman_anjamsefaresh());
////                                    }
//                                }catch (JSONException e)
//                                {
//                                    e.printStackTrace();
//                                }

                            }
                            else
                            {
                                Log.i("request_send_notific","response have not nodat");
                            }
                        }
                        else
                        {Log.i("request_send_notific","response is null");}

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // interface_list_sefarshha.error_sefarshha(error.getMessage());
                spinKitView.setVisibility(View.INVISIBLE);
                if (repeat_try_to_request_send_notification<Const_Variable.MAX_LENGTH_REPEAT_TRAY_TO_GET_DATA)
                {
                    repeat_try_to_request_send_notification++;
                    request_send_notification_to_managment(MotakhasesObject.id, MotakhasesObject.token);
                    spinKitView.setVisibility(View.VISIBLE);
                }
                else
                {
                   // alert_dialog_method("error","درخواست ناموفق","در خواست شما ثبت نشد");
                    Log.i("request_send_notific","error response");
                }
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("name",expert_name);
                parms.put("family",expert_family);
                return parms;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

    }
    public void get_profile_data(Model_Profile model_profile)
    {
        Picasso.with(getContext()).load(model_profile.getImgLinkAddress()).into(avatar);
        txt_name.setText(model_profile.getName()+" "+model_profile.getFamily());
        txt_shomareh_hamrah.setText(model_profile.getMobileNumber());
        txt_takhsos.setText(model_profile.getTakhasos());
        txt_eetbar.setText(model_profile.getWallet());
        constraintLayout.setVisibility(View.VISIBLE);
        spinKitView.setVisibility(View.INVISIBLE);
        MotakhasesObject.name=model_profile.getName();
        MotakhasesObject.family=model_profile.getFamily();

    }
    public void failure_in_get_profile_data()
    {
        spinKitView.setVisibility(View.INVISIBLE);
        if(repeat_try_to_getdata_profile<Const_Variable.MAX_LENGTH_REPEAT_TRAY_TO_GET_DATA)
        {

            valley_api.getMotakhasesdataProfile(MotakhasesObject.id,MotakhasesObject.token);
            spinKitView.setVisibility(View.VISIBLE);
            repeat_try_to_getdata_profile++;
        }
        else
        {
            alert_dialog_method("error","خطا در اتصال","خطا در اتصال به سرور");
        }

    }

}
