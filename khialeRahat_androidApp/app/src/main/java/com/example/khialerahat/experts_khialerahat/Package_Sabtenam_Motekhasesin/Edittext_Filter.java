package com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin;

import android.content.Context;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Log;

import com.example.khialerahat.experts_khialerahat.My_interface;
import com.example.khialerahat.experts_khialerahat.R;

public class Edittext_Filter {
  private     Context context;
  private  String[] hrofe_alefba;
  private final Boolean  harfe_vrodi_motabar_ast=true,harfe_vrodi_motabar_nist=false;
   private int txt_inplayout_id,edtxt_id;

      private My_interface myinterface;

    Edittext_Filter(Context context, int textInputLayout_id,int edtxt_id) {
        this.context = context;
        this.txt_inplayout_id=textInputLayout_id;
        this.edtxt_id=edtxt_id;
        this.hrofe_alefba=context.getResources().getStringArray(R.array.hrof_alefbayeh_farsi);

        this.myinterface =(My_interface) this.context;
    }

    private InputFilter inputFilter=new InputFilter() {
       @Override
       public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {


           if (source instanceof SpannableStringBuilder) {
               Log.i("mil",String.valueOf("تابع inputfilteredittext"));
               SpannableStringBuilder sourceAsSpannableBuilder = (SpannableStringBuilder)source;
               for (int i = end - 1; i >= start; i--) {
                   char currentChar = source.charAt(i);

                   if (!Character.isLetterOrDigit(currentChar) && !Character.isSpaceChar(currentChar)) {
                       sourceAsSpannableBuilder.delete(i, i+1);
                   }
               }
               return source;
           } else {
               StringBuilder filteredStringBuilder = new StringBuilder();
               for (int i = start; i < end; i++) {
                   char currentChar = source.charAt(i);
                   Log.i("mil2",String.valueOf(currentChar));
                   if (Character.isLetterOrDigit(currentChar) || Character.isSpaceChar(currentChar)) {

                       if (!sanjesh_motabar_ast(currentChar))
                       {
                           myinterface.txtfilter(harfe_vrodi_motabar_nist, txt_inplayout_id, edtxt_id);
                       }
                       else
                       {
                           myinterface.txtfilter(harfe_vrodi_motabar_ast, txt_inplayout_id, edtxt_id);
                           filteredStringBuilder.append(currentChar);
                       }

                   }
               }
               return filteredStringBuilder.toString();
           }
       }
   };

    private Boolean sanjesh_motabar_ast(char harfe_vrodi) {
        Boolean harfe_vrodi_motabarast=false;
        for(int i=0;i<hrofe_alefba.length;i++)
        {
            if(hrofe_alefba[i].contains(String.valueOf(harfe_vrodi)) || Character.isSpace(harfe_vrodi))
            {
                harfe_vrodi_motabarast=true;
                break;
            }
        }
      return harfe_vrodi_motabarast;
    }


    public InputFilter getInputFilter() {
        return inputFilter;
    }
}
