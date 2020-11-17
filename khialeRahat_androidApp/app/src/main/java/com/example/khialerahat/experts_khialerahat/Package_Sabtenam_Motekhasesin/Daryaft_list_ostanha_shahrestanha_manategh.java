package com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khialerahat.experts_khialerahat.My_interface;
import com.example.khialerahat.experts_khialerahat.Web_Service.Api_Interface;
import com.example.khialerahat.experts_khialerahat.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Daryaft_list_ostanha_shahrestanha_manategh
{
    Context context;

    private List<String> list_name_ostanha,list_name_shahrestanha;
    private List<String> list_name_manategh;
    Api_Interface api_interface;
    My_interface my_interface;

    public Daryaft_list_ostanha_shahrestanha_manategh(Context context)
    {   this.context=context;
        this.my_interface =(My_interface) this.context;
        this.list_name_ostanha=new ArrayList<>();
        this.list_name_shahrestanha=new ArrayList<>();
        this.list_name_manategh=new ArrayList<>();

       this.get_listostanha_asserver();
    }


    public List<String> getList_name_ostanha() {
        return list_name_ostanha;
    }

    public List<String> getList_name_manategh() {


        return list_name_manategh;

    }
    public List<String> getList_name_shahrestanha(String name_ostan) {

         get_listshrha_as_server(name_ostan);

        return list_name_shahrestanha;
    }


    public void get_listostanha_asserver()
    {   get_listshrha_as_server("تهران");
        get_listmanategh_as_server("تهران","اندیشه");
        list_name_ostanha.clear();
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

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i("vally_get_ostanha", "no data");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }


    public void get_listshrha_as_server(final String name_ostan)
    {          list_name_shahrestanha.clear();
        String url="http://khialrahat.com/app_files/city/get_shahr.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {


                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            list_name_shahrestanha.add(jsonObject.getString("name"));
                            Log.i("vally_get_shahrha", list_name_shahrestanha.get(i)+"milad");

                        }
                        my_interface.greftan_list_shahrha(list_name_shahrestanha,list_name_shahrestanha );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i("vally_get_ostanha", "no data");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms= new HashMap<>();
                parms.put("ostan",name_ostan);
                return parms;
            }
        };
        AppController.getInstance().addToRequestQueue(request);
    }

    public void get_listmanategh_as_server(final String name_ostan, final String name_shahr)
    {          list_name_manategh.clear();
        String url="https://khialrahat.com/app_files/city/get_manategh.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {


                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            list_name_manategh.add(jsonObject.getString("name"));
                            Log.i("vally_get_manategh", list_name_manategh.get(i));

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i("vally_get_manategh", "no data");
                }
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
                parms.put("ostan",name_ostan);
                parms.put("shahr",name_shahr);
                return parms;
            }
        };
        AppController.getInstance().addToRequestQueue(request);
    }

}
