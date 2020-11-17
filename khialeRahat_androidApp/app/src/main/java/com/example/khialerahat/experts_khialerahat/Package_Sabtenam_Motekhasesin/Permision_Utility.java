package com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class Permision_Utility
{   public static final int requst_code_callphon1=1000;
    public static final int requst_code_callphon2=1003;
    public static final int getRequst_code_camera=1001;
    public static final int getRequst_code_galery=1002;
    public static final int getRequst_code_recive_sms=1003;
    public  static Boolean check_permision(Context context,String noeh_darkhoast)
    {            int result=-1;
         switch (noeh_darkhoast)
         {

             case "call1":

                result =  ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);
                  break;

             case "call2":

                 result =  ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);
                 break;
             case "camera":
                 result =  ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
                 break;

             case "recive_sms":
                 result =  ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_SMS);
                 break;
         }
        return result== PackageManager.PERMISSION_GRANTED;
    }
    public static void requst_permision(Context activity,String noeh_darkhoast)
    {
        switch (noeh_darkhoast)
        {

            case "call1":
                ActivityCompat.requestPermissions((Activity) activity,new String[]{Manifest.permission.CALL_PHONE}
                ,requst_code_callphon1);

                break;

            case "call2":
                ActivityCompat.requestPermissions((Activity) activity,new String[]{Manifest.permission.CALL_PHONE}
                ,requst_code_callphon2);

                break;
            case "camera":
                ActivityCompat.requestPermissions((Activity) activity,new String[]{Manifest.permission.CAMERA},getRequst_code_camera);
                break;

            case "recive_sms":
                ActivityCompat.requestPermissions((Activity) activity,new String[]{Manifest.permission.RECEIVE_SMS},getRequst_code_recive_sms);
                break;

        }

    }
}
