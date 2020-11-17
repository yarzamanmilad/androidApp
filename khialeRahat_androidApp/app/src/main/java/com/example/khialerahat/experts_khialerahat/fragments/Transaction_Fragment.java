package com.example.khialerahat.experts_khialerahat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khialerahat.experts_khialerahat.MainPackage.Model_Transaction;
import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Const_Variable;
import com.example.khialerahat.experts_khialerahat.R;
import com.example.khialerahat.experts_khialerahat.Recycler_Viewholder_Adapter;
import com.example.khialerahat.experts_khialerahat.Web_Service.Valley_Api;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class Transaction_Fragment extends Fragment {

    WaveSwipeRefreshLayout waveSwipeRefreshLayout;
    ArrayList<String> akhbar=new ArrayList<>();
    ListView listView;


    ArrayAdapter<String> adapter;
    private View view;
    RecyclerView recyclerView;
    NestedScrollView nestedScrollView;
    TextView txtv_not_transaction;
    SpinKitView spinKitView;
    AlertDialog alertDialog;
    Valley_Api valley_api;
    private int number_repeat=0;
    String token="0";
    String number_of_row="0",row_from="0";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.f_transaction_layout, container, false);
        initials_view();
        valley_api=new Valley_Api(getContext());

        valley_api.get_transactions(MotakhasesObject.id,token,number_of_row,row_from);
        spinKitView.setVisibility(View.VISIBLE);
                return view;
    }
    public void set_transaction_data(List<Model_Transaction> transaction_list)
    {
        spinKitView.setVisibility(View.GONE);
        if(transaction_list!=null)
        {
            if(transaction_list.size()>0)
            setup_recyclerview(transaction_list);
            else
            {

                nestedScrollView.setVisibility(View.GONE);
                txtv_not_transaction.setVisibility(View.VISIBLE);
            }

        }
        else
        {
            nestedScrollView.setVisibility(View.GONE);
            txtv_not_transaction.setVisibility(View.VISIBLE);
        }
    }
    public void volley_api_error()
    {
        spinKitView.setVisibility(View.INVISIBLE);
        if(number_repeat< Const_Variable.MAX_LENGTH_REPEAT_TRAY_TO_GET_DATA)
        {
            number_repeat++;
            valley_api.get_transactions(MotakhasesObject.id,token,number_of_row,row_from);
            spinKitView.setVisibility(View.VISIBLE);
        }
        else
        {
            Toast.makeText(getContext(), "خطا در اتصال", Toast.LENGTH_SHORT).show();
        }
    }
  private  void setup_recyclerview(List<Model_Transaction> transaction_list)
  {
      List<Object> objectList=new ArrayList<>();


      for(int i=0;i<transaction_list.size();i++)
      {
          objectList.add(transaction_list.get(i));
      }
      Recycler_Viewholder_Adapter recycler_viewholder_adapter = new
              Recycler_Viewholder_Adapter(objectList,getContext());

      LinearLayoutManager gridLayoutManager=new LinearLayoutManager(getContext());

      recyclerView.setLayoutManager(gridLayoutManager);


      recyclerView.setAdapter(recycler_viewholder_adapter);
  }
    private void initials_view()
    {
        nestedScrollView=view.findViewById(R.id.nestscrol_in_transaction_lay);
       txtv_not_transaction=view.findViewById(R.id.txt_not_transaction_f_transaction);
       spinKitView=view.findViewById(R.id.progress_in_lay_transaction);
       recyclerView=view.findViewById(R.id.recycler_in_transactin_lay);

    }
}
