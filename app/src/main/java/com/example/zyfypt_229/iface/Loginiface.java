package com.example.zyfypt_229.iface;

public interface Loginiface {
    void getLoginResult(String username,
                        String pass,
                        LoginListener loginListener
    );
}
