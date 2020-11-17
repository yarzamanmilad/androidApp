package com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckedTextView;

import com.example.khialerahat.experts_khialerahat.R;

public class ViewHolder_Ezafekardan_Address extends RecyclerView.ViewHolder {
    CheckedTextView checkedTextView_address_mantaghe;
    View view;

    public View getView() {
        return view;
    }

    public CheckedTextView getCheckedTextView_address_mantaghe() {
        return checkedTextView_address_mantaghe;
    }

    public ViewHolder_Ezafekardan_Address(@NonNull View itemView) {
        super(itemView);
        this.checkedTextView_address_mantaghe=itemView.findViewById(R.id.checkettxtview_entkhab_manategh);
    }
}
