package com.example.zyfypt_229.iface;

public interface Collectiface {
    void collect(String mod,int id,String sessionid,CollectListener listener);
    void uncollect(String mod,int id,String sessionid,CollectListener listener);
    void exist(String mod,int id,String sessionid,CollectListener listener);
}
