package com.example.khialerahat.experts_khialerahat;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import com.example.khialerahat.experts_khialerahat.MainPackage.Model_Profile;
import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Const_Variable;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Model_Takhasos;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Model_Takhasos_level2;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Model_Time;
import com.example.khialerahat.experts_khialerahat.Web_Service.Valley_Api;
import com.example.khialerahat.experts_khialerahat.fragments.Messages_Fragment;
import com.example.khialerahat.experts_khialerahat.fragments.Homefrg;
import com.example.khialerahat.experts_khialerahat.List_Sefareshha.F_List_Sefareshha;
import com.example.khialerahat.experts_khialerahat.fragments.Profile_Fragment;
import com.example.khialerahat.experts_khialerahat.fragments.Transaction_Fragment;
import com.github.ybq.android.spinkit.SpinKitView;


import java.util.List;

import co.ronash.pushe.Pushe;
import me.everything.android.ui.overscroll.IOverScrollDecor;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivityy extends AppCompatActivity implements My_interface {
    FrameLayout frameLayout;
    Homefrg homefrg;
    int number_repeat_to_update_pushid=0;
   public Messages_Fragment messagesFragment;
   public F_List_Sefareshha List_Sefareshha_Fragment;
    CollapsingToolbarLayout collapsingToolbarLayout;
    public Transaction_Fragment transaction_fragment;
Typeface typeface;
    IOverScrollDecor tt;
   public AHBottomNavigation navigation;
    com.example.khialerahat.experts_khialerahat.fragments.Profile_Fragment Profile_Fragment;
    AppBarLayout appBarLayout;
    FragmentTransaction transaction;
    Dialog  d, closed;
    private Valley_Api valley_api;
    public SpinKitView spinKitView;
    AlertDialog alertDialog;
   public Progress_And_Dialog progress_and_dialog;
    SharedPreferences preferences;
      Intent intent;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent=getIntent();
        MotakhasesObject.new_order_id=intent.getStringExtra("new_order");
        spinKitView=findViewById(R.id.progress_in_lay_main_activity);
        progress_and_dialog=new Progress_And_Dialog(this,spinKitView);

        frameLayout = (FrameLayout) findViewById(R.id.framlayout);
        navigation = (AHBottomNavigation) findViewById(R.id.bottomBar);

        String pushe_id= Pushe.getPusheId(this) ;

        if(MotakhasesObject.pushid.contains(pushe_id))
        {

        }else
        {
            valley_api=new Valley_Api(this);
            valley_api.update_pushid(MotakhasesObject.id,pushe_id,MotakhasesObject.token);
            progress_and_dialog.show_progress();
        }

           typeface = Typeface.createFromAsset(getAssets(), "Shabnam.ttf");
     //   homefrg = new Homefrg();
        messagesFragment = new Messages_Fragment();
        List_Sefareshha_Fragment = new F_List_Sefareshha();
        Profile_Fragment = new Profile_Fragment();
        transaction_fragment=new Transaction_Fragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framlayout,Profile_Fragment);
        transaction.commit();

        //AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.title_home, R.drawable.ic_note_add_black_24dp, R.color.new3);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.list_sefaresh, R.drawable.ic_assignment_black_24dp, R.color.new3);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.akhbar, R.drawable.ic_notifications_black_24dp, R.color.new3);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.profile, R.drawable.ic_person_black_24dp, R.color.new3);
        AHBottomNavigationItem transaction_item = new AHBottomNavigationItem("تراکنش ها", R.drawable.wallet, R.color.new3);

       // navigation.addItem(item1);
        navigation.addItem(item1);
        navigation.addItem(transaction_item);
        navigation.addItem(item2);
        navigation.addItem(item3);
        navigation.setCurrentItem(3);
        navigation.setDefaultBackgroundColor(Color.WHITE);
        navigation.setAccentColor(Color.parseColor("#0D61F7"));
        navigation.setInactiveColor(Color.parseColor("#2f2f2f"));

        navigation.setDefaultBackgroundColor(Color.parseColor("#61E8E1"));
