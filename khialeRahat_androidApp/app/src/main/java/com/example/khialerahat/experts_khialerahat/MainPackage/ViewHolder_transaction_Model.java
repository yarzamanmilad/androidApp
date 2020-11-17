package com.example.khialerahat.experts_khialerahat.MainPackage;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.khialerahat.experts_khialerahat.R;

public class ViewHolder_transaction_Model extends RecyclerView.ViewHolder {
    public View getImgv() {
        return imgv;
    }

    private final View imgv;
    TextView type_transaction,requst_date,amount,tasvyeh_date,tracking_code;
    ConstraintLayout constraintLayout;

    public ConstraintLayout getConstraintLayout() {
        return constraintLayout;
    }

    public void setConstraintLayout(ConstraintLayout constraintLayout) {
        this.constraintLayout = constraintLayout;
    }

    public TextView getTracking_code() {
        return tracking_code;
    }

    public void setTracking_code(TextView tracking_code) {
        this.tracking_code = tracking_code;
    }

    public TextView getType_transaction() {
        return type_transaction;
    }

    public void setType_transaction(TextView type_transaction) {
        this.type_transaction = type_transaction;
    }

    public TextView getRequst_date() {
        return requst_date;
    }

    public void setRequst_date(TextView requst_date) {
        this.requst_date = requst_date;
    }

    public TextView getAmount() {
        return amount;
    }

    public void setAmount(TextView amount) {
        this.amount = amount;
    }

    public TextView getTasvyeh_date() {
        return tasvyeh_date;
    }

    public void setTasvyeh_date(TextView tasvyeh_date) {
        this.tasvyeh_date = tasvyeh_date;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    View view;
    public ViewHolder_transaction_Model(@NonNull View itemView) {
        super(itemView);
        view=itemView;
        type_transaction=itemView.findViewById(R.id.txt_darkhast_viewmodel_transaction);
        requst_date=itemView.findViewById(R.id.txttime_request_viewmodel_transaction);
        amount=itemView.findViewById(R.id.txt_amunt_viewmodel_transactin);
        tasvyeh_date=itemView.findViewById(R.id.txt_date_tasvie_viewmodel_transaction);
        tracking_code=itemView.findViewById(R.id.txt_tracking_code_transaction);
        constraintLayout=itemView.findViewById(R.id.main_lay_transaction_viewmodel);
        imgv=itemView.findViewById(R.id.imageView_viewmodel_transaction);
    }
}
