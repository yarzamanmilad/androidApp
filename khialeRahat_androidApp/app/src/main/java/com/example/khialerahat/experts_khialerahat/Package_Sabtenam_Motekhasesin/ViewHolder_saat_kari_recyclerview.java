package com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckedTextView;

import com.example.khialerahat.experts_khialerahat.R;

public class ViewHolder_saat_kari_recyclerview extends RecyclerView.ViewHolder {
    CheckedTextView checkedTextView;
    View view;

    public View getView() {
        return view;
    }

    public CheckedTextView getCheckedTextView() {
        return checkedTextView;
    }

    public ViewHolder_saat_kari_recyclerview(@NonNull View itemView) {
        super(itemView);
        this.checkedTextView=itemView.findViewById(R.id.checked_textview_saat_kari);
    }
}
