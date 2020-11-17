package com.example.khialerahat.experts_khialerahat.MainPackage;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.khialerahat.experts_khialerahat.R;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;

import de.hdodenhof.circleimageview.CircleImageView;


public class ViewHolder_Message extends RecyclerView.ViewHolder {
    CircleImageView circleImageView1,circleImageView2,circleImageView3;
    TextView title,time;
    ConstraintLayout constraintLayout;
    public ViewHolder_Message(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.viewmodelmessagetxttitle_inexpanlayout);
        time=itemView.findViewById(R.id.viewmodelmessagetxttime_inexpanlayout);
        constraintLayout=itemView.findViewById(R.id.consLayout4viewmodelmessage);
        circleImageView1=itemView.findViewById(R.id.imageView1viewmodelmessage);
        circleImageView2=itemView.findViewById(R.id.imageView2_viewmodelmessag);
        circleImageView3=itemView.findViewById(R.id.imageView3_viewmodelmessag);
    }

    public CircleImageView getCircleImageView1() {
        return circleImageView1;
    }

    public CircleImageView getCircleImageView2() {
        return circleImageView2;
    }

    public CircleImageView getCircleImageView3() {
        return circleImageView3;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getTime() {
        return time;
    }

    public void setTime(TextView time) {
        this.time = time;
    }

    public ConstraintLayout getConstraintLayout() {
        return constraintLayout;
    }

    public void setConstraintLayout(ConstraintLayout constraintLayout) {
        this.constraintLayout = constraintLayout;
    }
}
