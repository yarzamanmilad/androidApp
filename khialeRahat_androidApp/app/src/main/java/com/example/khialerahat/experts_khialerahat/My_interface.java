package com.example.khialerahat.experts_khialerahat;

import com.example.khialerahat.experts_khialerahat.MainPackage.Model_Profile;
import com.example.khialerahat.experts_khialerahat.MainPackage.MotakhasesObject;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Model_Takhasos;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Model_Takhasos_level2;
import com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin.Model_Time;

import java.util.List;

public interface My_interface {
    public  void txtfilter(Boolean harfe_vrodi_sahih_ast, int textInputLayout, int edtxt_id);
    public void greftan_list_manategh_entkhabi_as_recycler_view_adapter(String manategh_ya_takhasos,int item_position, int meghdar);
    public void greftan_list_shahrha(List<String> list_shahrha, List<String> list_id_shahrha);
    public void greftan_list_ostanha(List<String> list_ostanha);
    public void greftan_list_manategh(List<String> list_manategh);
    public void greftan_list_takhasos(Model_Takhasos[] list_takhasos);
    public void greftan_list_takhasos_level2(Model_Takhasos_level2[] list_takhasos_level2);
    public void greftan_list_times(Model_Time[] list_time);
    public void greftan_id_motakhases(String id);
    public void upload_status(Boolean status, String feild_name);
    public void recive_data_from_server_failur(String error_status, String title_error, String error_message);
    public void username_athuntication(String status_username);
    public void password_athuntication(MotakhasesObject motakhasesObject);
    public  void get_profile_data(Model_Profile model_profile);
    public  void greftan_list_manategh_entkhabi_have_childeren_as_recycler_view_adapter(String takhasos_name, int index, boolean chtxt_ischecked);
    public  void failure_in_athunticaation(boolean boolean_exit_or_repeat);
    public  void get_order_payment_type_from_server(String payment_type, String name);
    public  void c1(String c);
    public  void a1(String a);

    public  void b11(String b);
    public  void c11(String c);
    public  void a11(String a);

}
