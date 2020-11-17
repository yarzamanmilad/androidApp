package com.example.khialerahat.experts_khialerahat.Web_Service;

import com.example.khialerahat.experts_khialerahat.ApiModel.Model_list_ostanha;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api_Interface
 {  @GET("city/get_ostan.php")
    Call<Model_list_ostanha> get_nsme_ostanha();
}
