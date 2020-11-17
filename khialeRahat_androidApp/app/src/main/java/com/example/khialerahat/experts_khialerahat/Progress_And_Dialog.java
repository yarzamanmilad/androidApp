package com.example.khialerahat.experts_khialerahat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.khialerahat.experts_khialerahat.List_Sefareshha.A_NewOrder_Details;
import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.A_Greftan_Aks_Madarek_Motakhases;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.A_Safhe_vrod_sabtenam;
import com.github.ybq.android.spinkit.SpinKitView;

public class Progress_And_Dialog
{

    Context  context;
    private AlertDialog alert_dialog;
    private SpinKitView spinKitView;
    public Progress_And_Dialog(Context context, SpinKitView spinKitView) {
        this.context = context;
        this.spinKitView = spinKitView;
    }
    public Progress_And_Dialog(Context context, SpinKitView spinKitView,AlertDialog alertDialog) {
        this.context = context;
        this.spinKitView = spinKitView;
        this.alert_dialog = alert_dialog;
    }
    public Progress_And_Dialog(Context context, AlertDialog alert_dialog) {
        this.context = context;
        this.alert_dialog = alert_dialog;

    }
    public Progress_And_Dialog(Context context) {
        this.context = context;
    }
    public  void   show_progress()
    {

      // LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
     //  View progress_layout = inflater.inflate(R.layout.progress_layout, null);
      //  spinKitView =progress_layout.findViewById(R.id.progress_in_lay)
        if(spinKitView!=null)
         spinKitView.setVisibility(View.VISIBLE);
    }
    public  void   dismis_progress()
    {

        spinKitView.setVisibility(View.INVISIBLE);
    }
    public void alert_dialog_method(  String error, String title, String message)
    {
        switch (error)
        {
            case "error":


                Log.i("username_athu",message);
                LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialoglayout = inflater.inflate(R.layout.alert_dialog, null);
                TextView dialog_title=dialoglayout.findViewById(R.id.codemeli_dialog_txt_ersalmojadad);
                dialog_title.setText(title);
                TextView dialog_body=dialoglayout.findViewById(R.id.codemeli_dialog_txt_virayesh_shomareh);
                dialog_body.setText(message);
                Button button=dialoglayout.findViewById(R.id.codemeli_dialog_btn_taaid);

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                alert_dialog=builder.create();
                alert_dialog.setView(dialoglayout);
                alert_dialog.show();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert_dialog.dismiss();

                    }
                });

                break;
            case "ok":

                LayoutInflater inflater1 = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialoglayout1 = inflater1.inflate(R.layout.alert_dialog_ok, null);
                TextView dialog_title1=dialoglayout1.findViewById(R.id.ok_dialog_txt_ersalmojadad);
                dialog_title1.setText(title);
                TextView dialog_body1=dialoglayout1.findViewById(R.id.ok_dialog_txt_virayesh_shomareh);
                dialog_body1.setText(message);
                Button button1=dialoglayout1.findViewById(R.id.ok_dialog_btn_taaid);

                final AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                alert_dialog=builder1.create();
                alert_dialog.setView(dialoglayout1);
                alert_dialog.show();
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert_dialog.dismiss();

                    }
                });

                break;
            default:
        }

    }
    public void alert_dialog_register_success_full(final String expert_id  , String title, String message)
    {
        LayoutInflater inflater1 = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialoglayout1 = inflater1.inflate(R.layout.alert_dialog_ok, null);
        TextView dialog_title1=dialoglayout1.findViewById(R.id.ok_dialog_txt_ersalmojadad);
        dialog_title1.setText(title);
        TextView dialog_body1=dialoglayout1.findViewById(R.id.ok_dialog_txt_virayesh_shomareh);
        dialog_body1.setText(message);
        Button button1=dialoglayout1.findViewById(R.id.ok_dialog_btn_taaid);

        final AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        alert_dialog=builder1.create();
        alert_dialog.setView(dialoglayout1);
        alert_dialog.show();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert_dialog.dismiss();
                Intent intent=new Intent(context, A_Safhe_vrod_sabtenam.class);
                intent.putExtra("id",expert_id);
                context.startActivity(intent);
                ((A_Greftan_Aks_Madarek_Motakhases)context).finish();

            }
        });

    }
    public void failure_in_splash_Activit_atuontication(  String error, String title, String message)
    {

                Log.i("username_athu",message);
                LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialoglayout = inflater.inflate(R.layout.alert_dialog, null);
                TextView dialog_title=dialoglayout.findViewById(R.id.codemeli_dialog_txt_ersalmojadad);
                dialog_title.setText(title);
                TextView dialog_body=dialoglayout.findViewById(R.id.codemeli_dialog_txt_virayesh_shomareh);
                dialog_body.setText(message);
                Button button=dialoglayout.findViewById(R.id.codemeli_dialog_btn_taaid);

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                alert_dialog=builder.create();
                alert_dialog.setView(dialoglayout);
                alert_dialog.show();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alert_dialog.dismiss();
                        ((SplashActivity)context).finish();

                    }
                });



    }
    public void end_work_and_payment_type_is_naghdi(  String error, String title, String message)
    {

        Log.i("end_work_and_payment",message);
        LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialoglayout = inflater.inflate(R.layout.alert_dialog, null);
        TextView dialog_title=dialoglayout.findViewById(R.id.codemeli_dialog_txt_ersalmojadad);
        dialog_title.setText(title);
        TextView dialog_body=dialoglayout.findViewById(R.id.codemeli_dialog_txt_virayesh_shomareh);
        dialog_body.setText(message);
        Button button=dialoglayout.findViewById(R.id.codemeli_dialog_btn_taaid);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        alert_dialog=builder.create();
        alert_dialog.setView(dialoglayout);
        alert_dialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 alert_dialog.dismiss();
               // ((A_Jozeiat_Sefarsh_Darhal_Anjam)context).final_update_when_work_is_finish();

                Intent intent=new Intent(context, MainActivityy.class);
                context.startActivity(intent);
                Log.i("final_update_when_","start in progres class");

            }
        });

    }


    public void end_work_and_payment_type_is_onlin_pardakht_nashode(  String error, String title, String message)
    {

        Log.i("end_work_and_payment",message);
        LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialoglayout = inflater.inflate(R.layout.alert_dialog, null);
        TextView dialog_title=dialoglayout.findViewById(R.id.codemeli_dialog_txt_ersalmojadad);
        dialog_title.setText(title);
        TextView dialog_body=dialoglayout.findViewById(R.id.codemeli_dialog_txt_virayesh_shomareh);
        dialog_body.setText(message);
        Button button=dialoglayout.findViewById(R.id.codemeli_dialog_btn_taaid);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        alert_dialog=builder.create();
        alert_dialog.setView(dialoglayout);
        alert_dialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert_dialog.dismiss();
                // ((A_Jozeiat_Sefarsh_Darhal_Anjam)context).final_update_when_work_is_finish();
                Log.i("final_update_when_","start in progres class");

            }
        });

    }

    public void end_work_and_payment_type_is_online_pardakht_shode(  String error, String title, String message)
    {

        Log.i("end_work_and_payment",message);
        LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialoglayout = inflater.inflate(R.layout.alert_dialog_ok, null);
        TextView dialog_title=dialoglayout.findViewById(R.id.ok_dialog_txt_ersalmojadad);
        dialog_title.setText(title);
        TextView dialog_body=dialoglayout.findViewById(R.id.ok_dialog_txt_virayesh_shomareh);
        dialog_body.setText(message);
        Button button=dialoglayout.findViewById(R.id.ok_dialog_btn_taaid);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        alert_dialog=builder.create();
        alert_dialog.setView(dialoglayout);
        alert_dialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert_dialog.dismiss();
                // ((A_Jozeiat_Sefarsh_Darhal_Anjam)context).final_update_when_work_is_finish();

                Intent intent=new Intent(context, MainActivityy.class);
                context.startActivity(intent);
                Log.i("final_update_when_","start in progres class");

            }
        });

    }


    public void new_order_not_accept_alertdialog(final String error, String title, String message)
    {

        Log.i("username_athu",message);
        final LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialoglayout = inflater.inflate(R.layout.alert_dialog, null);
        TextView dialog_title=dialoglayout.findViewById(R.id.codemeli_dialog_txt_ersalmojadad);
        dialog_title.setText(title);
        TextView dialog_body=dialoglayout.findViewById(R.id.codemeli_dialog_txt_virayesh_shomareh);
        dialog_body.setText(message);
        Button button=dialoglayout.findViewById(R.id.codemeli_dialog_btn_taaid);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        alert_dialog=builder.create();
        alert_dialog.setView(dialoglayout);
        alert_dialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(error.contentEquals("true"))
                {
                    alert_dialog.dismiss();
                    ((A_NewOrder_Details)context).finish();
                    Intent intent=new Intent(context, MainActivityy.class);

                    if(!MotakhasesObject.temp_new_order_list.contentEquals("0"))
                        intent.putExtra("new_order", MotakhasesObject.temp_new_order_list);
                   // intent.putExtra("new_order", MotakhasesObject.new_order_id);
                    context.startActivity(intent);
                    Log.i("btn_accept_or_notaccpt","error  true in progress and dialog error=tru1"+MotakhasesObject.temp_new_order_list);
                }
                else if(error.contentEquals("true1"))
                {

                    alert_dialog.dismiss();

                    //  Intent intent=new Intent(context,MainActivityy.class);
                    //  context.startActivity(intent);
                    ((A_NewOrder_Details)context).finish();
                    Intent intent=new Intent(context, MainActivityy.class);
                    if(!MotakhasesObject.temp_new_order_list.contentEquals("0"))
                    intent.putExtra("new_order", MotakhasesObject.temp_new_order_list);
                    context.startActivity(intent);
                    Log.i("btn_accept_or_notaccpt","in progress and dialog error=tru1"+MotakhasesObject.temp_new_order_list);
                }
                else
                {
                    alert_dialog.dismiss();

                  //  Intent intent=new Intent(context,MainActivityy.class);
                  //  context.startActivity(intent);
                    ((A_NewOrder_Details)context).finish();
                    Intent intent=new Intent(context, MainActivityy.class);
                    if(!MotakhasesObject.temp_new_order_list.contentEquals("0"))
                    intent.putExtra("new_order", MotakhasesObject.new_order_id);
                    context.startActivity(intent);
                    Log.i("btn_accept_or_notaccpt","in progress and dialog error=false"+MotakhasesObject.new_order_id);
                }

            }
        });



    }
    public void failure_in_vrod_sabtenam(  String error, String title, String message)
    {

        Log.i("username_athu",message);
        LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialoglayout = inflater.inflate(R.layout.alert_dialog, null);
        TextView dialog_title=dialoglayout.findViewById(R.id.codemeli_dialog_txt_ersalmojadad);
        dialog_title.setText(title);
        TextView dialog_body=dialoglayout.findViewById(R.id.codemeli_dialog_txt_virayesh_shomareh);
        dialog_body.setText(message);
        Button button=dialoglayout.findViewById(R.id.codemeli_dialog_btn_taaid);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        alert_dialog=builder.create();
        alert_dialog.setView(dialoglayout);
        alert_dialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alert_dialog.dismiss();
                ((A_Safhe_vrod_sabtenam)context).finish();

            }
        });



    }
    public void failure_in_update_pushid(  String error, String title, String message)
    {

        Log.i("username_athu",message);
        LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialoglayout = inflater.inflate(R.layout.alert_dialog, null);
        TextView dialog_title=dialoglayout.findViewById(R.id.codemeli_dialog_txt_ersalmojadad);
        dialog_title.setText(title);
        TextView dialog_body=dialoglayout.findViewById(R.id.codemeli_dialog_txt_virayesh_shomareh);
        dialog_body.setText(message);
        Button button=dialoglayout.findViewById(R.id.codemeli_dialog_btn_taaid);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        alert_dialog=builder.create();
        alert_dialog.setView(dialoglayout);
        alert_dialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alert_dialog.dismiss();
                ((MainActivityy)context).finish();

            }
        });



    }

    public void show_f_message_dialog(String title, String body)
    {

        Log.i("show_f_message_dialog",title);
        LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialoglayout = inflater.inflate(R.layout.alert_dialog_f_message, null);
        TextView dialog_title=dialoglayout.findViewById(R.id.fmessage_dialog_txttitle);
        dialog_title.setText(title);
        TextView dialog_body=dialoglayout.findViewById(R.id.fmessage_dialog_txtbody);
        dialog_body.setText(body);
        Button button=dialoglayout.findViewById(R.id.fmessage_dialog_btn_taaid);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        alert_dialog=builder.create();
        alert_dialog.setView(dialoglayout);
        alert_dialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alert_dialog.dismiss();


            }
        });

    }
    public void versioncontrol_dialog(String title, String message)
    {

        Log.i("versioncontrol_dialog",message);
        LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialoglayout = inflater.inflate(R.layout.alert_dialog_version_control, null);
        TextView dialog_title=dialoglayout.findViewById(R.id.codemeli_dialog_txt_ersalmojadad_version_control);
        dialog_title.setText(title);
        TextView dialog_body=dialoglayout.findViewById(R.id.codemeli_dialog_txt_virayesh_shomareh_version_control);
        dialog_body.setText(message);
        Button button=dialoglayout.findViewById(R.id.codemeli_dialog_btn_taaid_version_control);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        alert_dialog=builder.create();
        alert_dialog.setView(dialoglayout);
        alert_dialog.show();
        button.setText("بستن");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    System.exit(0);

            }
        });
    }
    public void connection_error_dialog(String title, String message)
    {        Log.i("connection_error_dialog",message);
        LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialoglayout = inflater.inflate(R.layout.alert_dialog, null);
        TextView dialog_title=dialoglayout.findViewById(R.id.codemeli_dialog_txt_ersalmojadad);
        dialog_title.setText(title);
        TextView dialog_body=dialoglayout.findViewById(R.id.codemeli_dialog_txt_virayesh_shomareh);
        dialog_body.setText(message);
        Button button=dialoglayout.findViewById(R.id.codemeli_dialog_btn_taaid);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        alert_dialog=builder.create();
        alert_dialog.setView(dialoglayout);
        alert_dialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}
