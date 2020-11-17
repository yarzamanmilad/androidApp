package com.example.khialerahat.experts_khialerahat.List_Sefareshha;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khialerahat.experts_khialerahat.R;

public class ViewHolder_List_Sefaresh_Anjam_Shodeh extends RecyclerView.ViewHolder {
   TextView noeh_khadamat,hazineh_khadamat
           ,vazeiat_pardakht,zaman_anjam_kar,
    moshahdeh_jozeiat;
   ImageView imgv_aks_khadamat;
   CardView cardView;
   View view;
    public ViewHolder_List_Sefaresh_Anjam_Shodeh(@NonNull View itemView) {
        super(itemView);
        view=itemView;
        noeh_khadamat=itemView.findViewById(R.id.onvan);
        hazineh_khadamat=itemView.findViewById(R.id.gheymat_list_sefareshha);
        vazeiat_pardakht=itemView.findViewById(R.id.noesef);
        zaman_anjam_kar=view.findViewById(R.id.zamansef);
        moshahdeh_jozeiat=view.findViewById(R.id.nemayesh_jozeiat);
        imgv_aks_khadamat=view.findViewById(R.id.img);
        cardView=itemView.findViewById(R.id.cardview_viewmodel_list_sefarshha);

    }

    public CardView getCardView() {
        return cardView;
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
