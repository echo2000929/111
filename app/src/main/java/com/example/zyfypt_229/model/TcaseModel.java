package com.example.zyfypt_229.model;

import com.example.zyfypt_229.bean.TcaseBean;
import com.example.zyfypt_229.common.Common;
import com.example.zyfypt_229.iface.TcaseIface;
import com.example.zyfypt_229.iface.TcaseListener;
import com.example.zyfypt_229.service.TcaseService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TcaseModel implements TcaseIface {
    private Retrofit retrofit;

    //构造函数
    public TcaseModel()
    {   //使用Retrofit----1
        retrofit=new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    @Override
    public void getResultList(String mod, int page, String sessionID, final TcaseListener tcaseListener) {
        //使用Retrofit----2
        TcaseService service
                =retrofit.create(TcaseService.class);
        Call<List<TcaseBean>> call
                =service.getTcaseList(mod,page,sessionID);
        //使用Retrofit----3
        call.enqueue(new Callback<List<TcaseBean>>() {
            @Override
            public void onResponse(Call<List<TcaseBean>> call, Response<List<TcaseBean>> response) {
                if(response.isSuccessful() && response!=null)
                {  tcaseListener.onResponse(response.body());
                }
                else   tcaseListener.onFail("on response fail");
            }
            @Override
            public void onFailure(Call<List<TcaseBean>> call, Throwable t) {
                tcaseListener.onFail(t.toString());
            }
        });
    }
}
