package com.example.zyfypt_229.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zyfypt_229.R;

import com.example.zyfypt_229.adapter.CTwareAdapter;

import com.example.zyfypt_229.bean.CollectBean;
import com.example.zyfypt_229.bean.TwareBean;
import com.example.zyfypt_229.iface.CollectListListener;
import com.example.zyfypt_229.model.CTwareModel;


import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CFragment2 extends BaseFragment {
    private View view = null;
    private Context context;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;//显示布局效果
    private CTwareAdapter adapter;//适配器
    private List<CollectBean<TwareBean>> list = null;//数据源

    private String sessionID = "";

    private int page = 1;// 代表页数，并初始化为1，代表第1页。
    private int lastVisibleItemPosition;//最后一条可见条目的位置

    CollectListListener<TwareBean> listListener = new CollectListListener<TwareBean>() {
        @Override
        public void onResponse(List<CollectBean<TwareBean>> beanlist) {
            if (page == 1) {
                list = beanlist;
            } else {
                list.removeAll(beanlist);
                list.addAll(beanlist);
            }
            adapter.setList(list);//传给adapter
            adapter.notifyDataSetChanged();//通知更新
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        //动态加载Fragment1的布局文件
        view = inflater.inflate(R.layout.fragment2, container, false);

        readSP();

        System.out.println("----onCreateView page=" + page);

        //实例化ArticleModel，调用方法获取网络数据
        CTwareModel cTwareModel = new CTwareModel();
        cTwareModel.getResultList("tware", page, sessionID, listListener);

        initRecyclerView();
        //返回动态生成的view
        return view;
    }

    private void readSP() {
        sessionID = getSessionId();
    }

    private void initRecyclerView() {
        //获取RecyclerView，设置属性，获取数据源，绑定
        recyclerView = view.findViewById(R.id.recyclerView);
        //创建默认的线性布局
        layoutManager = new LinearLayoutManager(context);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //固定每个item高度，提高性能
        recyclerView.setHasFixedSize(true);
        //创建Adaper
        adapter = new CTwareAdapter(context);
        adapter.setList(list);
        //绑定RecyclerView和adapter
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == list.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    CTwareModel cTwareModel = new CTwareModel();
                    cTwareModel.getResultList("tware", page, sessionID, listListener);
                    System.out.println("----onScrollStateChanged  page=" + page);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();//滚动结束后将赋值为可见条目中最后一条位置
                //lastVisibleItemPosition = list.size() - 1;
            }
        });

    }
}