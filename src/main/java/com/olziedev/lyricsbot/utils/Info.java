package com.olziedev.lyricsbot.utils;

import com.olziedev.lyricsbot.LyricsBot;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class Info {

    public static String getInformation(String key) {
        try {
            FileReader reader = new FileReader(new File(LyricsBot.getDataFile(), "information.properties"));
            Properties props = new Properties();
            props.load(reader);
            String value = props.getProperty(key);
            reader.close();
            return value;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
