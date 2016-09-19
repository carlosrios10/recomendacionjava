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
                String config = System.getProperty("config");
                if (config == null){
                	config = "config.properties";
                }
                FileInputStream in =  new FileInputStream(config);
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