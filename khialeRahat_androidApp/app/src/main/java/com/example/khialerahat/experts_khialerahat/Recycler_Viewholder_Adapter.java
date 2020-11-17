package com.example.khialerahat.experts_khialerahat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;

import com.example.khialerahat.experts_khialerahat.List_Sefareshha.A_Jozeiat_Sefarsh_Darhal_Anjam;
import com.example.khialerahat.experts_khialerahat.List_Sefareshha.A_NewOrder_Details;
import com.example.khialerahat.experts_khialerahat.List_Sefareshha.Model_List_Sefaresh_Anjam_Shodeh;
import com.example.khialerahat.experts_khialerahat.List_Sefareshha.Model_List_Sefaresh_jadid;
import com.example.khialerahat.experts_khialerahat.List_Sefareshha.Model_Sefaresh_DarhaleAnjam;
import com.example.khialerahat.experts_khialerahat.List_Sefareshha.Model_Sefaresh_laghv_Shodeh;
import com.example.khialerahat.experts_khialerahat.List_Sefareshha.ViewHolder_List_Sefaresh_Anjam_Shodeh;
import com.example.khialerahat.experts_khialerahat.List_Sefareshha.ViewHolder_List_Sefaresh_DarhaleAnjam;
import com.example.khialerahat.experts_khialerahat.List_Sefareshha.ViewHolder_List_Sefaresh_Jadid;
import com.example.khialerahat.experts_khialerahat.List_Sefareshha.ViewHolder_List_Sefaresh_Laghv_Shodeh;
import com.example.khialerahat.experts_khialerahat.MainPackage.Model_Message;
import com.example.khialerahat.experts_khialerahat.MainPackage.Model_Transaction;
import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.MainPackage.ViewHolder_Message;
import com.example.khialerahat.experts_khialerahat.MainPackage.ViewHolder_transaction_Model;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.A_Greftan_Aks_Madarek_Motakhases;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Const_Variable;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Model_Takhasos_level2_recyclerview;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Model_Takhasos_recyclerview;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Model_saat_kari_recyclerview;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Model_Ezafehkardan_Address;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.ViewHolder_Ezafekardan_Address;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.ViewHolder_Takhasos_level2_recyclerview;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.ViewHolder_Takhasos_recyclerview;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.ViewHolder_saat_kari_recyclerview;
import com.example.khialerahat.experts_khialerahat.Web_Service.Valley_Api;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Recycler_Viewholder_Adapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int lastPosition = -1;

    My_interface myinterface;
   Valley_Api valley_api;
    List<Object> items = new ArrayList<>();
    private static final int
            type_Takhasos=0,type_ezafeh_kardane_address=1,getType_Takhasos_level2=3,
    type_saat_kari=4,type_list_sefaresh_anjam_shode=5,type_list_sefaresh_laghv_shode=6,type_list_sefaresh_darhaleAnjam=7
            ,type_transaction=8,type_list_sefaresh_jadid=9,type_message=10;
    public Context maincontext;
    private ViewGroup mainvViewGroup;


    //////////////////////////////////
    @Override
    public int getItemViewType(int position) {

            if(items.get(position) instanceof Model_Ezafehkardan_Address)
                return type_ezafeh_kardane_address;
            else if(items.get(position) instanceof Model_Takhasos_recyclerview)
                return type_Takhasos;
            else if(items.get(position)instanceof Model_saat_kari_recyclerview)
                return type_saat_kari;
            else if(items.get(position) instanceof Model_Takhasos_level2_recyclerview)
                return getType_Takhasos_level2;
            else if (items.get(position) instanceof Model_List_Sefaresh_Anjam_Shodeh)
                return type_list_sefaresh_anjam_shode;
            else if (items.get(position) instanceof Model_Sefaresh_laghv_Shodeh)
                return type_list_sefaresh_laghv_shode;

            else if (items.get(position) instanceof Model_Sefaresh_DarhaleAnjam)
                return type_list_sefaresh_darhaleAnjam;

            else if (items.get(position) instanceof Model_Transaction)
                return type_transaction;

            else if (items.get(position) instanceof Model_List_Sefaresh_jadid)
                return type_list_sefaresh_jadid;

            else if (items.get(position) instanceof Model_Message)
                return type_message;
            else return 1000;
    }
