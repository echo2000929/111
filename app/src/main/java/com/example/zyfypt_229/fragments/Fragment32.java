package com.example.zyfypt_229.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zyfypt_229.R;
import com.example.zyfypt_229.adapter.VideoAdapter;
import com.example.zyfypt_229.bean.VideoBean;
import com.example.zyfypt_229.iface.VideoListener;
import com.example.zyfypt_229.model.VideoModelb;

import java.util.List;

public class Fragment32 extends BaseFragment {
    private List<VideoBean> list;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager  layoutManager;
    private VideoAdapter adapter;

    private VideoListener listener = new VideoListener() {

        @Override
        public void onResponse(List<VideoBean> beanlist) {
            list=beanlist;
            adapter.setList(list);
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(context, "失败："+msg, Toast.LENGTH_SHORT).show();

        }
    };

    public Fragment32() {    }
    @Nullable
    @Override //生命周期方法，创建View
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment1,container,false);
    }
    @Override//生命周期方法，View创建完成
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("--f32--"+getSessionId());
        initRecyclerView(view);
        VideoModelb model=new VideoModelb();
        model.getResultList("video",1,getSessionId(),listener);//页码默认第一页
    }
    private void initRecyclerView(View view) {
        recyclerView=view.findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        //每个item如果是确定高度，设置此项提高性能
        recyclerView.setHasFixedSize(true);
        //实例化适配器
        adapter=new VideoAdapter(context);
        recyclerView.setAdapter(adapter);

    }
}

