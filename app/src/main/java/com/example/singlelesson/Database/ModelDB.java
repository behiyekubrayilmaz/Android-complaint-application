package com.example.singlelesson.Database;

import android.widget.EditText;

public class ModelDB {
    int id;
    String marka;
    String yorum;
    public ModelDB() {
    }

    public ModelDB(String marka, String yorum) {
        this.marka = marka;
        this.yorum = yorum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getYorum() {
        return yorum;
    }

    public void setYorum(String yorum) {
        this.yorum = yorum;
    }

}

