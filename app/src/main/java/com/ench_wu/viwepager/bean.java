package com.ench_wu.viwepager;

/**
 * author:ench_wu
 * Created on 2015/9/22.
 */
public class bean {
    private int drawId;
    private String doc;

    public bean(int drawId, String doc) {
        this.drawId = drawId;
        this.doc = doc;
    }

    public int getDrawId() {
        return drawId;
    }

    public void setDrawId(int drawId) {
        this.drawId = drawId;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }
}
