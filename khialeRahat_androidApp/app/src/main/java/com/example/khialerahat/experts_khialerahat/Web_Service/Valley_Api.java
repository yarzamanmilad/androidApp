package com.example.khialerahat.experts_khialerahat.Web_Service;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.khialerahat.experts_khialerahat.List_Sefareshha.A_Jozeiat_Sefarsh_Darhal_Anjam;
import com.example.khialerahat.experts_khialerahat.List_Sefareshha.Interface_List_Sefarshha;
import com.example.khialerahat.experts_khialerahat.List_Sefareshha.Model_Jozeiat_Sefarsh_Anjamshode;
import com.example.khialerahat.experts_khialerahat.List_Sefareshha.Model_Sefaresh_DarhaleAnjam;
import com.example.khialerahat.experts_khialerahat.List_Sefareshha.Model_Sefaresh_Jadid;
import com.example.khialerahat.experts_khialerahat.MainActivityy;
import com.example.khialerahat.experts_khialerahat.MainPackage.Model_Message;
import com.example.khialerahat.experts_khialerahat.MainPackage.Model_Profile;
import com.example.khialerahat.experts_khialerahat.MainPackage.Model_Transaction;
import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Const_Variable;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Model_Takhasos;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Model_Takhasos_level2;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Model_Time;
import com.example.khialerahat.experts_khialerahat.My_interface;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Interface_checkKardanCodemeli_ShomareHamrah;
import com.example.khialerahat.experts_khialerahat.SplashActivity;
import com.example.khialerahat.experts_khialerahat.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Valley_Api
{

    MotakhasesObject motakhasesObject;
    My_interface my_interface;
    Interface_checkKardanCodemeli_ShomareHamrah interface_checkKardanCodemeliSms;
   Interface_List_Sefarshha interface_list_sefarshha;

    Context context;
     String[] stringarray_takhasos ;
     List<String[]>  list_takhasos=new ArrayList<>();
    private Model_Takhasos[] model_takhasos;
    private Model_Takhasos_level2[] model_takhasos_level2;
   private Model_Time[] model_time;
    private List<String> list_name_ostanha;
    private List<String> list_name_shahrestanha;
    private List<String> list_name_manategh;
    String begin_time;
    String end_time;
    String workTimes;
    private List<String> list_id_shahrestanha;

    public Valley_Api(Context context) {
        this.context = context;


           this.begin_time="notime";
           this.end_time="notime";
    }



    public void get_JozeiatSefarshAnjamShode(final String order_id,
                                          final String token)
    {
        interface_list_sefarshha=(Interface_List_Sefarshha)context;

        Log.i("volley_sef_anjamshode","sef");
        final Model_Jozeiat_Sefarsh_Anjamshode model_jozeiat_sefarsh_anjamshode
                =new Model_Jozeiat_Sefarsh_Anjamshode();




        String url="http://khialrahat.com/app_files/Motakhases_Api/get_order_done_details.php";

        StringRequest stringRequest =new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("volley_ordrdone_details",response.toString()+"get_order_done_details_");
                        if(response!=null)
                        {
                            if(response.length()>0)
                            {
                                try {


                                    JSONArray jsonArray=new JSONArray(response);

                                     JSONObject jsonObject=jsonArray.getJSONObject(0);
                                     MotakhasesObject.token=jsonObject.getString("token");
                                     model_jozeiat_sefarsh_anjamshode.setOrder_time(jsonObject.getString("order_time"));
                                    model_jozeiat_sefarsh_anjamshode.setOrder_address(jsonObject.getString("order_address"));
                                    model_jozeiat_sefarsh_anjamshode.setOrder_services(jsonObject.getString("order_services"));
                                    model_jozeiat_sefarsh_anjamshode.setOrder_price(jsonObject.getString("order_price"));

                                    model_jozeiat_sefarsh_anjamshode.setOrder_discount_percent(jsonObject.getString("discount_percent"));
                                    model_jozeiat_sefarsh_anjamshode.setFinal_price(jsonObject.getString("final_price"));
                                    model_jozeiat_sefarsh_anjamshode.setMy_share(jsonObject.getString("my_share"));
                                    model_jozeiat_sefarsh_anjamshode.setPayment_methode(jsonObject.getString("payment_methode"));
                                    model_jozeiat_sefarsh_anjamshode.setWallet_status(jsonObject.getString("wallet_status"));
                                    interface_list_sefarshha.get_jozeiat_sefarsh_anjamshode(model_jozeiat_sefarsh_anjamshode);
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
                                Log.i("volley_sef_laghv","response have not nodat");
                            }
                        }
                        else
                        {Log.i("volley_sef_laghv","response is null");}

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
                parms.put("order_id",order_id);
                parms.put("token",token);
                return parms;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

    }
    public void register_status(final String register_status, final String id_motakhases,final String name)
    {

        motakhasesObject=new MotakhasesObject();
        String url="http://khialrahat.com/app_files/Motakhases_Api/register_status.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("volly_register_status",response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                my_interface.recive_data_from_server_failur("error","وضعیت ثبت نام" , " ثبت اطلاعات با خطا مواجه شد");
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("register_status",register_status);
                parms.put("id_motakhases",id_motakhases);
                parms.put("name",name);

                return parms;
            }
        };
        AppController.getInstance().addToRequestQueue(request);
    }
    public void get_list_takhasos()
    {


            String url = "https://khialrahat.com/app_files/List_takhasosha/get_list_takhasosha.php";
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response != null) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                             model_takhasos=new Model_Takhasos[jsonArray.length()];
                            for (int i = 0; i < jsonArray.length(); i++) {
                                model_takhasos[i]=new Model_Takhasos();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                stringarray_takhasos=new String[jsonObject.toString().length()];
                                Log.i("list_takhasos", jsonObject.toString()+"\n"+jsonObject.getString("name"));
                                model_takhasos[i].setId_takhasos(jsonObject.get("ID").toString());
                                model_takhasos[i].setName_takhasos(jsonObject.getString("name"));
                                model_takhasos[i].setSub_categuri_status(jsonObject.getString("sub_category_status"));
                                model_takhasos[i].setUrl_aks_takhasos(jsonObject.getString("photo_link"));
                               // model_takhasos[i].setUrl_aks_takhasos(jsonObject.getString("photo_link"));
                                                          }

                            my_interface.greftan_list_takhasos(model_takhasos);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.i("vally_get_list_takhasos", "no data");
                        model_takhasos=new Model_Takhasos[1];
                        model_takhasos[0]=new Model_Takhasos();
                        model_takhasos[0].setName_takhasos(null);
                        model_takhasos[0].setId_takhasos(null);

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();

                    my_interface.recive_data_from_server_failur("error","لیست تخصص" ,"خطا در دریافت لیست تخصص" );
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parms = new HashMap<>();
                    return parms;
                }
            };
        //10000 is the time in milliseconds adn is equal to 10 sec
        request.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            AppController.getInstance().addToRequestQueue(request);



