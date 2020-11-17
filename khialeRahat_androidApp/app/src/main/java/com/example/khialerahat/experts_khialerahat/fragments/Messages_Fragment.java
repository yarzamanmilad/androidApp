package com.example.khialerahat.experts_khialerahat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khialerahat.experts_khialerahat.MainActivityy;
import com.example.khialerahat.experts_khialerahat.MainPackage.Model_Message;
import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Const_Variable;
import com.example.khialerahat.experts_khialerahat.R;
import com.example.khialerahat.experts_khialerahat.Recycler_Viewholder_Adapter;
import com.example.khialerahat.experts_khialerahat.Web_Service.Valley_Api;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

import static com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject.token;

public class Messages_Fragment extends Fragment {

    WaveSwipeRefreshLayout waveSwipeRefreshLayout;
    ArrayList<String> akhbar=new ArrayList<>();
    ListView listView;

     Button button;
    ArrayAdapter<String> adapter;
    private ExpandableRelativeLayout expandableLayout1;
    private SpinKitView spinKitView;
    private TextView txt_message_not_found;
    private NestedScrollView nestedScrollView;
    private int number_repeat=0;
    private Valley_Api valley_api;
    private int row_from=0;
    private RecyclerView recyclerView;
    Model_Message model_message;
    List<Model_Message> model_messages_list=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.f_message_layout, container, false);

        initials_view(view);
        valley_api=new Valley_Api(getContext());

        //valley_api.get_transactions(MotakhasesObject.id,token,Const_Variable.NUMBER_OF_ROWS_FOR_FETCH_FROM_SERVER, String.valueOf(row_from));
        valley_api.get_message("0","2",MotakhasesObject.id);
        spinKitView.setVisibility(View.VISIBLE);

       // expandableLayout1 = view. findViewById(R.id.expandableLayout4);

        return view;
    }


    public void set_message_data(List<Model_Message> model_messages)
    {
        Log.i("message","setdata1");
        spinKitView.setVisibility(View.GONE);
        if(model_messages!=null)
        {
            Log.i("message","setdata not null");
            if(model_messages.size()>0) {
                setup_recyclerview(model_messages);
                nestedScrollView.setVisibility(View.VISIBLE);
                spinKitView.setVisibility(View.INVISIBLE);
                Log.i("message","setdata >0");
            }
            else
            {
                Log.i("message","setdata <0");
                nestedScrollView.setVisibility(View.GONE);
                txt_message_not_found.setVisibility(View.VISIBLE);
            }

        }
        else
        {
            nestedScrollView.setVisibility(View.GONE);
            txt_message_not_found.setVisibility(View.VISIBLE);
            Log.i("message","setdata is null");
        }
    }
    public void volley_api_error()
    {
        spinKitView.setVisibility(View.INVISIBLE);
        if(number_repeat< Const_Variable.MAX_LENGTH_REPEAT_TRAY_TO_GET_DATA)
        {
            number_repeat++;
            valley_api.get_message("0","2",MotakhasesObject.id);
            spinKitView.setVisibility(View.VISIBLE);
        }
        else
        {
            Toast.makeText(getContext(), "خطا در اتصال", Toast.LENGTH_SHORT).show();
        }
    }
    private  void setup_recyclerview(List<Model_Message> model_messages)
    {
        List<Object> objectList=new ArrayList<>();


        for(int i=0;i<model_messages.size();i++)
        {
            objectList.add(model_messages.get(i));
            Log.i("message",""+i);
        }
        Recycler_Viewholder_Adapter recycler_viewholder_adapter = new
                Recycler_Viewholder_Adapter(objectList,getContext());

        LinearLayoutManager gridLayoutManager=new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(gridLayoutManager);


        recyclerView.setAdapter(recycler_viewholder_adapter);
    }
    private void initials_view(View view)
    {
        nestedScrollView=view.findViewById(R.id.nestscrol_in_message_lay);
        txt_message_not_found=view.findViewById(R.id.txt_not_transaction_f_message);
        spinKitView=view.findViewById(R.id.progress_in_lay_message);
        recyclerView=view.findViewById(R.id.recycler_in_message_lay);

    }

    public void showdialog(String title, String body)
    {
        ((MainActivityy)getContext()).progress_and_dialog.show_f_message_dialog(title,body);
    }
}
