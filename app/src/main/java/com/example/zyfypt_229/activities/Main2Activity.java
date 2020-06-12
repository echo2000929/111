package com.example.zyfypt_229.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zyfypt_229.R;
import com.example.zyfypt_229.bean.ZCbean;
import com.example.zyfypt_229.service.ZuCeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.zyfypt_229.common.Common.BASEURL;

public class Main2Activity extends AppCompatActivity {
    private Button button7,button6;
    protected EditText editText3,editText4,editText5,editText7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initViews();
    }

    private void initViews() {
        button7=findViewById(R.id.button7);
        editText3=findViewById(R.id.editText3);
        editText4=findViewById(R.id.editText4);
        editText5=findViewById(R.id.editText5);
        editText7=findViewById(R.id.editText7);
        button6=findViewById(R.id.button6);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        button6.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=editText5.getText().toString();
                String b=editText4.getText().toString();
                if (a.equals( b ))
                    retrotif1();
                else
                    Toast.makeText( Main2Activity.this, "两次密码不一致", Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    public void retrotif1() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ZuCeService zuCeService = retrofit.create(ZuCeService.class);
        Call<ZCbean> call = zuCeService.zuceloign(editText3.getText().toString(), editText4.getText().toString());
        call.enqueue(new Callback<ZCbean>() {
            @Override
            public void onResponse(Call<ZCbean> call, Response<ZCbean> response) {
                Toast.makeText(Main2Activity.this, "注册失败：" + response.body().getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ZCbean> call, Throwable t) {
                Toast.makeText(Main2Activity.this, "注册成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
