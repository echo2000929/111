package com.example.zyfypt_229.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;

import com.example.zyfypt_229.R;
import com.example.zyfypt_229.iface.CollectListener;
import com.example.zyfypt_229.model.CollectModel;

public class ViewVideoActivity1 extends AppCompatActivity {
    private com.example.zyfypt_229.activities.MyVideoView videoView;
    private String path="";
    private String BASEURL="http://123.207.125.141:90/";
    private ProgressDialog dialog;

    private int resid;//资源id
    Context context;

    private Boolean flagcollect=false;//收藏标志

    private CollectModel collectmodel;//收藏model
    private SharedPreferences sp;//简单存储
    private String sessionID="";  //sessionid
    private MenuItem item;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_video1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置屏幕方向为横向
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        getWindow().setFormat(PixelFormat.TRANSLUCENT);//透明
        videoView=findViewById(R.id.videoView);

        path=BASEURL+"Uploads/video/video/"+getIntent().getStringExtra("videopath");//获取视频全路径
        dialog=ProgressDialog.show(ViewVideoActivity1.this, "视频加载中...", "请您稍候");//进度条

        Uri uri = Uri.parse(path);
        videoView.setMediaController(new MediaController(this));//媒体播放控制工具  导包见上方
        videoView.setVideoURI(uri);//设置视频路径
        videoView.setOnPreparedListener(new MyPlayerOnPreparedListener());//播放开始回调
        videoView.setOnCompletionListener( new MyPlayerOnCompletionListener());//播放完成回调
        videoView.requestFocus();// 让VideoView获取焦点
        videoView.start();//开始播放

        context=ViewVideoActivity1.this;

        resid  = getIntent().getIntExtra("resid",1);//获取传递的资源id

        sp=context.getSharedPreferences("login",MODE_PRIVATE);
        readSP();//读取sessionid
    }

    //自定义子类，监听视频准备好，消除加载对话框
    class MyPlayerOnPreparedListener implements MediaPlayer.OnPreparedListener {
        @Override
        public void onPrepared(MediaPlayer mp) {
            videoView.setBackgroundColor(Color.argb(0, 0, 255, 0));
            dialog.dismiss();
        }
    }
    //自定义子类，监听播放完成，显示完成
    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText( ViewVideoActivity1.this, "播放完成", Toast.LENGTH_SHORT).show();
            //getSupportActionBar().show();
        }
    }
}
