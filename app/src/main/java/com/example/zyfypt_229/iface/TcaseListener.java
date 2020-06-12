package com.example.zyfypt_229.iface;

import com.example.zyfypt_229.bean.TcaseBean;


import java.util.List;

public interface TcaseListener {
    //成功返回信息
    void onResponse(List<TcaseBean> beanlist);
    //失败返回错误字符串
    void onFail(String msg);
}
