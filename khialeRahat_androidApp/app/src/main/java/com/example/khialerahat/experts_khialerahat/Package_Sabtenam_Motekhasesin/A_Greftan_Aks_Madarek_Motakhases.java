 ///    آپلود مشخصات فردی در تاب    set_user_pic_into_imagvie    فراخوانی شده است یعنی بعد از دریافت آخرین عکس
 /////////آپلود زمان و تخصص و مناظق در تابعgreftan_id_motakhasesکه overraidشده فراخوانی شده اند
 /////////////////////در داخل رویداد کلیک ارسال داده    تابع vollyapi.greftan id motakhasesفراخوانی شده است


package com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khialerahat.experts_khialerahat.MainPackage.Model_Profile;
import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.My_interface;
import com.example.khialerahat.experts_khialerahat.Progress_And_Dialog;
import com.example.khialerahat.experts_khialerahat.R;
import com.example.khialerahat.experts_khialerahat.Recycler_Viewholder_Adapter;
import com.example.khialerahat.experts_khialerahat.Web_Service.Valley_Api;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.webianks.library.scroll_choice.ScrollChoice;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.ronash.pushe.Pushe;
import de.hdodenhof.circleimageview.CircleImageView;

public  class A_Greftan_Aks_Madarek_Motakhases extends AppCompatActivity implements ScrollChoice.OnItemSelectedListener, My_interface,View.OnClickListener {
      SpinKitView spinKitView;
      AlertDialog alertDialog;
      Progress_And_Dialog progress_and_dialog;
      Boolean upload_status_temp;
    AppCompatCheckedTextView chtxt_shanbeh,chtxt_yek_shanbeh,chtxt_do_shanbeh,
    chtxt_seh_shanbeh,chtxt_chehar_shanbeh,chtxt_panj_shanbeh,chtxt_jomeh;
    TextView txt_shroeh_shanbeh,txt_shroeh_yek_shanbeh,txt_shroeh_do_shanbeh
            ,txt_shroeh_seh_shanbeh,txt_shroeh_chehar_shanbeh,txt_shroeh_panj_shanbeh
            ,txt_sheroeh_jomeh;
    TextView txt_payan_shanbeh,txt_payan_yek_shanbeh,txt_payan_do_shanbeh
            ,txt_payan_seh_shanbeh,txt_payan_chehar_shanbeh,txt_payan_panj_shanbeh
            ,txt_payan_jomeh;
    ScrollChoice scrolchoice_payan_shanbeh,scrolchoice_payan_yek_shanbeh,scrolchoice_payan_do_shanbeh
            ,scrolchoice_payan_seh_shanbeh,scrolchoice_payan_chehar_shanbeh,scrolchoice_payan_panj_shanbeh
            ,scrolchoice_payan_jomeh;
    ScrollChoice[] array_scrollChoices_sheroe=new ScrollChoice[7];
    ScrollChoice[] array_scrollChoices_payan=new ScrollChoice[7];

    ScrollChoice scrolchoice_sheroeh_shanbeh,scrolchoice_sheroeh_yek_shanbeh,scrolchoice_sheroeh_do_shanbeh
            ,scrolchoice_sheroeh_seh_shanbeh,scrolchoice_sheroeh_chehar_shanbeh,scrolchoice_sheroeh_panj_shanbeh
            ,scrolchoice_sheroeh_jomeh;
    final int  PICK_IMAGE_REQUEST_FROM_GALERY=1000,PICK_IMAGE_REQUEST_FROM_CAMERA=1001;
    Valley_Api valley_api=new Valley_Api(this);
    ConstraintLayout constraintLayout_saat_kari;
    NestedScrollView nestedScrollView;
    TextView txtcartmeli,txtposhtcartmeli
            ,txtshenasname,txtpayankhedmat,txtgavahi_fani
            ,txtprofile
            ,txt_temp_for_change_txtcolor,
            txt_temp2_for_change_txtcolor;
    Moshakhasat_Motehkases moshakhasat_motehkases=new Moshakhasat_Motehkases();
    int[] int_temparray_barayeh_shahrhayeh_entkhabi;
    int[] int_temparray_barayeh_takhasoshayeh_entkhabi;
    int[] int_temparray_barayeh_takhasoshayeh_level2_entkhabi;
    int[] int_temparray_barayeh_saat_kari;

    Intent intent;

    long animation_duration=1000;
     ImageView img_cartmeli
             ,img_posht_kartemeli
             ,img_shenasname
             ,img_payanekhedmat
             ,img_gavahi_fani
             ,img_profile
             ,temp_imgView_for_recive_result
             ,temp2_imgView_for_recive_result;


    CircleImageView    back_imgbtn;
    ConstraintLayout main_layout_etsalat;
    String s;
    View lay_greftanaks;
    Button crd_btn_ersal;
    TextView filter;
    Bitmap img_bitmap;
    Uri uri_img;
    private ImageView imgv_aksezafehshode_etsalat;
    Boolean[] img_madarek_vared_shode_ast=new Boolean[6];
    int temp_index_boolean_imag_madarek_vared_shode_ast=100;
    List<String> string_list_address_manatehg=new ArrayList<>();
   static RecyclerView recyclerView_address_manategh,recycler_takhasos,recyclerView_takhasos_level2;
    private List<Object> object_list_address_manategh=new ArrayList<>();
    private List<Object> object_list_takhasos=new ArrayList<>();
    private List<Object> object_list_takhasos_level2=new ArrayList<>();
    private List<Object> object_list_saat_kari=new ArrayList<>();
    private List<Object> object_list_time=new ArrayList<>();
    private CardView cardView_manategh,cardview_takhasos,cardview_takhasos_level2,cardView_saat_kari;
    Handler handler;
    private GridLayoutManager gridLayoutManager;
    private Recycler_Viewholder_Adapter recycler_viewholder_adapter;

