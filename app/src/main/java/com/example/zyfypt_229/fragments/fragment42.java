package com.example.zyfypt_229.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zyfypt_229.adapter.TcaseAdpater;
import com.example.zyfypt_229.bean.TcaseBean;
import com.example.zyfypt_229.R;
import com.example.zyfypt_229.iface.TcaseListener;
import com.example.zyfypt_229.model.TcaseModel;

import java.util.List;

public class fragment42 extends BaseFragment {
    private List<TcaseBean> list;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager  layoutManager;
    private TcaseAdpater adapter;

    private TcaseListener listener = new TcaseListener() {

        @Override
        public void onResponse(List<TcaseBean> beanlist) {
            list=beanlist;
            adapter.setList(list);
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(context, "失败："+msg, Toast.LENGTH_SHORT).show();

        }
    };

    public fragment42() {    }
    @Nullable
    @Override //生命周期方法，创建View
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment1,container,false);
    }
    @Override//生命周期方法，View创建完成
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("--f42--"+getSessionId());
        initRecyclerView(view);
        TcaseModel model=new TcaseModel();
        model.getResultList("video",1,getSessionId(),listener);//页码默认第一页
    }
    private void initRecyclerView(View view) {
        recyclerView=view.findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        //每个item如果是确定高度，设置此项提高性能
        recyclerView.setHasFixedSize(true);
        //实例化适配器
        adapter=new TcaseAdpater(context);
        recyclerView.setAdapter(adapter);

    }
}