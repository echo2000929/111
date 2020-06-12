package com.example.zyfypt_229.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zyfypt_229.R;
import com.example.zyfypt_229.adapter.ArticleAdapter;
import com.example.zyfypt_229.bean.ArticleBean;
import com.example.zyfypt_229.iface.ArticleListener;
import com.example.zyfypt_229.model.ArticleModel;

import java.util.List;

import timber.log.Timber;


public class Fragment1 extends BaseFragment {
    private List<ArticleBean> list;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager  layoutManager;
    private ArticleAdapter adapter;

    private ArticleListener listener = new ArticleListener() {

        @Override
        public void onResponse(List<ArticleBean> beanlist) {
            list=beanlist;
            adapter.setList(list);
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(context, "失败："+msg, Toast.LENGTH_SHORT).show();

        }
    };

    public Fragment1() {    }
    @Nullable
    @Override //生命周期方法，创建View
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment1,container,false);
    }
    @Override//生命周期方法，View创建完成
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("--f1--"+getSessionId());
        Timber.i("on get session id %s", getSessionId());
        initRecyclerView(view);
        ArticleModel model=new ArticleModel();
        model.getResultList("article",1,getSessionId(),listener);//页码默认第一页
    }
    private void initRecyclerView(View view) {
        recyclerView=view.findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        //每个item如果是确定高度，设置此项提高性能
        recyclerView.setHasFixedSize(true);
        //实例化适配器
        adapter=new ArticleAdapter(context);
        recyclerView.setAdapter(adapter);

    }
}


