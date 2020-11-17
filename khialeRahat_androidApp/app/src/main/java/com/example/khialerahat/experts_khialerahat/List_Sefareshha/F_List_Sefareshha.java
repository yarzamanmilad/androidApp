 package com.example.khialerahat.experts_khialerahat.List_Sefareshha;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Const_Variable;
import com.example.khialerahat.experts_khialerahat.R;
import com.example.khialerahat.experts_khialerahat.Web_Service.Valley_Api;
import com.example.khialerahat.experts_khialerahat.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 public class F_List_Sefareshha extends Fragment implements Interface_List_Sefarshha {
    ViewPager viewPager;
    int int_temp_sefarsh_anjamshodeh=0;
    List<Model_List_Sefaresh_Anjam_Shodeh> list_temp_sefaresh_anjamshode;
    int start_row_from_number=1;
    F_List_Sefaresh_Jadid FListSefareshJadid;
    F_List_Sefaresh_Anjam_Shodeh FListSefareshAnjamShodeh;
    public F_List_Sefaresh_DarhaleAnjam f_list_sefaresh_darhaleAnjam;

    F_List_Sefaresh_Laghv_shodeh f_list_sefaresh_laghv_shodeh;
TabLayout tabLayout;
Valley_Api valley_api;
Context mycontext;
     private View view;


     @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.f_list_sefareshha, container, false);
          this.mycontext=container.getContext();

              Log.i("list_sefaresh","list_sefaresh");

        viewPager = (ViewPager) view.findViewById(R.id.pager);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);

         FListSefareshJadid =new F_List_Sefaresh_Jadid();
         FListSefareshAnjamShodeh =new F_List_Sefaresh_Anjam_Shodeh();
        f_list_sefaresh_laghv_shodeh=new F_List_Sefaresh_Laghv_shodeh();
        f_list_sefaresh_darhaleAnjam=new F_List_Sefaresh_DarhaleAnjam();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(FListSefareshJadid, "جدید");
         adapter.addFragment(f_list_sefaresh_darhaleAnjam, "در حال انجام");
        adapter.addFragment(FListSefareshAnjamShodeh, "انجام شده");
        adapter.addFragment(f_list_sefaresh_laghv_shodeh,"لغو شده");
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.parseColor("#000000")));
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#2f2f2f"));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
           viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
               @Override
               public void onPageScrolled(int i, float v, int i1) {

               }

               @Override
               public void onPageSelected(int i) {
                     Log.i("onPageSelected",""+i);
                     switch (i)
                     {
                         case 2:
                                  if(int_temp_sefarsh_anjamshodeh==0) {
                                      getListSefarshAnjamShode
                                              (Const_Variable.VAZEIAT_SEFARSH_ANJAM_SHODE,
                                                      MotakhasesObject.id, MotakhasesObject.token,
                                                  Const_Variable.NUMBER_OF_ROWS_FOR_FETCH_FROM_SERVER,start_row_from_number);
                                  }
                                  else
                                  {FListSefareshAnjamShodeh.list_sefarsh_anjam_shodeh(list_temp_sefaresh_anjamshode);}
                             break;
                         case 0:
                              Log.i("list_sefarsh_f","on page selct"+i+MotakhasesObject.new_order_id);
                             if(MotakhasesObject.new_order_id.contentEquals("0"))
                             {
                                FListSefareshJadid.not_found_new_order();
                             }
                             else
                             {

                                 get_new_order_details(MotakhasesObject.new_order_id);
                             }

                             break;

                         case 1:
                             Log.i("list_sefarsh_f","on page selct"+i+MotakhasesObject.new_order_id);
                             f_list_sefaresh_darhaleAnjam.function_onresum();

                             break;


                         default:
                     }
               }



               public void getListSefarshAnjamShode(final String vazeiatSefarshAnjamShode,
                                                    final String motakhases_id,
                                                    final String token, final String number_of_row,
                                                    final int start_row_from_number)
               {


                   Log.i("volley_sef_anjamshode","sef");



                   final List<Model_List_Sefaresh_Anjam_Shodeh> list_model_list_sefaresh_anjam_shode=new ArrayList<>();
                   String url="http://khialrahat.com/app_files/Motakhases_Api/get_order_anjamshode.php";

                   StringRequest stringRequest =new StringRequest(Request.Method.POST, url,
                           new Response.Listener<String>() {
                               @Override
                               public void onResponse(String response) {
                                   Log.i("volley_sef_anjamshode",response.toString()+"sef");
                                   if(response!=null)
                                   {
                                       if(response.length()>0)
                                       {
                                           try {

                                               JSONArray jsonArray=new JSONArray(response);
                                               for (int i=0;i<jsonArray.length();i++)
                                               {
                                                   Model_List_Sefaresh_Anjam_Shodeh model_list_sefaresh_anjam_shode
                                                           =new Model_List_Sefaresh_Anjam_Shodeh();
                                                   JSONObject jsonObject=jsonArray.getJSONObject(i);
                                                   model_list_sefaresh_anjam_shode.setNoeh_khadamat(jsonObject.getString("service_name"));
                                                   model_list_sefaresh_anjam_shode.setZaman_anjamsefaresh(jsonObject.getString("time_order"));
                                                   model_list_sefaresh_anjam_shode.setHazineh_khadamat(jsonObject.getString("total_price"));
                                                   model_list_sefaresh_anjam_shode.setId_sefaresh(jsonObject.getString("order_id"));
                                                   model_list_sefaresh_anjam_shode.setVazeiat_pardakht(jsonObject.getString("payment"));
                                                   model_list_sefaresh_anjam_shode.setAks_khadamat(jsonObject.getString("service_image_address"));
                                                   list_model_list_sefaresh_anjam_shode.add(model_list_sefaresh_anjam_shode);
                                                   //        Log.i("list_sef_laghv_shode",model_list_sefaresh_anjam_shode.get(i).getZaman_anjamsefaresh());

                                               }
                                              int_temp_sefarsh_anjamshodeh=1;
                                               list_temp_sefaresh_anjamshode=list_model_list_sefaresh_anjam_shode;
                                               FListSefareshAnjamShodeh.list_sefarsh_anjam_shodeh(list_model_list_sefaresh_anjam_shode);
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
                           parms.put("activation_status",vazeiatSefarshAnjamShode);
                           parms.put("motakhases_id",motakhases_id);
                           parms.put("number_of_row",number_of_row);
                           parms.put("start_row_from_number",""+start_row_from_number);
                           parms.put("token",token);
                           return parms;
                       }
                   };
                   AppController.getInstance().addToRequestQueue(stringRequest);

               }


               @Override
               public void onPageScrollStateChanged(int i) {

               }
           });

        return view;
    }

     public void get_new_order_details(final String order_id)
     {
         Log.i("get_new_order_list","start"+order_id);
         String url="http://khialrahat.com/app_files/Motakhases_Api/get_list_order_jadid.php";
         final List<Model_List_Sefaresh_jadid> model_list_sefaresh_jadids=new ArrayList<>();
         StringRequest stringRequest =new StringRequest(Request.Method.POST, url,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                         Log.i("get_new_order_list",response.toString());
                         if(response!=null)
                         {
                             if(response.length()>0)
                             {
                                 try {
                                     JSONArray jsonArray = new JSONArray(response);
                                     for (int i = 0; i < jsonArray.length(); i++) {
                                         JSONObject jsonObject = null;

                                         jsonObject = jsonArray.getJSONObject(i);


                                         Model_List_Sefaresh_jadid model_sefarshe_jadid2
                                                 = new Model_List_Sefaresh_jadid();
                                         // JSONObject jsonObject=jsonArray.getJSONObject(i);
                                         Log.i("new_order_service", jsonObject.getString("service_name"));
                                         //Log.i("new_order_service", jsonObject.getString("mobile"));
                                         model_sefarshe_jadid2.setNoeh_khadamat(jsonObject.getString("service_name"));
                                          model_sefarshe_jadid2.setAks_khadamat(jsonObject.getString("service_image_address"));
                                          model_sefarshe_jadid2.setId_sefaresh(jsonObject.getString("order_id"));
                                         Log.i("new_order_service", jsonObject.getString("order_id"));
                                         model_sefarshe_jadid2.setZaman_darkhasti_khadamat(jsonObject.getString("time_order"));
                                         Log.i("new_order_service", jsonObject.getString("time_order"));
                                         model_sefarshe_jadid2.setNahve_pardakht(jsonObject.getString("payment_type"));

                                         model_sefarshe_jadid2.setHazineh(jsonObject.getString("total_price"));
                                         Log.i("new_order_service", jsonObject.getString("payment_type"));
                                         Log.i("new_order_service", jsonObject.getString("total_price"));
//                                         Log.i("new_order_service",jsonObject.getString("service"));
//                                         Log.i("new_order_service",jsonObject.getString("mobile"));
//                                         Log.i("new_order_service",jsonObject.getString("time_in"));
//                                         Log.i("new_order_service","tow_step_finish"+jsonObject.getString("tow_step_finish"));
//                                         Log.i("new_order_service","group_order"+jsonObject.getString("group_order"));
//                                         Log.i("new_order_service","security_active"+jsonObject.getString("security_active"));
                                         model_sefarshe_jadid2.setTime_in(jsonObject.getString("time_in"));
                                         model_sefarshe_jadid2.setNoeh_khadamat_jozei(jsonObject.getString("service"));
                                         model_sefarshe_jadid2.setShomareh_hamrah_moshtari(jsonObject.getString("mobile"));
                                         model_sefarshe_jadid2.setAddress(jsonObject.getString("address"));


                                         //model_sefarshe_jadid2.setSahme_motakhases("50");
                                         model_sefarshe_jadid2.setVazeiat_pardakht(jsonObject.getString("payment"));


                                         model_sefarshe_jadid2.setService_type(jsonObject.getString("group_order"));
                                         model_sefarshe_jadid2.setSecurity_panel(jsonObject.getString("security_active"));
                                         model_sefarshe_jadid2.setTow_step_finish(jsonObject.getString("tow_step_finish"));
                                         model_list_sefaresh_jadids.add(model_sefarshe_jadid2);

                                     }




                                     FListSefareshJadid.sefarsh_jadid(model_list_sefaresh_jadids);
//

                                 } catch(JSONException e){
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


     @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void list_sefarsh_laghv_shodeh(List<Model_Sefaresh_laghv_Shodeh> list_laghv_shode) {

    }

    @Override
    public void list_sefarsh_anjam_shode(List<Model_List_Sefaresh_Anjam_Shodeh> list_anjam_shode) {
        F_List_Sefaresh_Anjam_Shodeh f_list_sefaresh_anjam_shodeh=new F_List_Sefaresh_Anjam_Shodeh();
        f_list_sefaresh_anjam_shodeh.list_sefarsh_anjam_shodeh(list_anjam_shode);
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

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mList = new ArrayList<>();
        private final List<String> mTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }
        @Override
        public Fragment getItem(int i) {
            return mList.get(i);
        }
        @Override
        public int getCount() {
            return mList.size();
        }
        public void addFragment(Fragment fragment, String title) {
            mList.add(fragment);
            mTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }
    }

}


