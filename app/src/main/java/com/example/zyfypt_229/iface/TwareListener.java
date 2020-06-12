package com.example.zyfypt_229.iface;

import com.example.zyfypt_229.bean.TwareBean;


import java.util.List;

public interface TwareListener {
    //成功返回信息
    void onResponse(List<TwareBean> beanlist);
    //失败返回错误字符串
    void onFail(String msg);
}
