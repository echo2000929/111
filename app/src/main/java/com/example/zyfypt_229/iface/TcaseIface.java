package com.example.zyfypt_229.iface;

public interface TcaseIface {
    void getResultList(String mod,
                       int page,
                       String sessionID,
                       TcaseListener tcaselistener
    );
}