//                 Log.i("my_interface",model_takhasos[0].getId_takhasos()+"  "+model_takhasos[0].getName_takhasos());
    }

    public void get_list_takhasos_level2(final String name_takhasos)
    {

         my_interface=(My_interface) context;
        String url = "https://khialrahat.com/app_files/List_takhasosha/get_list_takhasos_level2.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        Log.i("gsonarray_size",""+jsonArray.length());
                        model_takhasos_level2=new Model_Takhasos_level2[jsonArray.length()];
                        for (int i = 0; i < jsonArray.length(); i++) {
                            model_takhasos_level2[i]=new Model_Takhasos_level2();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            Log.i("list_takhasos", jsonObject.toString()+"\n"+jsonObject.getString("name"));
                            model_takhasos_level2[i].setId_takhasos(jsonObject.get("ID").toString());
                            model_takhasos_level2[i].setName_takhasos(jsonObject.getString("name"));
                            model_takhasos_level2[i].setId_takhasos_level1(jsonObject.getString("service_id"));
                           // model_takhasos_level2[i].setUrl_aks_takhasos(jsonObject.getString("photo_link"));
                        }

                        my_interface.greftan_list_takhasos_level2(model_takhasos_level2);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i("vally_get_manategh", "no data");
                    model_takhasos_level2=new Model_Takhasos_level2[1];
                    model_takhasos_level2[0]=new Model_Takhasos_level2();
                    model_takhasos_level2[0].setName_takhasos(null);
                    model_takhasos_level2[0].setId_takhasos(null);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                my_interface.recive_data_from_server_failur("error","لیست تخصص.." ,"خطا در دریافت لیست تخصص" );
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parms = new HashMap<>();
                parms.put("takhasos_name",name_takhasos);
                return parms;
            }
        };

        //10000 is the time in milliseconds adn is equal to 10 sec
        request.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(request);
                //   Log.i("my_interface",model_takhasos_level2[0].getId_takhasos()+"  "+model_takhasos[0].getName_takhasos());
    }


