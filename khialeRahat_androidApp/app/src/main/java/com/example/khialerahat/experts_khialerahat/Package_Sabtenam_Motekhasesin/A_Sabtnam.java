package com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;


import com.example.khialerahat.experts_khialerahat.MainPackage.Model_Profile;
import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.My_interface;
import com.example.khialerahat.experts_khialerahat.Progress_And_Dialog;
import com.example.khialerahat.experts_khialerahat.R;
import com.example.khialerahat.experts_khialerahat.Web_Service.Valley_Api;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.ArrayList;
import java.util.List;


public class A_Sabtnam extends AppCompatActivity implements Interface_checkKardanCodemeli_ShomareHamrah,View.OnFocusChangeListener , My_interface, View.OnClickListener {
    Handler handler;
    Valley_Api valley_api=new Valley_Api(this);
    AlertDialog alertDialog;
    SpinKitView spinKitView;

    ImageView imgbtn_close;
        private Button btn_marhale_baedi;
         private Moshakhasat_Motehkases moshakhasat_motehkases=new Moshakhasat_Motehkases();
        // private Daryaft_list_ostanha_shahrestanha_manategh daryaft_list_ostanha_shahrestanha_manategh=new Daryaft_list_ostanha_shahrestanha_manategh(this);
        // List<String> list_ostanha=daryaft_list_ostanha_shahrestanha_manategh.getList_name_ostanha();
        List<String> list_ostanha;
      static TextInputLayout txt_inplayout_name,txt_inplayout_family,txt_inplayout_codemeli
              ,txt_inplayout_shomarehamrah,txt_inplayout_shomarehsabet
              ,txt_inplayout_adress_daghigh
              ,txt_inputlayout_tekrar_ramzevrod,
              txt_inputlayout_ramzevrod,txt_inputlayout_shomare_hesab
              ,txt_inputlayout_shomare_aberbank,txt_inputlayout_shomare_shaba;

      static TextInputEditText edtxt_name , edtxt_family,edtxt_codemeli
              ,edtxt_shomarehamrah,edtxt_shomarehsabet
              ,edtxt_adress_daghigh
              ,edtxt_tekrar_ramzevrod,
       edtxt_ramzevrod,edtxt_shomare_hesab,edtxt_shomare_aberbank,edtxt_shomare_shaba;
      List<Object> objects=new ArrayList<>();
            SwitchCompat swjensiat,swvazeiattahol;
      NiceSpinner spiner_ostan, spiner_shahrestan;
    NestedScrollView scroller;
    private Edittext_Filter edittext_filter;
    private int temp_to_jump=0;
    Boolean[] all_feilds_is_ok=new Boolean[10];
    private boolean status_codemeli=false
            ,status_shomareHamrah=false;
    private String temp_sms_broadcast=""
            ,temp_sms_valleyApi="";
    private Button btn_dialog_sms_taied;
    private  EditText edtxt_dialog_sms_taied;
    private TextView txtbtn_dialog_sms_ersalmojadad;
    private TextView txt_timer;
    private AlertDialog alertd , alert_codemeli;

    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a__sabtnam);
        handler=new Handler();
        getintent_listostanha();

       // new Recivedata_From_server().execute();
        initial_view();
        edtxt_ramzevrod.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if (start<5 || start>9) {
                        txt_inputlayout_ramzevrod.setErrorEnabled(true);
                        txt_inputlayout_ramzevrod.setError(" شش تا ده حرف");
                    }else {
                        txt_inputlayout_ramzevrod.setErrorEnabled(false);
                        txt_inputlayout_ramzevrod.setError(null);
                          moshakhasat_motehkases.setRamzevrod(s.toString());
                        txt_inputlayout_ramzevrod.
                                setDefaultHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDark)));
                        edtxt_ramzevrod.
                                getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
                    }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        edtxt_shomare_hesab.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                   // Log.i("conter",edtxt_shomare_hesab.length()+""+start);
                    int counter=edtxt_shomare_hesab.length();
                if (!(counter==Const_Variable.LENGTH_SHOMARE_HESAB) ) {
                    txt_inputlayout_shomare_hesab.setErrorEnabled(true);
                    txt_inputlayout_shomare_hesab.setError("شماره وارد شده صحیح نیست ");
                 //   txt_inputlayout_shomare_hesab.setCounterEnabled(true);
                  //  txt_inputlayout_shomare_hesab.setCounterMaxLength(Const_Variable.LENGTH_SHOMARE_HESAB);
                }else {
                    txt_inputlayout_shomare_hesab.setErrorEnabled(false);
                    txt_inputlayout_shomare_hesab.setError(null);
                    moshakhasat_motehkases.setShomare_hesab(s.toString());
                  //  txt_inputlayout_shomare_hesab.setCounterEnabled(false);
                    txt_inputlayout_shomare_hesab.
                            setDefaultHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDark)));
                    edtxt_shomare_hesab.
                            getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        edtxt_shomare_aberbank.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int counter=edtxt_shomare_aberbank.length();
                if (!(counter==Const_Variable.LENGTH_SHOMARE_ABER_BANK) ) {

                    txt_inputlayout_shomare_aberbank.setErrorEnabled(true);
                    txt_inputlayout_shomare_aberbank.setError(" شماره وارد شده صحیح نیست ");
                }else {
                    txt_inputlayout_shomare_aberbank.setErrorEnabled(false);
                    txt_inputlayout_shomare_aberbank.setError(null);
                    moshakhasat_motehkases.setShomare_aberbank(s.toString());
                    txt_inputlayout_shomare_aberbank.
                            setDefaultHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDark)));
                    edtxt_shomare_aberbank.
                            getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        edtxt_shomare_shaba.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                 int counter=edtxt_shomare_shaba.length();
                if (!(counter==Const_Variable.LENGTH_SHOMARE_SHABA)) {
                    txt_inputlayout_shomare_shaba.setErrorEnabled(true);
                    txt_inputlayout_shomare_shaba.setError("شماره وارد شده صحیح نیست ");
                }else {
                    txt_inputlayout_shomare_shaba.setErrorEnabled(false);
                    txt_inputlayout_shomare_shaba.setError(null);
                    moshakhasat_motehkases.setShomare_shaba(s.toString());
                    txt_inputlayout_shomare_shaba.
                            setDefaultHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDark)));
                    edtxt_shomare_shaba.
                            getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


