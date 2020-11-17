package com.example.khialerahat.experts_khialerahat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import co.ronash.pushe.Pushe;

import com.example.khialerahat.experts_khialerahat.MainPackage.Model_Profile;
import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.A_Safhe_vrod_sabtenam;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Const_Variable;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Model_Takhasos;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Model_Takhasos_level2;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Model_Time;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Permision_Utility;
import com.example.khialerahat.experts_khialerahat.Web_Service.Valley_Api;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.List;

public class SplashActivity extends AppCompatActivity implements My_interface {
Valley_Api valley_api=new Valley_Api(this);
String password,username;
   SpinKitView spinKitView;

   AlertDialog  alertDialog;
   Progress_And_Dialog progress_and_dialog;
   int number_of_repeat=0;
    private String version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
         spinKitView=findViewById(R.id.pross);
         progress_and_dialog=new Progress_And_Dialog(this,spinKitView);

        Pushe.initialize(this,true);
        Log.i("push",Pushe.getPusheId(this));
       // MotakhasesObject.pushid=Pushe.getPusheId(this);
        try {
            PackageInfo pInfo = getBaseContext().getPackageManager().getPackageInfo(getPackageName(), 0);
             version = pInfo.versionName;
            Log.i("version",version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        valley_api.version_control(version);
        progress_and_dialog.show_progress();
    }
     void main_operation()
     {

         SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());

         final String secure_checkbox = preferences.getString("secure_checkbox", null);





         if (secure_checkbox!=null&&secure_checkbox.contentEquals("ischecked")){

             password=preferences.getString("password",null);
             username=preferences.getString("username",null);

             if(password!=null&&username!=null)
             {

                 valley_api.password_athuntication(username,password);
                 progress_and_dialog.show_progress();
             }
             else
             {

                 Intent i=new Intent(SplashActivity.this, A_Safhe_vrod_sabtenam.class);
                 startActivity(i);
                 finish();
                 Log.i("rememberme:","ischecked usernam or pass   null");
             }

         }
         else
         {

             Log.i("rememberme:","is not checked or  null");
             Intent i=new Intent(SplashActivity.this, A_Safhe_vrod_sabtenam.class);
             startActivity(i);
             finish();

         }
     }


     public void error_get_version_control_result()
     {
         progress_and_dialog.dismis_progress();
         if(number_of_repeat<Integer.parseInt(Const_Variable.NUMBER_OF_ROWS_FOR_FETCH_FROM_SERVER))
         {
            valley_api.version_control(version);
            progress_and_dialog.show_progress();
            number_of_repeat++;
         }
         else
         {
             progress_and_dialog.connection_error_dialog("خطا در الصال","خطا در اتصال به سرور");
         }
     }
    public void get_version_control_result(String status)
    {
        progress_and_dialog.dismis_progress();
        if(status.contentEquals("true"))
        {
              main_operation();
        }
        else
        {
            progress_and_dialog.versioncontrol_dialog("بروز رسانی","لطفا آخرین ورژن برنامه رو نصب کنید");
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
    public void password_athuntication(MotakhasesObject motakhasesObject)
    {
        progress_and_dialog.dismis_progress();
        if(motakhasesObject.getId()!=null)
        {
             Intent i=new Intent(SplashActivity.this, MainActivityy.class);
            startActivity(i);
            finish();
        }else
        {
            Log.i("rememberme:","ischecked usernam and password is set    but vollyapi  return null object"+username+password);
            Intent i=new Intent(SplashActivity.this,A_Safhe_vrod_sabtenam.class);
            startActivity(i);
            finish();
        }

    }

    @Override
    public void recive_data_from_server_failur(String error_status, String title_error, String error_message) {
        progress_and_dialog.dismis_progress();
        if(number_of_repeat< Const_Variable.MAX_LENGTH_REPEAT_TRAY_TO_GET_DATA)
        {
            valley_api.password_athuntication(username,password);
            progress_and_dialog.show_progress();
        }
        else
        {

            progress_and_dialog=new Progress_And_Dialog(this,alertDialog);
            progress_and_dialog.failure_in_splash_Activit_atuontication(error_status,title_error,error_message);
        }

    }

    @Override
    public void txtfilter(Boolean harfe_vrodi_sahih_ast, int textInputLayout, int edtxt_id) {

    }

    @Override
    public void greftan_list_manategh_entkhabi_as_recycler_view_adapter(String manategh_ya_takhasos, int item_position, int meghdar) {

    }

    @Override
    public void greftan_list_shahrha(List<String> list_shahrha, List<String> list_id_shahrha) {

    }

    @Override
    public void greftan_list_ostanha(List<String> list_ostanha) {

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
    public void username_athuntication(String status_username) {

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

