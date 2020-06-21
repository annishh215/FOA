package com.example.foa;

public class cartlist {
    String id;
    String q;
    String imgurl;
    String f;
    String p;
    public cartlist() {

    }

    public cartlist(String id, String q, String imgurl, String f, String p) {
        this.id = id;
        this.q = q;
        this.imgurl = imgurl;
        this.f = f;
        this.p = p;
    }

    public String getId() {
        return id;
    }

    public String getQ() {
        return q;
    }

    public String getImgurl() {
        return imgurl;
    }

    public String getF() {
        return f;
    }

    public String getP() {
        return p;
    }
}
