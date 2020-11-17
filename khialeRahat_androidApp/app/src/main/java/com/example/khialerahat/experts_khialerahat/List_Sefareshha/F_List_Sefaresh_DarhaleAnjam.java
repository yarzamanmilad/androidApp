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
import android.widget.Toast;

import com.example.khialerahat.experts_khialerahat.MainActivityy;
import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Const_Variable;
import com.example.khialerahat.experts_khialerahat.R;
import com.example.khialerahat.experts_khialerahat.Recycler_Viewholder_Adapter;
import com.example.khialerahat.experts_khialerahat.Web_Service.Valley_Api;

import java.util.ArrayList;
import java.util.List;

public class F_List_Sefaresh_DarhaleAnjam extends Fragment{
    List<Object> list_object_sefarsh_anjamshodeh;
    RecyclerView recyclerView;
    Recycler_Viewholder_Adapter recycler_viewholder_adapter;
     View view;
    private TextView txt_sefarsh_darhale_anjam;
    private String temp_text_when_list_is_empaty="سفارش ثبت نشده است";
    Valley_Api valley_api;
    List<Model_Sefaresh_DarhaleAnjam> temp_list_model_sefaresh_darhaleAnjams=new ArrayList<>();
    private int number_repeat=0;
    private RecyclerView recyclerview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.f__list__sefarsh__darhal_anjam, container, false);
         //fill_list_object();
        Log.i("list_sefaresh","list_sefaresh_darhale_anjam_oncreat");

       //  setup_recyclerview();

        txt_sefarsh_darhale_anjam=view.findViewById(R.id.txt_f_list_sefaresh_darhaleanjam);
        recyclerview=view.findViewById(R.id.recycler_listsefarsh_darhalanjam);


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        Log.i("list_sefaresh","list_sefaresh_darhalanjam_onresum");
    }
    public void function_onresum()
    {
        if (temp_list_model_sefaresh_darhaleAnjams!=null)
        {
            if (temp_list_model_sefaresh_darhaleAnjams.size()>0)
            {function_list_sefarsh_darhalAnjam(temp_list_model_sefaresh_darhaleAnjams);}
            else
            {
                valley_api=new Valley_Api(getContext());
                valley_api.get_list_sefarshDarhaleAnjam(MotakhasesObject.id);
                if (((MainActivityy)getContext()).spinKitView!=null)
                ((MainActivityy)getContext()).spinKitView.setVisibility(View.VISIBLE);
            }

        }
        else
        {
            if (((MainActivityy)getContext()).spinKitView!=null)
            ((MainActivityy)getContext()).spinKitView.setVisibility(View.VISIBLE);
            valley_api=new Valley_Api(getContext());
            valley_api.get_list_sefarshDarhaleAnjam(MotakhasesObject.id);
        }

    }
    public void function_list_sefarsh_darhalAnjam(List<Model_Sefaresh_DarhaleAnjam> list_darhale_anjam) {
        Log.i("list_sefaresh","function_list_sefarsh_darhalAnjam start");
        ((MainActivityy)getContext()).spinKitView.setVisibility(View.INVISIBLE);
        temp_list_model_sefaresh_darhaleAnjams=list_darhale_anjam;
        

        if(list_darhale_anjam.size()>0)
        {

            Log.i("list_sefaresh","function_list_sefarsh_darhalAnjam start size >0");
            List<Object> objectList=new ArrayList<>();
            objectList.clear();
            for(int i=0;i<list_darhale_anjam.size();i++)
            {
                objectList.add(list_darhale_anjam.get(i));
               // list_darhale_anjam.get(i).setId_sefaresh();
                 Log.i("list_sef_anjam",list_darhale_anjam.get(i).getId());
            }
            Recycler_Viewholder_Adapter recycler_viewholder_adapter = new
                    Recycler_Viewholder_Adapter(objectList,getContext());

            LinearLayoutManager gridLayoutManager=new LinearLayoutManager(getContext());

            recyclerview.setLayoutManager(gridLayoutManager);


            recyclerview.setAdapter(recycler_viewholder_adapter);
            recyclerview.setVisibility(View.VISIBLE);
            txt_sefarsh_darhale_anjam.setVisibility(View.INVISIBLE);
        }
        else
        {
            Log.i("list_sefaresh","function_list_sefarsh_darhalAnjam start size <=0");
            recyclerview.setVisibility(View.INVISIBLE);
            txt_sefarsh_darhale_anjam.setVisibility(View.VISIBLE);
            txt_sefarsh_darhale_anjam.setText(temp_text_when_list_is_empaty);

        }

    }

    public void error()
    {
        ((MainActivityy)getContext()).spinKitView.setVisibility(View.INVISIBLE);
        if(number_repeat< Const_Variable.MAX_LENGTH_REPEAT_TRAY_TO_GET_DATA)
        {
            ((MainActivityy)getContext()).spinKitView.setVisibility(View.VISIBLE);
            valley_api=new Valley_Api(getContext());
            valley_api.get_list_sefarshDarhaleAnjam(MotakhasesObject.id);
            number_repeat++;

        }
        else
        {
            Toast.makeText(getContext(), "خطا در اتصال", Toast.LENGTH_SHORT).show();
        }
    }
}

