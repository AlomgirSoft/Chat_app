package com.bdcit.mymasssenger;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Utils {


    public static void ShowAlert(Context context,String sms){

   AlertDialog.Builder builder = new AlertDialog.Builder(context);
             builder.setMessage(sms);

             builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {

                 }
             }).create().show();



    }
}
