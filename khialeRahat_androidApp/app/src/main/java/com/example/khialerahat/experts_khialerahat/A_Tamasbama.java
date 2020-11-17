package com.example.khialerahat.experts_khialerahat;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Permision_Utility;

public class A_Tamasbama extends AppCompatActivity implements View.OnClickListener {
    ImageView img_btn_close;
    TextView txtbtncall1,txtbtncall2;
    ConstraintLayout constraintLayout;
    private Uri uri_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a__tamasbama);
        constraintLayout=findViewById(R.id.a_tamasbama_main_cinstlayout);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim_kesho);
        constraintLayout.setAnimation(animation);
        img_btn_close=findViewById(R.id.btn_close_tamasbama);
        img_btn_close.setOnClickListener(this);
        txtbtncall1=findViewById(R.id.txtbtncall1);
        txtbtncall1.setOnClickListener(this);
        txtbtncall2=findViewById(R.id.txtbtncall2);
        txtbtncall2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.btn_close_tamasbama:
                finish();
                break;
            case R.id.txtbtncall1:
                if (Permision_Utility.check_permision(A_Tamasbama.this,"call1"))
                {
                    try {dialPhoneNumber(txtbtncall1.getText().toString(),700);}catch (ActivityNotFoundException e){e.printStackTrace();}
                }else
                {
                    Permision_Utility.requst_permision(A_Tamasbama.this,"call1");
                }
                break;
            case R.id.txtbtncall2:
                if (Permision_Utility.check_permision(A_Tamasbama.this,"call2"))
                {
                    try {dialPhoneNumber(txtbtncall2.getText().toString(),700);}catch (ActivityNotFoundException e){e.printStackTrace();}
                }else
                {
                    Permision_Utility.requst_permision(this,"call2");
                }

                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       if(grantResults.length>0)
       {

           switch (requestCode)
           {
               case Permision_Utility.requst_code_callphon1:
                      if (grantResults[0]== PackageManager.PERMISSION_GRANTED)
                       {
                           try {dialPhoneNumber(txtbtncall1.getText().toString(),700);}catch (ActivityNotFoundException e){e.printStackTrace();}
//                           Intent intent=new Intent(Intent.ACTION_CALL);
//                           intent.setPackage("com.android.server.tlecom");
//                           intent.setData(Uri.parse("tel:"+txtbtncall1.getText().toString()));
//                           startActivityForResult(intent,700);
                       }else{
                           Toast.makeText(this, "شما دسترسی لازم را ندارید", Toast.LENGTH_SHORT).show();
                       }

                   break;

               case Permision_Utility.requst_code_callphon2:
                   if (grantResults[0]== PackageManager.PERMISSION_GRANTED)
                   {
                       try {dialPhoneNumber(txtbtncall2.getText().toString(),700);}catch (ActivityNotFoundException e){e.printStackTrace();}

//
//                       Intent intent=new Intent(Intent.ACTION_CALL);
//                       intent.setPackage("com.android.server.tlecom");
//                       intent.setData(Uri.parse("tel:"+txtbtncall2.getText().toString()));
//                       startActivityForResult(intent,700);
                   }else{
                       Toast.makeText(this, "شما دسترسی لازم را ندارید", Toast.LENGTH_SHORT).show();
                   }
                   break;
           }
       }
       else
       {
           Toast.makeText(this, "خظایی رخ داده است", Toast.LENGTH_SHORT).show();
       }
    }

    public void dialPhoneNumber(String phoneNumber , int back_code) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent,back_code);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if (resultCode
                ==RESULT_OK) {
            switch (requestCode) {

                case 700:
                    Uri uri = data.getData();
                    uri_img=uri;
                    Toast.makeText(this, "calll", Toast.LENGTH_SHORT).show();

                    break;
            }

        }
    }
}

