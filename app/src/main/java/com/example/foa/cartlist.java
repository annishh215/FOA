package com.example.foa;

public class cartlist {
    String q;
    String imgurl;
    String f;
    String p;
    public cartlist() {

    }

    public cartlist(String q, String imgurl, String f, String p) {
        this.q = q;
        this.imgurl = imgurl;
        this.f = f;
        this.p = p;
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