////////////////////////////////////////////
    public Recycler_Viewholder_Adapter(List<Object> objects_list) {
        this.items = objects_list;
    }

    public Context getMaincontext() {
        return maincontext;
    }

    public Recycler_Viewholder_Adapter(List<Object> objects_list, Context context) {
        this.items = objects_list;
        this.maincontext=context;

    }
////////////////////////////////////////
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int view_type) {
        View view;
         maincontext=viewGroup.getContext();
         mainvViewGroup=viewGroup;
        RecyclerView.ViewHolder viewHolder=null;
        switch (view_type)
        {

            case  type_message:

                view=LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.view_model_message,viewGroup,false);
                viewHolder=new ViewHolder_Message(view);
                break;

            case type_list_sefaresh_jadid:

                view=LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.view_model_list_new_order,viewGroup,false);
                viewHolder=new ViewHolder_List_Sefaresh_Jadid(view);
                break;
            case  type_transaction:

                view=LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.view_model_transaction,viewGroup,false);
                viewHolder=new ViewHolder_transaction_Model(view);
                break;
            case type_list_sefaresh_laghv_shode:

                view=LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.view_model_list_sefareshha,viewGroup,false);
                viewHolder=new ViewHolder_List_Sefaresh_Laghv_Shodeh(view);
                break;
            case type_list_sefaresh_anjam_shode:

                view=LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.view_model_list_sefareshha,viewGroup,false);
                viewHolder=new ViewHolder_List_Sefaresh_Anjam_Shodeh(view);
                break;

            case type_list_sefaresh_darhaleAnjam:

                view=LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.view_model_list_sefareshha,viewGroup,false);
                viewHolder=new ViewHolder_List_Sefaresh_DarhaleAnjam(view);
                break;
            case  type_ezafeh_kardane_address:
                view=LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.a_sabtenam_modelview_recycler_entkhab_manategh,viewGroup,false);
                viewHolder=new ViewHolder_Ezafekardan_Address(view);
                break;

            case type_Takhasos:
                view=LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.a_sabtenam_modelview_recycler_entkhab_manategh,viewGroup,false);
                viewHolder=new ViewHolder_Takhasos_recyclerview(view);
                break;


            case getType_Takhasos_level2:
                view=LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.a_sabtenam_modelview_recycler_entkhab_takhasos_level2,viewGroup,false);
                viewHolder=new ViewHolder_Takhasos_level2_recyclerview(view);
                break;


            case type_saat_kari:
                view=LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.view_model_saat_kari,viewGroup,false);
                viewHolder=new ViewHolder_saat_kari_recyclerview(view);
                break;




            default:
        }
     return viewHolder;
    }

    @Override
    public void onBindViewHolder(

            @NonNull RecyclerView.ViewHolder viewHolder,
            int i) {

        int view_typ=viewHolder.getItemViewType();
        switch (view_typ)
        {



            case type_message:
                show_list_message(viewHolder,i);
                break;
            case type_list_sefaresh_jadid:
                show_list_sefaresh_jadid(viewHolder,i);
                break;
            case type_transaction:
                show_transaction(viewHolder,i);
                break;
            case type_list_sefaresh_laghv_shode:
                show_list_sefaresh_laghv_shodeh(viewHolder,i);
                break;

            case type_list_sefaresh_anjam_shode:
                show_list_sefaresh_anjam_shodeh(viewHolder,i);
                break;
            case type_list_sefaresh_darhaleAnjam:
                show_list_sefaresh_darhaleanjam(viewHolder,i);
                break;
            case type_ezafeh_kardane_address:
                show_ezafehkardaneh_address(viewHolder,i);
                break;
            case type_Takhasos:
                  Show_takhasos(viewHolder,i);
                break;

            case getType_Takhasos_level2:
                show_takhasos_level2(viewHolder,i);
                break;
            case type_saat_kari:
                show_Model_saat_kari(viewHolder,i);
                break;
                default:

        }

        // setFadeAnimation(viewHolder.itemView);
       // setAnimation(viewHolder.itemView, i);
       // setscallanimation(viewHolder.itemView);
    }

    private void show_list_message(RecyclerView.ViewHolder viewHolder, int i)
    {
        Log.i("message","recycler"+i);
        final Model_Message model_message=(Model_Message)items.get(i);
        final ViewHolder_Message viewHolder_message=(ViewHolder_Message) viewHolder;

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        viewHolder_message.getCircleImageView1().setBackgroundColor(color);
        viewHolder_message.getCircleImageView2().setBackgroundColor(color);
        viewHolder_message.getCircleImageView3().setBackgroundColor(color);
         viewHolder_message.getTitle().setText(model_message.getTiltle());
         viewHolder_message.getTime().setText(model_message.getMessage_time());
         viewHolder_message.getConstraintLayout().setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 ((MainActivityy)maincontext).messagesFragment.showdialog(model_message.getTiltle(),
                         model_message.getMessage_body());
             }
         });

    }


    private void show_list_sefaresh_jadid(RecyclerView.ViewHolder viewHolder, final int i)
    {
        final Model_List_Sefaresh_jadid model_list_sefaresh_jadid=(Model_List_Sefaresh_jadid) items.get(i);
        ViewHolder_List_Sefaresh_Jadid viewHolder_list_sefaresh_jadid
                =(ViewHolder_List_Sefaresh_Jadid) viewHolder;
        Log.i("show_sefjadid",""+model_list_sefaresh_jadid.getNoeh_khadamat()+model_list_sefaresh_jadid.getHazineh()+
                model_list_sefaresh_jadid.getZaman_darkhasti_khadamat());
        viewHolder_list_sefaresh_jadid.getNoeh_khadamat().setText(model_list_sefaresh_jadid.getNoeh_khadamat());
        viewHolder_list_sefaresh_jadid.getHazineh_khadamat().setText(model_list_sefaresh_jadid.getHazineh());

        viewHolder_list_sefaresh_jadid.getZaman_anjam_kar().setText(model_list_sefaresh_jadid.getZaman_darkhasti_khadamat());
        if (model_list_sefaresh_jadid.getNahve_pardakht().contentEquals(Const_Variable.PAYMENT_TYPE_IS_ONLIN))
        {viewHolder_list_sefaresh_jadid.getVazeiat_pardakht().setTextColor(maincontext.getResources()
                .getColor(R.color.colorPrimary));
            viewHolder_list_sefaresh_jadid.getVazeiat_pardakht().setText(Const_Variable.ONLIN_WORD);

        }
        else
        {
            viewHolder_list_sefaresh_jadid.getVazeiat_pardakht().setTextColor(maincontext.getResources().
                    getColor(R.color.red));
            viewHolder_list_sefaresh_jadid.getVazeiat_pardakht().setText(Const_Variable.NAGHDI_WORD);
        }
        Picasso.with(maincontext).load(model_list_sefaresh_jadid.getAks_khadamat()).into(
                viewHolder_list_sefaresh_jadid.getImgv_aks_khadamat()
        );
        viewHolder_list_sefaresh_jadid.getOrderDetails().setText(model_list_sefaresh_jadid.getNoeh_khadamat_jozei()
        +"\n"+model_list_sefaresh_jadid.getAddress());
        viewHolder_list_sefaresh_jadid.getConstraintLayout().setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(maincontext, A_NewOrder_Details.class);
                        intent.putExtra("order_id",model_list_sefaresh_jadid.getId_sefaresh());
                        maincontext.startActivity(intent);
                        if(MotakhasesObject.temp_new_order_list!=null)
                        {
                            if(!MotakhasesObject.temp_new_order_list.contentEquals("0"))
                            {
                                String temp=MotakhasesObject.temp_new_order_list;
                                Log.i("split_in_recyclerA1",MotakhasesObject.temp_new_order_list);
                               String[] arraytemp= temp.split(Const_Variable.CHARACTER_SPLIT);
                               if(arraytemp.length>1)
                               {
                                 MotakhasesObject.temp_new_order_list=  remove_id_from_newOrder_idList(model_list_sefaresh_jadid.getId_sefaresh(),arraytemp);
                                 Log.i("split_in_recyclerA",MotakhasesObject.temp_new_order_list);

                               }
                               else
                               {MotakhasesObject.temp_new_order_list="0";}
                            }
                            else
                            {}
                        }
                        else
                        {}

                    }
                }
        );

    }

    private String remove_id_from_newOrder_idList(String id_sefaresh, String[] arraytemp)
    {
       // String[] temparry=new String[arraytemp.length-1];
        String strtemp="";
        for (int i=1;i<arraytemp.length;i++)
        {
            Log.i("strtemp_split_recycler",i+"");
            if(arraytemp[i].contentEquals(id_sefaresh))
            {}
            else
            {
             strtemp+=(Const_Variable.CHARACTER_SPLIT);
                strtemp+=(arraytemp[i]);
             Log.i("strtemp_split_recycler",i+":"+id_sefaresh+" : "+arraytemp[i]+"  "+strtemp);
            }
        }
        if(!strtemp.contentEquals(""))
        strtemp+=(Const_Variable.CHARACTER_SPLIT);
        else
            strtemp="0";
        return strtemp;
    }

    private void show_transaction(RecyclerView.ViewHolder viewHolder, int i)
    {
        Model_Transaction model_transaction=(Model_Transaction)items.get(i);
        ViewHolder_transaction_Model viewHolder_transaction_model=(ViewHolder_transaction_Model)viewHolder;
        if(model_transaction.getType_transaction().contentEquals(Const_Variable.TRANSACTION_TYPE_IS_REQUEST))
        {
            viewHolder_transaction_model.getType_transaction().setText("درخواست تسویه");
            viewHolder_transaction_model.getRequst_date().setText(model_transaction.getRequst_date());
            viewHolder_transaction_model.getAmount().setVisibility(View.GONE);
        viewHolder_transaction_model.getTasvyeh_date().setVisibility(View.GONE);
        viewHolder_transaction_model.getTracking_code().setVisibility(View.GONE);
        viewHolder_transaction_model.getConstraintLayout().setBackground(maincontext.getResources().getDrawable(R.drawable.shap_with_strok_and_radious_ghermez));
        viewHolder_transaction_model.getImgv().setBackgroundColor(maincontext.getResources().getColor(R.color.red));
        }
        else
        {

            viewHolder_transaction_model.getType_transaction().setText("واریز وجه");
            viewHolder_transaction_model.getRequst_date().setText(model_transaction.getRequst_date());
            viewHolder_transaction_model.getAmount().setText(model_transaction.getAmount());
            viewHolder_transaction_model.getTasvyeh_date().setText(model_transaction.getTasvyeh_date());
            viewHolder_transaction_model.getTracking_code().setText(model_transaction.getTracking_code());
        }
    }

    private void show_list_sefaresh_darhaleanjam(RecyclerView.ViewHolder viewHolder, int i)
    {

        final Model_Sefaresh_DarhaleAnjam model_list_sefaresh_darhaleAnjam=(Model_Sefaresh_DarhaleAnjam)items.get(i);
        ViewHolder_List_Sefaresh_DarhaleAnjam viewHolder_list_sefaresh_darhaleAnjam
                =(ViewHolder_List_Sefaresh_DarhaleAnjam)viewHolder;
        viewHolder_list_sefaresh_darhaleAnjam.getNoeh_khadamat().setText(model_list_sefaresh_darhaleAnjam.getNoeh_dastebandi_khadamat());
        viewHolder_list_sefaresh_darhaleAnjam.getHazineh_khadamat().setText(model_list_sefaresh_darhaleAnjam.getMablagh_nahaei());

        if (model_list_sefaresh_darhaleAnjam.getNahve_pardakht().contains(Const_Variable.PAYMENT_TYPE_IS_ONLIN))

        {
            if (model_list_sefaresh_darhaleAnjam.getVazeiat_pardakht().contains(Const_Variable.PAYMENT_STATUS_IS_PARDAKHT_SHODE))
            {

                viewHolder_list_sefaresh_darhaleAnjam.getVazeiat_pardakht().setText(Const_Variable.ONLIN_WORD);
                viewHolder_list_sefaresh_darhaleAnjam.getVazeiat_pardakht().setTextColor(maincontext.getResources()
                        .getColor(R.color.colorPrimary));
            }
            else
            {

                viewHolder_list_sefaresh_darhaleAnjam.getVazeiat_pardakht().setText(Const_Variable.ONLIN_WORD);
                viewHolder_list_sefaresh_darhaleAnjam.getVazeiat_pardakht().setTextColor(maincontext.getResources()
                        .getColor(R.color.red));
            }

        }
        else
        {

            viewHolder_list_sefaresh_darhaleAnjam.getVazeiat_pardakht().setText(Const_Variable.NAGHDI_WORD);
            viewHolder_list_sefaresh_darhaleAnjam.getVazeiat_pardakht().setTextColor(maincontext.getResources()
                    .getColor(R.color.red));
        }
        Picasso.with(maincontext).load(model_list_sefaresh_darhaleAnjam.getService_imgaddress()).into(
                viewHolder_list_sefaresh_darhaleAnjam.getImgv_aks_khadamat()
        );
        viewHolder_list_sefaresh_darhaleAnjam.getZaman_anjam_kar().
                setText(model_list_sefaresh_darhaleAnjam.getZaman_darkhasti_khadamat());
        viewHolder_list_sefaresh_darhaleAnjam.getCardView().setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(maincontext, A_Jozeiat_Sefarsh_Darhal_Anjam.class);
                        intent.putExtra("order_id",model_list_sefaresh_darhaleAnjam.getId());
                       maincontext.startActivity(intent);
                        //Valley_Api valley_api=new Valley_Api(maincontext);
                       // valley_api.get_new_order_details(model_list_sefaresh_darhaleAnjam.getId());
                    }
                }
        );
    }

    private void show_list_sefaresh_laghv_shodeh(RecyclerView.ViewHolder viewHolder, int i)
    {
        Model_Sefaresh_laghv_Shodeh model_sefaresh_laghv_shodeh=(Model_Sefaresh_laghv_Shodeh)items.get(i);
        ViewHolder_List_Sefaresh_Laghv_Shodeh viewHolder_list_sefaresh_laghv_shodeh=
                (ViewHolder_List_Sefaresh_Laghv_Shodeh)viewHolder;
        viewHolder_list_sefaresh_laghv_shodeh.getNoeh_khadamat()
                .setText(model_sefaresh_laghv_shodeh.getNoeh_khadamat());
        viewHolder_list_sefaresh_laghv_shodeh.getZaman_sefaresh().
                setText(model_sefaresh_laghv_shodeh.getZaman_anjamsefaresh());
        viewHolder_list_sefaresh_laghv_shodeh.getZaman_laghv_sefaresh()
                .setText(model_sefaresh_laghv_shodeh.getZaman_laghve_sefarsh());

        Picasso.with(maincontext).load(model_sefaresh_laghv_shodeh.getImg_address()).into(
                viewHolder_list_sefaresh_laghv_shodeh.getImageView());


    }

    private void show_list_sefaresh_anjam_shodeh(RecyclerView.ViewHolder viewHolder, final int i)
    {
        final Model_List_Sefaresh_Anjam_Shodeh model_list_sefaresh_anjam_shodeh=(Model_List_Sefaresh_Anjam_Shodeh)items.get(i);
        ViewHolder_List_Sefaresh_Anjam_Shodeh viewHolder_list_sefaresh_anjam_shodeh
                =(ViewHolder_List_Sefaresh_Anjam_Shodeh)viewHolder;
        viewHolder_list_sefaresh_anjam_shodeh.getNoeh_khadamat().setText(model_list_sefaresh_anjam_shodeh.getNoeh_khadamat());
        viewHolder_list_sefaresh_anjam_shodeh.getHazineh_khadamat().setText(model_list_sefaresh_anjam_shodeh.getHazineh_khadamat());
        viewHolder_list_sefaresh_anjam_shodeh.getVazeiat_pardakht().setText(model_list_sefaresh_anjam_shodeh.getVazeiat_pardakht());
        if (model_list_sefaresh_anjam_shodeh.getVazeiat_pardakht().contains(Const_Variable.INCREASE_KIF_POL))
        {viewHolder_list_sefaresh_anjam_shodeh.getVazeiat_pardakht().setTextColor(maincontext.getResources()
                .getColor(R.color.colorPrimary));

        }
        else
        {
            viewHolder_list_sefaresh_anjam_shodeh.getVazeiat_pardakht().setTextColor(maincontext.getResources().
                    getColor(R.color.red));
        }
        Picasso.with(maincontext).load(model_list_sefaresh_anjam_shodeh.getAks_khadamat()).into(
                viewHolder_list_sefaresh_anjam_shodeh.getImgv_aks_khadamat()
        );
        viewHolder_list_sefaresh_anjam_shodeh.getZaman_anjam_kar().setText(model_list_sefaresh_anjam_shodeh.getZaman_anjamsefaresh());
        viewHolder_list_sefaresh_anjam_shodeh.getCardView().setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(maincontext, A_Jozeiat_Sefaresh_Anjamshode.class);
                        intent.putExtra("order_id",model_list_sefaresh_anjam_shodeh.getId_sefaresh());
                        maincontext.startActivity(intent);
                    }
                }
        );

    }
    private void show_Model_saat_kari(RecyclerView.ViewHolder viewHolder, final int i)
    {
        myinterface=(My_interface)maincontext;
        Model_saat_kari_recyclerview model_saat_kari_recyclerview=(Model_saat_kari_recyclerview)items.get(i);
        final ViewHolder_saat_kari_recyclerview viewHolder_saat_kari_recyclerview=(ViewHolder_saat_kari_recyclerview)viewHolder;
        viewHolder_saat_kari_recyclerview.getCheckedTextView().setText(model_saat_kari_recyclerview.getSaat_kari());

        viewHolder_saat_kari_recyclerview.getCheckedTextView().setChecked(false);
                viewHolder_saat_kari_recyclerview.getCheckedTextView().setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                viewHolder_saat_kari_recyclerview.getCheckedTextView().toggle();
                                if (viewHolder_saat_kari_recyclerview.getCheckedTextView().isChecked())
                                {

                                    viewHolder_saat_kari_recyclerview.getCheckedTextView().
                                            setCheckMarkDrawable(maincontext.getResources()
                                            .getDrawable(R.drawable.checked_circle));
                                    myinterface.greftan_list_manategh_entkhabi_as_recycler_view_adapter("saat_kari",i,1);
                                 //   viewHolder_saat_kari_recyclerview.getCheckedTextView().setText(viewHolder_saat_kari_recyclerview.getCheckedTextView().getText());
//                                    myinterface.greftan_list_manategh_entkhabi_as_recycler_view_adapter("saat_kari",i,1);
                                }
                                else
                                {

                                    viewHolder_saat_kari_recyclerview.getCheckedTextView().
                                            setCheckMarkDrawable(null);
                                    myinterface.greftan_list_manategh_entkhabi_as_recycler_view_adapter("saat_kari",i,0);
                            //        myinterface.greftan_list_manategh_entkhabi_as_recycler_view_adapter("saat_kari",i,0);
                                }
                            }
                        }
                );

    }

    private void show_takhasos_level2(RecyclerView.ViewHolder viewHolder, final int i)
    {        this.myinterface =(My_interface)this.maincontext;
        Model_Takhasos_level2_recyclerview model_takhasos_level2_recyclerview=(Model_Takhasos_level2_recyclerview)items.get(i);
        final ViewHolder_Takhasos_level2_recyclerview viewHolder_takhasos_level2_recyclerview=(ViewHolder_Takhasos_level2_recyclerview)viewHolder;
        viewHolder_takhasos_level2_recyclerview.getCheckedTextView().setText(model_takhasos_level2_recyclerview.getTakhasos_level2());
        viewHolder_takhasos_level2_recyclerview.getCheckedTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewHolder_takhasos_level2_recyclerview.getCheckedTextView().toggle();
                if(viewHolder_takhasos_level2_recyclerview.getCheckedTextView().isChecked())
                {

                    myinterface.
                            greftan_list_manategh_entkhabi_as_recycler_view_adapter("takhasos_level2",i,1);
                }
                else
                {


                    myinterface.
                            greftan_list_manategh_entkhabi_as_recycler_view_adapter("takhasos_level2",i,0);

                }





            }
        });

    }

    private void Show_takhasos(RecyclerView.ViewHolder viewHolder, final int i)
    {
        myinterface=(My_interface) maincontext;
        valley_api=new Valley_Api(maincontext);

        final Model_Takhasos_recyclerview model_takhasos_recyclerview= (Model_Takhasos_recyclerview) items.get(i);
        final ViewHolder_Takhasos_recyclerview holder_takhasos_recyclerview= (ViewHolder_Takhasos_recyclerview) viewHolder;
        holder_takhasos_recyclerview.getCheckedTextView().setText(model_takhasos_recyclerview.getTakhasos());
        
        holder_takhasos_recyclerview.getCheckedTextView().setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(model_takhasos_recyclerview.getSub_categuti_status().contentEquals(
                                Const_Variable.SERVICE_HAVE_SUB_CATEGORY ))
                        {

                            holder_takhasos_recyclerview.getCheckedTextView().toggle();
                            ((A_Greftan_Aks_Madarek_Motakhases)maincontext).get_servics_have_subcategory
                                    (model_takhasos_recyclerview.getTakhasos()
                                            ,i,holder_takhasos_recyclerview.getCheckedTextView().isChecked());
                        }
                        else
                        {
                            holder_takhasos_recyclerview.getCheckedTextView().toggle();
                            if(holder_takhasos_recyclerview.getCheckedTextView().isChecked())
                            {

                                myinterface.
                                        greftan_list_manategh_entkhabi_as_recycler_view_adapter("takhasos",i,1);
                            }
                            else
                            {


                                myinterface.
                                        greftan_list_manategh_entkhabi_as_recycler_view_adapter("takhasos",i,0);

                            }
                        }
                    }
                }
        );
    }

    //////////////////////////////////////////showezafehkardaneh_address
    ///////////////////////////////////////////
    private void show_ezafehkardaneh_address(RecyclerView.ViewHolder viewHolder, final int i)
    {   this.myinterface =(My_interface)this.maincontext;
        Model_Ezafehkardan_Address model_ezafehkardan_address= (Model_Ezafehkardan_Address) items.get(i);
        final ViewHolder_Ezafekardan_Address viewHolder_ezafekardan_address= (ViewHolder_Ezafekardan_Address) viewHolder;
        viewHolder_ezafekardan_address.getCheckedTextView_address_mantaghe().setText(model_ezafehkardan_address.getAddress());
        viewHolder_ezafekardan_address.getCheckedTextView_address_mantaghe().setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        viewHolder_ezafekardan_address.getCheckedTextView_address_mantaghe().toggle();
                        if(viewHolder_ezafekardan_address.getCheckedTextView_address_mantaghe().isChecked())
                        {

                            myinterface.
                                    greftan_list_manategh_entkhabi_as_recycler_view_adapter("manategh",i,1);
                                                 }
                        else
                            {


                                myinterface.
                                        greftan_list_manategh_entkhabi_as_recycler_view_adapter("manategh",i,0);

                        }




                    }
                }
        );
            }
    public void setFadeAnimation(View view) {
      //  AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
     //   anim.setDuration(500);
      //  view.startAnimation(anim);

            Animation animation= AnimationUtils.loadAnimation(maincontext,R.anim.anim_kesho);
            animation.setDuration(500);
            view.setAnimation(animation);

    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(maincontext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    private void setscallanimation(View itemView)
    {

        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(500);
        itemView.startAnimation(anim);

    }
    @Override
    public int getItemCount() {
        return items.size();
    }


}
