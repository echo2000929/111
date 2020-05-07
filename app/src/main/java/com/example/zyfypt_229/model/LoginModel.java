package com.example.zyfypt_229.model;

import com.example.zyfypt_229.bean.LoginBean;
import com.example.zyfypt_229.common.Common;
import com.example.zyfypt_229.iface.LoginListener;
import com.example.zyfypt_229.iface.Loginiface;
import com.example.zyfypt_229.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginModel implements Loginiface {
    private Retrofit retrofit;
    //构造函数
    public LoginModel()
    {   //使用Retrofit----1
        retrofit=new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    @Override
    public void getLoginResult(String username, String pass, final LoginListener loginListener) {
        //使用Retrofit----2
        UserService userService
                =retrofit.create(UserService.class);
        Call<LoginBean> call
                =userService.login(username,pass);
        //使用Retrofit----3
        call.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                if(response.isSuccessful()&&response.body()!=null&& response.body().getId()!=null)
                {
                    loginListener.onResponse(response.body());
                }
                else loginListener.onFail("login fail");
            }
            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                loginListener.onFail(t.getMessage ());
            }
        });
    }
}
