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
import com.example.zyfypt_229.adapter.TwareAdapter;
import com.example.zyfypt_229.bean.TwareBean;
import com.example.zyfypt_229.iface.TwareListener;
import com.example.zyfypt_229.model.TwareModel;

import java.util.List;


public class Fragment2 extends BaseFragment {
    private List<TwareBean> list;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager  layoutManager;
    private TwareAdapter adapter;

    private TwareListener listener = new TwareListener() {

        @Override
        public void onResponse(List<TwareBean> beanlist) {
            list=beanlist;
            adapter.setList(list);
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(context, "失败："+msg, Toast.LENGTH_SHORT).show();

        }
    };

    public Fragment2() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment2,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("--f2--"+getSessionId());
        initRecyclerView(view);
        TwareModel model=new TwareModel();
        model.getResultList("tware",1,getSessionId(),listener);//页码默认第一页
    }
    private void initRecyclerView(View view) {
        recyclerView=view.findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        //每个item如果是确定高度，设置此项提高性能
        recyclerView.setHasFixedSize(true);
        //实例化适配器
        adapter=new TwareAdapter(context);
        recyclerView.setAdapter(adapter);

    }

}
