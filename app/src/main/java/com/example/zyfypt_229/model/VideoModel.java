package com.example.zyfypt_229.model;

import com.example.zyfypt_229.bean.VideoBean;
import com.example.zyfypt_229.common.Common;
import com.example.zyfypt_229.iface.VideoIface;
import com.example.zyfypt_229.iface.VideoListener;
import com.example.zyfypt_229.service.VideoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoModel implements VideoIface {
    private Retrofit retrofit;

    //构造函数
    public VideoModel() {   //使用Retrofit----1
        retrofit = new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getResultList(String mod, int page, String sessionID, final VideoListener videoListener) {
        //使用Retrofit----2
        VideoService service
                = retrofit.create(VideoService.class);
        Call<List<VideoBean>> call
                = service.getVideoList(mod, page, sessionID);
        //使用Retrofit----3
        call.enqueue(new Callback<List<VideoBean>>() {
            @Override
            public void onResponse(Call<List<VideoBean>> call, Response<List<VideoBean>> response) {
                if (response.isSuccessful() && response != null) {
                    videoListener.onResponse(response.body());
                } else videoListener.onFail("on response fail");
            }

            @Override
            public void onFailure(Call<List<VideoBean>> call, Throwable t) {
                videoListener.onFail(t.toString());
            }
        });
    }
}