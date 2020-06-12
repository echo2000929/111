package com.example.zyfypt_229.service;

import com.example.zyfypt_229.bean.ArticleBean;
import com.example.zyfypt_229.bean.CollectBean;
import com.example.zyfypt_229.bean.ProjectBean;
import com.example.zyfypt_229.bean.TcaseBean;
import com.example.zyfypt_229.bean.TwareBean;
import com.example.zyfypt_229.bean.VideoBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CollectListService {
    //收藏文章列表
    @GET("api.php/listmycollect/mod/collect{mod}")
    Call<List<CollectBean<ArticleBean>>> getCArticleList(
            @Path("mod") String mode,
            @Query("page") int page,
            @Header("SessionID") String SessionID);
    //收藏案例列表
    @GET("api.php/listmycollect/mod/collect{mod}")
    Call<List<CollectBean<TcaseBean>>> getCTcaseList(
            @Path("mod") String mode,
            @Query("page") int page,
            @Header("SessionID") String SessionID);
    //收藏课件列表
    @GET("api.php/listmycollect/mod/collect{mod}")
    Call<List<CollectBean<TwareBean>>> getCTwareList(
            @Path("mod") String mode,
            @Query("page") int page,
            @Header("SessionID") String SessionID);
    //收藏项目列表
    @GET("api.php/listmycollect/mod/collect{mod}")
    Call<List<CollectBean<ProjectBean>>> getCPojectList(
            @Path("mod") String mode,
            @Query("page") int page,
            @Header("SessionID") String SessionID);
    //收藏视频列表
    @GET("api.php/listmycollect/mod/collect{mod}")
    Call<List<CollectBean<VideoBean>>> getCVideoList(
            @Path("mod") String mode,
            @Query("page") int page,
            @Header("SessionID") String SessionID);
}
