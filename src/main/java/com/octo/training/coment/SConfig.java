package com.octo.training.cmoment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SConfig {
    private static SConfig conf;

    private Properties properties;

    private SConfig() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("file.properties"));
        } catch (IOException e) {
            throw new RuntimeException("File Not Found 'file.properties'");
        }
    }

    public static synchronized SConfig getInstance() {
        if (conf == null) {
            conf = new SConfig();
        }
        return conf;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }


    
}
