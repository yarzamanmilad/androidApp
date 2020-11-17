package com.example.khialerahat.experts_khialerahat.List_Sefareshha;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
//import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Const_Variable;
import com.example.khialerahat.experts_khialerahat.Progress_And_Dialog;
import com.example.khialerahat.experts_khialerahat.R;
import com.example.khialerahat.experts_khialerahat.S_Security_Panel;
import com.example.khialerahat.experts_khialerahat.Web_Service.Valley_Api;
import com.github.ybq.android.spinkit.SpinKitView;

public class A_Jozeiat_Sefarsh_Darhal_Anjam extends AppCompatActivity {
    Valley_Api valley_api;
    String order_id;
    int number_of_repeat_when_update_finish_error=0;
    int number_of_repeat_when_send_time_to_server_error=0;
    TextView txt_sefarsh_jadid_vjod_nadarad
            ,txtv_zaman,txtv_address_name,txtv_services,txtv_total_price
            ,txtv_discont,txtv_final_price,txtv_myshred_amount,txtv_payment_status,txtv_payment_type;
    View view;

  //  ConstraintLayout constraintLayout;
    NestedScrollView nestedScrollView;
    Context myContext;
    Button btn_end,btn_start_job;
    private TextView txtv_wallet_status;
    private Progress_And_Dialog progress_and_dialog;
    AlertDialog alertDialog;
    Model_Sefaresh_Jadid model_sefaresh_jadid;
    String order_id_final;
    SpinKitView spinKitView;

    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a__jozeiat__sefarsh__darhal_anjam);
        initials_view();
        progress_and_dialog=new Progress_And_Dialog(this,spinKitView,alertDialog);

        nestedScrollView.setVisibility(View.INVISIBLE);
        Log.i("list_sefaresh","list_sefaresh_jadid_shode_oncreat");


        btn_end_onclick();
        btn_start_job_onclick();

        valley_api=new Valley_Api(this);
        order_id=getIntent().getStringExtra("order_id");
        if(order_id!=null)
        {
            if(order_id.contentEquals(""))
            {

            }
            else
            {
                valley_api.get_new_order_details(order_id);
                progress_and_dialog.show_progress();
            }
        }
        else
        {}
    }

    private void btn_start_job_onclick()
    {
        btn_start_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              progress_and_dialog.show_progress();
              valley_api.send_time_to_server(order_id,"1");
              btn_end.setEnabled(true);

              btn_start_job.setEnabled(false);
              btn_start_job.setVisibility(View.GONE);



            }
        });
    }

    private void btn_end_onclick()
    {
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isMyServiceRunning(S_Security_Panel.class))
                {
                    Intent intent=new Intent(getBaseContext(),S_Security_Panel.class);
                    stopService(intent);
                }
              final_update_when_work_is_finish();



            }
        });
    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
   public void response_send_time_to_server()
   {
       progress_and_dialog.dismis_progress();
       if(model_sefaresh_jadid.getSecurity_panel().contentEquals(Const_Variable.SERVICE_HAVE_SECURITY_PANEL))
       {
           Intent intent=new Intent(this, S_Security_Panel.class);
           intent.putExtra("order_id",order_id);
           //intent.putExtra("expert_id",MotakhasesObject.id);
           intent.putExtra("expert_name_family",MotakhasesObject.name+" "+MotakhasesObject.family);
              startService(intent);
              finish();
       }
       else
       {}
   }
    public void error_response_send_time_to_server()
    {
        progress_and_dialog.dismis_progress();
        if (number_of_repeat_when_send_time_to_server_error<Const_Variable.MAX_LENGTH_REPEAT_TRAY_TO_GET_DATA)
        {
            progress_and_dialog.show_progress();
            valley_api.send_time_to_server(order_id,"1");
            number_of_repeat_when_send_time_to_server_error++;
        }
        else
        {
            Toast.makeText(this, "خطا در اتصال", Toast.LENGTH_SHORT).show();

        }
    }
    public void final_update_when_work_is_finish()
    {
        Log.i("final_update_when_","start");
        valley_api.update_final_status_work_is_finish(order_id,Const_Variable.WORK_STATUS_IS_OK,
                MotakhasesObject.id,model_sefaresh_jadid.getKhialerahat_amount()
                ,model_sefaresh_jadid.getMotakhases_amount(),model_sefaresh_jadid.getNahve_pardakht());
       progress_and_dialog.show_progress();
    }
  public void   response_update_final_status_work_is_finish( String payment_type, String payment_status , String error)
    {
        progress_and_dialog.dismis_progress();
        Log.i("final_update_when_"," response_update_final_status_work_is_finish"+payment_type+payment_status+error);

        if(error.contentEquals("false"))
        {
            if(payment_type.contentEquals(Const_Variable.PAYMENT_TYPE_IS_ONLIN) &&
                    payment_status.contentEquals(Const_Variable.PAYMENT_STATUS_IS_PARDAKHT_SHODE))
            {
                   progress_and_dialog.end_work_and_payment_type_is_online_pardakht_shode("false",
                           "پرداخت شده","مبلغ"+model_sefaresh_jadid.getMotakhases_amount()+"تومان به کیف پول شما اضافه شد");
            }
            else if(payment_type.contentEquals(Const_Variable.PAYMENT_TYPE_IS_ONLIN) &&
                    payment_status.contentEquals(Const_Variable.PAYMENT_STATUS_IS_PARDAKHT_NASHODE))
            {
                    progress_and_dialog.end_work_and_payment_type_is_onlin_pardakht_nashode("true","پرداخت آنلاین","پرداخت صورت نگرفته است ");
                    txtv_payment_status.setText(Const_Variable.PARDAKHT_NASHODE_WORD);
                    txtv_payment_status.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious_ghermez));
                    txtv_payment_type.setText(Const_Variable.ONLIN_WORD);
                txtv_payment_type.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious));
                txtv_wallet_status.setText(Const_Variable.INCREASE_KIF_POL);
                txtv_wallet_status.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious));


            }
            else
            {

                progress_and_dialog.end_work_and_payment_type_is_naghdi("","دریافت وجه نقدی",
                        "مبلغ"+model_sefaresh_jadid.getKhialerahat_amount()+"تومان از کبف پول شما کسر می شود. ");
            }
        }
        else
        {}
    }

    public void   error_update_final_status_work_is_finish()
    {
        progress_and_dialog.dismis_progress();
        if (number_of_repeat_when_update_finish_error<Const_Variable.MAX_LENGTH_REPEAT_TRAY_TO_GET_DATA)
        {

            Log.i("final_update_when_","start");
            valley_api.update_final_status_work_is_finish(order_id,Const_Variable.WORK_STATUS_IS_OK,
                    MotakhasesObject.id,model_sefaresh_jadid.getKhialerahat_amount()
                    ,model_sefaresh_jadid.getMotakhases_amount(),model_sefaresh_jadid.getNahve_pardakht());
            progress_and_dialog.show_progress();
        }
        else
        {}
    }

    private void initials_view()
    {


        //constraintLayout=findViewById(R.id.mainconslay_sefarshjadidd);
        btn_end=findViewById(R.id.btn_accept_new_orderd);
        btn_start_job=findViewById(R.id.btn_not_accept_new_orderd);
        txtv_address_name=findViewById(R.id.txt_address_sefaresh_jadidd);
        txtv_zaman=findViewById(R.id.txt_zamane_sefaresh_jadidd);
        txtv_services=findViewById(R.id.txt_khadamat_darkhasti_jadidhd);
        txtv_total_price=findViewById(R.id.txt_hazineh_khadamat_jadidd);
        txtv_discont=findViewById(R.id.txt_takhfif_jadidd);
        txtv_final_price=findViewById(R.id.txt_mizan_daryaftid);
        txtv_myshred_amount=findViewById(R.id.txt_sahme_motakhases_sefjadidd);
        txtv_payment_status=findViewById(R.id.txt_vazeiat_pardakht_sefjadidd);
        txtv_payment_type=findViewById(R.id.txt_noeh_pardakht_hazine_sefjadidd);
        txtv_wallet_status=findViewById(R.id.txt_wallet_status_sefjadidd);
        nestedScrollView=findViewById(R.id.neasted_sefarshjadidd);
        spinKitView=findViewById(R.id.progress_in_layjozeiatsefarsh);
    }

    public void sefarsh_jadid(Model_Sefaresh_Jadid model_sefaresh_jadid)
    {
        this.model_sefaresh_jadid=new Model_Sefaresh_Jadid();
        this.model_sefaresh_jadid=model_sefaresh_jadid;
        nestedScrollView.setVisibility(View.VISIBLE);

        txtv_zaman.setText(model_sefaresh_jadid.getZaman_darkhasti_khadamat());
        txtv_address_name.setText(model_sefaresh_jadid.getAddress()+"\n"+"\n"+
                model_sefaresh_jadid.getShomareh_hamrah_moshtari());

        String[] separated = model_sefaresh_jadid.getNoeh_khadamat_jozei().split("%");
        // this will contain "Fruit"
        String temp="";
        for(int x=0;x<separated.length;x++)
        {
            temp+=separated[x];
            temp+="\n";
        }
        txtv_services.setText(temp);
        txtv_total_price.setText(model_sefaresh_jadid.getHazineh());
        txtv_discont.setText(model_sefaresh_jadid.getTakhfif());
        txtv_myshred_amount.setText(model_sefaresh_jadid.getSahme_motakhases());

        if(model_sefaresh_jadid.getVazeiat_pardakht().contentEquals(Const_Variable.PAYMENT_STATUS_IS_PARDAKHT_SHODE))
        {
            txtv_payment_status.setText(Const_Variable.PARDAKHT_SHODE_WORD);
            txtv_payment_status.setTextColor(getResources().getColor(R.color.green));
        }
        else
        {
            txtv_payment_status.setText(Const_Variable.PARDAKHT_NASHODE_WORD);
            txtv_payment_status.setTextColor(getResources().getColor(R.color.red));
            txtv_payment_status.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious_ghermez));
        }
        if(model_sefaresh_jadid.getNahve_pardakht().contentEquals( Const_Variable.PAYMENT_TYPE_IS_ONLIN))
        {
            txtv_payment_type.setText(Const_Variable.ONLIN_WORD);
            txtv_payment_type.setTextColor(getResources().getColor(R.color.green));
            txtv_wallet_status.setText(Const_Variable.INCREASE_KIF_POL);
            txtv_wallet_status.setTextColor(getResources().getColor(R.color.green));
        }
        else
        {
            txtv_payment_type.setText(Const_Variable.NAGHDI_WORD);
            txtv_payment_type.setTextColor(getResources().getColor(R.color.red));
            txtv_wallet_status.setText(Const_Variable.DECREASE_KIF_POL);
            txtv_wallet_status.setTextColor(getResources().getColor(R.color.red));
            txtv_wallet_status.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious_ghermez));
            txtv_payment_type.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious_ghermez));
        }

        float float_total_price=Float.valueOf(model_sefaresh_jadid.getHazineh());
        float float_discount=Float.valueOf(model_sefaresh_jadid.getTakhfif());
        float float_final_price=float_total_price-((float_total_price*float_discount)/100);
        model_sefaresh_jadid.setMablagh_nahaei(String.valueOf(float_final_price));
        txtv_final_price.setText(String.valueOf(float_final_price));
        float float_percentage=Integer.parseInt(model_sefaresh_jadid.getSahme_motakhases());
        float float_my_shared_amount=(float_total_price*float_percentage)/100;
        txtv_myshred_amount.setText(String.valueOf(float_my_shared_amount));
        model_sefaresh_jadid.setMotakhases_percentage(String.valueOf(float_percentage));
        model_sefaresh_jadid.setMotakhases_amount(String.valueOf(float_my_shared_amount));
        model_sefaresh_jadid.setKhialerahat_percentage(String.valueOf(100-float_percentage));
        float khialerahat_shared=(float_final_price-float_my_shared_amount);
        model_sefaresh_jadid.setKhialerahat_amount(String.valueOf(khialerahat_shared));
        Log.i("float_total_price:  ",""+float_total_price);
        Log.i("float_discount:  ",""+float_discount);
        Log.i("float_final_price:  ",""+float_final_price);
        Log.i("float_percentage:  ",""+float_percentage);
        Log.i("float_my_shared_:  ",""+float_my_shared_amount);
        Log.i("float_Khialerahatp:  ",""+model_sefaresh_jadid.getKhialerahat_percentage());
        Log.i("float_Khialerahatam:  ",""+model_sefaresh_jadid.getKhialerahat_amount());
        progress_and_dialog.dismis_progress();
        if(model_sefaresh_jadid.getTow_step_finish().contentEquals(Const_Variable.SERVICE_HAVE_TOW_STEP_TOFINISH_JOB))
        {
               if(model_sefaresh_jadid.getTime_in().contentEquals(Const_Variable.JOB_NOT_START))
               {
                   btn_end.setEnabled(false);
               }
               else
               {
                   btn_start_job.setEnabled(false);
                   btn_start_job.setVisibility(View.GONE);
               }
        }
          else
        {
            btn_start_job.setVisibility(View.GONE);
        }
    }

    public void not_found_new_order()
    {

        nestedScrollView.setVisibility(View.INVISIBLE);
        txt_sefarsh_jadid_vjod_nadarad.setVisibility(View.VISIBLE);
        txt_sefarsh_jadid_vjod_nadarad.setText("سفارش جدید وجود ندارد");
        txt_sefarsh_jadid_vjod_nadarad.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious));
    }


}
