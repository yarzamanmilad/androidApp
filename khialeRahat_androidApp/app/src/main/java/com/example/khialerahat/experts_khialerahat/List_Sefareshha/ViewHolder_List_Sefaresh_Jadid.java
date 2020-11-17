package com.example.khialerahat.experts_khialerahat.List_Sefareshha;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khialerahat.experts_khialerahat.R;

public class ViewHolder_List_Sefaresh_Jadid extends RecyclerView.ViewHolder {
   TextView noeh_khadamat,hazineh_khadamat
           ,vazeiat_pardakht,zaman_anjam_kar,
    moshahdeh_jozeiat,orderDetails,txtbtnNotaccept;
   ImageView imgv_aks_khadamat;
   ConstraintLayout constraintLayout;
   View view;

    public TextView getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(TextView orderDetails) {
        this.orderDetails = orderDetails;
    }

    public TextView getTxtbtnNotaccept() {
        return txtbtnNotaccept;
    }

    public void setTxtbtnNotaccept(TextView txtbtnNotaccept) {
        this.txtbtnNotaccept = txtbtnNotaccept;
    }

    public ViewHolder_List_Sefaresh_Jadid(@NonNull View itemView) {
        super(itemView);
        view=itemView;
        noeh_khadamat=itemView.findViewById(R.id.onvan_viewmodel_neworder);
        hazineh_khadamat=itemView.findViewById(R.id.gheymat_viewmodel_neworder);
        vazeiat_pardakht=itemView.findViewById(R.id.noesef_viewmodel_neworder);
        zaman_anjam_kar=itemView.findViewById(R.id.zamansef_viewmodel_neworder);
        constraintLayout=itemView.findViewById(R.id.conslay_vimodelneworder);
        moshahdeh_jozeiat=itemView.findViewById(R.id.nemayesh_jozeiat_viewmodel_neworder);
        imgv_aks_khadamat=itemView.findViewById(R.id.img_viewmodel_neworder);
        orderDetails=itemView.findViewById(R.id.txt_a_neworder_details);
        txtbtnNotaccept=itemView.findViewById(R.id.txtbtn_notaccept);

    }

    public ConstraintLayout getConstraintLayout() {
        return constraintLayout;
    }

    public TextView getNoeh_khadamat() {
        return noeh_khadamat;
    }

    public void setNoeh_khadamat(TextView noeh_khadamat) {
        this.noeh_khadamat = noeh_khadamat;
    }

    public TextView getHazineh_khadamat() {
        return hazineh_khadamat;
    }

    public void setHazineh_khadamat(TextView hazineh_khadamat) {
        this.hazineh_khadamat = hazineh_khadamat;
    }

    public TextView getVazeiat_pardakht() {
        return vazeiat_pardakht;
    }

    public void setVazeiat_pardakht(TextView vazeiat_pardakht) {
        this.vazeiat_pardakht = vazeiat_pardakht;
    }

    public TextView getZaman_anjam_kar() {
        Log.i("show_sefjadid","getzaman");
        return zaman_anjam_kar;
    }

    public void setZaman_anjam_kar(TextView zaman_anjam_kar) {
        this.zaman_anjam_kar = zaman_anjam_kar;
    }

    public TextView getMoshahdeh_jozeiat() {
        return moshahdeh_jozeiat;
    }

    public void setMoshahdeh_jozeiat(TextView moshahdeh_jozeiat) {
        this.moshahdeh_jozeiat = moshahdeh_jozeiat;
    }

    public ImageView getImgv_aks_khadamat() {
        return imgv_aks_khadamat;
    }

    public void setImgv_aks_khadamat(ImageView imgv_aks_khadamat) {
        this.imgv_aks_khadamat = imgv_aks_khadamat;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
