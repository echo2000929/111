package com.example.zyfypt_229.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.zyfypt_229.R;
import com.example.zyfypt_229.bean.LoginBean;
import com.example.zyfypt_229.iface.LoginListener;
import com.example.zyfypt_229.model.LoginModel;

public class LoginActivity extends AppCompatActivity {
    private EditText etuser, etpass;
    private Button btnlogin,button3;
    private String username = "", password = "", sessionID = "";
    private Switch sw;
    private SharedPreferences sp;
    private int ZX=0;


    private LoginListener loginListener = new LoginListener() {
        @Override
        public void onResponse(LoginBean loginBean) {
            sessionID = loginBean.getSessionid();
            Log.i("LoginActivity", "----sessionID=" + sessionID);
            if (sessionID != null) {
                saveSP();
                Toast.makeText(LoginActivity.this, "登录成功" + sessionID, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else
                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onFail(String msg) {
            Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        sp=getSharedPreferences("login",MODE_PRIVATE);
        readSP();
    }
    private void initViews() {
        etuser = findViewById(R.id.editText4);
        etpass = findViewById(R.id.editText6);
        btnlogin = findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        sw=findViewById(R.id.switch1);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //登录的代码
                username = etuser.getText().toString();
                password = etpass.getText().toString();
                LoginModel loginModel = new LoginModel();
                loginModel.getLoginResult(username, password, loginListener);

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }
    private void saveSP()
    {
        boolean remember=sw.isChecked();
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.putBoolean("remember",remember);
        editor.putString("sessionID",sessionID);
        boolean b=editor.commit();
        Log.i("LoginActivity","---写入SP返回值"+b);
    }

    private void readSP()
    {
        String name=sp.getString("username","");
        String pass=sp.getString("password","");
        boolean b=sp.getBoolean("remember",false);
        sw.setChecked(b);
        if (b&&ZX==0){
            etuser.setText(name);
            etpass.setText(pass);
        }
        else
        {etuser.setText("");
            etpass.setText("");}

    }

    private void deleteSP() {
        SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();

    }

}

