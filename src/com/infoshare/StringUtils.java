package com.infoshare;

public class StringUtils {


    public String[] getLines(String yourLongText) {
        String[] lines = yourLongText.split("\n");
        return lines;
    }

}
