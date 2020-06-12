package com.example.zyfypt_229.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.zyfypt_229.R;
import com.example.zyfypt_229.common.Common;
import com.example.zyfypt_229.iface.CollectListener;
import com.example.zyfypt_229.model.CollectModel;

public class ViewArticleActivity extends AppCompatActivity {
    private int resid;
    private WebView webView;

    Context context;

    private Boolean flagcollect=false;//收藏标志

    private CollectModel collectmodel;//收藏model
    private SharedPreferences sp;//简单存储
    private String sessionID="";  //sessionid
    private MenuItem item;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_article);
        System.out.println("----查看文章详情");
        context=ViewArticleActivity.this;

        resid  = getIntent().getIntExtra("resid",1);

        webView = findViewById(R.id.webview);
        webView.loadUrl(Common.ARTICLEURL+resid);

        sp=context.getSharedPreferences("login",MODE_PRIVATE);
        readSP();//读取sessionid


    }


    CollectListener listener=new CollectListener() {
        @Override
        public void onResponse(String msg) {
            //获取菜单视图
            if (item==null)
                item=findViewById(R.id.menucollect);

            //根据mode中response返回的字符串区分返回结果
            switch (msg)
            {
                case "2": System.out.println("----收藏成功");
                    flagcollect=true;
                    item.setTitle("取消收藏");
                    break;
                case "1":System.out.println("----收藏失败");
                    break;
                case "4":System.out.println("----取消收藏成功");
                    flagcollect=false;
                    item.setTitle("收藏");
                    break;
                case "3":System.out.println("----取消收藏失败");
                    break;
                case "5":System.out.println("----已收藏");
                    flagcollect=true;
                    item.setTitle("取消收藏");
                    break;
                case "6":System.out.println("----未收藏");
                    flagcollect=false;
                    item.setTitle("收藏");
                    break;
                default:
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onFail(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    };



    private void readSP() {
        sessionID=sp.getString("sessionID",null);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu. menucollect, menu);//加载菜单布局
        item=menu.findItem(R.id.menucollect);
        collectmodel=new CollectModel();//实例化对象
        collectmodel.exist("article",resid,sessionID,listener);//判断是否收藏
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menucollect:
                if(flagcollect)//如果已收藏，则调用取消收藏
                {
                    System.out.println("----准备取消收藏");
                    collectmodel.uncollect("article",resid,sessionID,listener);
                }
                else//如果未收藏，则调用收藏
                {
                    System.out.println("----准备收藏");
                    collectmodel.collect("article",resid,sessionID,listener);
                }
                break;
            case R.id.menufocus:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

}


