package com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class Moshakhasat_Motehkases
{
    public Boolean status_uplad_moshakhasat_fardi,
    status_upload_workTimes , status_upload_manateghKHedmatDahi,
    status_upload_listTakhasos;

    private   String id;
    private String name;
    private String family;
    private String codemeli;
    private String shomare_aberbank,shomare_hesab,shomare_shaba;
    private List<String>  list_manategh_khedmatdahi=new ArrayList<>();
    private List<String>  list_id_manategh_khedmatdahi=new ArrayList<>();
    private List<String>  list_saatKari_shanbeh=new ArrayList<>();
    private List<String>  list_saatKari_yek_shanbeh=new ArrayList<>();
    private List<String>  list_saatKari_do_shanbeh=new ArrayList<>();
    private List<String>  list_saatKari_seh_shanbeh=new ArrayList<>();
    private List<String>  list_saatKari_chehar_shanbeh=new ArrayList<>();
    private List<String>  list_saatKari_panj_shanbeh=new ArrayList<>();
    private List<String>  list_saatKari_jomeh=new ArrayList<>();
    private List<String> list_takhasos=new ArrayList<>();
    private List<String> list_id_takhasos=new ArrayList<>();
   private String jensiat;
   private String pushid;


    private String vazeyat_tahol;
    private String shomare_hamrah;
    private String shomare_sabet;
    private String ostan;
    private String shahrestan;
    private String address_daghigh;
    private String ramzevrod;
    Bitmap  aks_kartmeli
            ,aks_poshtekartmeli,
             aks_shenasname,
    aks_payankhedmat,aks_gavahi_fani,
    aks_sayermadarek;
    private List<String> manategh_shahrestan=new ArrayList<>();

    List<String>List_id_takhasos_level1=new ArrayList<>();


    private List<String> List_takhasos_level2=new ArrayList<>();
    private List<String> List_id_takhasos_level2=new ArrayList<>();


    public Moshakhasat_Motehkases() {

        this.status_uplad_moshakhasat_fardi=false;
      this.  status_upload_workTimes=false;
      this.status_upload_manateghKHedmatDahi=false;
      this.status_upload_listTakhasos=false;
        list_saatKari_shanbeh.add("100");
        list_saatKari_shanbeh.add("100");
        list_saatKari_yek_shanbeh.add("100");
        list_saatKari_yek_shanbeh.add("100");
        list_saatKari_do_shanbeh.add("100");
        list_saatKari_do_shanbeh.add("100");
        list_saatKari_seh_shanbeh.add("100");
        list_saatKari_seh_shanbeh.add("100");

        list_saatKari_chehar_shanbeh.add("100");
        list_saatKari_chehar_shanbeh.add("100");
        list_saatKari_panj_shanbeh.add("100");

        list_saatKari_panj_shanbeh.add("100");
        list_saatKari_jomeh.add("100");
        list_saatKari_jomeh.add("100");

    }

    public String getPushid() {
        return pushid;
    }

    public void setPushid(String pushid) {
        this.pushid = pushid;
    }

    public String getShomare_aberbank() {
        return shomare_aberbank;
    }

    public void setShomare_aberbank(String shomare_aberbank) {
        this.shomare_aberbank = shomare_aberbank;
    }

    public String getShomare_hesab() {
        return shomare_hesab;
    }

    public void setShomare_hesab(String shomare_hesab) {
        this.shomare_hesab = shomare_hesab;
    }

    public String getShomare_shaba() {
        return shomare_shaba;
    }

    public void setShomare_shaba(String shomare_shaba) {
        this.shomare_shaba = shomare_shaba;
    }

    public Boolean getStatus_uplad_moshakhasat_fardi() {
        return status_uplad_moshakhasat_fardi;
    }

    public void setStatus_uplad_moshakhasat_fardi(Boolean status_uplad_moshakhasat_fardi) {
        this.status_uplad_moshakhasat_fardi = status_uplad_moshakhasat_fardi;
    }

    public Boolean getStatus_upload_workTimes() {
        return status_upload_workTimes;
    }

    public void setStatus_upload_workTimes(Boolean status_upload_workTimes) {
        this.status_upload_workTimes = status_upload_workTimes;
    }

    public Boolean getStatus_upload_manateghKHedmatDahi() {
        return status_upload_manateghKHedmatDahi;
    }

    public void setStatus_upload_manateghKHedmatDahi(Boolean status_upload_manateghKHedmatDahi) {
        this.status_upload_manateghKHedmatDahi = status_upload_manateghKHedmatDahi;
    }

    public Boolean getStatus_upload_listTakhasos() {
        return status_upload_listTakhasos;
    }

    public void setStatus_upload_listTakhasos(Boolean status_upload_listTakhasos) {
        this.status_upload_listTakhasos = status_upload_listTakhasos;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getSet_status_moshakhasat_fardi() {
        return status_uplad_moshakhasat_fardi;
    }

    public void setSet_status_moshakhasat_fardi(Boolean set_status_moshakhasat_fardi) {
        this.status_uplad_moshakhasat_fardi = set_status_moshakhasat_fardi;
    }

    public String getId() {
        return id;
    }
    public List<String> getManategh_shahrestan() {
        return manategh_shahrestan;
    }

    public void setManategh_shahrestan(List<String> manategh_shahrestan) {
        this.manategh_shahrestan = manategh_shahrestan;
    }

    public List<String> getList_id_manategh_khedmatdahi() {
        return list_id_manategh_khedmatdahi;
    }

    public void setList_id_manategh_khedmatdahi(List<String> list_id_manategh_khedmatdahi) {
        this.list_id_manategh_khedmatdahi = list_id_manategh_khedmatdahi;
    }
    public Bitmap getAks_kartmeli() {
        return aks_kartmeli;
    }

    public void setAks_kartmeli(Bitmap aks_kartmeli) {
        this.aks_kartmeli = aks_kartmeli;
    }

    public Bitmap getAks_poshtekartmeli() {
        return aks_poshtekartmeli;
    }

    public void setAks_poshtekartmeli(Bitmap aks_poshtekartmeli) {
        this.aks_poshtekartmeli = aks_poshtekartmeli;
    }

    public Bitmap getAks_shenasname() {
        return aks_shenasname;
    }

    public void setAks_shenasname(Bitmap aks_shenasname) {
        this.aks_shenasname = aks_shenasname;
    }

    public Bitmap getAks_payankhedmat() {
        return aks_payankhedmat;
    }

    public void setAks_payankhedmat(Bitmap aks_payankhedmat) {
        this.aks_payankhedmat = aks_payankhedmat;
    }

    public Bitmap getAks_gavahi_fani() {
        return aks_gavahi_fani;
    }

    public void setAks_gavahi_fani(Bitmap aks_gavahi_fani) {
        this.aks_gavahi_fani = aks_gavahi_fani;
    }

    public Bitmap getAks_sayermadarek() {
        return aks_sayermadarek;
    }

    public void setAks_sayermadarek(Bitmap aks_sayermadarek) {
        this.aks_sayermadarek = aks_sayermadarek;
    }

    public List<String> getList_saatKari_shanbeh() {
        return list_saatKari_shanbeh;
    }

    public void setList_saatKari_shanbeh(List<String> list_saatKari_shanbeh) {
        this.list_saatKari_shanbeh = list_saatKari_shanbeh;
    }

    public List<String> getList_saatKari_yek_shanbeh() {
        return list_saatKari_yek_shanbeh;
    }

    public void setList_saatKari_yek_shanbeh(List<String> list_saatKari_yek_shanbeh) {
        this.list_saatKari_yek_shanbeh = list_saatKari_yek_shanbeh;
    }

    public List<String> getList_saatKari_do_shanbeh() {
        return list_saatKari_do_shanbeh;
    }

    public void setList_saatKari_do_shanbeh(List<String> list_saatKari_do_shanbeh) {
        this.list_saatKari_do_shanbeh = list_saatKari_do_shanbeh;
    }

    public List<String> getList_saatKari_seh_shanbeh() {
        return list_saatKari_seh_shanbeh;
    }

    public void setList_saatKari_seh_shanbeh(List<String> list_saatKari_seh_shanbeh) {
        this.list_saatKari_seh_shanbeh = list_saatKari_seh_shanbeh;
    }

    public List<String> getList_saatKari_chehar_shanbeh() {
        return list_saatKari_chehar_shanbeh;
    }

    public void setList_saatKari_chehar_shanbeh(List<String> list_saatKari_chehar_shanbeh) {
        this.list_saatKari_chehar_shanbeh = list_saatKari_chehar_shanbeh;
    }

    public List<String> getList_saatKari_panj_shanbeh() {
        return list_saatKari_panj_shanbeh;
    }

    public void setList_saatKari_panj_shanbeh(List<String> list_saatKari_panj_shanbeh) {
        this.list_saatKari_panj_shanbeh = list_saatKari_panj_shanbeh;
    }

    public List<String> getList_saatKari_jomeh() {
        return list_saatKari_jomeh;
    }

    public List<String> getList_id_takhasos_level1() {
        return List_id_takhasos_level1;
    }

    public void setList_id_takhasos_level1(List<String> list_id_takhasos_level1) {
        List_id_takhasos_level1 = list_id_takhasos_level1;
    }

    public void setList_saatKari_jomeh(List<String> list_saatKari_jomeh) {
        this.list_saatKari_jomeh = list_saatKari_jomeh;
    }

    public List<String> getList_takhasos() {
        return list_takhasos;
    }

    public void setList_takhasos(List<String> list_takhasos) {
        this.list_takhasos = list_takhasos;
    }

    public List<String> getList_takhasos_level2() {
        return List_takhasos_level2;
    }

    public void setList_takhasos_level2(List<String> list_takhasos_level2) {
        List_takhasos_level2 = list_takhasos_level2;
    }

    public List<String> getList_id_takhasos() {
        return list_id_takhasos;
    }

    public void setList_id_takhasos(List<String> list_id_takhasos) {
        this.list_id_takhasos = list_id_takhasos;
    }

    public List<String> getList_id_takhasos_level2() {
        return List_id_takhasos_level2;
    }

    public void setList_id_takhasos_level2(List<String> list_id_takhasos_level2) {
        List_id_takhasos_level2 = list_id_takhasos_level2;
    }

    public List<String> getList_manategh_khedmatdahi() {

        return list_manategh_khedmatdahi;
    }

    public void setList_manategh_khedmatdahi(List<String> list_manategh_khedmatdahi) {
        this.list_manategh_khedmatdahi = list_manategh_khedmatdahi;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setCodemeli(String codemeli) {
        this.codemeli = codemeli;
    }

    public void setJensiat(String jensiat) {
        this.jensiat = jensiat;
    }

    public void setVazeyat_tahol(String vazeyat_tahol) {
        this.vazeyat_tahol = vazeyat_tahol;
    }

    public void setShomare_hamrah(String shomare_hamrah) {
        this.shomare_hamrah = shomare_hamrah;
    }

    public void setShomare_sabet(String shomare_sabet) {
        this.shomare_sabet = shomare_sabet;
    }

    public void setOstan(String ostan) {
        this.ostan = ostan;
    }

    public void setShahrestan(String shahrestan) {
        this.shahrestan = shahrestan;
    }

    public void setAddress_daghigh(String address_daghigh) {
        this.address_daghigh = address_daghigh;
    }

    public void setRamzevrod(String ramzevrod) {
        this.ramzevrod = ramzevrod;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public String getCodemeli() {
        return codemeli;
    }

    public String getJensiat() {
        return jensiat;
    }

    public String getVazeyat_tahol() {
        return vazeyat_tahol;
    }

    public String getShomare_hamrah() {
        return shomare_hamrah;
    }

    public String getShomare_sabet() {
        return shomare_sabet;
    }


    public String getOstan() {
        return ostan;
    }

    public String getShahrestan() {
        return shahrestan;
    }

    public String getAddress_daghigh() {
        return address_daghigh;
    }

    public String getRamzevrod() {
        return ramzevrod;
    }
}