//    public void get_list_time()
//    {
//
//
//        String url = "https://khialrahat.com/app_files/Motakhases_Api/get_list_time_from_Time.php";
//        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if (response != null) {
//                    try {
//                        JSONArray jsonArray = new JSONArray(response);
//                        Log.i("gsonarray_size",""+jsonArray.length());
//                        model_time=new Model_Time[jsonArray.length()];
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            model_time[i]=new Model_Time();
//                            JSONObject jsonObject = jsonArray.getJSONObject(i);
//                            stringarray_takhasos=new String[jsonObject.toString().length()];
//                            Log.i("list_time", jsonObject.toString()+"\n"+jsonObject.getString("begin_time"));
//                            model_time[i].setTime_id(jsonObject.get("id").toString());
//                            model_time[i].setBegin_time(jsonObject.getString("begin_time"));
//                            model_time[i].setEnd_time(jsonObject.getString("end_time"));
//                        }
//
//                        my_interface.greftan_list_times(model_time);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    Log.i("vally_get_manategh", "no data");
//                    model_time=new Model_Time[1];
//                    model_time[0]=new Model_Time();
//                    model_time[0].setBegin_time(null);
//                    model_time[0].setTime_id(null);
//
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                my_interface.recive_data_from_server_failur(error.getMessage(), , );
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> parms = new HashMap<>();
//                return parms;
//            }
//        };
//        AppController.getInstance().addToRequestQueue(request);
//
//
//
////                 Log.i("my_interface",model_takhasos[0].getId_takhasos()+"  "+model_takhasos[0].getName_takhasos());
//    }


    public void get_listostanha_asserver()
    {   list_name_ostanha=new ArrayList<>();
        list_name_ostanha.clear();
        my_interface=(My_interface)context;
        String url="http://khialrahat.com/app_files/city/get_ostan.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {


                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            list_name_ostanha.add(jsonObject.getString("name"));
                            Log.i("vally_get_ostanha", list_name_ostanha.get(i));

                        }
                      my_interface.greftan_list_ostanha(list_name_ostanha);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i("vally_get_ostanha", "no data");
                    list_name_ostanha.add("nodata");
                    my_interface.greftan_list_ostanha(list_name_ostanha);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    my_interface.recive_data_from_server_failur("error", Const_Variable.GET_LIST_OSTANHA_ERROR
                            ,Const_Variable.SAFHE_VROD_SABTENAM_ERROR_MESSAGE);
                    error.printStackTrace();
            }
        });

        //10000 is the time in milliseconds adn is equal to 10 sec
        request.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(request);
    }


    public void sms_security(final String shomareHamrah)
                {
                    interface_checkKardanCodemeliSms=(Interface_checkKardanCodemeli_ShomareHamrah) context;
                    Log.i("vally_smsSecurity: ","start");
        String url="http://khialrahat.com/app_files/sms.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response != null) {



                            Log.i("vally_smsSecurity: ",response.toString());

                        interface_checkKardanCodemeliSms.checkKardan_shomarehHamrah_valleyapi(response.toString());

                } else {
                    Log.i("vally_smsSecyrity", "no data");

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                       my_interface.recive_data_from_server_failur("error","کد تایید" ,"خطا در دریافت کد تایید" );
                       Log.i("vally_smsSecurity","error");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("mobile",shomareHamrah);
                return parms;
            }
        };

                    //10000 is the time in milliseconds adn is equal to 10 sec
                    request.setRetryPolicy(new DefaultRetryPolicy(
                            100000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                    AppController.getInstance().addToRequestQueue(request);
    }


    public void check_kardan_codemeli(final String codemeli)
    {
        this. interface_checkKardanCodemeliSms=(Interface_checkKardanCodemeli_ShomareHamrah)context;
        String url="http://khialrahat.com/app_files/Motakhases_Api/checkKardaneCodemeli.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response != null) {


                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        Log.i("vally_code_meli: ", jsonArray.length()+"");
                        if(jsonArray.length()>0)
                        {

                            interface_checkKardanCodemeliSms.checkKardanCodemeli(false);
                            Log.i("vally_code_meli: ","کد ملی قبلا ثبت شده است");
                            Log.i("vally_code_meli: ",response.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                Log.i("vally_get_manategh",jsonObject.getString("id") );

                            }
                        }else{
                            Log.i("vally_code_meli: ","کد ملی قبلا ثبت نشده است");
                            Log.i("vally_code_meli: ",response.toString());
                            interface_checkKardanCodemeliSms.checkKardanCodemeli(true);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i("vally_vazeiat_code_meli", "no data");


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                my_interface.recive_data_from_server_failur("error", "کد ملی","خطا در چک کردن کد ملی" );
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("codemeli",codemeli);
                return parms;
            }
        };

        //10000 is the time in milliseconds adn is equal to 10 sec
        request.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(request);
    }


    public void username_athuntication(final String codemeli)
    {
           my_interface=(My_interface)context;
        String url="http://khialrahat.com/app_files/Motakhases_Api/checkKardaneCodemeli.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response != null) {


                    try {
                        JSONArray jsonArray = new JSONArray(response);


                        if(jsonArray.length()>0)
                        {


                            my_interface.username_athuntication(Const_Variable.IS_OK);
                            Log.i("vally_athu_usernam: ","کد ملی قبلا ثبت شده است");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);



                            }
                        }else{
                            Log.i("vally_athu_usernam: ","کد ملی قبلا ثبت نشده است");

                            my_interface.username_athuntication(Const_Variable.IS_NOT_OK);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i("vally_athu_usernam", "no data");


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                my_interface.recive_data_from_server_failur("error",
                        Const_Variable.USERNAME_ATHUNTICATION_EEROR,Const_Variable.SAFHE_VROD_SABTENAM_ERROR_MESSAGE);
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("codemeli",codemeli);
                return parms;
            }
        };

        //10000 is the time in milliseconds adn is equal to 10 sec
        request.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(request);
    }


    public void password_athuntication(final String username, final String password)
    {
        my_interface=(My_interface)context;
       motakhasesObject=new MotakhasesObject();
        String url="http://khialrahat.com/app_files/Motakhases_Api/userAthuntication.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response != null) {

                    Log.i("vally_athu_userpass", response);
                    try {
                        JSONArray jsonArray = new JSONArray(response);


                        if(jsonArray.length()>0)
                        {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                motakhasesObject.setId(jsonObject.getString("id"));
                                motakhasesObject.setAddress_aks_profaile(jsonObject.getString("aksprofile"));
                                motakhasesObject.setKifePol(jsonObject.getString("eetbar"));
                                motakhasesObject.setToken(jsonObject.getString("token"));
                                motakhasesObject.setJensyat(jsonObject.getString("jensyat"));
                                MotakhasesObject.pushid=jsonObject.getString("pushe_id");
                                motakhasesObject.setToken(jsonObject.getString("vazeiat_feli_faliat"));
                                motakhasesObject.setVazeiat_sabtnam(jsonObject.getString("vazeiat_sabtenam"));
                                Log.i("vally_athu_userpass", "pushid= "+jsonObject.getString("pushe_id"));
                                Log.i("vally_athu_userpass", "id= "+jsonObject.getString("id"));
                                Log.i("vally_athu_userpass", "aksprofile= "+jsonObject.getString("aksprofile"));
                                Log.i("vally_athu_userpass", "eetbar= "+jsonObject.getString("eetbar"));

                               Log.i("vally_athu_userpass", "token= "+jsonObject.getString("token"));
                               Log.i("vally_athu_userpass", "jensyat= "+jsonObject.getString("jensyat"));
                                Log.i("vally_athu_userpass", "vazeiat_sabtenam= "+jsonObject.getString("vazeiat_sabtenam"));
                                Log.i("vally_athu_userpass", "vazeiat_feli_faliat= "+jsonObject.getString("vazeiat_feli_faliat"));

                            }
                            my_interface.password_athuntication(motakhasesObject);
                        }else{
                            Log.i("vally_athu_userpass", "no data json array<=0");
                            motakhasesObject.setId(null);
                            motakhasesObject.setVazeiat_sabtnam(Const_Variable.REGISTER_STATUS_IS_NOT_OK);
                            my_interface.password_athuntication(motakhasesObject);

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i("vally_athu_userpass", "no data");


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                my_interface.recive_data_from_server_failur("error",
                        Const_Variable.PASSWORD_ATHUNTICATION_EEROR,Const_Variable.SAFHE_VROD_SABTENAM_ERROR_MESSAGE);
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("username",username);
                parms.put("password",password);

                return parms;
            }
        };

        //10000 is the time in milliseconds adn is equal to 10 sec
        request.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(request);
    }

    public void upload_list_manategh_khedmatdahi(final String list_id_manategh_khedmatdahi, final String id_motakhases)
    {
        Log.i("upload_manategh: ",list_id_manategh_khedmatdahi);
        Log.i("upload_manategh: ",id_motakhases);
        String url="http://khialrahat.com/app_files/Motakhases_Api/upload_manategh_khedmatdahi.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response != null) {

                    Log.i("upload_manateg respo: ",response);


                            try {

                                JSONObject jsonObject=new JSONObject(new String(response));

                                    Log.i("upload_manategh", jsonObject.getString("error"));
                                Log.i("upload_manategh", jsonObject.getString("error"));

                           Log.i("upload_manategh_jsonobj",jsonObject.toString() );
                             String status_manategh=jsonObject.getString("error");
                                Log.i("str_status_mana", jsonObject.getString("error")+"jjjjjjjj");
                                if(status_manategh.contentEquals("false"))
                                {my_interface.upload_status(false, Const_Variable.UPLOAD_MANATEGH_KHEDMAT_DAHI);}
                                else
                                {my_interface.upload_status(true, Const_Variable.UPLOAD_MANATEGH_KHEDMAT_DAHI);}



                        } catch (JSONException e) {
                                e.printStackTrace();
                            }



                } else {
                    Log.i("upload_manategh", "no data");


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                my_interface.recive_data_from_server_failur("error","مناطق خدمت دهی" ,"خطا در آپلود مناطق خدمت دهی" );
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("ids",list_id_manategh_khedmatdahi);
                parms.put("id_motakhases",id_motakhases);
                return parms;
            }
        };

        //10000 is the time in milliseconds adn is equal to 10 sec
        request.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(request);
    }



    public void upload_list_takhasos(final String list_id_takhasos, final String id_motakhases)
    {
        Log.i("upload_takhasos",list_id_takhasos+id_motakhases);

        String url="http://khialrahat.com/app_files/Motakhases_Api/upload_takhasos.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("upload_takhas_res",response);



                if (response != null) {


                    try {
                         JSONObject jsonObject=new JSONObject(new String(response));
                        String str_status_takhasos2=jsonObject.getString("error");
                        Log.i("str_status_takhasos2",str_status_takhasos2+"nnnnnnnnnnnnnnnnnnnnnnnnnnnn");
                        if(str_status_takhasos2.contentEquals("false"))
                        {my_interface.upload_status(false, Const_Variable.UPLOAD_TAKHASOS);}
                        else
                        {my_interface.upload_status(true, Const_Variable.UPLOAD_TAKHASOS);}


                    }
                    catch (JSONException exseption)
                    {
                        Log.i("upload_takhasos:",exseption.getMessage());
                    }

                } else {
                    Log.i("upload_takhasos", "no data");


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                my_interface.recive_data_from_server_failur("error","تخصص" , "خطا در اپلود تخصص ها");
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("ids",list_id_takhasos);
                parms.put("id_motakhases",id_motakhases);
                return parms;
            }
        };

        //10000 is the time in milliseconds adn is equal to 10 sec
        request.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(request);
    }

    public void greftan_idmotekhases(final String codemeli)
    {
        final String[] id_motekhases = {"-1"};
        String url="http://khialrahat.com/app_files/Motakhases_Api/checkKardaneCodemeli.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response != null) {


                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        Log.i("vally_code_meli: ", jsonArray.length()+"");
                        if(jsonArray.length()>0)
                        {


                            Log.i("vally_code_meli: ","کد ملی قبلا ثبت شده است");
                            Log.i("vally_code_meli: ",response.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                Log.i("volly_greftan_id_motak",jsonObject.getString("id") );
                                id_motekhases[0] =jsonObject.getString("id");

                            }
                            my_interface.greftan_id_motakhases(id_motekhases[0]);
                        }else{
                            Log.i("vally_code_meli: ","کد ملی قبلا ثبت نشده است");
                            Log.i("vally_code_meli: ",response.toString());
                            id_motekhases[0]="-1";
                            my_interface.greftan_id_motakhases(id_motekhases[0]);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i("vally_vazeiat_code_meli", "no data");


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                my_interface.recive_data_from_server_failur("error","شناسه متخصص", "خطا در دریافت شناسه متخصص" );
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("codemeli",codemeli);
                return parms;
            }
        };

        //10000 is the time in milliseconds adn is equal to 10 sec
        request.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(request);
    }

    public void upload_saat_kari(final String date_name , final List<String> list_saat_kari, final String id)
     {
        final RequestQueue requestQueue=Volley.newRequestQueue(context);

        if(list_saat_kari.size()>0)
        {
                begin_time=list_saat_kari.get(0);
                end_time=list_saat_kari.get(list_saat_kari.size()-1);
            Log.i("uploadtime",".............after respuns...............");
                Log.i("uploadtime_id:",id);
            Log.i("uploadtime_day:",date_name);
            Log.i("uploadtime_begin:",begin_time);
            Log.i("uploadtime_end:",end_time);
            Log.i("........","............................");


        }else {

        }
        String url="http://khialrahat.com/app_files/Motakhases_Api/uploadWorkTime.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                 requestQueue.stop();
                if (response != null) {
                    try {

                        JSONObject jsonObject=new JSONObject(new String(response));
                        if(jsonObject.length()>0) {
                            Log.i("upload_jsontime",jsonObject.toString() );
                            if (jsonObject.getString("error").contentEquals("false"))
                            {
                                my_interface.upload_status(false, Const_Variable.UPLOAD_WORK_TIMES);
                            }
                            else{my_interface.upload_status(true,Const_Variable.UPLOAD_WORK_TIMES );}
                            Log.i("upload_time",jsonObject.getString("error"));
                        }
                        Log.i("upload_jsontime",jsonObject.getString("error"));
                    }
                    catch (JSONException exseption)
                    {
                        Log.i("upload_time:",exseption.getMessage());
                    }

                } else {
                    Log.i("uploadtime", "respunse=null");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                my_interface.recive_data_from_server_failur("error", "ساعات کاری","خطا در آپلود ساعات کاری" );
                error.printStackTrace();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();

                begin_time=list_saat_kari.get(0);
                end_time=list_saat_kari.get(list_saat_kari.size()-1);

                Log.i("uploadtime","........beforhash....................");
                Log.i("uploadtime_idhash:",id);
                Log.i("uploadtime_dayhash:",date_name);
                Log.i("uploadtime_begin:hash",begin_time);
                Log.i("uploadtime_end:hash",end_time);
                Log.i("uploadtime","............................");
                parms.put("day_id",date_name);
                parms.put("begin_time",begin_time);
                parms.put("end_time",end_time);
                parms.put("id_motakhases",id);

                Log.i("uploadtime","............afterhash................");
                Log.i("uploadtime_idhash:",id);
                Log.i("uploadtime_dayhash:",date_name);
                Log.i("uploadtime_begin:hash",begin_time);
                Log.i("uploadtime_end:hash",end_time);
                Log.i("uploadtime","............................");
                return parms;
            }
        };

         //10000 is the time in milliseconds adn is equal to 10 sec
         request.setRetryPolicy(new DefaultRetryPolicy(
                 100000,
                 DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                 DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

         requestQueue.add(request);
    }


    public void upload_saat_kari_use_tempstring(final String stringAllWorkTimee_name )
    {

        String url="http://khialrahat.com/app_files/Motakhases_Api/uploadWorkTime.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response != null) {
                    if(response.length()>0)
                    {
                        Log.i("uploadtime: ",response.toString());
                    }else{
                        Log.i("uploadtime: ","erro");
                        Log.i("uploadtime: ",response.toString());
                    }

                } else {
                    Log.i("uploadtime", "respunse=null");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                my_interface.recive_data_from_server_failur("error", "ساعات کاری","خطا در آپلود ساعات کاری" );
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("worktime",stringAllWorkTimee_name);
                return parms;
            }
        };
        AppController.getInstance().addToRequestQueue(request);
    }

    public void get_listmanategh_as_server(final String name_ostan, final String name_shahr)
    {   list_name_manategh=new ArrayList<>();
        list_name_manategh.clear();
        String url="https://khialrahat.com/app_files/city/get_manategh.php";
        Log.i("vally_get_manategh", "start");
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    Log.i("vally_get_manategh", response.toString());

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            list_name_manategh.add(jsonObject.getString("name"));
                            Log.i("vally_get_manategh", list_name_manategh.get(i));

                        }
                     my_interface.greftan_list_manategh(list_name_manategh);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i("vally_get_manategh", "no data");
                    list_name_manategh.add("nodata");
                    my_interface.greftan_list_manategh(list_name_manategh);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                my_interface.recive_data_from_server_failur("error","لیست مناطق","خطا در دریافت لیست مناطق" );
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("ostan",name_ostan);
                parms.put("shahr",name_shahr);
                return parms;
            }
        };

        //10000 is the time in milliseconds adn is equal to 10 sec
        request.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(request);
    }


    public void get_listshrha_as_server(final String name_ostan)
    {
        my_interface=(My_interface) context;
        list_name_shahrestanha=new ArrayList<>();
        list_id_shahrestanha=new ArrayList<>();
        list_name_shahrestanha.clear();
        list_id_shahrestanha.clear();
        Log.i("vally_get_shahrha_name","start");
        String url="http://khialrahat.com/app_files/city/get_shahr.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    Log.i("vally_get_shahrha_name",response.toString());

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            list_id_shahrestanha.add(jsonObject.getString("ID"));
                            Log.i("vally_get_shahrhaid: "+i, list_id_shahrestanha.get(i));

                            list_name_shahrestanha.add(jsonObject.getString("name"));
                            Log.i("vally_get_shahrha_name: "+i, list_name_shahrestanha.get(i));

                        }
                        my_interface.greftan_list_shahrha(list_name_shahrestanha,list_id_shahrestanha);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i("vally_get_shahrha", "no data");
                    list_name_shahrestanha.add("nodata");
                    my_interface.greftan_list_shahrha(list_name_shahrestanha,list_id_shahrestanha );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                my_interface.recive_data_from_server_failur("error","لیست شهرستانها" ,"خطا در دریافت لیست شهرستانها" );
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("ostan",name_ostan);
                return parms;
            }
        };

        //10000 is the time in milliseconds adn is equal to 10 sec
        request.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(request);
    }

    public byte[] getfiledatafromdrawable(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,Const_Variable.IMAGE_QUALITY,byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();

    }


    public void upload_image(final String name, final String family, final String codemeli,
                             final String shomare_hamrah, final String shomare_sabet,
                             final String vazeyat_tahol, final String jensiat, final String ostan,
                             final String shahrestan, final String address_daghigh,
                             final String ramzevrod, final Bitmap aks_kartmeli, final Bitmap aks_poshtekartmeli,
                             final Bitmap aks_shenasname, final Bitmap aks_payankhedmat, final Bitmap aks_gavahi_fani,
                             final Bitmap aks_sayermadarek, final String pushe_id, final String shomare_aberbank,
                             final String shomare_hesab, final String shomare_shaba) {




        String url="http://khialrahat.com/app_files/Motakhases_Api/uploadimage.php";

        VolleyMultipartRequest volleyMultipartRequest=new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {

                            JSONObject jsonObject=new JSONObject(new String(response.data));
                            if(jsonObject.length()>0) {
                                Log.i("upload_jsonobject",jsonObject.toString() );
                                if (jsonObject.getString("error").contentEquals("false"))
                                {
                                    my_interface.upload_status(false, Const_Variable.UPLOAD_MOSHAKHAST_FARDI);
                                }
                                else{my_interface.upload_status(true,Const_Variable.UPLOAD_MOSHAKHAST_FARDI );}
                                Log.i("upload_moshakhasatFrd",pushe_id);
                                Log.i("upload_moshakhasatFrd",jsonObject.getString("error"));
                            }
                            Log.i("upload_jsonobject",jsonObject.getString("error"));
                        }
                        catch (JSONException exseption)
                        {
                            Log.i("upload_img_jsonexption:",exseption.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

                my_interface.recive_data_from_server_failur("error", "مشخصات متخصص","خطا در آپلود مشخصات متخصص" );
            }
        }){

           // @Override
           // public Map<String, String> getHeaders() throws AuthFailureError {
              //  HashMap<String, String> headers = new HashMap<String, String>();
              //  headers.put("Content-Type", "application/json; charset=utf-8");
             //  return headers;
     //      }
          @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
              String name1="",family1="",vazeyat_tahol1=""
                      ,jensiat1="",ostan1="",shahrestan1="",address_daghigh1=""
                      ;

              try {
                  name1 = URLEncoder.encode(name,"UTF-8");
                  family1 = URLEncoder.encode(family,"UTF-8");

                  vazeyat_tahol1 = URLEncoder.encode(vazeyat_tahol,"UTF-8");
                  jensiat1 = URLEncoder.encode(jensiat,"UTF-8");

                  ostan1 = URLEncoder.encode(ostan,"UTF-8");

                  shahrestan1 = URLEncoder.encode(shahrestan,"UTF-8");
                  address_daghigh1 = URLEncoder.encode(address_daghigh,"UTF-8");

              } catch (UnsupportedEncodingException e) {
                  e.printStackTrace();
              }
                params.put("name",name1);
                params.put("family",family1);
                params.put("codemeli",codemeli);
                params.put("shomare_hamrah",shomare_hamrah);
                params.put("shomare_sabet",shomare_sabet);
                params.put("vazeyat_tahol",vazeyat_tahol1);
                params.put("jensiat",jensiat1);
                params.put("ostan",ostan1);
                params.put("shahrestan",shahrestan1);
                params.put("address_daghigh",address_daghigh1);
                params.put("ramzevrod",ramzevrod);
              params.put("pushe_id",pushe_id);
              params.put("shomare_aberbank",shomare_aberbank);
              params.put("shomare_hesab",shomare_hesab);
              params.put("shomare_shaba",shomare_shaba);
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() throws AuthFailureError {
                Map<String,DataPart> params=new HashMap<>();
                long stringlong_imagename=System.currentTimeMillis();
                params.put("aks_kartmeli",new DataPart(stringlong_imagename+codemeli+".png",getfiledatafromdrawable(aks_kartmeli)));
                stringlong_imagename=System.currentTimeMillis();
                params.put("aks_poshtekartmeli",new DataPart(stringlong_imagename+codemeli+".png",getfiledatafromdrawable(aks_poshtekartmeli)));
                stringlong_imagename=System.currentTimeMillis();
                params.put("aks_shenasname",new DataPart(stringlong_imagename+codemeli+".png",getfiledatafromdrawable(aks_shenasname)));
                stringlong_imagename=System.currentTimeMillis();
                params.put("aks_payankhedmat",new DataPart(stringlong_imagename+codemeli+".png",getfiledatafromdrawable(aks_payankhedmat)));
                stringlong_imagename=System.currentTimeMillis();
                params.put("aks_gavahi_fani",new DataPart(stringlong_imagename+codemeli+".png",getfiledatafromdrawable(aks_gavahi_fani)));
                stringlong_imagename=System.currentTimeMillis();
                params.put("aks_sayermadarek",new DataPart(stringlong_imagename+codemeli+".png",getfiledatafromdrawable(aks_sayermadarek)));
                return params;
            }
        };
        //10000 is the time in milliseconds adn is equal to 10 sec
        volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(context).add(volleyMultipartRequest);
    }






    public void getMotakhasesdataProfile(final String id,
                                             final String token)
    {

        final Model_Profile model_profile=new Model_Profile();
        my_interface=(My_interface)this.context;
        String url="http://khialrahat.com/app_files/Motakhases_Api/getMotakhasesProfile.php";

        StringRequest stringRequest =new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("getMotakhasesdataPro ",response.toString());
                        Log.i("volley_profile",response.toString()+"get_profile_");
                        if(response!=null)
                        {
                            if(response.length()>0)
                            {
                                try {


                                    JSONArray jsonArray=new JSONArray(response);

                                    JSONObject jsonObject=jsonArray.getJSONObject(0);


                                    Log.i("getMotakhasesdataPro ","دریافت موفق");



                                        model_profile.setImgLinkAddress(jsonObject.getString("image_link_address"));
                                        model_profile.setName(jsonObject.getString("name"));
                                      String family= jsonObject.getString("family");
                                              model_profile.setFamily(jsonObject.getString("family"));
                                        model_profile.setTakhasos(jsonObject.getString("takhasos"));
                                        model_profile.setMobileNumber(jsonObject.getString("mobile_number"));
                                        model_profile.setWallet(jsonObject.getString("wallet"));

                                    my_interface.get_profile_data(model_profile);
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
                                Log.i("volley_sef_laghv","response have not nodat");
                            }
                        }
                        else
                        {Log.i("volley_sef_laghv","response is null");}

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                   my_interface.recive_data_from_server_failur("error","profile","خطا در");
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("motakhases_id",id);
                parms.put("token",token);
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

    public void get_list_sefarshDarhaleAnjam(final String expert_id)
    {


        Log.i("volley_sef_darhaleanjam","sef");




        String url="http://khialrahat.com/app_files/Motakhases_Api/get_order_darhale_anjam.php";

        StringRequest stringRequest =new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("volley_sef_darhaeanjam",response.toString()+"sef");
                        if(response!=null)
                        {
                            if(response.length()>0)
                            {
                                try {

                                    JSONArray jsonArray=new JSONArray(response);
                                    List<Model_Sefaresh_DarhaleAnjam> list_model_list_sefaresh_darhale_anjam=new ArrayList<>();
                                    for (int i = 0; i<jsonArray.length(); i++)
                                    {
                                        Model_Sefaresh_DarhaleAnjam model_list_sefaresh_darhale_anjam
                                                =new Model_Sefaresh_DarhaleAnjam();
                                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                                        model_list_sefaresh_darhale_anjam.setNoeh_dastebandi_khadamat(jsonObject.getString("service_name"));
                                        model_list_sefaresh_darhale_anjam.setZaman_darkhasti_khadamat(jsonObject.getString("time_order"));
                                        model_list_sefaresh_darhale_anjam.setHazineh(jsonObject.getString("total_price"));
                                        model_list_sefaresh_darhale_anjam.setMablagh_nahaei(jsonObject.getString("final_price"));
                                        //model_list_sefaresh_darhale_anjam.setTakhfif(jsonObject.getString("discount"));
                                        model_list_sefaresh_darhale_anjam.setNahve_pardakht(jsonObject.getString("payment_type"));
                                        model_list_sefaresh_darhale_anjam.setId(jsonObject.getString("order_id"));
                                        model_list_sefaresh_darhale_anjam.setVazeiat_pardakht(jsonObject.getString("payment"));
                                        model_list_sefaresh_darhale_anjam.setService_imgaddress(jsonObject.getString("service_image_address"));
                                        list_model_list_sefaresh_darhale_anjam.add(model_list_sefaresh_darhale_anjam);
                                        //        Log.i("list_sef_laghv_shode",model_list_sefaresh_darhale_anjam.get(i).getZaman_anjamsefaresh());

                                    }


                                    ((MainActivityy)context).List_Sefareshha_Fragment.f_list_sefaresh_darhaleAnjam.
                                            function_list_sefarsh_darhalAnjam(list_model_list_sefaresh_darhale_anjam);
                                    Log.i("volley","darhale_anjam"+list_model_list_sefaresh_darhale_anjam.size());
//
                                }catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }

                            }
                            else {
                                Log.i("volley_sef_"," darhaleanjamresponse have not nodat");
                            }
                        }
                        else
                        {Log.i("volley_sef_","darhaleanjam response is null");}

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ((MainActivityy)context).List_Sefareshha_Fragment.f_list_sefaresh_darhaleAnjam.error();
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();

                parms.put("expert_id",expert_id);

                return parms;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);



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

                                    ((A_Jozeiat_Sefarsh_Darhal_Anjam)context).sefarsh_jadid(model_sefarshe_jadid2);
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
    public void send_time_to_server(final String order_id, final String int_number_last_status)
    {

        Log.i("security_panel ",order_id+int_number_last_status);
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
                                    ((A_Jozeiat_Sefarsh_Darhal_Anjam)context).response_send_time_to_server();

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
                ((A_Jozeiat_Sefarsh_Darhal_Anjam)context).error_response_send_time_to_server();
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


    public void update_pushid(final String id, final String pushe_id, final String token)
    {

        final Model_Profile model_profile=new Model_Profile();
        my_interface=(My_interface)this.context;
        String url="http://khialrahat.com/app_files/Motakhases_Api/update_pushe_id.php";

        StringRequest stringRequest =new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("volly_updatepushid ",response.toString());
                        Log.i("volly_updatepushid",response.toString()+"get_profile_");
                        if(response!=null)
                        {
                            if(response.length()>0)
                            {
                               my_interface.upload_status(false,"not set in vally");
                            }
                            else {
                                Log.i("volly_updatepushid","response have not nodat");
                                my_interface.upload_status(true,"not set in vally");
                            }
                        }
                        else
                        {Log.i("volly_updatepushid","response is null");}

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // interface_list_sefarshha.error_sefarshha(error.getMessage());
                my_interface.recive_data_from_server_failur("error",Const_Variable.UPDATE_PUSHID_ERROR_TITLE
                ,Const_Variable.UPDATE_PUSHID_ERROR_MESSAGE);
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("motakhases_id",id);
                parms.put("pushe_id",pushe_id);
                parms.put("token",token);
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

    public void get_order_payment_type_from_server(final String order_id)
    {


       my_interface=(My_interface)this.context;
        String url="http://khialrahat.com/app_files/Motakhases_Api/get_order_payment_type.php";

        StringRequest stringRequest =new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("getPaymentTypFrmServer",response.toString());

                        if(response!=null)
                        {
                            if(response.length()>0)
                            {
                                try {


                                    JSONArray jsonArray=new JSONArray(response);

                                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                                    Log.i("getPaymentTypFrmServer ","دریافت موفق");
                                    String error=jsonObject.getString("error");
                                    Log.i("getPaymentTypFrmServer","error:"+error);
                                    if (error.contentEquals("false"))
                                    {
                                        String order_payment_type=jsonObject.getString("payment_type");
                                        Log.i("getPaymentTypFrmServer","payment_type:"+order_payment_type);
                                        my_interface.get_order_payment_type_from_server(order_payment_type,"payment_type");
                                    }
                                    else
                                    {}



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
                                Log.i("getPaymentTypFrmServer","response have not nodat");
                            }
                        }
                        else
                        {Log.i("getPaymentTypFrmServer","response is null");}

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

    public void update_final_status_work_is_finish(final String order_id,final String work_status,
                                                   final String id, final String khialerahat_amount, final String motakhases_amount, final String payment_type)
    {
        Log.i("update_work_finish",work_status);

      //  my_interface=(My_interface)this.context;
        String url="http://khialrahat.com/app_files/Motakhases_Api/update_work_finish.php";

        StringRequest stringRequest =new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("update_work_finish ",response.toString());

                        if(response!=null)
                        {
                            if(response.length()>0)
                            {
                                try {
                                    JSONArray jsonArray=new JSONArray(response);
                                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                                    String error=jsonObject.getString("error");
                                    String message=jsonObject.getString("message");
                                    String payment_type=jsonObject.getString("payment_type");
                                    String payment_status=jsonObject.getString("payment_status");
                                    Log.i("update_work_finish ",error+"::::"+message+payment_status+payment_type);

                                        ((A_Jozeiat_Sefarsh_Darhal_Anjam)context).
                                                response_update_final_status_work_is_finish(payment_type,payment_status,error);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Log.i("update_work_finish","update seccess");
                            }
                            else {
                                Log.i("update_work_finish","response have not nodat");

                            }
                        }
                        else
                        {Log.i("update_work_finish","response is null");}

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // interface_list_sefarshha.error_sefarshha(error.getMessage());
              //  my_interface.recive_data_from_server_failur("error",Const_Variable.UPDATE_PUSHID_ERROR_TITLE
                    ///    ,Const_Variable.UPDATE_PUSHID_ERROR_MESSAGE);
                ((A_Jozeiat_Sefarsh_Darhal_Anjam)context).error_update_final_status_work_is_finish();
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("order_id",order_id);
                parms.put("khialerahat_amount",khialerahat_amount);
                parms.put("motakhases_amount",motakhases_amount);
                parms.put("motakhases_id",id);
                parms.put("payment_type",payment_type);
                parms.put("work_status",work_status);

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


    public void get_transactions(
                                         final String motakhases_id,
                                         final String token, final String number_of_row,
                                         final String start_row_from_number)
    {


        Log.i("get_transactions","sef");


        final List<Model_Transaction>[] model_transactionArrayList = new List[]{new ArrayList<>()};
        String url="http://khialrahat.com/app_files/Motakhases_Api/get_expert_transaction.php";

        StringRequest stringRequest =new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("get_transactions",response.toString()+"sef");
                        if(response!=null)
                        {
                            if(response.length()>0)
                            {
                                try {

                                    JSONArray jsonArray=new JSONArray(response);
                                    for (int i=0;i<jsonArray.length();i++)
                                    {
                                        Model_Transaction model_transaction
                                                =new Model_Transaction();
                                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                                        model_transaction.setType_transaction(jsonObject.getString("transaction_status"));
                                        model_transaction.setRequst_date(jsonObject.getString("request_date"));
                                        model_transaction.setAmount(jsonObject.getString("transaction_amount"));
                                        model_transaction.setTasvyeh_date(jsonObject.getString("transaction_date"));
                                        model_transaction.setTracking_code(jsonObject.getString("tracking_code"));
                                        model_transaction.setId(jsonObject.getString("id"));
                                        model_transactionArrayList[0].add(model_transaction);
                                        //        Log.i("list_sef_laghv_shode",model_list_sefaresh_anjam_shode.get(i).getZaman_anjamsefaresh());

                                    }
//                                    for (int j=0;j<model_list_sefaresh_anjam_shode.size();j++)
//                                    {
//                                        Log.i("sef_laghv",model_list_sefaresh_anjam_shode.get(j).getZaman_anjamsefaresh());
//                                    }
                                    ((MainActivityy)context).transaction_fragment.set_transaction_data(model_transactionArrayList[0]);
                                }catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }

                            }
                            else {
                                model_transactionArrayList[0] =null;
                                ((MainActivityy)context).transaction_fragment.set_transaction_data(model_transactionArrayList[0]);
                                Log.i("gettransaction","response have not nodat");
                            }
                        }
                        else
                        {
                            model_transactionArrayList[0] =null;
                            ((MainActivityy)context).transaction_fragment.set_transaction_data(model_transactionArrayList[0]);
                            Log.i("gettransaction","response have not nodat");}

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // interface_list_sefarshha.error_sefarshha(error.getMessage());
                ((MainActivityy)context).transaction_fragment.volley_api_error();
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("expert_id",motakhases_id);
                parms.put("number_of_row",number_of_row);
                parms.put("start_row_from_number",""+start_row_from_number);
                parms.put("token",token);
                return parms;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

    }

    public void get_message(
            final String user_id,
            final String id, final String expert_id)
    {


        final List<Model_Message>[] model_message_list = new List[]{new ArrayList<>()};
        String url="http://khialrahat.com/app_files/get/get_notification.php";

        StringRequest stringRequest =new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("get_messages",response.toString());
                        if(response!=null)
                        {
                            if(response.length()>0)
                            {
                                try {

                                    JSONArray jsonArray=new JSONArray(response);
                                    for (int i=0;i<jsonArray.length();i++)
                                    {
                                        Model_Message model_message
                                                =new Model_Message();
                                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                                        model_message.setMessage_id(jsonObject.getString("ID"));
                                        model_message.setTiltle(jsonObject.getString("title"));
                                        model_message.setMessage_body(jsonObject.getString("message"));
                                        model_message.setMessage_time(jsonObject.getString("time"));

                                        model_message_list[0].add(model_message);
                                        //        Log.i("list_sef_laghv_shode",model_list_sefaresh_anjam_shode.get(i).getZaman_anjamsefaresh());

                                    }
//                                    for (int j=0;j<model_list_sefaresh_anjam_shode.size();j++)
//                                    {
//                                        Log.i("sef_laghv",model_list_sefaresh_anjam_shode.get(j).getZaman_anjamsefaresh());
//                                    }
                                    ((MainActivityy)context).messagesFragment.set_message_data(model_message_list[0]);
                                }catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }

                            }
                            else {
                                model_message_list[0] =null;
                                ((MainActivityy)context).messagesFragment.set_message_data(model_message_list[0]);
                                Log.i("getmessage","response have not nodat");
                            }
                        }
                        else
                        {
                            model_message_list[0] =null;
                            ((MainActivityy)context).messagesFragment.set_message_data(model_message_list[0]);
                            Log.i("getmessage","response have not nodat");}

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // interface_list_sefarshha.error_sefarshha(error.getMessage());
                ((MainActivityy)context).messagesFragment.volley_api_error();
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("expert_id",expert_id);
                parms.put("id",id);
                parms.put("user_id",""+user_id);

                return parms;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

    }

    public void version_control(final String version)
    {


        String url="http://khialrahat.com/app_files/Motakhases_Api/version_control.php";

        StringRequest stringRequest =new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("versioncontrol",response.toString());
                        if(response!=null)
                        {
                            if(response.length()>0)
                            {
                                try {

                                    JSONArray jsonArray=new JSONArray(response);

                                        JSONObject jsonObject=jsonArray.getJSONObject(0);
                                        String message=jsonObject.getString("message");
                                        String error=jsonObject.getString("error");
                                        Log.i("error,message",error+","+message);
                                    if(error.contentEquals("true"))
                                    {
                                        ((SplashActivity)context).error_get_version_control_result();
                                    }
                                    else
                                    {
                                        ((SplashActivity)context).get_version_control_result(message);
                                    }
                                }catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }

                            }
                            else
                                {

                                Log.i("versioncontrol","response have not nodat");
                                    ((SplashActivity)context).error_get_version_control_result();
                            }
                        }
                        else
                        {
                            Log.i("versioncontrol","response have not nodat");
                            ((SplashActivity)context).error_get_version_control_result();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // interface_list_sefarshha.error_sefarshha(error.getMessage());
                Log.i("versioncontrol","error response have not nodat"+error.getMessage());
                error.printStackTrace();
                ((SplashActivity)context).error_get_version_control_result();
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("expert_app_version",version);

                return parms;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

    }
}
