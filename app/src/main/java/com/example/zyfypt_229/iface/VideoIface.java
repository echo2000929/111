package com.example.zyfypt_229.iface;

public interface VideoIface {
    void getResultList(String mod,
                       int page,
                       String sessionID,
                       VideoListener videolistener
    );
}

