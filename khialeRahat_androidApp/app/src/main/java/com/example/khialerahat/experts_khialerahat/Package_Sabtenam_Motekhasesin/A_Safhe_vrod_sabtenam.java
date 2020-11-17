package com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khialerahat.experts_khialerahat.MainActivityy;
import com.example.khialerahat.experts_khialerahat.MainPackage.Model_Profile;
import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.My_interface;
import com.example.khialerahat.experts_khialerahat.Progress_And_Dialog;
import com.example.khialerahat.experts_khialerahat.R;
import com.example.khialerahat.experts_khialerahat.Web_Service.Valley_Api;
import com.github.ybq.android.spinkit.SpinKitView;

import java.io.Serializable;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class A_Safhe_vrod_sabtenam extends AppCompatActivity implements My_interface, View.OnClickListener {
    Valley_Api valley_api=new Valley_Api(this);
    int number_repeat_try_to_getData_userAthu=0;
    int number_repeat_try_to_getData_passAthu=0;
    int number_repeat_try_to_getData_listostan=0;
    List<String> list_ostanha;
    Intent intent;
    Button sabtnam,vrod;
    private EditText edtxt_codetaied;
    TextInputLayout txtinplay_username,txtinplay_password;
    CircleImageView cimgv_avatar;
    TextInputEditText username,password;
    CheckBox rememberme;
    Boolean username_status=false;
    Boolean password_status=false;
    private AlertDialog alert_register_status;
    private AlertDialog alert_dialog;

    SpinKitView spinKitView;
    AlertDialog alertDialog;
    Progress_And_Dialog progress_and_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_safhe_vrod);
        spinKitView=findViewById(R.id.progress_in_lay_vrod_sabtnam);
        progress_and_dialog=new Progress_And_Dialog(this,spinKitView);
        txtinplay_username=findViewById(R.id.txtinplat_username);
        txtinplay_password=findViewById(R.id.txtinplat_password);
        sabtnam=findViewById(R.id.signUp1);
        vrod=findViewById(R.id.login);
        sabtnam.setEnabled(false);
        sabtnam.setOnClickListener(this);
        vrod.setOnClickListener(this);
        vrod.setEnabled(false);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        rememberme=findViewById(R.id.rememberMe);
        if (Permision_Utility.check_permision(A_Safhe_vrod_sabtenam.this,"recive_sms"))
        {


        }
        else
        {
            Permision_Utility.requst_permision(A_Safhe_vrod_sabtenam.this,"recive_sms");
        }
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    if(username.getText().length()==10)
                    {
                        valley_api.username_athuntication(username.getText().toString());
                        progress_and_dialog.show_progress();

                    }
                    else
                        {
                            alert_dialog_method("error","نام کاربری نامعتبر","نام کاربری باید ده رقمی باشد");
                            //username.requestFocus();
                        }
                }
                else {

                }
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus&&  !username_status)
                {

                   username.requestFocus();


                }
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String id_motakhases = preferences.getString("id_motakhases", null);
        if(id_motakhases!=null)
        {
            sabtnam.setEnabled(false);
            vrod.setEnabled(true);
        }
        else
        {
            valley_api.get_listostanha_asserver();
            progress_and_dialog.show_progress();

        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        Log.i("permisionRequest",grantResults[0]+ ""+requestCode);
        switch (requestCode)
        {
            case Permision_Utility.getRequst_code_recive_sms:
                if(grantResults.length>0)
                {
                    if(grantResults[0]== PackageManager.PERMISSION_GRANTED)
                    {
                        /////////////   yourcode


                    }else{
                        Toast.makeText(this, "شما دسترسی لازم را ندارید", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "شما دسترسی لازم را ندارید...", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.login:
                Log.i("username_status",String.valueOf(username_status));
             if(username_status)
             {
                      if(password.getText().length()>=6)
                      {
                          valley_api.password_athuntication(username.getText().toString()
                          ,password.getText().toString());
                          progress_and_dialog.show_progress();
                      }
                      else
                      {
                          password.requestFocus();
                          alert_dialog_method("error","ورود ناموفق","پسورد اشتباه است");
                      }

             }
             else
             {
                 username.requestFocus();
             }



                break;
            case R.id.signUp1:


               intent=new Intent(getBaseContext(), A_Sabtnam.class);
               intent.putExtra("list_ostanha", (Serializable) list_ostanha);
                startActivity(intent);
                finish();

                break;
        }

    }

    @Override
    public void recive_data_from_server_failur(String error_status, String title_error, String error_message)
    {
        progress_and_dialog.dismis_progress();
        switch (title_error)
        {
            case Const_Variable.GET_LIST_OSTANHA_ERROR:


                if(number_repeat_try_to_getData_listostan< Const_Variable.MAX_LENGTH_REPEAT_TRAY_TO_GET_DATA)
                {
                    number_repeat_try_to_getData_listostan++;
                    valley_api.get_listostanha_asserver();
                    progress_and_dialog.show_progress();
                }
                else
                {

                    progress_and_dialog=new Progress_And_Dialog(this,alertDialog);
                    progress_and_dialog.failure_in_vrod_sabtenam(error_status,title_error,error_message);
                }
                break;
            case Const_Variable.PASSWORD_ATHUNTICATION_EEROR:

                if(number_repeat_try_to_getData_passAthu< Const_Variable.MAX_LENGTH_REPEAT_TRAY_TO_GET_DATA)
                {
                    number_repeat_try_to_getData_passAthu++;

                    valley_api.password_athuntication(username.getText().toString()
                            ,password.getText().toString());
                    progress_and_dialog.show_progress();
                }
                else
                {

                    progress_and_dialog=new Progress_And_Dialog(this,alertDialog);
                    progress_and_dialog.failure_in_vrod_sabtenam(error_status,title_error,error_message);
                }
                break;
            case Const_Variable.USERNAME_ATHUNTICATION_EEROR:

                if(number_repeat_try_to_getData_userAthu< Const_Variable.MAX_LENGTH_REPEAT_TRAY_TO_GET_DATA)
                {
                    number_repeat_try_to_getData_userAthu++;

                    valley_api.username_athuntication(username.getText().toString());
                    progress_and_dialog.show_progress();
                }
                else
                {

                    progress_and_dialog=new Progress_And_Dialog(this,alertDialog);
                    progress_and_dialog.failure_in_vrod_sabtenam(error_status,title_error,error_message);
                }

                break;
            default:
        }


    }
    @Override
    public void username_athuntication(String status_username) {
        progress_and_dialog.dismis_progress();
        if(status_username==Const_Variable.IS_OK)
        {
            username.setEnabled(false);
            txtinplay_username.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious));
            username_status=true;
            password.requestFocus();
        }
        else
        {
            username.requestFocus();
            username_status=false;
            alert_dialog_method("error","نام کاربری نامعتبر","این کد ملی قبلا ثبت نشده است");
        }
    }

    private void alert_dialog_method(String error, String title, String message)
    {
        switch (error)
        {
            case "error":


                Log.i("username_athu",message);
                LayoutInflater inflater = getLayoutInflater();
                View dialoglayout = inflater.inflate(R.layout.alert_dialog, null);
                TextView dialog_title=dialoglayout.findViewById(R.id.codemeli_dialog_txt_ersalmojadad);
                dialog_title.setText(title);
                TextView dialog_body=dialoglayout.findViewById(R.id.codemeli_dialog_txt_virayesh_shomareh);
                dialog_body.setText(message);
                Button button=dialoglayout.findViewById(R.id.codemeli_dialog_btn_taaid);

                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
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

                LayoutInflater inflater1 = getLayoutInflater();
                View dialoglayout1 = inflater1.inflate(R.layout.alert_dialog_ok, null);
                TextView dialog_title1=dialoglayout1.findViewById(R.id.ok_dialog_txt_ersalmojadad);
                dialog_title1.setText(title);
                TextView dialog_body1=dialoglayout1.findViewById(R.id.ok_dialog_txt_virayesh_shomareh);
                dialog_body1.setText(message);
                Button button1=dialoglayout1.findViewById(R.id.ok_dialog_btn_taaid);

                final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
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

    @Override
    public void password_athuntication(MotakhasesObject motakhasesObject)
    {
        progress_and_dialog.dismis_progress();
        if(motakhasesObject.getId()!=null &&
                motakhasesObject.getVazeiat_sabtnam().contentEquals(Const_Variable.REGISTER_STATUS_IS_COMPLETE))
        {
            password.setEnabled(false);


            if(rememberme.isChecked())
            {

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("secure_checkbox","ischecked");
                editor.putString("username",username.getText().toString());
                editor.putString("password",password.getText().toString());
                editor.apply();

             }


            intent=new Intent(getBaseContext(), MainActivityy.class);
            startActivity(intent);
            finish();

        }
        else if(motakhasesObject.getVazeiat_sabtnam().contentEquals(Const_Variable.REGISTER_STATUS_IS_DARHALE_BARRASI))
        {

            LayoutInflater inflater = getLayoutInflater();
            View dialoglayout = inflater.inflate(R.layout.alert_dialog, null);
            TextView dialog_title=dialoglayout.findViewById(R.id.codemeli_dialog_txt_ersalmojadad);
            dialog_title.setText("در حال بررسی ");
            TextView dialog_body=dialoglayout.findViewById(R.id.codemeli_dialog_txt_virayesh_shomareh);
            dialog_body.setText("طی 24 ساعت آینده مجوز دسترسی صادر می شود");
            Button button=dialoglayout.findViewById(R.id.codemeli_dialog_btn_taaid);

            final AlertDialog.Builder builder = new AlertDialog.Builder(A_Safhe_vrod_sabtenam.this);
            alert_register_status=builder.create();
            alert_register_status.setView(dialoglayout);
            alert_register_status.show();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alert_register_status.dismiss();
                    finish();
                    System.exit(0);
                }
            });
        }
        else if(motakhasesObject.getVazeiat_sabtnam().contentEquals(Const_Variable.REGISTER_STATUS_IS_RAD_SHODEH))
        {

            LayoutInflater inflater = getLayoutInflater();
            View dialoglayout = inflater.inflate(R.layout.alert_dialog, null);
            TextView dialog_title=dialoglayout.findViewById(R.id.codemeli_dialog_txt_ersalmojadad);
            dialog_title.setText("عدم پذیرش");
            TextView dialog_body=dialoglayout.findViewById(R.id.codemeli_dialog_txt_virayesh_shomareh);
            dialog_body.setText("متاسفانه در خواست همکاری شما توسط منابع انسانی رد شده است");
            Button button=dialoglayout.findViewById(R.id.codemeli_dialog_btn_taaid);

            final AlertDialog.Builder builder = new AlertDialog.Builder(A_Safhe_vrod_sabtenam.this);
            alert_register_status=builder.create();
            alert_register_status.setView(dialoglayout);
            alert_register_status.show();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alert_register_status.dismiss();
                    finish();
                }
            });
        }
        else
        {
          ///  Toast.makeText(this, "پسورد وارد شده اشتباه است", Toast.LENGTH_SHORT).show();
            Log.i("vazeiat_sabtenam",motakhasesObject.getVazeiat_sabtnam()+":"+Const_Variable.REGISTER_STATUS_IS_RAD_SHODEH);
            password.requestFocus();
            txtinplay_password.setBackground(getResources().getDrawable(R.drawable.shap_with_strok_and_radious_ghermez));
            alert_dialog_method("error","ورود ناموفق","پسورد اشتباه است");
        }
    }

    @Override
    public void greftan_list_ostanha(List<String> list_ostanha)
    {
        progress_and_dialog.dismis_progress();
        if (!list_ostanha.get(0).contains("nodata")) {
            this.list_ostanha = list_ostanha;
            vrod.setEnabled(true);
            sabtnam.setEnabled(true);
        }
        else
        {
            Log.i("nodata_listostanha","nodata");
        }
    }
    @Override
    public void txtfilter(Boolean harfe_vrodi_sahih_ast, int textInputLayout, int edtxt_id) {

    }

    @Override
    public void greftan_list_manategh_entkhabi_as_recycler_view_adapter(String manategh_ya_takhasos,int item_position, int meghdar) {

    }

    @Override
    public void greftan_list_shahrha(List<String> list_shahrha, List<String> list_id_shahrha) {

    }


    @Override
    public void greftan_list_manategh(List<String> list_manategh) {

    }

    @Override
    public void greftan_list_takhasos(Model_Takhasos[] list_takhasos) {

    }

    @Override
    public void greftan_list_takhasos_level2(Model_Takhasos_level2[] list_takhasos_level2) {

    }

    @Override
    public void greftan_list_times(Model_Time[] list_time) {

    }

    @Override
    public void greftan_id_motakhases(String id) {

    }

    @Override
    public void upload_status(Boolean status, String feild_name) {

    }


    @Override
    public void get_profile_data(Model_Profile model_profile) {

    }

    @Override

    public void greftan_list_manategh_entkhabi_have_childeren_as_recycler_view_adapter
            (String takhasos_name, int index,  boolean chtxt_ischecked){

}

    @Override
    public void failure_in_athunticaation(boolean boolean_exit_or_repeat) {

    }

    @Override
    public void get_order_payment_type_from_server(String payment_type, String name) {

    }

    @Override
    public void c1(String c) {

    }

    @Override
    public void a1(String a) {

    }

    @Override
    public void b11(String b) {

    }

    @Override
    public void c11(String c) {

    }

    @Override
    public void a11(String a) {

    }
}

