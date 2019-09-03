package com.milaev.medicine.board.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResProperties {

    public Properties getProperties(String fileName) throws IOException {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName)) {
            Properties prop = new Properties();
            if (inputStream == null) {
                throw new IOException();
            }
            prop.load(inputStream);
            return prop;
        }
    }

}