navigation.setNotificationTypeface(typeface);
navigation.setNotificationBackgroundColorResource(R.color.new5);
navigation.setNotificationTextColorResource(R.color.white);

        navigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        navigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position){
//                    case 0:
//
//                        homefrg=new Homefrg();
//                        transaction = getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.framlayout, homefrg);
//                        transaction.commit();
//
//
//                        return true;
//
                    case 0:


                     //   navigation.setNotification("", 0);
                        List_Sefareshha_Fragment = new F_List_Sefareshha();

                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.framlayout, List_Sefareshha_Fragment);
                        transaction.commit();

                        return true;

                    case 1:


                        //   navigation.setNotification("", 0);
                        transaction_fragment = new Transaction_Fragment();

                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.framlayout, transaction_fragment);
                        transaction.commit();

                        return true;
                    case 2:

                        messagesFragment =new Messages_Fragment();
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.framlayout, messagesFragment);
                        transaction.commit();


                        return true;
                    case 3:

                        Profile_Fragment= new Profile_Fragment();
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.framlayout, Profile_Fragment);
                        transaction.commit();

                        return true;
                }
                return false;
            }
        });
 }

    @Override
    public void upload_status(Boolean error_status, String feild_name) {
        progress_and_dialog.dismis_progress();
        if(error_status)
        {

            progress_and_dialog=new Progress_And_Dialog(this,alertDialog);
            progress_and_dialog.failure_in_update_pushid(""+error_status, Const_Variable.UPDATE_PUSHID_ERROR_TITLE
                    ,Const_Variable.UPDATE_PUSHID_ERROR_MESSAGE);
        }
        else
        {
            progress_and_dialog.dismis_progress();
        }

    }

    @Override
    public void recive_data_from_server_failur(String error_status, String title_error, String error_message)
    {
            if(title_error.contains("profile"))
            {
                Profile_Fragment.failure_in_get_profile_data();
            }
            else
            {

                if(number_repeat_to_update_pushid< Const_Variable.MAX_LENGTH_REPEAT_TRAY_TO_GET_DATA)
                {
                    number_repeat_to_update_pushid++;
                    valley_api.get_listostanha_asserver();
                    progress_and_dialog.show_progress();
                }
                else
                {

                    progress_and_dialog=new Progress_And_Dialog(this,alertDialog);
                    progress_and_dialog.failure_in_update_pushid(error_status,title_error,error_message);
                }
            }


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            closed = new Dialog(MainActivityy.this, R.style.dialog);
            closed.setContentView(R.layout.dialog_out);
            Button bastan = (Button) closed.findViewById(R.id.close);
            Button out = (Button) closed.findViewById(R.id.out);

            out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            bastan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closed.cancel();
                }
            });
            closed.show();
        }
        return super.onKeyDown(keyCode, event);
    }



    @Override
    public void onPause() {
        super.onPause();
        Log.i("mainactivity",": onpause  =false shreprefrenc");
        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("main_activity_isActive", true).commit();
    }


    @Override
    public void onResume() {
        super.onResume();

        if (MotakhasesObject.new_order_id==null)
        {
            MotakhasesObject.new_order_id="0";Log.i("mainactivity","get intent:" +"null");
        }
        Log.i("mainactivity","get intent:" +MotakhasesObject.new_order_id);
        if(MotakhasesObject.new_order_id.contentEquals("0"))
        {

            preferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());

            MotakhasesObject.new_order_id = preferences.getString("new_order_id", null);
            Log.i("mainactivity","shareprefrence order id:" +MotakhasesObject.new_order_id);

            if(MotakhasesObject.new_order_id!=null)
            {
                navigation.setNotification("جدید", 0);

            }
            else
            {
                MotakhasesObject.new_order_id="0";
            }
        }
        else
        {
            Log.i("mainactivity","get intent:" +MotakhasesObject.new_order_id);
            navigation.setNotification("جدید", 0);

            MPlayer_CounterDown.countDownTimer=new CountDownTimer(Const_Variable.MAX_MILISECND_WAIT_TO_ACCEPT_NEW_ORDERS
            ,Const_Variable.MAX_MILISECND_WAIT_TO_ACCEPT_NEW_ORDERS) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.i("counter_down","in main activity onresum  is ontick");
                }

                @Override
                public void onFinish() {
                    Log.i("counter_down","in main activity onresum  is ontick");
                    Log.i("MPlayer_CounterDown","onfinish");
                    navigation.setNotification("", 0);
                    if (MPlayer_CounterDown.mediaPlayer!=null) {

                        if(MPlayer_CounterDown.mediaPlayer.isPlaying())
                        {

                            MPlayer_CounterDown.mediaPlayer.release();
                            MPlayer_CounterDown.mediaPlayer=null;
                            Log.i("mplayer","in main activity onresum  is stop and set null");
                        }
                        else
                        {

                            Log.i("mplayer","in main activity onresum  set null");
                            MPlayer_CounterDown.mediaPlayer=null;
                        }
                    }
                    MotakhasesObject.new_order_id="0";
                }
            }.start();



        }

        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("main_activity_isActive", true).commit();
        Log.i("mainactivity",": onresum");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("main_activity_isActive", false).commit();
        Log.i("mainactivity",": destroy");
        if (closed != null && closed.isShowing()) {
            closed.dismiss();
        }
    }




    @Override
    public void get_profile_data(Model_Profile model_profile)
    {
        Profile_Fragment.get_profile_data(model_profile);
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
    public void username_athuntication(String status_username) {

    }

    @Override
    public void password_athuntication(MotakhasesObject motakhasesObject) {

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
