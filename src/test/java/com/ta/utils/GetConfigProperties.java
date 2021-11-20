package com.ta.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetConfigProperties {

    public static String getTestingService(){
        FileInputStream inputStream;
        Properties property = new Properties();
        try {
             inputStream = new FileInputStream("src/test/resources/config.properties");
             property.load(inputStream);
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
        return property.getProperty("testingService");
    }
}

