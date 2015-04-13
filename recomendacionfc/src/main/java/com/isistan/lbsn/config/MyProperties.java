package com.isistan.lbsn.config;

import java.io.FileInputStream;
import java.util.Properties;

public class MyProperties extends Properties {
    private static MyProperties instance = null;

    private MyProperties() {
    }

    public static MyProperties getInstance() {
        if (instance == null) {
            try {
                instance = new MyProperties();
                FileInputStream in =  new FileInputStream("config.properties");
                instance.load(in);
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return instance;
    }
}