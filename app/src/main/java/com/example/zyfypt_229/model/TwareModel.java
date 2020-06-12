package com.example.zyfypt_229.model;

import com.example.zyfypt_229.bean.TwareBean;
import com.example.zyfypt_229.common.Common;
import com.example.zyfypt_229.iface.TwareIface;
import com.example.zyfypt_229.iface.TwareListener;
import com.example.zyfypt_229.service.TwareService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TwareModel implements TwareIface {
    private Retrofit retrofit;

    //构造函数
    public TwareModel()
    {   //使用Retrofit----1
        retrofit=new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getResultList(String mod, int page, String sessionID, final TwareListener twareListener) {
        //使用Retrofit----2
        TwareService service
                =retrofit.create(TwareService.class);
        Call<List<TwareBean>> call
                =service.getTwareList(mod,page,sessionID);
        //使用Retrofit----3
        call.enqueue(new Callback<List<TwareBean>>() {
            @Override
            public void onResponse(Call<List<TwareBean>> call, Response<List<TwareBean>> response) {
                if(response.isSuccessful() && response!=null)
                {  twareListener.onResponse(response.body());
                }
                else   twareListener.onFail("on response fail");
            }
            @Override
            public void onFailure(Call<List<TwareBean>> call, Throwable t) {
                twareListener.onFail(t.toString());
            }
        });
    }

}
