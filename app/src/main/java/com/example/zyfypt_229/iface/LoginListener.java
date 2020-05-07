package com.example.zyfypt_229.iface;

import com.example.zyfypt_229.bean.LoginBean;

public interface LoginListener {
    void onResponse(LoginBean loginBean);
    void onFail(String msg);
}