    private int[] int_temparray_barayeh_time;
    private Model_Takhasos[] List_takhasos_entkhabi;
    private Model_Time[] list_time_entkhabi;
    private Model_Takhasos_level2[] list_takhasos_level2_entkhabi;
    List<String> saat_Shabaneh_Roz=new ArrayList<>();
    private NiceSpinner spiner_ostan,spiner_shahrestan;
    private List<String> list_ostanha=new ArrayList<>();
    private List<String> list_shahrestanha=new ArrayList<>();
    private List<String> list_id_Shahrestanha=new ArrayList<>();
    private int number_of_day_uploaded=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.a_mainlayout_greftan_aks_madarek);
        valley_api.get_listostanha_asserver();
        handler=new Handler();
        greftan_bundel();
        moshakhasat_motehkases.getManategh_shahrestan().clear();
        initial_myview();

        progress_and_dialog =new Progress_And_Dialog(this,spinKitView);
        progress_and_dialog.show_progress();
        setup_scrool_choice();



        nestedScrollView_position_change();

    }

    private void setup_scrool_choice()
    {
        saat_Shabaneh_Roz.add("00:01");
        saat_Shabaneh_Roz.add("01:01");

        saat_Shabaneh_Roz.add("02:01");
        saat_Shabaneh_Roz.add("03:01");

        saat_Shabaneh_Roz.add("04:01");
        saat_Shabaneh_Roz.add("05:01");

        saat_Shabaneh_Roz.add("06:01");
        saat_Shabaneh_Roz.add("07:01");

        saat_Shabaneh_Roz.add("08:01");
        saat_Shabaneh_Roz.add("09:01");

        saat_Shabaneh_Roz.add("10:01");
        saat_Shabaneh_Roz.add("11:01");

        saat_Shabaneh_Roz.add("12:01");
        saat_Shabaneh_Roz.add("-:-");
        saat_Shabaneh_Roz.add("13:01");

        saat_Shabaneh_Roz.add("14:01");
        saat_Shabaneh_Roz.add("15:01");

        saat_Shabaneh_Roz.add("16:01");
        saat_Shabaneh_Roz.add("17:01");

        saat_Shabaneh_Roz.add("18:01");
        saat_Shabaneh_Roz.add("19:01");

        saat_Shabaneh_Roz.add("20:01");
        saat_Shabaneh_Roz.add("21:01");

        saat_Shabaneh_Roz.add("22:01");
        saat_Shabaneh_Roz.add("23:01");

        for (int i=0;i<array_scrollChoices_sheroe.length;i++)
        {
            array_scrollChoices_sheroe[i].setOnItemSelectedListener(this);
            array_scrollChoices_sheroe[i].addItems(saat_Shabaneh_Roz,13);
            array_scrollChoices_payan[i].setOnItemSelectedListener(this);
            array_scrollChoices_payan[i].addItems(saat_Shabaneh_Roz,13);
        }


    }

    private void nestedScrollView_position_change()
    {

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override

            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int[] a=new int[2];
                cardview_takhasos.getLocationInWindow(a);

                if(a[1]<400)
                    crd_btn_ersal.setVisibility(View.VISIBLE);
                 else  crd_btn_ersal.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void greftan_list_takhasos_ha_as_server()

    {

        valley_api.get_list_takhasos();
        progress_and_dialog.show_progress();
    }


    private void greftan_bundel()
    {

        String gson_moshakhasat_motehkases = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            gson_moshakhasat_motehkases = extras.getString("moshakhasat_motehkases");
        }
        moshakhasat_motehkases = new Gson().fromJson(gson_moshakhasat_motehkases, Moshakhasat_Motehkases.class);



    }

    private void initial_myview()
    {
        spinKitView=findViewById(R.id.progress_in_lay);
        spiner_ostan=findViewById(R.id.sp_entkhab_ostan);
        spiner_shahrestan=findViewById(R.id.sp_entkhab_shahrestan);
        txt_shroeh_shanbeh=findViewById(R.id.txt_shroeh_shanbeh);
        txt_shroeh_yek_shanbeh=findViewById(R.id.txt_shroeh_yek_shanbeh);
        txt_shroeh_do_shanbeh=findViewById(R.id.txt_shroeh_do_shanbeh);
        txt_shroeh_seh_shanbeh=findViewById(R.id.txt_shroeh_seh_shanbeh);
        txt_shroeh_chehar_shanbeh=findViewById(R.id.txt_shroeh_chehar_shanbeh);
        txt_shroeh_panj_shanbeh=findViewById(R.id.txt_shroeh_panj_shanbeh);
        txt_sheroeh_jomeh=findViewById(R.id.txt_shroeh_jomeh);
        //////////////////////////////
        txt_payan_shanbeh=findViewById(R.id.txt_payan_shanbeh);
        txt_payan_yek_shanbeh=findViewById(R.id.txt_payan_yek_shanbeh);
        txt_payan_do_shanbeh=findViewById(R.id.txt_payan_do_shanbeh);
        txt_payan_seh_shanbeh=findViewById(R.id.txt_payan_seh_shanbeh);
        txt_payan_chehar_shanbeh=findViewById(R.id.txt_payan_chehar_shanbeh);
        txt_payan_panj_shanbeh=findViewById(R.id.txt_payan_panj_shanbeh);
        txt_payan_jomeh=findViewById(R.id.txt_payan_jomeh);
        ///////////////////////////////

        scrolchoice_sheroeh_shanbeh=findViewById(R.id.scroolchoice_shroeh_shanbeh);
        scrolchoice_sheroeh_yek_shanbeh=findViewById(R.id.scroolchoice_shroeh_yek_shanbeh);
        scrolchoice_sheroeh_do_shanbeh=findViewById(R.id.scroolchoice_shroeh_do_shanbeh);
        scrolchoice_sheroeh_seh_shanbeh=findViewById(R.id.scroolchoice_shroeh_seh_shanbeh);
        scrolchoice_sheroeh_chehar_shanbeh=findViewById(R.id.scroolchoice_shroeh_chehar_shanbeh);
        scrolchoice_sheroeh_panj_shanbeh=findViewById(R.id.scroolchoice_shroeh_panj_shanbeh);
        scrolchoice_sheroeh_jomeh=findViewById(R.id.scroolchoice_shroeh_jomeh);
        /////////////////
        /////////////////////

        array_scrollChoices_sheroe[0]=findViewById(R.id.scroolchoice_shroeh_shanbeh);
        array_scrollChoices_sheroe[1]=findViewById(R.id.scroolchoice_shroeh_yek_shanbeh);
        array_scrollChoices_sheroe[2]=findViewById(R.id.scroolchoice_shroeh_do_shanbeh);
        array_scrollChoices_sheroe[3]=findViewById(R.id.scroolchoice_shroeh_seh_shanbeh);
        array_scrollChoices_sheroe[4]=findViewById(R.id.scroolchoice_shroeh_chehar_shanbeh);
        array_scrollChoices_sheroe[5]=findViewById(R.id.scroolchoice_shroeh_panj_shanbeh);
        array_scrollChoices_sheroe[6]=findViewById(R.id.scroolchoice_shroeh_jomeh);
        //

        array_scrollChoices_payan[0]=findViewById(R.id.scroolchoice_payan_shanbeh);
        array_scrollChoices_payan[1]=findViewById(R.id.scroolchoice_payan_yek_shanbeh);
        array_scrollChoices_payan[2]=findViewById(R.id.scroolchoice_payan_do_shanbeh);
        array_scrollChoices_payan[3]=findViewById(R.id.scroolchoice_payan_seh_shanbeh);
        array_scrollChoices_payan[4]=findViewById(R.id.scroolchoice_payan_chehar_shanbeh);
        array_scrollChoices_payan[5]=findViewById(R.id.scroolchoice_payan_panj_shanbeh);
        array_scrollChoices_payan[6]=findViewById(R.id.scroolchoice_payan_jomeh);

        //////////////////

        scrolchoice_payan_shanbeh=findViewById(R.id.scroolchoice_payan_shanbeh);
        scrolchoice_payan_yek_shanbeh=findViewById(R.id.scroolchoice_payan_yek_shanbeh);
        scrolchoice_payan_do_shanbeh=findViewById(R.id.scroolchoice_payan_do_shanbeh);
        scrolchoice_payan_seh_shanbeh=findViewById(R.id.scroolchoice_payan_seh_shanbeh);
        scrolchoice_payan_chehar_shanbeh=findViewById(R.id.scroolchoice_payan_chehar_shanbeh);
        scrolchoice_payan_panj_shanbeh=findViewById(R.id.scroolchoice_payan_panj_shanbeh);
        scrolchoice_payan_jomeh=findViewById(R.id.scroolchoice_payan_jomeh);
        ////////////////////
        chtxt_shanbeh=findViewById(R.id.chtxt_shanbeh);
        chtxt_yek_shanbeh=findViewById(R.id.chtxt_yek_shanbeh);
        chtxt_do_shanbeh=findViewById(R.id.chtxt_do_shanbeh);
        chtxt_seh_shanbeh=findViewById(R.id.chtxt_seh_shanbeh);
        chtxt_chehar_shanbeh=findViewById(R.id.chtxt_chehar_shanbeh);
        chtxt_panj_shanbeh=findViewById(R.id.chtxt_panj_shanbeh);
        chtxt_jomeh=findViewById(R.id.chtxt_jomeh);

        nestedScrollView=findViewById(R.id.nestedscroll_greftanaks);


        cardView_saat_kari=findViewById(R.id.cardview_saat_kari);
        cardView_saat_kari.setVisibility(View.INVISIBLE);

        cardView_manategh=findViewById(R.id.crdview_recyclerview_manategh);
        recyclerView_address_manategh=findViewById(R.id.recycler_entkhab_manategh);
        cardview_takhasos=findViewById(R.id.cardview_sabtnammotahkassin_maillayout_greftanaks_listtakhasos);
        recycler_takhasos=findViewById(R.id.recycler_entkhab_takhasosha);
        cardview_takhasos_level2=findViewById(R.id.cardview_sabtnammotahkassin_maillayout_greftanaks_listtakhasos_level2);
        recyclerView_takhasos_level2=findViewById(R.id.recycler_entkhab_takhasosha_level2);




        recyclerView_address_manategh=findViewById(R.id.recycler_entkhab_manategh);
        main_layout_etsalat=findViewById(R.id.main_layout_greftan_aks_madarek);
        lay_greftanaks=   findViewById(R.id.layout_greftanaks_etsalat);
        crd_btn_ersal=findViewById(R.id.crd_btn_ersalaks_etsalat);
        // main_layout_etsalat.removeView(lay_greftanaks);
        lay_greftanaks.setVisibility(View.GONE);
        back_imgbtn=findViewById(R.id.close_imgview_sabtenam);
        back_imgbtn.setOnClickListener(this);
        crd_btn_ersal=findViewById(R.id.crd_btn_ersalaks_etsalat);
        crd_btn_ersal.setOnClickListener(this);
        img_cartmeli=findViewById(R.id.aks_kart_meli);
        img_cartmeli.setOnClickListener(this);
        img_posht_kartemeli=findViewById(R.id.aks_posht_kart_meli);
        img_posht_kartemeli.setOnClickListener(this);
        img_posht_kartemeli.setEnabled(false);
        img_shenasname=findViewById(R.id.safhe_aval_shenasname);
        img_shenasname.setEnabled(false);
        img_shenasname.setOnClickListener(this);
        img_payanekhedmat=findViewById(R.id.payane_khedmat);
        img_payanekhedmat.setEnabled(false);
        img_payanekhedmat.setOnClickListener(this);
        img_gavahi_fani=findViewById(R.id.gavahi_fani);
        img_gavahi_fani.setEnabled(false);
        img_gavahi_fani.setOnClickListener(this);
        img_profile=findViewById(R.id.aks_profile);
       // img_profile.setEnabled(false);
        img_profile.setOnClickListener(this);

        txtcartmeli=findViewById(R.id.textView1);
        txtposhtcartmeli=findViewById(R.id.txtview_poshtcartmeli);
        txtshenasname=findViewById(R.id.textView3);
        txtpayankhedmat=findViewById(R.id.textView4);
        txtgavahi_fani=findViewById(R.id.textView5);
        txtprofile=findViewById(R.id.textView6);
    }


    public void handel_animation_imageview(ImageView img)
   {
             ObjectAnimator objectAnimatororentation = ObjectAnimator.ofFloat(img, "rotation", 1f, 90f);

             objectAnimatororentation.setDuration(animation_duration);

             AnimatorSet animatorSet = new AnimatorSet();
             animatorSet.playTogether(objectAnimatororentation);
             animatorSet.start();


   }
    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.close_imgview_sabtenam:
                call_imgbtn_back();
                break;

            case R.id.crd_btn_ersalaks_etsalat:
                btn_upload_data();
                break;

            case R.id.aks_kart_meli:
                call_imgbtn_ezafehkardanaks(img_cartmeli);
                meghdardehi_motaghayerhaye_komaki(img_cartmeli,txtcartmeli,
                        txtposhtcartmeli,img_posht_kartemeli,0);
                 break;

            case R.id.aks_posht_kart_meli:
                  call_imgbtn_ezafehkardanaks(img_posht_kartemeli);
                  meghdardehi_motaghayerhaye_komaki(img_posht_kartemeli
                  ,txtposhtcartmeli,txtshenasname,img_shenasname,1);
                break;


            case R.id.safhe_aval_shenasname:
                if(!moshakhasat_motehkases.getJensiat().contains("زن"))
                {
                    call_imgbtn_ezafehkardanaks(img_shenasname);
                    meghdardehi_motaghayerhaye_komaki(img_shenasname
                            , txtshenasname, txtpayankhedmat, img_payanekhedmat, 2);
                    break;
                }
                else
                {

                    call_imgbtn_ezafehkardanaks(img_shenasname);
                    meghdardehi_motaghayerhaye_komaki(img_shenasname
                            , txtshenasname, txtgavahi_fani, img_gavahi_fani, 3);
                    break;
                }
            case R.id.payane_khedmat:
                call_imgbtn_ezafehkardanaks(img_payanekhedmat);
                meghdardehi_motaghayerhaye_komaki(img_payanekhedmat
                        ,txtpayankhedmat,txtgavahi_fani,img_gavahi_fani,3);
                break;
           case R.id.gavahi_fani:
                call_imgbtn_ezafehkardanaks(img_gavahi_fani);
                meghdardehi_motaghayerhaye_komaki(img_gavahi_fani
                        ,txtgavahi_fani,txtprofile,img_profile,4);
                break;
            case R.id.aks_profile:

                call_imgbtn_ezafehkardanaks(img_profile);
                meghdardehi_motaghayerhaye_komaki(img_profile
                        ,txtprofile,txtprofile,img_profile,5);
                break;



        }
    }

    private void meghdardehi_motaghayerhaye_komaki(ImageView img_curerent, TextView txt_current,
                                   TextView txtnext, ImageView imgnext, int index_for_temp_index)
    {
        temp_imgView_for_recive_result=img_curerent;
        txt_temp_for_change_txtcolor=txt_current;
        txt_temp2_for_change_txtcolor=txtnext;
        temp2_imgView_for_recive_result=imgnext;
        temp2_imgView_for_recive_result.setEnabled(true);
        temp_index_boolean_imag_madarek_vared_shode_ast=index_for_temp_index;

    }


    private void btn_upload_data()
    {


      int numberofroze_entekhabi=0;
       numberofroze_entekhabi= setup_saat_kari();


        List<String> list_temp=new ArrayList<>();
       // Toast.makeText(this, "img is ready to send", Toast.LENGTH_SHORT).show();
         ///////////////////////////////////////////////add takhasos to moshakhasat_motekhases
            list_temp.clear();
            List<String> list_id_temp=new ArrayList<>();
            list_id_temp.clear();
            int numberoftakhasosentkhabi=0;
        for(int i=0;i<int_temparray_barayeh_takhasoshayeh_entkhabi.length;i++)
        {
            if(int_temparray_barayeh_takhasoshayeh_entkhabi[i]==1)
            {
                Log.i("takhasos_entkhabi: ",List_takhasos_entkhabi[i].getName_takhasos());
                list_temp.add(List_takhasos_entkhabi[i].getName_takhasos());
                list_id_temp.add(List_takhasos_entkhabi[i].getId_takhasos());
                numberoftakhasosentkhabi++;
            }
        }

        moshakhasat_motehkases.setList_takhasos(list_temp);

        moshakhasat_motehkases.setList_id_takhasos(list_id_temp);
        ///////////////////////////////////////////////add takhasos_level2 to moshakhasat_motekhases
        list_temp.clear();
       List<String> list_id_temp_takhasos_levl2=new ArrayList<>();
       list_id_temp_takhasos_levl2.clear();
        List<String> list_id_temp_takhasos_levl1=new ArrayList<>();
        list_id_temp_takhasos_levl1.clear();

        ////////if takhasos_level2_entkhab_nashodeh_bashad
        if (int_temparray_barayeh_takhasoshayeh_level2_entkhabi!=null) {
            for (int i = 0; i < int_temparray_barayeh_takhasoshayeh_level2_entkhabi.length; i++) {
                if (int_temparray_barayeh_takhasoshayeh_level2_entkhabi[i] == 1) {
                    Log.i("takhasos_entkhabi: ", list_takhasos_level2_entkhabi[i].getName_takhasos());
                    list_temp.add(list_takhasos_level2_entkhabi[i].getName_takhasos());
                    list_id_temp_takhasos_levl2.add(list_takhasos_level2_entkhabi[i].getId_takhasos());
                    list_id_temp_takhasos_levl1.add(list_takhasos_level2_entkhabi[i].getId_takhasos_level1());
                }
            }
            moshakhasat_motehkases.setList_takhasos_level2(list_temp);
            moshakhasat_motehkases.setList_id_takhasos_level2(list_id_temp_takhasos_levl2);
            moshakhasat_motehkases.setList_id_takhasos_level1(list_id_temp_takhasos_levl1);
        }
        ///////////////////////////////////////////////////

        list_temp.clear();
        List<String> temp2=new ArrayList<>();
        int numberofmanategh_entekhabi=0;
        for(int i=0;i<int_temparray_barayeh_shahrhayeh_entkhabi.length;i++)
        {
            if(int_temparray_barayeh_shahrhayeh_entkhabi[i]==1)
            {
                Log.i("manategh_entkhabi: ",list_shahrestanha.get(i));
                list_temp.add(list_shahrestanha.get(i));
                temp2.add(list_id_Shahrestanha.get(i));
                numberofmanategh_entekhabi++;
            }
        }
        moshakhasat_motehkases.setList_manategh_khedmatdahi(list_temp);
        moshakhasat_motehkases.setList_id_manategh_khedmatdahi(temp2);




        if (numberofmanategh_entekhabi>0&&numberoftakhasosentkhabi>0&&numberofroze_entekhabi>0) {
         //   progressDialog=ProgressDialog.show(this,"ثبت اطلاعات","در حال ثبت اطلاعات");
            if (moshakhasat_motehkases.getSet_status_moshakhasat_fardi()) {
                valley_api.greftan_idmotekhases(moshakhasat_motehkases.getCodemeli());
                progress_and_dialog.show_progress();
                //////uplode saat karri call in overraid greftan code meli

            }
        }
        else
            {
                if(numberofroze_entekhabi==0)
                {
                    Toast.makeText(this, "تعداد روز های مد نظر شما کمتر از حد مجاز است", Toast.LENGTH_SHORT).show();
                    scrolchoice_sheroeh_shanbeh.requestFocus();
                }
                else if(numberofmanategh_entekhabi==0)
                {
                    Toast.makeText(this, "محدوده کاری خود را انتخاب کنید", Toast.LENGTH_SHORT).show();
                    cardView_manategh.requestFocus();

                }
                else
                {
                    Toast.makeText(this, "تخضض خود را انتخاب کنید", Toast.LENGTH_SHORT).show();
                    cardview_takhasos.requestFocus();
                }
            }


        ///////////////////
        log_nahaie();




    }

    private void log_nahaie() {

        Log.i("name:",moshakhasat_motehkases.getName());
        Log.i("family:",moshakhasat_motehkases.getFamily());
        Log.i("codemeli:",moshakhasat_motehkases.getCodemeli());
        Log.i("jensiat:",moshakhasat_motehkases.getJensiat());
        Log.i("vazeiat_taahol:",moshakhasat_motehkases.getVazeyat_tahol());

        Log.i("shomareh_hamrah:",moshakhasat_motehkases.getShomare_hamrah());
        Log.i("shomare_sabt",moshakhasat_motehkases.getShomare_sabet());

        Log.i("shahrestan:",moshakhasat_motehkases.getShahrestan());
        Log.i("ostan:",moshakhasat_motehkases.getOstan());

        Log.i("adress_daghigh:",moshakhasat_motehkases.getAddress_daghigh());
        for(int i=0;i<moshakhasat_motehkases.getList_manategh_khedmatdahi().size();i++)
            Log.i("manategh1:",moshakhasat_motehkases.getList_manategh_khedmatdahi().get(i));
        Log.i("ramze_vrod:",moshakhasat_motehkases.getRamzevrod());
    }

    private int setup_saat_kari()
    {
        int teedad_rozentkhabi=0;
        if (chtxt_shanbeh.isChecked()){
            teedad_rozentkhabi++;
            moshakhasat_motehkases.setList_saatKari_shanbeh(set_saat_kari(
                    txt_shroeh_shanbeh,txt_payan_shanbeh
            ));}

        ////////////////////
        if (chtxt_yek_shanbeh.isChecked()){
            teedad_rozentkhabi++;
            moshakhasat_motehkases.setList_saatKari_yek_shanbeh(set_saat_kari(
                    txt_shroeh_yek_shanbeh,txt_payan_yek_shanbeh
            ));
        }
        else
        {
            Log.i("saatkari_yek_shanbeh: ","مشخص نشده");
        }
        //////////////////////
        if (chtxt_do_shanbeh.isChecked()){
            teedad_rozentkhabi++;
            moshakhasat_motehkases.setList_saatKari_do_shanbeh(set_saat_kari(
                    txt_shroeh_do_shanbeh,txt_payan_do_shanbeh
            ));}
        else
        {
            Log.i("saatkari_do_shanbeh: ","مشخص نشده");
        }
        /////////////////////////////
        if (chtxt_seh_shanbeh.isChecked()){
            teedad_rozentkhabi++;
            moshakhasat_motehkases.setList_saatKari_seh_shanbeh(set_saat_kari(
                    txt_shroeh_seh_shanbeh,txt_payan_seh_shanbeh
            ));}
        else
        {
            Log.i("saatkari_seh_shanbeh: ","مشخص نشده");
        }
        ////////////////
        if (chtxt_chehar_shanbeh.isChecked()){
            teedad_rozentkhabi++;
            moshakhasat_motehkases.setList_saatKari_chehar_shanbeh(set_saat_kari(
                    txt_shroeh_chehar_shanbeh,txt_payan_chehar_shanbeh
            ));}
        else
        {
            Log.i("saatkari_charshanbeh: ","مشخص نشده");
        }
        ///////////////////
        if (chtxt_panj_shanbeh.isChecked()){
            teedad_rozentkhabi++;
            moshakhasat_motehkases.setList_saatKari_panj_shanbeh(set_saat_kari(
                    txt_shroeh_panj_shanbeh,txt_payan_panj_shanbeh
            ));}
        else
        {
            Log.i("saatkari_panj_shanbeh: ","مشخص نشده");
        }
        //////////////////////////
        if (chtxt_jomeh.isChecked()){
            teedad_rozentkhabi++;
            moshakhasat_motehkases.setList_saatKari_jomeh(set_saat_kari(
                    txt_sheroeh_jomeh,txt_payan_jomeh
            ));}
        else
        {
            Log.i("saatkari_jomeh: ","مشخص نشده");
        }


        return teedad_rozentkhabi;
    }

       private List<String> mohasebeh_saat_kari(TextView txt_shroeh, TextView txt_payan)
    {  List<String> list_temp=new ArrayList<>();
        int int_shroe=0,int_payan=0;
       String str_temp_shroe=txt_shroeh.getText().subSequence(0,2).toString();
        String str_temp_payan=txt_payan.getText().subSequence(0,2).toString();
        int_shroe=Integer.parseInt(str_temp_shroe);
        int_payan=Integer.parseInt(str_temp_payan);

            int temp=int_shroe;
            while (temp!=int_payan)
            {
                list_temp.add(String.valueOf(temp));
                Log.i("times",""+temp);
                if(temp==23)
                {temp=0;}else{temp++;}

            }

        return list_temp;
    }

    ///////////////////////این تابع مورد استفاده قرار گرفته است
    private List<String> set_saat_kari(TextView txt_shroeh, TextView txt_payan)
    {  List<String> list_temp=new ArrayList<>();
        String str_temp_shroe=txt_shroeh.getText().subSequence(0,2).toString();
        String str_temp_payan=txt_payan.getText().subSequence(0,2).toString();
         list_temp.add(str_temp_shroe);
         list_temp.add(str_temp_payan);
         return list_temp;
    }


    private int mohasebeh_saat_kari(TextView txt_shroeh, TextView txt_payan,String a)
    {    int counter=0;
        int int_shroe=0,int_payan=0;
        String str_temp_shroe=txt_shroeh.getText().subSequence(0,2).toString();
        String str_temp_payan=txt_payan.getText().subSequence(0,2).toString();
        int_shroe=Integer.parseInt(str_temp_shroe);
        int_payan=Integer.parseInt(str_temp_payan);

        int temp=int_shroe;
        while (temp!=int_payan)
        {
            counter++;
            Log.i("times",""+temp);
            if(temp==23)
            {temp=0;}else{temp++;}

        }

        return counter;
    }

    public void call_imgbtn_back(){
        back_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void setup_spiners()
    {

///////////////////////////////
        //cardView_manategh.setVisibility(View.VISIBLE);
        spiner_ostan.attachDataSource(list_ostanha);
        if (list_ostanha.size()>=1){
        valley_api.get_listshrha_as_server(spiner_ostan.getItemAtPosition(0).toString());
        progress_and_dialog.show_progress();}
        else {Toast.makeText(this, "خطا در اتصال", Toast.LENGTH_SHORT).show();
        Log.i("spinerListOstanha:","eeror");}

        //moshakhasat_motehkases.setOstan(spiner_ostan.getItemAtPosition(0).toString());

        spiner_ostan.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                //moshakhasat_motehkases.setOstan(parent.getItemAtPosition(position).toString());
                valley_api.get_listshrha_as_server(parent.getItemAtPosition(position).toString());
                progress_and_dialog.show_progress();

            }
        });

    }

    private void call_imgbtn_ezafehkardanaks(ImageView img) {

        img.setEnabled(false);

        lay_greftanaks.setVisibility(View.VISIBLE);
        CardView ensraf=findViewById(R.id.crdbtn_ensraf_grftanaks_etsalat);
        //crd_btn_ersal.setEnabled(false);
        Button bazkardandorbin=findViewById(R.id.btn_bazkardandorbin);
        Button entkhabaksasgalery=findViewById(R.id.btn_entkhabaksasgalery);
        //imgv_aksezafehshode_etsalat=findViewById(R.id.img_btn_aksezafehshode_etsalat);
        bazkardandorbin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Permision_Utility.check_permision(A_Greftan_Aks_Madarek_Motakhases.this,"camera"))
                {

                    intent_greftaneaks();
                }
                else
                {
                    Permision_Utility.requst_permision(A_Greftan_Aks_Madarek_Motakhases.this,"camera");
                }

            }
        });
        entkhabaksasgalery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(galleryIntent,PICK_IMAGE_REQUEST_FROM_GALERY);
            }
        });
        ensraf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                temp_imgView_for_recive_result.setEnabled(true);
                temp_imgView_for_recive_result.setClickable(true);
                crd_btn_ersal.setEnabled(true);
                lay_greftanaks.setVisibility(View.GONE);
            }
        });



    }
    private void intent_greftaneaks() {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());
        try {
            intent.putExtra("return-data", true);

            startActivityForResult(intent,PICK_IMAGE_REQUEST_FROM_CAMERA);
        } catch (ActivityNotFoundException e) {
        }


    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        Log.i("permisionRequest",grantResults[0]+ ""+requestCode);
        switch (requestCode)
        {
            case Permision_Utility.getRequst_code_camera:
                if(grantResults.length>0)
                {
                    if(grantResults[0]== PackageManager.PERMISSION_GRANTED)
                    {
                        intent_greftaneaks();
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if (resultCode
                == RESULT_OK && data != null && data.getData() != null) {
            switch (requestCode) {

                case PICK_IMAGE_REQUEST_FROM_GALERY:
                    Uri uri = data.getData();
                    uri_img=uri;
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        set_user_pic_into_imagvie(bitmap);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                case PICK_IMAGE_REQUEST_FROM_CAMERA:


                    uri = data.getData();
                    uri_img=uri;
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        // Log.d(TAG, String.valueOf(bitmap));

                        set_user_pic_into_imagvie(bitmap);
                        //crd_btn_ersal.setEnabled(true);
                        //crd_btn_ersal.setBackground(getResources().getDrawable(R.drawable.but_on_sabz));
                        //crd_btn_ersal.setTextColor(getResources().getColor(R.color.sabz));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }
    }
      ///////////////upload moshakhasat  call in this function
    private void set_user_pic_into_imagvie(Bitmap bitmap)
    {
        switch (temp_index_boolean_imag_madarek_vared_shode_ast)
        {
            case 5:

                img_madarek_vared_shode_ast[temp_index_boolean_imag_madarek_vared_shode_ast]=true;
                txt_temp_for_change_txtcolor.setBackground(getResources().getDrawable(R.drawable.textview_shap));
                temp_imgView_for_recive_result.setClickable(false);
                temp_imgView_for_recive_result.setImageBitmap(bitmap);
                temp_imgView_for_recive_result.setBackground(temp_imgView_for_recive_result.getDrawable());
                lay_greftanaks.setVisibility(View.GONE);

                //////////////////////////////
                ////////////////////////greftan_list_takhasos_as server
                moshakhasat_motehkases.setAks_kartmeli(((BitmapDrawable)img_cartmeli.getDrawable()).getBitmap());
                moshakhasat_motehkases.setAks_poshtekartmeli(((BitmapDrawable)img_posht_kartemeli.getDrawable()).getBitmap());
                moshakhasat_motehkases.setAks_shenasname(((BitmapDrawable)img_shenasname.getDrawable()).getBitmap());

                moshakhasat_motehkases.setAks_payankhedmat(((BitmapDrawable)img_payanekhedmat.getDrawable()).getBitmap());
                moshakhasat_motehkases.setAks_gavahi_fani(((BitmapDrawable)img_gavahi_fani.getDrawable()).getBitmap());
                moshakhasat_motehkases.setAks_sayermadarek(((BitmapDrawable)img_profile.getDrawable()).getBitmap());

                valley_api.upload_image(moshakhasat_motehkases.getName(),
                        moshakhasat_motehkases.getFamily(),moshakhasat_motehkases.getCodemeli()
                        ,moshakhasat_motehkases.getShomare_hamrah(),
                        moshakhasat_motehkases.getShomare_sabet(),
                        moshakhasat_motehkases.getVazeyat_tahol(),
                        moshakhasat_motehkases.getJensiat(),
                        moshakhasat_motehkases.getOstan(),
                        moshakhasat_motehkases.getShahrestan(),
                        moshakhasat_motehkases.getAddress_daghigh(),
                        moshakhasat_motehkases.getRamzevrod(),
                        moshakhasat_motehkases.getAks_kartmeli(),
                        moshakhasat_motehkases.getAks_poshtekartmeli(),
                        moshakhasat_motehkases.getAks_shenasname(),
                        moshakhasat_motehkases.getAks_payankhedmat(),
                        moshakhasat_motehkases.getAks_gavahi_fani(),
                        moshakhasat_motehkases.getAks_sayermadarek(), Pushe.getPusheId(this),
                        moshakhasat_motehkases.getShomare_aberbank(),
                        moshakhasat_motehkases.getShomare_hesab(),
                        moshakhasat_motehkases.getShomare_shaba());
                Log.i("pushid_send to save",Pushe.getPusheId(this));
                progress_and_dialog.show_progress();
               // cardView_saat_kari.setVisibility(View.VISIBLE);
                //animatin_keshoei(cardView_saat_kari);


                break;
                default:

                    img_madarek_vared_shode_ast[temp_index_boolean_imag_madarek_vared_shode_ast]=true;
                    txt_temp_for_change_txtcolor.setBackground(getResources().getDrawable(R.drawable.textview_shap));
                    temp_imgView_for_recive_result.setClickable(false);
                    temp_imgView_for_recive_result.setImageBitmap(bitmap);
                    temp_imgView_for_recive_result.setBackground(temp_imgView_for_recive_result.getDrawable());
                    txt_temp2_for_change_txtcolor.setBackground(getResources().getDrawable(R.drawable.textview_shap_khakestari));

                    temp2_imgView_for_recive_result.setClickable(true);
                    lay_greftanaks.setVisibility(View.GONE);
        }
    }




    private void animatin_keshoei(View view)
    {
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.anim_kesho);
        view.setAnimation(animation);
    }

    private void setup_recycler_View(int cardview_id)
    {
       switch (cardview_id)
       {
           /////////////////////// cardview manategh call in @overraid greftan time
           case R.id.crdview_recyclerview_manategh:
               setup_recyclerviews(recyclerView_address_manategh);
              // cardView_manategh.setVisibility(View.VISIBLE);
             //  animatin_keshoei(cardView_manategh);
               break;

           case R.id.cardview_sabtnammotahkassin_maillayout_greftanaks_listtakhasos:
               setup_recyclerviews(recycler_takhasos);
               cardview_takhasos.setVisibility(View.VISIBLE);
               animatin_keshoei(cardview_takhasos);



               break;
           case R.id.cardview_sabtnammotahkassin_maillayout_greftanaks_listtakhasos_level2:
               setup_recyclerviews(recyclerView_takhasos_level2);
               cardview_takhasos_level2.setVisibility(View.VISIBLE);
              // animatin_keshoei(cardview_takhasos_level2);
               break;
           default:

       }
    }



    private void setup_recyclerviews(RecyclerView crdview_recyclerview)
    {
        switch (crdview_recyclerview.getId())
        {
            case R.id.recycler_entkhab_takhasosha:
                recycler_viewholder_adapter = new
                        Recycler_Viewholder_Adapter(object_list_takhasos,this);

                GridLayoutManager gridLayoutManager=new GridLayoutManager(this,4);
                crdview_recyclerview.setLayoutManager(gridLayoutManager);

                Log.i("object_list_manategh",object_list_address_manategh.size()+"");
                crdview_recyclerview.setAdapter(recycler_viewholder_adapter);

                break;

            case R.id.recycler_entkhab_takhasosha_level2:
               Recycler_Viewholder_Adapter recycler_viewholder_adapter_takhasoslevl2 = new
                        Recycler_Viewholder_Adapter(object_list_takhasos_level2,this);

                GridLayoutManager gridLayoutManager1=new GridLayoutManager(this,4);
                crdview_recyclerview.setLayoutManager(gridLayoutManager1);

                Log.i("object_list_manategh",object_list_address_manategh.size()+"");
                crdview_recyclerview.setAdapter(recycler_viewholder_adapter_takhasoslevl2);

                break;
            case R.id.recycler_entkhab_manategh:
                meghdar_dahi_modell_address_manategh(R.id.recycler_entkhab_manategh);
                recycler_viewholder_adapter = new
                        Recycler_Viewholder_Adapter(object_list_address_manategh,this);

                GridLayoutManager gridLayoutManager3=new GridLayoutManager(this,4);
                crdview_recyclerview.setLayoutManager(gridLayoutManager3);

                Log.i("object_list_manategh",object_list_address_manategh.size()+"");
                crdview_recyclerview.setAdapter(recycler_viewholder_adapter);
                break;

            default:
        }





    }

    private void meghdar_dahi_modell_address_manategh(int recyclerviw_id)
    {
        switch (recyclerviw_id)
        {
            case R.id.recycler_entkhab_manategh:
                   object_list_address_manategh.clear();
                for(int i=0;i<list_shahrestanha.size();i++)
                {
                    object_list_address_manategh.add(new Model_Ezafehkardan_Address(list_shahrestanha.get(i)));
                }

                break;

            default:
        }
    }

    @Override
    public void txtfilter(Boolean harfe_vrodi_sahih_ast, int textInputLayout, int edtxt_id) {

    }

    @Override
    public void greftan_list_manategh_entkhabi_as_recycler_view_adapter(String manategh_ya_takhasos,int item_position, int meghdar)
     {
         if(manategh_ya_takhasos.contains("manategh")) {
            int_temparray_barayeh_shahrhayeh_entkhabi[item_position] = meghdar;

         }
         else if(manategh_ya_takhasos.contains("takhasos_level2")) {
             int_temparray_barayeh_takhasoshayeh_level2_entkhabi[item_position] = meghdar;

         }
         else if(manategh_ya_takhasos.contains("takhasos"))
         {

                   int_temparray_barayeh_takhasoshayeh_entkhabi[item_position] = meghdar;


         }
         else if(manategh_ya_takhasos.contains("saat_kari"))
         {
             int_temparray_barayeh_time[item_position]=meghdar;
         }
    }

    @Override
    public void greftan_list_takhasos(Model_Takhasos[] list_takhasos) {
        progress_and_dialog.dismis_progress();
       // nestedScrollView.addView( array_scrollChoices_sheroe[0],0);
        nestedScrollView.setEnabled(true);
        int x=nestedScrollView.getScrollX();
        int_temparray_barayeh_takhasoshayeh_entkhabi=new int[list_takhasos.length];
        List_takhasos_entkhabi=new Model_Takhasos[list_takhasos.length];
        List_takhasos_entkhabi=list_takhasos;
        for(int i=0;i<list_takhasos.length;i++)
        {
            Log.i("list_takhasos",list_takhasos[i].getName_takhasos());
            object_list_takhasos.add(new Model_Takhasos_recyclerview(list_takhasos[i].getName_takhasos(),
                    list_takhasos[i].getSub_categuri_status(),list_takhasos[i].getUrl_aks_takhasos()));
            cardview_takhasos.setVisibility(View.VISIBLE);
            animatin_keshoei(cardview_takhasos);
            setup_recycler_View(cardview_takhasos.getId());
            nestedScrollView.scrollTo(0,x);
            cardView_saat_kari.setVisibility(View.VISIBLE);
            animatin_keshoei(cardView_saat_kari);
        }


    }

    @Override
    public void greftan_list_shahrha(List<String> list_shahrha, List<String> list_id_shahrha) {
     progress_and_dialog.dismis_progress();
        int_temparray_barayeh_shahrhayeh_entkhabi=new int[list_shahrha.size()];
        moshakhasat_motehkases.getManategh_shahrestan().clear();

        for(int j=0;j<list_shahrha.size();j++)
            Log.i("list_shahrha",list_shahrha.get(j));
        list_shahrestanha.clear();
        list_shahrestanha=list_shahrha;
        list_id_Shahrestanha.clear();
        list_id_Shahrestanha=list_id_shahrha;
        setup_recycler_View(cardView_manategh.getId());
        cardView_manategh.setVisibility(View.VISIBLE);
        animatin_keshoei(cardView_manategh);

    }

    @Override
    public void greftan_list_ostanha(List<String> list_ostanha)
    {
            this.list_ostanha=list_ostanha;
            progress_and_dialog.dismis_progress();
    }

    @Override
    public void greftan_list_manategh(List<String> list_manategh)
    {
    }

    @Override
    public void greftan_list_takhasos_level2(Model_Takhasos_level2[] list_takhasos_level2)
    {   progress_and_dialog.dismis_progress();
        object_list_takhasos_level2.clear();
        int_temparray_barayeh_takhasoshayeh_level2_entkhabi=new int[list_takhasos_level2.length];
          list_takhasos_level2_entkhabi=new Model_Takhasos_level2[list_takhasos_level2.length];

          list_takhasos_level2_entkhabi=list_takhasos_level2;
        for(int i=0;i<list_takhasos_level2.length;i++)
        {
            Log.i("list_takhasos_level2",list_takhasos_level2[i].getName_takhasos());
            object_list_takhasos_level2.add(new Model_Takhasos_level2_recyclerview(list_takhasos_level2[i].getName_takhasos()));
            cardview_takhasos_level2.setVisibility(View.VISIBLE);
            animatin_keshoei(cardview_takhasos_level2);
            setup_recycler_View(cardview_takhasos_level2.getId());

        }


    }

    @Override
    public void greftan_list_times(Model_Time[] list_time)
    {
        int_temparray_barayeh_time=new int[list_time.length];
        list_time_entkhabi=new  Model_Time[list_time.length];
        list_time_entkhabi=list_time;
        for(int i=0;i<list_time.length;i++)
        {
            Log.i("list_time1",list_time[i].getBegin_time());
            object_list_time.add(new Model_saat_kari_recyclerview(list_time[i].getBegin_time()+" - "+list_time[i].getEnd_time()));
        }
      //  cardView_saat_kari.setVisibility(View.VISIBLE);
      //
        //  animatin_keshoei(cardView_saat_kari);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divideritemdecoration_bottomlin));
        recycler_viewholder_adapter=new Recycler_Viewholder_Adapter(object_list_time);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,1);
       /// recyclerview_saat_kari.setLayoutManager(gridLayoutManager);
      //  recyclerview_saat_kari.addItemDecoration(dividerItemDecoration);
       // recyclerview_saat_kari.setAdapter(recycler_viewholder_adapter);


        //////////////////////////////////
        //setup_recycler_View(R.id.crdview_recyclerview_manategh);


    }
  ////////////////upload worktime call in this function
    @Override
    public void greftan_id_motakhases(String id_motakhases) {
        progress_and_dialog.dismis_progress();
        if(id_motakhases.contentEquals("-1"))
        {
            Log.i("greftan_id_motakhses","متخصص با این کد ملی ثبت نشده است");
            moshakhasat_motehkases.setId("-1");

        }
        else {

            /////////////////////////////////////
             moshakhasat_motehkases.setId(id_motakhases);
             //////////////////////////////////////

            valley_api.upload_saat_kari("1",moshakhasat_motehkases.getList_saatKari_shanbeh(),id_motakhases);
            progress_and_dialog.show_progress();









///////////////////////////////////////////////////
            //////////1=شنبه و۲= دو شنبه و /////
//            ///string  concat
//            String stringAllWorkTime=id+"%"+"1"+"%"+moshakhasat_motehkases.getList_saatKari_shanbeh().get(0)+"%"
//                    +moshakhasat_motehkases.getList_saatKari_shanbeh().get(1)+"%"+
//                    "2"+"%"+
//                    moshakhasat_motehkases.getList_saatKari_yek_shanbeh().get(0)+"%"
//                    +moshakhasat_motehkases.getList_saatKari_yek_shanbeh().get(1)+
//                    "%"+"3"+"%"+
//                    moshakhasat_motehkases.getList_saatKari_do_shanbeh().get(0)+"%"
//                    +moshakhasat_motehkases.getList_saatKari_do_shanbeh().get(1)+
//            "%"+"4"+"%"+
//                    moshakhasat_motehkases.getList_saatKari_seh_shanbeh().get(0)+"%"
//                    +moshakhasat_motehkases.getList_saatKari_seh_shanbeh().get(1)+
//            "%"+"5"+"%"+
//                    moshakhasat_motehkases.getList_saatKari_chehar_shanbeh().get(0)+"%"
//                    +moshakhasat_motehkases.getList_saatKari_chehar_shanbeh().get(1)+
//                    "%"+"6"+"%"+
//                    moshakhasat_motehkases.getList_saatKari_panj_shanbeh().get(0)+"%"
//                    +moshakhasat_motehkases.getList_saatKari_panj_shanbeh().get(1)+
//                    "%"+"7"+"%"+
//                    moshakhasat_motehkases.getList_saatKari_jomeh().get(0)+"%"
//                    +moshakhasat_motehkases.getList_saatKari_jomeh().get(1);
//            valley_api.upload_saat_kari_use_tempstring(stringAllWorkTime);
         }
    }

    @Override
    public void upload_status(Boolean error, String feild_name)  {
        //error=false------> upload is ok
        Log.i("upload_status_err",feild_name+": "+String.valueOf(error));

        if (error)
        {
            progress_and_dialog=new Progress_And_Dialog(this,alertDialog);
            progress_and_dialog.alert_dialog_method("error","ثبت نهایی","ثبت نهایی ناموفق بود");

            Log.i("upload_status_errin",feild_name);
            //Toast.makeText(this, "عملیات نا موفق", Toast.LENGTH_SHORT).show();
        }
     else {
            switch (feild_name) {

                case Const_Variable.UPLOAD_MOSHAKHAST_FARDI:

                    if (error) {
                        moshakhasat_motehkases.setStatus_uplad_moshakhasat_fardi(!error);


                    } else {
                        moshakhasat_motehkases.setStatus_uplad_moshakhasat_fardi(!error);

                        setup_spiners();

                        greftan_list_takhasos_ha_as_server();
                        txt_shroeh_shanbeh.requestFocus();
                        progress_and_dialog.dismis_progress();

                    }
                    break;

                case Const_Variable.UPLOAD_WORK_TIMES:

                    if (error) {
                        moshakhasat_motehkases.setStatus_upload_workTimes(!error);

                    } else {
                        moshakhasat_motehkases.setStatus_upload_workTimes(!error);
                        number_of_day_uploaded++;
                        progress_and_dialog.dismis_progress();
                        if(number_of_day_uploaded==7) {
                            progress_and_dialog.dismis_progress();
                            ///////////////upload manategh khedmat dahi
                            String id_manategh_str_temp = "";

                            for (int i = 0; i < moshakhasat_motehkases.getList_id_manategh_khedmatdahi().size(); i++) {
                                if (i< moshakhasat_motehkases.getList_id_manategh_khedmatdahi().size()-1)
                                id_manategh_str_temp += moshakhasat_motehkases.
                                        getList_id_manategh_khedmatdahi().get(i) + "%";
                                else
                                    id_manategh_str_temp += moshakhasat_motehkases.
                                            getList_id_manategh_khedmatdahi().get(i);
                                Log.i("upload_manategh", id_manategh_str_temp);
                            }
                            valley_api.upload_list_manategh_khedmatdahi(id_manategh_str_temp, moshakhasat_motehkases.getId());
                            progress_and_dialog.show_progress();
                        }else
                        {
                            upload_next_day_work_tims(number_of_day_uploaded);
                        }
                        ////////////////////////////


                    }
                    break;

                case Const_Variable.UPLOAD_MANATEGH_KHEDMAT_DAHI:

                    if (error)
                        moshakhasat_motehkases.setStatus_upload_manateghKHedmatDahi(!error);
                    else {
                        moshakhasat_motehkases.setStatus_upload_manateghKHedmatDahi(!error);
                        ///////////////////////////upload list takhasos && list takhasos level2

                        /////////////////data template to  send is:   idtakhasos,idtakhasoslevel1

                        String id_takhasos_str_temp=new String();
                        if(moshakhasat_motehkases.getList_takhasos_level2().size()>0)
                        {
                            ////////////////////////id_takhasos_str_temp  =>  idtakhasoslevel2+%+idtakhasoslevel1
                            id_takhasos_str_temp="";
                            for(int i=0;i<moshakhasat_motehkases.getList_id_takhasos_level2().size();i++)
                            {
                                id_takhasos_str_temp+=moshakhasat_motehkases.getList_id_takhasos_level2().get(i)+"%"+
                                        moshakhasat_motehkases.getList_id_takhasos_level1().get(i)+"%";
                                Log.i("upload_takhasos2",id_takhasos_str_temp);
                            }

                            valley_api.upload_list_takhasos(id_takhasos_str_temp,moshakhasat_motehkases.getId());
                            progress_and_dialog.show_progress();
                        }
                        else
                        {
                            ////////////////////////id_takhasos_str_temp  =>  idtakhasos%//       //= idtakhasoslevel1

                            id_takhasos_str_temp="";
                            for(int i=0;i<moshakhasat_motehkases.getList_id_takhasos().size();i++)
                            {
                                id_takhasos_str_temp+=moshakhasat_motehkases.getList_id_takhasos().get(i)+"%"+"//"+"%";
                                Log.i("upload_takhasos",id_takhasos_str_temp);
                            }
                            valley_api.upload_list_takhasos(id_takhasos_str_temp,moshakhasat_motehkases.getId());
                            progress_and_dialog.show_progress();
                        }
                    }
                    break;

                case Const_Variable.UPLOAD_TAKHASOS:

                    if (error) {
                        moshakhasat_motehkases.setStatus_upload_listTakhasos(!error);

                    } else {
                        moshakhasat_motehkases.setStatus_upload_listTakhasos(!error);


                        Log.i("upload_status","is ok");


                        if(moshakhasat_motehkases.getStatus_upload_listTakhasos()
                        && moshakhasat_motehkases.getStatus_upload_manateghKHedmatDahi()
                        && moshakhasat_motehkases.getStatus_upload_workTimes()&&
                        moshakhasat_motehkases.getStatus_uplad_moshakhasat_fardi())
                        {
                             valley_api.register_status(Const_Variable.REGISTER_STATUS_IS_DARHALE_BARRASI,
                             moshakhasat_motehkases.getId(),moshakhasat_motehkases.getName()+" "+moshakhasat_motehkases.getFamily());
                            progress_and_dialog.show_progress();

                            Log.i("upload_status_nahaei","\ngetStatus_upload_listTakhasos:  "+
                                    moshakhasat_motehkases.getStatus_upload_listTakhasos());
                            Log.i("upload_status_nahaei","\ngetStatus_upload_manateghKHedmatDahi:  "+
                                    moshakhasat_motehkases.getStatus_upload_manateghKHedmatDahi());

                            Log.i("upload_status_nahaei","\ngetStatus_upload_workTimes:  "+
                                    moshakhasat_motehkases.getStatus_upload_workTimes());
                            Log.i("upload_status_nahaei","\ngetStatus_uplad_moshakhasat_fardi:  "+
                                    moshakhasat_motehkases.getStatus_uplad_moshakhasat_fardi());
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("id_motakhases",moshakhasat_motehkases.getId());
                            editor.putString("password",moshakhasat_motehkases.getRamzevrod());
                            editor.putString("codemeli",moshakhasat_motehkases.getCodemeli());
                            editor.apply();
                            progress_and_dialog.alert_dialog_register_success_full(moshakhasat_motehkases.getId()
                            ,"پایان ثبت نام ","طی 24 ساعت آینده در صورت تایید مدارک، حساب شما فعال می شود");


                        }
                        else
                        {

                            Log.i("upload_status_nahaei","\ngetStatus_upload_listTakhasos:  "+
                                    moshakhasat_motehkases.getStatus_upload_listTakhasos());
                            Log.i("upload_status_nahaei","\ngetStatus_upload_manateghKHedmatDahi:  "+
                                    moshakhasat_motehkases.getStatus_upload_manateghKHedmatDahi());

                            Log.i("upload_status_nahaei","\ngetStatus_upload_workTimes:  "+
                                    moshakhasat_motehkases.getStatus_upload_workTimes());
                            Log.i("upload_status_nahaei","\ngetStatus_uplad_moshakhasat_fardi:  "+
                                    moshakhasat_motehkases.getStatus_uplad_moshakhasat_fardi());

                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }


                            if(moshakhasat_motehkases.getStatus_upload_listTakhasos()
                                                && moshakhasat_motehkases.getStatus_upload_manateghKHedmatDahi()
                                                && moshakhasat_motehkases.getStatus_upload_workTimes()&&
                                                moshakhasat_motehkases.getStatus_uplad_moshakhasat_fardi())
                                        {

                                            valley_api.register_status(Const_Variable.REGISTER_STATUS_IS_DARHALE_BARRASI,
                                                    moshakhasat_motehkases.getId(),moshakhasat_motehkases.getName()+" "+moshakhasat_motehkases.getFamily());
                                            progress_and_dialog.show_progress();

                                            Log.i("upload_status_nahaei","\ngetStatus_upload_listTakhasos:  "+
                                                    moshakhasat_motehkases.getStatus_upload_listTakhasos());
                                            Log.i("upload_status_nahaei","\ngetStatus_upload_manateghKHedmatDahi:  "+
                                                    moshakhasat_motehkases.getStatus_upload_manateghKHedmatDahi());

                                            Log.i("upload_status_nahaei","\ngetStatus_upload_workTimes:  "+
                                                    moshakhasat_motehkases.getStatus_upload_workTimes());
                                            Log.i("upload_status_nahaei","\ngetStatus_uplad_moshakhasat_fardi:  "+
                                                    moshakhasat_motehkases.getStatus_uplad_moshakhasat_fardi());
                                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                            SharedPreferences.Editor editor = preferences.edit();
                                            editor.putString("id_motakhases",moshakhasat_motehkases.getId());
                                            editor.putString("password",moshakhasat_motehkases.getRamzevrod());
                                            editor.putString("codemeli",moshakhasat_motehkases.getCodemeli());
                                            editor.apply();
                                            Intent intent = new Intent(this, A_Safhe_vrod_sabtenam.class);
                                            intent.putExtra("id",moshakhasat_motehkases.getId());

                                            startActivity(intent);
                                            finish();
                                        }
                                        else
                                        {

                                            valley_api.register_status(Const_Variable.REGISTER_STATUS_IS_NOT_OK,
                                                    moshakhasat_motehkases.getId(),moshakhasat_motehkases.getName()+" "+moshakhasat_motehkases.getFamily());

                                              Toast.makeText(this, "عملیات نا موفق", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(this, A_Safhe_vrod_sabtenam.class);
                                            startActivity(intent);
                                             finish();
                                        }


                        }

                    }
                    break;
                default:
            }
        }
    }

    private void upload_next_day_work_tims(int number_of_day_uploaded)
    {
        //////////////آپلود روز شنبه در تابع اوورراید شده getidmotakhases فراخوانی شده است
        switch (number_of_day_uploaded)
        {
            case 1:

                valley_api.upload_saat_kari("2",moshakhasat_motehkases.getList_saatKari_yek_shanbeh(),moshakhasat_motehkases.getId());
                progress_and_dialog.show_progress();
                break;

            case 2:

                valley_api.upload_saat_kari("3",moshakhasat_motehkases.getList_saatKari_do_shanbeh(),moshakhasat_motehkases.getId());
                progress_and_dialog.show_progress();
                break;

            case 3:

                valley_api.upload_saat_kari("4",moshakhasat_motehkases.getList_saatKari_seh_shanbeh(),moshakhasat_motehkases.getId());
                progress_and_dialog.show_progress();
                break;

            case 4:

                valley_api.upload_saat_kari("5",moshakhasat_motehkases.getList_saatKari_chehar_shanbeh(),moshakhasat_motehkases.getId());
                progress_and_dialog.show_progress();
                break;

            case 5:

                valley_api.upload_saat_kari("6",moshakhasat_motehkases.getList_saatKari_panj_shanbeh(),moshakhasat_motehkases.getId());
                progress_and_dialog.show_progress();
                break;

            case 6:

                valley_api.upload_saat_kari("7",moshakhasat_motehkases.getList_saatKari_jomeh(),moshakhasat_motehkases.getId());
                progress_and_dialog.show_progress();
                break;
            default:
        }
    }

    @Override
    public void recive_data_from_server_failur(String error_status, String title_error, String error_message)
    {
       progress_and_dialog=new Progress_And_Dialog(this,alertDialog);
       progress_and_dialog.alert_dialog_method(error_status,title_error,error_message);
    }

    @Override
    public void username_athuntication(String status_username)
    {

    }

    @Override
    public void password_athuntication(MotakhasesObject motakhasesObject) {

    }

    @Override
    public void get_profile_data(Model_Profile model_profile) {

    }

    @Override
    public void greftan_list_manategh_entkhabi_have_childeren_as_recycler_view_adapter
            (String takhasos_name, int index,  boolean chtxt_ischecked) {


    }



    public void get_servics_have_subcategory
            (String takhasos_name, int index,  boolean chtxt_ischecked) {

        if(chtxt_ischecked)
        {
            int_temparray_barayeh_takhasoshayeh_entkhabi[index] = 1;
            cardview_takhasos_level2.setVisibility(View.INVISIBLE);
            valley_api.get_list_takhasos_level2(takhasos_name);

            progress_and_dialog.show_progress();
        }
        else
        {
            int_temparray_barayeh_takhasoshayeh_entkhabi[index] = 0;
            cardview_takhasos_level2.setVisibility(View.INVISIBLE);
        }

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

    @Override
    public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
        switch (scrollChoice.getId())
        {
            case R.id.scroolchoice_shroeh_shanbeh:
                setup_setting_scroolchoice_shroe(txt_payan_shanbeh,txt_shroeh_shanbeh,chtxt_shanbeh,scrolchoice_sheroeh_shanbeh,name);

                break;

            case R.id.scroolchoice_payan_shanbeh:
                setup_setting_scroolchoice_payan(txt_payan_shanbeh,txt_shroeh_shanbeh,chtxt_shanbeh,scrolchoice_payan_shanbeh,name);
                break;
                ////////////////
            ////////////////////

            case R.id.scroolchoice_shroeh_yek_shanbeh:
                setup_setting_scroolchoice_shroe(txt_payan_yek_shanbeh,txt_shroeh_yek_shanbeh,chtxt_yek_shanbeh,
                        scrolchoice_sheroeh_yek_shanbeh, name);

                break;

            case R.id.scroolchoice_payan_yek_shanbeh:
                setup_setting_scroolchoice_payan(txt_payan_yek_shanbeh,txt_shroeh_yek_shanbeh,chtxt_yek_shanbeh,
                        scrolchoice_payan_yek_shanbeh, name);
                break;
                ////////////////////////
            ////////////////////////////

            case R.id.scroolchoice_shroeh_do_shanbeh:
                setup_setting_scroolchoice_shroe(txt_payan_do_shanbeh,txt_shroeh_do_shanbeh,chtxt_do_shanbeh,
                        scrolchoice_sheroeh_do_shanbeh, name);

                break;

            case R.id.scroolchoice_payan_do_shanbeh:
                setup_setting_scroolchoice_payan(txt_payan_do_shanbeh,txt_shroeh_do_shanbeh,chtxt_do_shanbeh,
                        scrolchoice_payan_do_shanbeh, name);
                break;
                ///////////////////////////
            ////////////////////////////

            case R.id.scroolchoice_shroeh_seh_shanbeh:
                setup_setting_scroolchoice_shroe(txt_payan_seh_shanbeh,txt_shroeh_seh_shanbeh,chtxt_seh_shanbeh,
                        scrolchoice_sheroeh_seh_shanbeh, name);

                break;

            case R.id.scroolchoice_payan_seh_shanbeh:
                setup_setting_scroolchoice_payan(txt_payan_seh_shanbeh,txt_shroeh_seh_shanbeh,chtxt_seh_shanbeh,
                        scrolchoice_payan_seh_shanbeh, name);
                break;
                /////////////////////
            ///////////////////////////

            case R.id.scroolchoice_shroeh_chehar_shanbeh:
                setup_setting_scroolchoice_shroe(txt_payan_chehar_shanbeh,txt_shroeh_chehar_shanbeh,chtxt_chehar_shanbeh,
                        scrolchoice_sheroeh_chehar_shanbeh, name);

                break;

            case R.id.scroolchoice_payan_chehar_shanbeh:
                setup_setting_scroolchoice_payan(txt_payan_chehar_shanbeh,txt_shroeh_chehar_shanbeh,chtxt_chehar_shanbeh,
                        scrolchoice_payan_chehar_shanbeh, name);
                break;
                ///////////////
            /////////////////////////

            case R.id.scroolchoice_shroeh_panj_shanbeh:
                setup_setting_scroolchoice_shroe(txt_payan_panj_shanbeh,txt_shroeh_panj_shanbeh,chtxt_panj_shanbeh,
                        scrolchoice_sheroeh_panj_shanbeh, name);

                break;

            case R.id.scroolchoice_payan_panj_shanbeh:
                setup_setting_scroolchoice_payan(txt_payan_panj_shanbeh,txt_shroeh_panj_shanbeh,chtxt_panj_shanbeh,
                        scrolchoice_payan_panj_shanbeh, name);
                break;
                ////////////////////////
            //////////////////////

            case R.id.scroolchoice_shroeh_jomeh:
                setup_setting_scroolchoice_shroe(txt_payan_jomeh,txt_sheroeh_jomeh,chtxt_jomeh,
                        scrolchoice_sheroeh_jomeh, name);

                break;

            case R.id.scroolchoice_payan_jomeh:
                setup_setting_scroolchoice_payan(txt_payan_jomeh,txt_sheroeh_jomeh,chtxt_jomeh,
                        scrolchoice_payan_jomeh, name);
                break;
            default:
        }

    }

    private void setup_setting_scroolchoice_payan(TextView txt_payan, TextView txt_shroeh,
                                                  AppCompatCheckedTextView chtxt, ScrollChoice scrolchoice_payan, String name)
    {

        if (  !name.contains("-:-"))
            if(txt_shroeh.getText().toString().contains("-:-"))
            {
                scrolchoice_payan.setSelectedItemPosition(13);

                scrolchoice_sheroeh_shanbeh.requestFocus();

            }
            else if(!name.contains(txt_shroeh.getText().toString()))
            {
                txt_payan.setText(name);
                chtxt.setCheckMarkDrawable(R.drawable.checked_circle);
                chtxt.setChecked(true);

            }
            else
            {
                scrolchoice_payan.setSelectedItemPosition(13);
                txt_payan.setText("-:-");
                chtxt.setCheckMarkDrawable(null);
                chtxt.setChecked(false);



            }

        else
        {
            scrolchoice_payan.setSelectedItemPosition(13);
            txt_payan.setText("-:-");
            chtxt.setCheckMarkDrawable(null);
            chtxt.setChecked(false);



        }

    }

    private void setup_setting_scroolchoice_shroe(TextView txt_payan, TextView txt_shroeh,
                                                  AppCompatCheckedTextView chtxt, ScrollChoice scrolchoice_sheroeh, String name)
    {
        if(!name.contains("-:-"))
        {

            if(((!(txt_payan.getText().toString().contains("-:-")))&&
                    (!(name.contains(txt_payan.getText().toString())))))
            {
                txt_shroeh.setText(name);

                chtxt.setCheckMarkDrawable(R.drawable.checked_circle);
                chtxt.setChecked(true);
            }
            else if(name.contains(txt_payan.getText().toString()))
            {

                scrolchoice_sheroeh.setSelectedItemPosition(13);
                txt_shroeh.setText("-:-");
                chtxt.setCheckMarkDrawable(null);
                chtxt.setChecked(false);
            }
            else
            {

                txt_shroeh.setText(name);
            }

        }
        else
        {

             txt_shroeh.setText(name);

            chtxt.setCheckMarkDrawable(null);
            chtxt.setChecked(false);
        }
    }
}