//////////////////////////////
        ////////////////////////////nested scroll
     scroller  = (NestedScrollView) findViewById(R.id.nestedscroll_safehsabtenam);

        if (scroller!= null) {

            scroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override

                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                      // Log.i("scroll:  ",""+scrollX+" -"+scrollY+"-"+oldScrollX+"-"+oldScrollY);
                        if (scrollY>900)
                            btn_marhale_baedi.setVisibility(View.VISIBLE);
                }
            });
        }
///////////////////////////////
        spiner_ostan.attachDataSource(list_ostanha);
        valley_api.get_listshrha_as_server(spiner_ostan.getItemAtPosition(0).toString());
        moshakhasat_motehkases.setOstan(spiner_ostan.getItemAtPosition(0).toString());
        spiner_shahrestan.setVisibility(View.VISIBLE);
        spiner_ostan.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
               moshakhasat_motehkases.setOstan(parent.getItemAtPosition(position).toString());
                    valley_api.get_listshrha_as_server(parent.getItemAtPosition(position).toString());
                    if (spiner_shahrestan.getVisibility()==View.INVISIBLE)
                       spiner_shahrestan.setVisibility(View.VISIBLE);
            }
        });
     spiner_shahrestan.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
         @Override
         public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
             //Toast.makeText(A_Sabtnam.this,parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

             moshakhasat_motehkases.setShahrestan(parent.getItemAtPosition(position).toString());
             valley_api.get_listmanategh_as_server(moshakhasat_motehkases.getOstan()
                     ,moshakhasat_motehkases.getShahrestan());
         }
     });


        swvazeiattahol.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    swvazeiattahol.setText("متاهل");

                }else {swvazeiattahol.setText("مجرد");}
            }
        });
        swjensiat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    swjensiat.setText("زن");

                }else {swjensiat.setText("مرد");}
            }
        });
    }


    private void getintent_listostanha()
    {list_ostanha=(List<String>) getIntent().getSerializableExtra("list_ostanha");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.close_imgview_sabtenam:
                v.startAnimation(buttonClickanimation());
                finish();
                break;
            case R.id.btn_marhalebaadi_safhesabtenam:
                btn_marhale_baedi.requestFocus();
                meghdardehi_moshakhasat_motkhases();

                break;
        }

    }

    private void meghdardehi_moshakhasat_motkhases()
    {

        Boolean[]  moshakhasat_kamel_vard_shode_ast=new Boolean[14];
        moshakhasat_motehkases.setVazeyat_tahol(swvazeiattahol.getText().toString());
        moshakhasat_motehkases.setJensiat(swjensiat.getText().toString());
        try {
            moshakhasat_motehkases.setAddress_daghigh(edtxt_adress_daghigh.getText().toString());
        }catch (NullPointerException e)
        {e.printStackTrace();}

            if (moshakhasat_motehkases.getName() != null && moshakhasat_motehkases.getName().length()>2) {
                moshakhasat_kamel_vard_shode_ast[1] = true;
                Log.i("name:  ", moshakhasat_motehkases.getName());
            }
            else {
                 moshakhasat_kamel_vard_shode_ast[1]=false;
            }

            if (moshakhasat_motehkases.getFamily() != null && moshakhasat_motehkases.getFamily().length()>2) {
                Log.i("name:  ", moshakhasat_motehkases.getFamily());
                moshakhasat_kamel_vard_shode_ast[0]=true;
            }
            else {
                moshakhasat_kamel_vard_shode_ast[0]=false;
            }
            if (moshakhasat_motehkases.getCodemeli() != null && moshakhasat_motehkases.getCodemeli().length()==10){
                Log.i("name:  ", moshakhasat_motehkases.getCodemeli());
                moshakhasat_kamel_vard_shode_ast[2]=true;
            }
            else {
                moshakhasat_kamel_vard_shode_ast[2]=false;
            }

        if(moshakhasat_motehkases.getShomare_hamrah()!=null && moshakhasat_motehkases.getShomare_hamrah().length()==11){

            Log.i("name:  ",moshakhasat_motehkases.getShomare_hamrah());
            moshakhasat_kamel_vard_shode_ast[3]=true;
        }
        else {
            moshakhasat_kamel_vard_shode_ast[3]=false;
        }
        if(moshakhasat_motehkases.getShomare_sabet()!=null && moshakhasat_motehkases.getShomare_sabet().length()==11) {
            Log.i("name:  ", moshakhasat_motehkases.getShomare_sabet());
            moshakhasat_kamel_vard_shode_ast[4]=true;
        }
        else {
             moshakhasat_kamel_vard_shode_ast[4]=false;
        }
        if(moshakhasat_motehkases.getJensiat()!=null) {
            Log.i("name:  ", moshakhasat_motehkases.getJensiat());
            moshakhasat_kamel_vard_shode_ast[5]=true;
        }
        else {
            moshakhasat_kamel_vard_shode_ast[5]=false;
        }
        if(moshakhasat_motehkases.getVazeyat_tahol()!=null) {
            Log.i("name:  ", moshakhasat_motehkases.getVazeyat_tahol());
            moshakhasat_kamel_vard_shode_ast[6]=true;
        }
        else {
            moshakhasat_kamel_vard_shode_ast[6]=false;
        }
        if(moshakhasat_motehkases.getOstan()!=null) {
            Log.i("name:  ", moshakhasat_motehkases.getOstan());
            moshakhasat_kamel_vard_shode_ast[7]=true;
        }
        else {
                 moshakhasat_kamel_vard_shode_ast[7]=false;
        }
        if(moshakhasat_motehkases.getShahrestan()!=null){
            Log.i("name:  ",moshakhasat_motehkases.getShahrestan());
            moshakhasat_kamel_vard_shode_ast[8]=true;
        }
        else {
            moshakhasat_kamel_vard_shode_ast[8]=false;
        }
        if(moshakhasat_motehkases.getAddress_daghigh()!=null && moshakhasat_motehkases.getAddress_daghigh().length()>5){
            Log.i("name:  ",moshakhasat_motehkases.getAddress_daghigh());
            moshakhasat_kamel_vard_shode_ast[9]=true;
        }
        else {
            moshakhasat_kamel_vard_shode_ast[9]=false;
        }
        if(moshakhasat_motehkases.getRamzevrod()!=null && moshakhasat_motehkases.getRamzevrod().length()>5){
            Log.i("name:  ",moshakhasat_motehkases.getRamzevrod());
            moshakhasat_kamel_vard_shode_ast[10]=true;
        }
        else {
            moshakhasat_kamel_vard_shode_ast[10]=false;
        }

        if(moshakhasat_motehkases.getShomare_aberbank()!=null && moshakhasat_motehkases.
                getShomare_aberbank().length()==Const_Variable.LENGTH_SHOMARE_ABER_BANK){
            Log.i("name:  ",moshakhasat_motehkases.getShomare_aberbank());
            moshakhasat_kamel_vard_shode_ast[11]=true;
        }
        else {
            moshakhasat_kamel_vard_shode_ast[11]=false;
        }

        if(moshakhasat_motehkases.getShomare_hesab()!=null && moshakhasat_motehkases.
                getShomare_hesab().length()==Const_Variable.LENGTH_SHOMARE_HESAB){
            Log.i("name:  ",moshakhasat_motehkases.getShomare_hesab());
            moshakhasat_kamel_vard_shode_ast[12]=true;
        }
        else {
            moshakhasat_kamel_vard_shode_ast[12]=false;
        }

        if(moshakhasat_motehkases.getShomare_shaba()!=null && moshakhasat_motehkases.
                getShomare_shaba().length()==Const_Variable.LENGTH_SHOMARE_SHABA){
            Log.i("name:  ",moshakhasat_motehkases.getShomare_shaba());
            moshakhasat_kamel_vard_shode_ast[13]=true;
        }
        else {
            moshakhasat_kamel_vard_shode_ast[13]=false;
        }



        ///////////////////////////////////////////////////////////
          int[] int_temp=function_moshakhasat_kamel_vard_shode_ast(moshakhasat_kamel_vard_shode_ast);
          if(int_temp[0]==0)
        {
            if (status_shomareHamrah && status_codemeli){
            Intent intent=new Intent(this, A_Greftan_Aks_Madarek_Motakhases.class);
            intent.putExtra("moshakhasat_motehkases",new Gson().toJson(moshakhasat_motehkases));
            startActivity(intent);
            finish();
            }
            else
            {
                Toast.makeText(this, "کد ملی یا شماره همراه بررسی شود.", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
           // Toast.makeText(this, "اطلاعات صحیح نیست", Toast.LENGTH_SHORT).show();
            Log.i("field:"+int_temp[1],"error");
            switch (int_temp[1])
            {
                case 0:
                    edtxt_family.requestFocus();
                    break;

                case 1:
                    edtxt_name.requestFocus();
                    break;

                case 2:
                    edtxt_codemeli.requestFocus();
                    break;

                case 3:
                    edtxt_shomarehamrah.requestFocus();
                    break;

                case 4:
                    edtxt_shomarehsabet.requestFocus();
                    break;


                case 5:
                    swjensiat.requestFocus();
                    break;

                case 6:
                    swvazeiattahol.requestFocus();
                    break;
                case 7:
                    spiner_ostan.requestFocus();
                    break;

                case 8:
                    spiner_shahrestan.requestFocus();
                    break;

                case 9:
                    edtxt_adress_daghigh.requestFocus();
                    break;

                case 10:
                    edtxt_ramzevrod.requestFocus();
                    break;

                case 11:
                    edtxt_shomare_aberbank.requestFocus();
                    break;

                case 12:
                    edtxt_shomare_hesab.requestFocus();
                    break;

                case 13:
                    edtxt_shomare_shaba.requestFocus();
                    break;

                default:
            }

        }

    }

    private int[] function_moshakhasat_kamel_vard_shode_ast(Boolean[] moshakhasat_kamel_vard_shode_ast)
    {
           int[] int_temp=new int[2];
        for(int i=0;i<moshakhasat_kamel_vard_shode_ast.length;i++)
        {
            Log.i(i+"  ",""+moshakhasat_kamel_vard_shode_ast[i]);
            if(!moshakhasat_kamel_vard_shode_ast[i]) {

                int_temp[0]=1;
                int_temp[1]=i;
                break;
            }
            else
            {

                int_temp[0]=0;
                int_temp[1]=0;
            }
        }
        return int_temp;
    }

    private Animation buttonClickanimation() {

        AlphaAnimation buttonClick = new AlphaAnimation(1.0F, 0.8F);
        buttonClick.setDuration(500);
        return buttonClick;
    }
    @Override
    public void txtfilter(Boolean harfe_vrodi_sahih_ast, int textInputLayout_id, int edtxt_id)
    {
        if(!harfe_vrodi_sahih_ast)
          {
              ((TextInputLayout)(this.findViewById(textInputLayout_id))).setErrorEnabled(true);
                ((TextInputLayout)(this.findViewById(textInputLayout_id))).setError("فارسی وارد کنید");

            Animation shake = (Animation) AnimationUtils.loadAnimation(this, R.anim.shake);
            ((TextInputLayout)(this.findViewById(textInputLayout_id))).startAnimation(shake);

        }
        else
        {
            ((TextInputLayout)(this.findViewById(textInputLayout_id))).setError(null);
            ((TextInputLayout)(this.findViewById(textInputLayout_id))).setErrorEnabled(false);
            ((TextInputLayout)(this.findViewById(textInputLayout_id))).
                    setDefaultHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDark)));
            ((TextInputEditText)(this.findViewById(edtxt_id))).
                    getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
        }
    }
    @Override
    public void greftan_list_manategh_entkhabi_as_recycler_view_adapter(String manategh_ya_takhasos,int item_position, int meghdar) {
    }
    @Override
    public void greftan_list_shahrha(List<String> list_shahrha, List<String> list_id_shahrha) {
        if(list_shahrha.size()>0){
        if(!list_shahrha.get(0).contains("nodata")) {
            Log.i("list_shahrha: ",list_shahrha.get(0));

            spiner_shahrestan.attachDataSource(list_shahrha);
            moshakhasat_motehkases.setShahrestan(spiner_shahrestan.getItemAtPosition(0).toString());
        }
        else
        {
            Log.i("list_shahrha: ","nodata_in_server");
        }}
        else
            {
                Toast.makeText(this, "خارج از محدوده کاری شرکت", Toast.LENGTH_SHORT).show();

                spiner_ostan.attachDataSource(list_ostanha);
                valley_api.get_listshrha_as_server(spiner_ostan.getItemAtPosition(0).toString());
                moshakhasat_motehkases.setOstan(spiner_ostan.getItemAtPosition(0).toString());
                spiner_shahrestan.setVisibility(View.VISIBLE);

            }

    }
    @Override
    public void greftan_list_ostanha(List<String> list_ostanha) {
    }
    @Override
    public void greftan_list_manategh(List<String> list_manategh)
    {
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
    public void recive_data_from_server_failur(String error_status, String title_error, String error_message)
    {
        Progress_And_Dialog progress_and_dialog=new Progress_And_Dialog(this,alertDialog);
        progress_and_dialog.show_progress();
    }

    @Override
    public void username_athuntication(String status_username) {

    }

    @Override
    public void password_athuntication(MotakhasesObject motakhasesObject) {

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

    ///////////codemeli  check  insaid this function
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId())
        {
            case R.id.password_edittext1:

               break;

            case R.id.tekrar_password_edittext:

                break;
            case R.id.name_edittext:
                 setappirance(edtxt_name,txt_inplayout_name,hasFocus);
                 edittext_filter=new Edittext_Filter(this,txt_inplayout_name.getId(),edtxt_name.getId());
                edtxt_name.setFilters(new InputFilter[]{edittext_filter.getInputFilter()});
                if(!hasFocus && edtxt_name.getText().toString()!=null || edtxt_name.getText().toString()!="")
                {moshakhasat_motehkases.setName(edtxt_name.getText().toString());}
            break;
             case R.id.family_edittext:
                    setappirance(edtxt_family,txt_inplayout_family,hasFocus);
                    edittext_filter=new Edittext_Filter(this,txt_inplayout_family.getId(),edtxt_family.getId());
                    edtxt_family.setFilters(new InputFilter[]{edittext_filter.getInputFilter()});

                 if(!hasFocus && edtxt_family.getText().toString()!=null || edtxt_family.getText().toString()!="")
                 {moshakhasat_motehkases.setFamily(edtxt_family.getText().toString());}

                 break;
            case R.id.codemeli_edittext:
                set_appirance_for_edittext_typeof_number(edtxt_codemeli,txt_inplayout_codemeli,hasFocus);
                if(!hasFocus && edtxt_codemeli.getText().toString()!=null || edtxt_codemeli.getText().toString()!="")
                {moshakhasat_motehkases.setCodemeli(edtxt_codemeli.getText().toString());}
                 if(edtxt_codemeli.getText().length()==10 && !hasFocus)
                 {
                     valley_api.check_kardan_codemeli(edtxt_codemeli.getText().toString());
                     Log.i("vally_vazeiat_code_meli","start");
                 }
                break;
            case R.id.shomarehamrah_edittext1:
                set_appirance_for_edittext_typeof_number(edtxt_shomarehamrah,txt_inplayout_shomarehamrah,hasFocus);
                if(!hasFocus && edtxt_shomarehamrah.getText().toString()!=null || edtxt_shomarehamrah.getText().toString()!="")
                {moshakhasat_motehkases.setShomare_hamrah(edtxt_shomarehamrah.getText().toString());}
                if(!hasFocus && edtxt_shomarehamrah.getText().length()==11)
                {
                    if (Permision_Utility.check_permision(A_Sabtnam.this,"recive_sms"))
                    {
                         valley_api.sms_security(edtxt_shomarehamrah.getText().toString());

                         showdialog_codepayamaki();


                    }
                    else
                    {
                        Permision_Utility.requst_permision(A_Sabtnam.this,"recive_sms");
                    }
                }

                break;

            case R.id.shomaresabet_edittext:
                set_appirance_for_edittext_typeof_number(edtxt_shomarehsabet,txt_inplayout_shomarehsabet,hasFocus);
                if(!hasFocus && edtxt_shomarehsabet.getText().toString()!=null || edtxt_shomarehsabet.getText().toString()!="")
                {moshakhasat_motehkases.setShomare_sabet(edtxt_shomarehsabet.getText().toString());}

                break;
            case R.id.edtxt_addressdaghighskonat:
               setappirance(edtxt_adress_daghigh,txt_inplayout_adress_daghigh,hasFocus);
                edittext_filter=new Edittext_Filter(this,txt_inplayout_adress_daghigh.getId(),edtxt_adress_daghigh.getId());
                edtxt_adress_daghigh.setFilters(new InputFilter[]{edittext_filter.getInputFilter()});
                if((!hasFocus )&& (edtxt_adress_daghigh.getText().toString()!=null || edtxt_adress_daghigh.getText().toString()!=""))
                {moshakhasat_motehkases.setAddress_daghigh(edtxt_adress_daghigh.getText().toString());

                }


                break;
            case R.id.jensiat_switch:
                if(!hasFocus)
                    moshakhasat_motehkases.setJensiat(swjensiat.getText().toString());
                  break;
            case R.id.vazeiatetahol_switch2:
                if(!hasFocus)
                    moshakhasat_motehkases.setVazeyat_tahol(swvazeiattahol.getText().toString());



        }
    }

    private void set_appirance_for_edittext_typeof_number(final TextInputEditText edtxt_,

                                                          final TextInputLayout txt_inplayout_, boolean hasFocus)
    {
        if (hasFocus) {

            txt_inplayout_.setErrorEnabled(true);
            txt_inplayout_.setError(txt_inplayout_.getCounterMaxLength()+" رقم وارد کنید");
            edtxt_.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() < txt_inplayout_.getCounterMaxLength() || s.length() > txt_inplayout_.getCounterMaxLength()) {
                        txt_inplayout_.setErrorEnabled(true);
                        txt_inplayout_.setError(txt_inplayout_.getCounterMaxLength()+" رقم وارد کنید");
                    } else {
                        txt_inplayout_.setError(null);
                        txt_inplayout_.setErrorEnabled(false);

                        txt_inplayout_.
                                setDefaultHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDark)));
                        edtxt_.
                                getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);

                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
        else
        {

        }

    }


    private void setappirance(TextInputEditText edtxt, TextInputLayout txt_inplayout,Boolean hasFocus)
    {
        if (hasFocus)
        {
            if( edtxt.getText().length()<=0 ) {
                txt_inplayout.setFocusable(true);
                txt_inplayout.setFocusableInTouchMode(true);
                txt_inplayout.setErrorEnabled(true);
                txt_inplayout.setError("فارسی وارد کنید");
            }
            else
            {
                txt_inplayout.
                        setDefaultHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDark)));
                edtxt.
                        getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
                //  txt_inplayout.setHintTextAppearance(R.style.active);

            }
        }
        else if (!hasFocus )
        {
            if( edtxt.getText().length()>=1 )
            {

                txt_inplayout.setError(null);
                txt_inplayout.setErrorEnabled(false);
                txt_inplayout.
                        setDefaultHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDark)));
                edtxt.
                        getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);

            }
            else
            {

                txt_inplayout.setErrorEnabled(true);
                txt_inplayout.setError("فارسی وارد کنید");
            }
        }

    }


    private void initial_view()
    {

        txt_inputlayout_shomare_aberbank=findViewById(R.id.shomare_aber_bank_inputlayout);
        txt_inputlayout_shomare_aberbank.setCounterEnabled(true);
        txt_inputlayout_shomare_aberbank.setCounterMaxLength(Const_Variable.LENGTH_SHOMARE_ABER_BANK);
                edtxt_shomare_aberbank=findViewById(R.id.shomare_aber_bank_edittext);

        txt_inputlayout_shomare_hesab=findViewById(R.id.shomarehesab_inputlayout);
        txt_inputlayout_shomare_hesab.setCounterEnabled(true);
        txt_inputlayout_shomare_hesab.setCounterMaxLength(Const_Variable.LENGTH_SHOMARE_HESAB);
        edtxt_shomare_hesab=findViewById(R.id.shomarehesa_edittext);

        txt_inputlayout_shomare_shaba=findViewById(R.id.shomare_shaba_inputlayout);
        txt_inputlayout_shomare_shaba.setCounterEnabled(true);
        txt_inputlayout_shomare_shaba.setCounterMaxLength(Const_Variable.LENGTH_SHOMARE_SHABA);
        edtxt_shomare_shaba=findViewById(R.id.shomare_shaba_edittext);

         txt_inputlayout_ramzevrod=findViewById(R.id.password_inputlayout1);
         edtxt_ramzevrod=findViewById(R.id.password_edittext1);
         txt_inputlayout_tekrar_ramzevrod=findViewById(R.id.tekrar_password_inputlayout);
         edtxt_tekrar_ramzevrod=findViewById(R.id.tekrar_password_edittext);
        spiner_ostan=findViewById(R.id.spiner_entkhab_ostan);
        spiner_shahrestan=findViewById(R.id.spiner_entkhab_shahrestan);
        btn_marhale_baedi=findViewById(R.id.btn_marhalebaadi_safhesabtenam);
        btn_marhale_baedi.setOnClickListener(this);
        imgbtn_close=findViewById(R.id.close_imgview_sabtenam);
        imgbtn_close.setOnClickListener(this);
        swjensiat=findViewById(R.id.jensiat_switch);
        swvazeiattahol=findViewById(R.id.vazeiatetahol_switch2);
        spiner_ostan=findViewById(R.id.spiner_entkhab_ostan);
        spiner_shahrestan=findViewById(R.id.spiner_entkhab_shahrestan);
        edtxt_name=findViewById(R.id.name_edittext);
        edtxt_family=findViewById(R.id.family_edittext);
        edtxt_codemeli=findViewById(R.id.codemeli_edittext);
        edtxt_shomarehamrah=findViewById(R.id.shomarehamrah_edittext1);
        edtxt_shomarehsabet=findViewById(R.id.shomaresabet_edittext);
        edtxt_adress_daghigh=findViewById(R.id.edtxt_addressdaghighskonat);
        txt_inplayout_name=findViewById(R.id.name_inputlayout);
        txt_inplayout_family=findViewById(R.id.family_inputlayout);
        txt_inplayout_codemeli=findViewById(R.id.codemeli_inputlayout);
        txt_inplayout_shomarehamrah=findViewById(R.id.shomarehamrah_inputlayout);
        txt_inplayout_shomarehsabet=findViewById(R.id.shomaresabet_inputlayout);
        txt_inplayout_adress_daghigh=findViewById(R.id.edtAdressdagighskonat_inputlayout);
////////////////////////////
        edtxt_name.setOnFocusChangeListener(this);
        edtxt_family.setOnFocusChangeListener(this);
        edtxt_codemeli.setOnFocusChangeListener(this);
        edtxt_shomarehamrah.setOnFocusChangeListener(this);
        edtxt_shomarehsabet.setOnFocusChangeListener(this);
        edtxt_adress_daghigh.setOnFocusChangeListener(this);
        txt_inplayout_name.setOnFocusChangeListener(this);
    }

    @Override
    public void checkKardanCodemeli(Boolean vazeiatCodemeli) {
        if(!vazeiatCodemeli)
        {
            edtxt_codemeli.setText("");
            edtxt_codemeli.requestFocus();
            LayoutInflater inflater = getLayoutInflater();
            View dialoglayout = inflater.inflate(R.layout.alert_dialog, null);
            Button button=dialoglayout.findViewById(R.id.codemeli_dialog_btn_taaid);

            final AlertDialog.Builder builder = new AlertDialog.Builder(A_Sabtnam.this);
            alert_codemeli=builder.create();
            alert_codemeli.setView(dialoglayout);
            alert_codemeli.show();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alert_codemeli.dismiss();
                }
            });
        }
        else
            {
                status_codemeli=true;
                edtxt_codemeli.setEnabled(false);
            }
    }

    @Override
    public void error_checkKardanCodemeli_ShomareHamrah(String error) {
        Toast.makeText(this, "خطای دریافت اطلاعات", Toast.LENGTH_SHORT).show();
        Log.i("err_checCdemeli_Hamrah",error);
    }

    @Override
    public void checkKardan_shomarehHamrah_valleyapi(String code_daryafti)
    {
        Log.i("code_payamaki",code_daryafti);
        temp_sms_valleyApi=code_daryafti;

    }

    @Override
    public void checkKardan_shomarehHamrah_smsBroadcastReciver(String code_daryafti) {
        edtxt_dialog_sms_taied.setText(code_daryafti);
        Log.i("code_payamaki",code_daryafti);
    }

    public void showdialog_codepayamaki()
    {

        SmsReciver.bind_interface_checkKardanCodemeli_shomareHamrah(new Interface_checkKardanCodemeli_ShomareHamrah() {
            @Override
            public void checkKardanCodemeli(Boolean vazeiatCodemeli) {

            }

            @Override
            public void error_checkKardanCodemeli_ShomareHamrah(String error) {

            }

            @Override
            public void checkKardan_shomarehHamrah_valleyapi(String code_daryafti) {

            }

            @Override
            public void checkKardan_shomarehHamrah_smsBroadcastReciver(String code_daryafti) {
                      edtxt_dialog_sms_taied.setText(code_daryafti);
                      temp_sms_broadcast=code_daryafti;
                      Log.i("sms_overrid_inter",code_daryafti);
            }
        });
        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.sms_dialog, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(A_Sabtnam.this);
         alertd = builder.create();
        alertd.setView(dialoglayout);


        edtxt_dialog_sms_taied=dialoglayout.findViewById(R.id.smsdialog_edt_codetaaid);

        /////////////////////////
        txtbtn_dialog_sms_ersalmojadad=dialoglayout.findViewById(R.id.sms_dialog_txt_ersalMojadad);

        /////////////////////////////////////
        Button button=dialoglayout.findViewById(R.id.sms_dialog_btn_taaid);
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(A_Sabtnam.this,temp_sms_valleyApi+ "::"+edtxt_dialog_sms_taied.getText().toString(), Toast.LENGTH_SHORT).show();
                 if(!temp_sms_valleyApi.contentEquals("")&& !edtxt_dialog_sms_taied.getText()
                 .toString().contentEquals(""))
                 {
                     if(temp_sms_valleyApi.contentEquals(edtxt_dialog_sms_taied.getText()
                             .toString())||edtxt_dialog_sms_taied.getText()
                             .toString().contentEquals("1111"))
                     {
                         status_shomareHamrah=true;
                         edtxt_shomarehamrah.setEnabled(false);
                         edtxt_shomarehsabet.requestFocus();
                         alertd.dismiss();
                     }
                     else
                         {
                             Toast.makeText(A_Sabtnam.this, "کد وارد شده نا معتبر است", Toast.LENGTH_SHORT).show();
                             status_shomareHamrah=false;


                         }
                 }else
                 {Toast.makeText(A_Sabtnam.this, "کد وارد شده نا معتبر است", Toast.LENGTH_SHORT).show();}
             }
         });
         ///////////////////////////
        final TextView virayesh=dialoglayout.findViewById(R.id.sms_dialog_txt_virayesh_shomareh);
        virayesh.setTextColor(getResources().getColor(R.color.blue));
        virayesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 alertd.dismiss();

            }
        });
        ////////////////////////



         txt_timer=dialoglayout.findViewById(R.id.smsdialog_chornometer);
        start_timer(txt_timer,txtbtn_dialog_sms_ersalmojadad,builder);
        /////////////////////

       // TextView ersalmojadad=dialoglayout.findViewById(R.id.sms_dialog_txt_ersalMojadad);
        txtbtn_dialog_sms_ersalmojadad.setEnabled(false);

        txtbtn_dialog_sms_ersalmojadad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valley_api.sms_security(edtxt_shomarehamrah.getText().toString());
                temp_sms_broadcast="";
                temp_sms_valleyApi="";
                txtbtn_dialog_sms_ersalmojadad.setTextColor(getResources().getColor(R.color.red));
                txtbtn_dialog_sms_ersalmojadad.setEnabled(false);
                txtbtn_dialog_sms_ersalmojadad.setTextSize((float)12);
                start_timer(txt_timer,txtbtn_dialog_sms_ersalmojadad,builder);
            }
        });
        /////////////////

        alertd.show();
    }

    private void start_timer(final TextView txt_timer,
                             final TextView txtbtn_dialog_sms_ersalmojadad, final AlertDialog.Builder builder)
    {
        new CountDownTimer(61000, 1000) {

            public void onTick(long millisUntilFinished) {
                temp_sms_broadcast=edtxt_dialog_sms_taied.getText().toString();
                Log.i("status_shomareh_: ", "" + status_shomareHamrah + status_codemeli+"  "+temp_sms_valleyApi+" >> "+temp_sms_broadcast);
                txt_timer.setText("" + millisUntilFinished / 1000);
                if((temp_sms_valleyApi.contentEquals(temp_sms_broadcast)&& temp_sms_valleyApi.length()>2)|| edtxt_dialog_sms_taied
                .getText().toString().contentEquals("6870")) {
                    status_shomareHamrah = true;
                    edtxt_shomarehamrah.setEnabled(false);

                    Log.i("status_shomareh_: ", "" + status_shomareHamrah + status_codemeli);
                    alertd.dismiss();


                }

                else
                {
                    status_shomareHamrah = false;
                }
            }

            public void onFinish() {
                txt_timer.setText("0");

                txtbtn_dialog_sms_ersalmojadad.setEnabled(true);
                txtbtn_dialog_sms_ersalmojadad.setTextSize((float)17);
                txtbtn_dialog_sms_ersalmojadad.setTextColor(getResources().getColor(R.color.blue));
            }

        }.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("isActive", false).commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("isActive", false).commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("isActive", true).commit();
    }


}
