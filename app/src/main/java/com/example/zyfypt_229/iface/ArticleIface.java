package com.example.zyfypt_229.iface;

public interface ArticleIface {
    void getResultList(String mod,
                       int page,
                       String sessionID,
                       ArticleListener listener
    );

}
