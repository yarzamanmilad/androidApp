package com.example.khialerahat.experts_khialerahat;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.khialerahat.experts_khialerahat.List_Sefareshha.Interface_List_Sefarshha;
import com.example.khialerahat.experts_khialerahat.List_Sefareshha.Model_Jozeiat_Sefarsh_Anjamshode;
import com.example.khialerahat.experts_khialerahat.List_Sefareshha.Model_List_Sefaresh_Anjam_Shodeh;
import com.example.khialerahat.experts_khialerahat.List_Sefareshha.Model_Sefaresh_laghv_Shodeh;
import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.Web_Service.Valley_Api;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class A_Jozeiat_Sefaresh_Anjamshode extends AppCompatActivity implements Interface_List_Sefarshha {
String num_id;
TextView[] txt_view=new TextView[9];
CircleImageView back;
ProgressBar progressBar;
NestedScrollView nestedScrollView;
Valley_Api valley_api=new Valley_Api(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_jozeiat_list_sefaresh_anjamshode);
        txt_view[0]=findViewById(R.id.txt_zamane_sefaresh_anjamshode);
        txt_view[1]=findViewById(R.id.txt_address_sefaresh_anjamshode);
        txt_view[2]=findViewById(R.id.txt_khadamat_darkhasti_anjamshodeh);

        txt_view[3]=findViewById(R.id.txt_hazineh_khadamat_anjamshode);
        txt_view[4]=findViewById(R.id.txt_takhfif_anjamshode);
        txt_view[5]=findViewById(R.id.txt_mizan_daryafti_sef_anjamshode);

        txt_view[6]=findViewById(R.id.txt_sahme_motakhases_sefanjamshode);
        txt_view[7]=findViewById(R.id.txt_noeh_pardakht_hazine_sefanjamshode);
        txt_view[8]=findViewById(R.id.txt_vazeiat_pardakht_beh_motakhases_sefanjamshode);
        nestedScrollView=findViewById(R.id.nestedScrollView_sef_anjamshode);
      //  nestedScrollView.setVisibility(View.INVISIBLE);
        back=findViewById(R.id.close_viewmodel_list_sefaresh);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        String order_id = intent.getStringExtra("order_id");
        valley_api.get_JozeiatSefarshAnjamShode(order_id, MotakhasesObject.token);
       progressBar=findViewById(R.id.jozeiat_sefarsh_anjamshode_progressBar2);
       progressBar.setVisibility(View.VISIBLE);



    }

    @Override
    public void list_sefarsh_laghv_shodeh(List<Model_Sefaresh_laghv_Shodeh> list_laghv_shode) {

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
        txt_view[0].setText(jozeiat_sefarsh_anjamshode.getOrder_time());
        txt_view[1].setText(jozeiat_sefarsh_anjamshode.getOrder_address());
        txt_view[2].setText(jozeiat_sefarsh_anjamshode.getOrder_services());

        txt_view[3].setText(jozeiat_sefarsh_anjamshode.getOrder_price());
        txt_view[4].setText(jozeiat_sefarsh_anjamshode.getOrder_discount_percent());
        txt_view[5].setText(jozeiat_sefarsh_anjamshode.getFinal_price());
        txt_view[6].setText(jozeiat_sefarsh_anjamshode.getMy_share());
        txt_view[7].setText(jozeiat_sefarsh_anjamshode.getPayment_methode());
        txt_view[8].setText(jozeiat_sefarsh_anjamshode.getWallet_status());
        if (txt_view[7].getText().toString().contains("آنلاین"))
        {
            txt_view[7].setTextColor(getResources().getColor(R.color.colorPrimary));
        }else
            {
                txt_view[7].setTextColor(getResources().getColor(R.color.red));
            }


        if (txt_view[8].getText().toString().contains("افزایش"))
        {
            txt_view[8].setTextColor(getResources().getColor(R.color.colorPrimary));
        }else
        {
            txt_view[8].setTextColor(getResources().getColor(R.color.red));
        }
        nestedScrollView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
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
}
