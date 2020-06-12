package com.example.zyfypt_229.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zyfypt_229.R;
import com.example.zyfypt_229.activities.TcaseActivity;

import com.example.zyfypt_229.bean.TcaseBean;
import com.example.zyfypt_229.common.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TcaseAdpater extends RecyclerView.Adapter{
    private List<TcaseBean> list;//向rv中填充的数据
    private Context context;//上下文
    private LayoutInflater layoutInflater;//动态布局

    //自定义 构造方法
    public TcaseAdpater(Context context)
    {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }
    //自定义 ViewHolder子类，容纳item视图
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvtitle,tvauthor,tvtime;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            tvtitle=itemView.findViewById(R.id.textView);
            tvauthor=itemView.findViewById(R.id.textView2);
            tvtime=itemView.findViewById(R.id.textView3);
        }
    }
    //自定义 设置数据list
    public void setList(List<TcaseBean> list)
    {
        this.list=list;
        notifyDataSetChanged();//通知RV刷新数据
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=layoutInflater.inflate(
                R.layout.item,parent,false);

        return new TcaseAdpater.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final TcaseBean  bean=list.get(position);
        if(bean==null)
            return;
        TcaseAdpater.ViewHolder viewHolder=(TcaseAdpater.ViewHolder)holder;
        Picasso.with(context)//新版的Picasso方法改为get()
                .load(Common.IMAGEURL+bean.getThumb())
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.imageView);
        viewHolder.tvtitle.setText(bean.getName());

        viewHolder.tvauthor.setText(bean.getAuthor());
        viewHolder.tvtime.setText(bean.getUpdate_time());

        //item条目点击事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取出当前item的id
                int id=bean.getId();//bean需要增加final
                Toast.makeText(context, ""+id, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, TcaseActivity.class);
                intent.putExtra("resid",bean.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        else
            return 0;
    }
}
