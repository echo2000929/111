package com.example.zyfypt_229.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.zyfypt_229.R;
import com.example.zyfypt_229.service.RetrofitService;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyPDFView extends AppCompatActivity {

    private PDFView pdfView;

    private MenuItem menuItem;

    //设置Retrofit访问网络的超时时间
    private static final OkHttpClient client =
            new OkHttpClient.Builder().
                    connectTimeout(600, TimeUnit.SECONDS).
                    readTimeout(600, TimeUnit.SECONDS).
                    writeTimeout(600, TimeUnit.SECONDS).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pdfView = (PDFView) findViewById(R.id.pdfView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_progress, menu);
        menuItem = menu.findItem(R.id.progress);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.progress:

                //显示进度
                setLoadingState(true);
                //加载PDF
                loadPdf();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void loadPdf(){

        //显示本地文件（assets目录下的文件）
//        pdfView.fromAsset("fragment.pdf").load();
//        pdfView.fromAsset("Thinking_in_Java_4th_edition.pdf").swipeHorizontal(true).load();

        //显示在线文件（校内网测试文件）
        String baseUrl = "http://amicool.neusoft.edu.cn/Uploads/";
        String url = "tware/pdfattach/20170524/20170524122736_810326265.pdf";


        //显示在线文件（互联网测试文件）
//        baseUrl = "https://kotlinlang.org/";
//        url = "docs/kotlin-docs.pdf";

        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .client(client)
                        .build();


        RetrofitService service = retrofit.create(RetrofitService.class);

        final Call<ResponseBody> call = service.getPdf(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()){

                    try {
                        byte[] data = response.body().bytes();

                        //加载PDF
                        pdfView.fromBytes(data).swipeHorizontal(true).load();
                        //隐藏进度
                        setLoadingState(false);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    public void setLoadingState(boolean refreshing) {

        if (refreshing){

            menuItem.setActionView(R.layout.progress);
        }else {

            menuItem.setActionView(null);
        }
    }
}

