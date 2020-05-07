package com.example.zyfypt_229.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zyfypt_229.R;
import com.example.zyfypt_229.bean.LoginBean;
import com.example.zyfypt_229.iface.LoginListener;
import com.example.zyfypt_229.model.LoginModel;

public class LoginActivity extends AppCompatActivity {
    private EditText etuser, etpass;
    private Button btnlogin;
    private String username = "", password = "", sessionID = "";

    private LoginListener loginListener = new LoginListener() {
        @Override
        public void onResponse(LoginBean loginBean) {
            sessionID = loginBean.getSessionid();
            Log.i("LoginActivity", "----sessionID=" + sessionID);
            if (sessionID != null) {
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
    }
    private void initViews() {
        etuser = findViewById(R.id.editText4);
        etpass = findViewById(R.id.editText6);
        btnlogin = findViewById(R.id.button2);

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
    }
}
