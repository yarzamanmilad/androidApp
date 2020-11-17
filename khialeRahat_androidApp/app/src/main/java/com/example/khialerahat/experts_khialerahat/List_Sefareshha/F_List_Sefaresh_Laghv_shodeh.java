package com.example.khialerahat.experts_khialerahat.List_Sefareshha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Const_Variable;
import com.example.khialerahat.experts_khialerahat.R;
import com.example.khialerahat.experts_khialerahat.Recycler_Viewholder_Adapter;
import com.example.khialerahat.experts_khialerahat.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class F_List_Sefaresh_Laghv_shodeh extends Fragment implements Interface_List_Sefarshha {
View view_f;
TextView txt_sefarsh_laghv_shode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.f_list_sefarsh_laghv_shode, container, false);
              view_f=view;
        Log.i("list_sefaresh","list_sefaresh_laghv_shode_oncreat");
        return view;
    }


    @Override
    public void list_sefarsh_laghv_shodeh(List<Model_Sefaresh_laghv_Shodeh> list_laghv_shode) {
        txt_sefarsh_laghv_shode=view_f.findViewById(R.id.txt_f_list_sefaresh_laghv_shode);
        RecyclerView recyclerview=view_f.findViewById(R.id.recyclerview_sefarsh_laghvshode);
        if(list_laghv_shode.size()>0)
        {


            List<Object> objectList=new ArrayList<>();
            for(int i=0;i<list_laghv_shode.size();i++)
            {
                objectList.add(list_laghv_shode.get(i));
                list_laghv_shode.get(i).getAddress();
              //  Log.i("list_sef_laghv",list_laghv_shode.get(i).getZaman_anjamsefaresh());
            }
            Recycler_Viewholder_Adapter recycler_viewholder_adapter = new
                    Recycler_Viewholder_Adapter(objectList,getContext());

            LinearLayoutManager gridLayoutManager=new LinearLayoutManager(getContext());

            recyclerview.setLayoutManager(gridLayoutManager);

            Log.i("object_list_manategh",objectList.size()+"");
            recyclerview.setAdapter(recycler_viewholder_adapter);
            recyclerview.setVisibility(View.VISIBLE);
            txt_sefarsh_laghv_shode.setVisibility(View.INVISIBLE);
        }
        else
        {

            recyclerview.setVisibility(View.INVISIBLE);
            txt_sefarsh_laghv_shode.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onResume() {
        super.onResume();


        getListSefarshLaghvShode(Const_Variable.VAZEIAT_SEFARSH_LAGHV_SHODE,MotakhasesObject.id,MotakhasesObject.token);

        Log.i("list_sefaresh","list_sefaresh_laghv_shode_onresum");
    }

    @Override
    public void list_sefarsh_anjam_shode(List<Model_List_Sefaresh_Anjam_Shodeh> list_anjam_shode) {

    }

    @Override
    public void sefarsh_jadid(List<String> sefarsh_jadid) {

    }

    @Override
    public void error_sefarshha(String error) {

    }

    @Override
    public void get_jozeiat_sefarsh_anjamshode(Model_Jozeiat_Sefarsh_Anjamshode jozeiat_sefarsh_anjamshode) {

    }

    @Override
    public void b(String b) {

    }

    @Override
    public void c(String c) {

    }

    @Override
    public void d(String a) {

    }

    public void getListSefarshLaghvShode(final String vazeiatSefarshLaghvShode, final String motakhases_id, final String token)
    {
        Log.i("seflaghvshode",vazeiatSefarshLaghvShode+"::::"+motakhases_id);


        Log.i("seflaghvshode","vallistart");

        final List<Model_Sefaresh_laghv_Shodeh> list_model_sefaresh_laghv_shodehs=new ArrayList<>();
        String url="http://khialrahat.com/app_files/Motakhases_Api/get_cancel_orders.php";

        StringRequest stringRequest =new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("volley_sefarsh_laghv",response.toString()+"sef");
                        if(response!=null)
                        {
                            if(response.length()>0)
                            {
                                try {

                                    JSONArray jsonArray=new JSONArray(response);
                                    for (int i=0;i<jsonArray.length();i++)
                                    {
                                         Model_Sefaresh_laghv_Shodeh model_sefaresh_laghv_shodeh=new Model_Sefaresh_laghv_Shodeh();
                                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                                        model_sefaresh_laghv_shodeh.setNoeh_khadamat(jsonObject.getString("service_name"));
                                        model_sefaresh_laghv_shodeh.setZaman_anjamsefaresh(jsonObject.getString("time_order"));
                                        model_sefaresh_laghv_shodeh.setAddress(jsonObject.getString("address"));
                                        model_sefaresh_laghv_shodeh.setImg_address(jsonObject.getString("image_address"));
                                        model_sefaresh_laghv_shodeh.setZaman_laghve_sefarsh("مشخص نشده");
                                        list_model_sefaresh_laghv_shodehs.add(model_sefaresh_laghv_shodeh);
                                //        Log.i("list_sef_laghv_shode",list_model_sefaresh_laghv_shodehs.get(i).getZaman_anjamsefaresh());

                                    }
                                    list_sefarsh_laghv_shodeh(list_model_sefaresh_laghv_shodehs);
//                                    for (int j=0;j<list_model_sefaresh_laghv_shodehs.size();j++)
//                                    {
//                                        Log.i("sef_laghv",list_model_sefaresh_laghv_shodehs.get(j).getZaman_anjamsefaresh());
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
                parms.put("activation_status",vazeiatSefarshLaghvShode);
                parms.put("motakhases_id",motakhases_id);
                parms.put("number_of_row","6");
                parms.put("token",token);
                return parms;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

    }
}
