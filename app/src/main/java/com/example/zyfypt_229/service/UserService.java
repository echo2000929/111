package com.example.zyfypt_229.service;

import com.example.zyfypt_229.bean.LoginBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET("api.php/login")
    Call<LoginBean> login(
            @Query("username")String username,
            @Query("password")String password
    );
}
