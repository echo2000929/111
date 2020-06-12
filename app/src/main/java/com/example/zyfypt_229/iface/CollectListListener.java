package com.example.zyfypt_229.iface;

import com.example.zyfypt_229.bean.CollectBean;
import com.vividsolutions.jts.awt.PointShapeFactory;

import java.util.List;

public interface CollectListListener<T> {
    void onResponse(List<CollectBean<T>> beanlist);
    void onFail(String msg);
}


