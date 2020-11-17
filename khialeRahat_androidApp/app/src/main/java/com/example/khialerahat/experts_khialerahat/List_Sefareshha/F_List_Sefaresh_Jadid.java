package com.example.khialerahat.experts_khialerahat.List_Sefareshha;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.khialerahat.experts_khialerahat.MPlayer_CounterDown;
import com.example.khialerahat.experts_khialerahat.MainActivityy;
import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.Progress_And_Dialog;
import com.example.khialerahat.experts_khialerahat.R;
import com.example.khialerahat.experts_khialerahat.Recycler_Viewholder_Adapter;

import java.util.ArrayList;
import java.util.List;

public class F_List_Sefaresh_Jadid extends Fragment  {

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
    private SharedPreferences preferences;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.f_list_sefaresh_jadid, container, false);
        initials_view();


        progress_and_dialog=new Progress_And_Dialog(getContext(),alertDialog);
        nestedScrollView.setVisibility(View.INVISIBLE);
        Log.i("list_sefaresh","list_sefaresh_jadid_shode_oncreat");
        this.myContext=container.getContext();


        return view;
    }


    private void initials_view()
    {

        txt_sefarsh_jadid_vjod_nadarad=view.findViewById(R.id.txt_f_list_sefaresh_jadid);
        recyclerView=view.findViewById(R.id.recycler_listsefarsh_jadid);
        nestedScrollView=view.findViewById(R.id.nesscrool_in_f_sefjadid);
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
         //   MotakhasesObject.new_order_id="0";

            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("new_order_id",null).commit();
            ((MainActivityy)getContext()).navigation.setNotification("",0);
            if(MPlayer_CounterDown.countDownTimer!=null) {
                MPlayer_CounterDown.countDownTimer.cancel();
                MPlayer_CounterDown.countDownTimer = null;
            }
            Log.i("counterdown","cancel in onresum sefarsh jadid");
            ((F_List_Sefareshha)getParentFragment()).get_new_order_details(order_id_final);
        }
        //Toast.makeText(getContext(), "jadid", Toast.LENGTH_SHORT).show();
        Log.i("list_sefaresh","list_sefaresh_jadid_onresum");
    }

    public void sefarsh_jadid(List<Model_List_Sefaresh_jadid> model_list_sefaresh_jadids)
    {
        Log.i("list_sef_anjam",model_list_sefaresh_jadids.size()+"");
        if(model_list_sefaresh_jadids.size()>0)
        {


            List<Object> objectList=new ArrayList<>();
            objectList.clear();
            for(int i=0;i<model_list_sefaresh_jadids.size();i++)
            {
                objectList.add(model_list_sefaresh_jadids.get(i));
                // model_list_sefaresh_jadids.get(i).setId_sefaresh();
                Log.i("list_sef_anjam",model_list_sefaresh_jadids.get(i).getId_sefaresh());
            }
            Recycler_Viewholder_Adapter recycler_viewholder_adapter = new
                    Recycler_Viewholder_Adapter(objectList,getContext());

            LinearLayoutManager gridLayoutManager=new LinearLayoutManager(getContext());

            recyclerView.setLayoutManager(gridLayoutManager);


            recyclerView.setAdapter(recycler_viewholder_adapter);
            nestedScrollView.setVisibility(View.VISIBLE);
            txt_sefarsh_jadid_vjod_nadarad.setVisibility(View.INVISIBLE);
        }
        else
        {
             not_found_new_order();

        }

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
