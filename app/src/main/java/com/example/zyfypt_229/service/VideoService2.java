package com.example.zyfypt_229.service;

import com.example.zyfypt_229.bean.VideoBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VideoService2 {
    @GET("api.php/listspecial/mod/{mod}")
    Call<List<VideoBean>> getVideoList(
            @Path("mod") String mod,
            @Query("page") int page,
            @Header("SessionID") String sessionID);
}
