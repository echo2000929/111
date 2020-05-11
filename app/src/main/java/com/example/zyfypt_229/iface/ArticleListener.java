package com.example.zyfypt_229.iface;

import com.example.zyfypt_229.bean.ArticleBean;

import java.util.List;

public interface ArticleListener {
    //成功返回信息
    void onResponse(List<ArticleBean> beanlist);
    //失败返回错误字符串
    void onFail(String msg);
}
