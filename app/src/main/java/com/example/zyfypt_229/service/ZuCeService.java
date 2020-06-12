package com.example.zyfypt_229.service;

import com.example.zyfypt_229.bean.ZCbean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ZuCeService {
    @GET("api.php/reg/username/{username}/password/{password}/")

    Call<ZCbean> zuceloign(@Path("username") String username, @Path("password") String password);
}
