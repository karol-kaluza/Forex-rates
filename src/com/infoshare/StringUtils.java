package com.infoshare;

public class StringUtils {


    public String[] getLines(String veryLongText) {
        String[] lines = veryLongText.split("\n");
        return lines;
    }
    public String [] getWords(String oneLineText ){
        String[] words = oneLineText.split(" ");
        return words;
    }

}
