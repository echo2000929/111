package com.example.zyfypt_229.iface;

public interface TwareIface {
    void getResultList(String mod,
                       int page,
                       String sessionID,
                       TwareListener twarelistener
    );
}
