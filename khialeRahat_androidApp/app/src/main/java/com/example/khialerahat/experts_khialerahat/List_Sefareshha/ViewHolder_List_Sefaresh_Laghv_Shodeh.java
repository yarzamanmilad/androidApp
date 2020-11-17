package com.example.khialerahat.experts_khialerahat.List_Sefareshha;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khialerahat.experts_khialerahat.R;

public class ViewHolder_List_Sefaresh_Laghv_Shodeh extends RecyclerView.ViewHolder {
    private final TextView moshahdeh_jozeiat;
    private final TextView hazineh_khadamat;
    TextView noeh_khadamat;
    TextView zaman_sefaresh;
    TextView zaman_laghv_sefaresh;
    TextView address;
    ImageView imageView;

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TextView getItem_number() {
        return item_number;
    }

    public void setItem_number(TextView item_number) {
        this.item_number = item_number;
    }

    TextView item_number;

   View view;

    public TextView getNoeh_khadamat() {
        return noeh_khadamat;
    }

    public void setNoeh_khadamat(TextView noeh_khadamat) {
        this.noeh_khadamat = noeh_khadamat;
    }

    public TextView getZaman_sefaresh() {
        return zaman_sefaresh;
    }

    public void setZaman_sefaresh(TextView zaman_sefaresh) {
        this.zaman_sefaresh = zaman_sefaresh;
    }

    public TextView getZaman_laghv_sefaresh() {
        return zaman_laghv_sefaresh;
    }

    public void setZaman_laghv_sefaresh(TextView zaman_laghv_sefaresh) {
        this.zaman_laghv_sefaresh = zaman_laghv_sefaresh;
    }

    public TextView getAddress() {
        return address;
    }

    public void setAddress(TextView address) {
        this.address = address;
    }



    public ViewHolder_List_Sefaresh_Laghv_Shodeh(@NonNull View itemView) {
        super(itemView);
        view=itemView;
       // address=view.findViewById(R.id.txt_address_sefaresh_laghvshode);
        noeh_khadamat=view.findViewById(R.id.onvan);
        zaman_sefaresh=view.findViewById(R.id.zamansef);
        zaman_laghv_sefaresh=view.findViewById(R.id.noesef);
      //  item_number=view.findViewById(R.id.txt_position_sefaresh_laghvshode);






        hazineh_khadamat=itemView.findViewById(R.id.gheymat_list_sefareshha);
        hazineh_khadamat.setVisibility(View.INVISIBLE);

        moshahdeh_jozeiat=view.findViewById(R.id.nemayesh_jozeiat);
        moshahdeh_jozeiat.setVisibility(View.INVISIBLE);
        imageView=view.findViewById(R.id.img);




    }


    public TextView getMoshahdeh_jozeiat() {
        return moshahdeh_jozeiat;
    }

    public TextView getHazineh_khadamat() {
        return hazineh_khadamat;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
