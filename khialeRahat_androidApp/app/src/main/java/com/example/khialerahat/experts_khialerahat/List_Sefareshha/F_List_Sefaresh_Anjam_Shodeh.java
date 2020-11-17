package com.example.khialerahat.experts_khialerahat.List_Sefareshha;

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
import android.widget.TextView;

import com.example.khialerahat.experts_khialerahat.R;
import com.example.khialerahat.experts_khialerahat.Recycler_Viewholder_Adapter;

import java.util.ArrayList;
import java.util.List;

public class F_List_Sefaresh_Anjam_Shodeh extends Fragment{
    List<Object> list_object_sefarsh_anjamshodeh;
    RecyclerView recyclerView;
    Recycler_Viewholder_Adapter recycler_viewholder_adapter;

     View view;
    private TextView txt_sefarsh_anjam_shode;
    private String temp_text_when_list_is_empaty="سفارش انجام شده ثبت نشده است";
    private RecyclerView recyclerview;
    private NestedScrollView nestedScrollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.f_list_sefaresh_anjam_shodeh, container, false);
         initial_view();
         //fill_list_object();
        Log.i("list_sefaresh","list_sefaresh_anjam_shode_oncreat");

       //  setup_recyclerview();

        return view;
    }

    private void initial_view()
    {
        txt_sefarsh_anjam_shode=view.findViewById(R.id.txt_f_list_sefaresh_anjam_shode);
        recyclerview=view.findViewById(R.id.recycler_listsefarsh_anjamshodeh);
        nestedScrollView=view.findViewById(R.id.nesscrool_in_f_sefanjamshode);
    }


    @Override
    public void onResume() {
        super.onResume();
         initial_view();
        //getListSefarshAnjamShode(Const_Variable.VAZEIAT_SEFARSH_ANJAM_SHODE,MotakhasesObject.id,MotakhasesObject.token,Const_Variable.NUMBER_OF_ROWS_FOR_FETCH_FROM_SERVER);
      //  Toast.makeText(getContext(), "anjamshde", Toast.LENGTH_SHORT).show();
        Log.i("list_sefaresh","list_sefaresh_anjam_shode_onresum");
    }

    public void list_sefarsh_anjam_shodeh(List<Model_List_Sefaresh_Anjam_Shodeh> list_anjam_shode) {


        if(list_anjam_shode.size()>0)
        {


            List<Object> objectList=new ArrayList<>();
            objectList.clear();
            for(int i=0;i<list_anjam_shode.size();i++)
            {
                objectList.add(list_anjam_shode.get(i));
               // list_anjam_shode.get(i).setId_sefaresh();
                 Log.i("list_sef_anjam",list_anjam_shode.get(i).getId_sefaresh());
            }
            Recycler_Viewholder_Adapter recycler_viewholder_adapter = new
                    Recycler_Viewholder_Adapter(objectList,getContext());

            LinearLayoutManager gridLayoutManager=new LinearLayoutManager(getContext());

            recyclerview.setLayoutManager(gridLayoutManager);


            recyclerview.setAdapter(recycler_viewholder_adapter);
            nestedScrollView.setVisibility(View.VISIBLE);
            txt_sefarsh_anjam_shode.setVisibility(View.INVISIBLE);
        }
        else
        {

            nestedScrollView.setVisibility(View.INVISIBLE);
            txt_sefarsh_anjam_shode.setVisibility(View.VISIBLE);
            txt_sefarsh_anjam_shode.setText(temp_text_when_list_is_empaty);

        }

    }

}

